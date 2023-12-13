package com.cognicx.AppointmentRemainder.jwt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cognicx.AppointmentRemainder.Dto.TokenDetailsDto;


public class JwtAuthTokenFilter extends OncePerRequestFilter {

	@Autowired
	private JwtProvider tokenProvider;

	@Value("${app.userurl}")
	private String userurl;

	@Value("${app.whitelist.ip}")
	private String whiteListedIPs = "";
	
	@Value("${app.auth.enabled}")
	private boolean authEnabled;

	private static final Logger logger = LoggerFactory.getLogger(JwtAuthTokenFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			List<String> whiteListedIPList = new ArrayList<String>();
			if (!StringUtils.isEmpty(whiteListedIPs)){
				whiteListedIPList = Arrays.asList(whiteListedIPs.split(","));
			}
			if (!(whiteListedIPList.isEmpty()) && !(StringUtils.isEmpty(request.getRemoteAddr())) &&
					!(whiteListedIPList.contains(request.getRemoteAddr()))) {
				((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN,
						"Host not allowed to access the resource");
			}else {
				String jwt = tokenProvider.getJwt(request);
				if (authEnabled && null != jwt) {
					Object[] tokenObj = tokenProvider.validateJwtTokenObj(jwt, response);
					boolean tokenFlag = (boolean) tokenObj[0];
					if (tokenFlag) {
						String username = tokenProvider.getUserNameFromJwtToken(jwt);
						TokenDetailsDto tokenDetailsDto = new TokenDetailsDto();
						tokenDetailsDto.setEmployeeId(username);
						tokenDetailsDto.setToken(jwt);
						HttpHeaders headers = new HttpHeaders();
						headers.setContentType(MediaType.APPLICATION_JSON);
						headers.add("Authorization", "Bearer " + jwt);
						RestTemplate restTemplate = new RestTemplate();
						HttpEntity<String> entity = new HttpEntity(tokenDetailsDto, headers);
						ResponseEntity<String> data = restTemplate.exchange(userurl, HttpMethod.POST, entity,
								String.class);
						// boolean data = restTemplate.postForObject(userurl, tokenDetailsDto,
						// Boolean.class);
						boolean tokenExist = Boolean.valueOf(data.getBody());
						if (tokenExist && username != null && !username.isEmpty()) {
							UserDetails userDetails = tokenProvider.getUserDetailsFromJwtToken(jwt);
							UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
									userDetails, null, userDetails.getAuthorities());
							authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
							SecurityContextHolder.getContext().setAuthentication(authentication);
						}
					} else {
						response = (HttpServletResponse) tokenObj[1];
					}
					logger.debug("Token Found");

				}
				filterChain.doFilter(request, response);
			} /*
				 * else { ((HttpServletResponse)
				 * response).sendError(HttpServletResponse.SC_PARTIAL_CONTENT,
				 * "JWT token should not be empty."); }
				 */
		} catch (Exception e) {
			logger.error("Can NOT set user authentication -> Message: {}", e);
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
		}

	}

}
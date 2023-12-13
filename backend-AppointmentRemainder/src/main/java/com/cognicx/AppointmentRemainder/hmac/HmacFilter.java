package com.cognicx.AppointmentRemainder.hmac;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

public class HmacFilter extends OncePerRequestFilter {
	private static final Logger logger = LoggerFactory.getLogger(HmacSecurityProvider.class);

	@Autowired
	HmacSecurityProvider hmacSecurityProvider;

	public HmacFilter() {
	}


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		try {
			if (request.getRequestURI().contains("/ruleEngine/uploadListVariable")
					|| request.getRequestURI().contains("/ruleEngine")) {
				filterChain.doFilter(request, response);
			} else {
				CustomHttpRequestBody wrappedRequest = new CustomHttpRequestBody(request);
				if (hmacSecurityProvider.verifyHmac(wrappedRequest)) {
					filterChain.doFilter(wrappedRequest, response);
				}
			}
		} catch (Exception e) {
			logger.debug("Error while generating hmac token");
			logger.trace("Error while generating hmac token {}", e);
			response.setStatus(403);
			response.getWriter().write(e.getMessage());
		}
	}
}
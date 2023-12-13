package com.cognicx.AppointmentRemainder.hmac;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.util.ContentCachingRequestWrapper;

import com.cognicx.AppointmentRemainder.jwt.JwtProvider;

@Component
public class HmacSecurityProvider {

	@Autowired
	private JwtProvider tokenProvider;

	private static final Logger logger = LoggerFactory.getLogger(HmacSecurityProvider.class);

	public boolean verifyHmac(CustomHttpRequestBody request) throws Exception {
		String jwt = tokenProvider.getJwt(request);

		if (jwt == null || jwt.isEmpty()) {
			throw new Exception("The Jwt is missing from the '" + HmacUtil.AUTHENTICATION + "'header");
		}

		String signatureClient = request.getHeader(HmacUtil.X_PK_SIGNATURE);
		if (signatureClient == null || signatureClient.isEmpty()) {
			throw new Exception("The signature is missing from the '" + HmacUtil.X_PK_SIGNATURE + "' header");
		}

		// Get Timestamp header
		String timestampHeader = request.getHeader(HmacUtil.X_PK_TIMESTAMP);

		if (timestampHeader == null || timestampHeader.isEmpty()) {
			throw new Exception("The timestamp is missing from the '" + HmacUtil.X_PK_TIMESTAMP + "' header");
		}

		String hmacSecret = "secretKeyHmac2021";

		Assert.notNull(hmacSecret, "Secret key cannot be null");

		String message = request.getBody();

		// Digest are calculated using a public shared secret
		String signatureServer = HmacUtil.sign(request, hmacSecret, jwt, message, timestampHeader);

		logger.debug("HMAC JWT: {}", jwt);
		logger.debug("HMAC Signature server: {}", signatureServer);
		logger.debug("HMAC Signature client: {}", signatureClient);

		if (signatureClient.equals(signatureServer)) {
			return true;
		} else {
			throw new Exception("Signature are not matching!");
		}
	}

	private static String logRequestBody(HttpServletRequest request) {
		String requestBody = null;
		ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
		byte[] buf = wrappedRequest.getContentAsByteArray();
		if (buf.length > 0) {
			try {
				requestBody = new String(buf, 0, buf.length, request.getCharacterEncoding());
			} catch (Exception e) {
				logger.error("error in reading request body");
			}
		}
		return requestBody;
	}
}
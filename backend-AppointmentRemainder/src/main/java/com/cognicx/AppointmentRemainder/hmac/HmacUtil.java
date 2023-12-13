package com.cognicx.AppointmentRemainder.hmac;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class HmacUtil {
	private static final Logger logger = LoggerFactory.getLogger(HmacUtil.class);
	public static final String HMAC_SHA_256 = "HmacSHA256";
	public static final String AUTHENTICATION = "Authentication";

	public static final String X_PK_SIGNATURE = "X-PK-Signature";
	public static final String X_PK_TIMESTAMP = "X-PK-Timestamp";

	public static String sign(CustomHttpRequestBody request, String secret, String accessToken, String body,
			String timestamp) throws Exception {
		String message;
		if (!"GET".equals(request.getMethod())) {
			message = canocalized(body);

		} else {
			message = request.getMethod().concat(":").concat(resolveRequestPath(request)).concat(":")
					.concat(accessToken).concat(":").concat(timestamp);
		}

		logger.info("HMAC Message Server {}", message);
		return hmacSha256(secret, message);
	}

	private static String resolveRequestPath(HttpServletRequest request) throws Exception {
		String path = request.getServletPath();
		if (request.getQueryString() != null) {
			path += "?" + URLDecoder.decode(request.getQueryString(), StandardCharsets.UTF_8.displayName());
		}
		return path;
	}

	public static byte[] sha256(String value) {
		return DigestUtils.sha256(value);
	}

	public static String hmacSha256(String secret, String message) throws Exception {
		String digest;
		try {
			SecretKeySpec key = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), HMAC_SHA_256);
			Mac mac = Mac.getInstance(HMAC_SHA_256);
			mac.init(key);
			byte[] bytes = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));
			StringBuilder hash = new StringBuilder();
			for (byte b : bytes) {
				String hex = Integer.toHexString(0xFF & b);
				if (hex.length() == 1) {
					hash.append('0');
				}
				hash.append(hex);
			}
			digest = hash.toString();
		} catch (Exception e) {
			logger.debug("Error while encoding request with hmac");
			logger.trace("Error while encoding request with hmac {}", e);
			throw new Exception("Error while encoding request with hmac");
		}
		return digest;
	}

	private static String canocalized(String json) {
		if (json == null)
			return "";
		else
			return json.replaceAll("\r", "").replaceAll("\n", "").replaceAll("\t", "");
	}
}
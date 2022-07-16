package com.poc.urlshortner.util;

import java.net.URLDecoder;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * To handle encode/decode funtinality
 * 
 * @author Bala
 *
 */
public class ConversionUtil {
	private static final Logger LOG = LoggerFactory.getLogger(ConversionUtil.class);

	private static final String ALLOWED_DATA = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private char[] allowedValues = ALLOWED_DATA.toCharArray();
	private int base = allowedValues.length;

	/**
	 * 
	 * @param input
	 * @return
	 */
	public String encode(long input) {
		StringBuilder encodedString = new StringBuilder();
		if (input == 0) {
			return String.valueOf(allowedValues[0]);
		}
		while (input > 0) {
			encodedString.append(allowedValues[(int) (input % base)]);
			input = input / base;
		}
		return encodedString.reverse().toString();
	}

	/**
	 * 
	 * @param input
	 * @return
	 */
	public long decode(String input) {
		char[] characters = input.toCharArray();
		int length = characters.length;
		long decoded = 0;
		double counter = 1;
		for (int i = 0; i < length; i++) {
			decoded += ALLOWED_DATA.indexOf(characters[i]) * Math.pow(base, length - counter);
			counter++;
		}
		return decoded;
	}

	/**
	 * 
	 * @param url
	 * @return
	 */
	public String computeCheckSum(String url) {
		String decodedUrl = String.valueOf((url));
		String md5Hex = DigestUtils.md5Hex(decodedUrl).toUpperCase();
		return md5Hex;
	}

	/**
	 * 
	 * @param url
	 * @return
	 */
	public String decodeUrl(String url) {
		try {
			String decodedUrlString = URLDecoder.decode(url, "UTF-8");
			return decodedUrlString;
		} catch (Exception exception) {
			LOG.error("Exception while decoding Url::{}", exception.getMessage());
			return "Error at decode Url" + exception.getMessage();
		}
	}

}

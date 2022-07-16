package com.poc.urlshortner.util;

import java.net.URLDecoder;

import org.apache.commons.codec.digest.DigestUtils;

public class ConversionUtil {

	private static final String ALLOWED_DATA = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private char[] allowedValues = ALLOWED_DATA.toCharArray();
	private int base = allowedValues.length;

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
	
	public String computeCheckSum(String url) {
        String decodedUrl = String.valueOf((url));
        String md5Hex = DigestUtils.md5Hex(decodedUrl).toUpperCase();
        return md5Hex;
    }

    public String decodeUrl(String url) {
        try {
            String decodedUrlString = URLDecoder.decode(url, "UTF-8");
            return decodedUrlString;
        } catch (Exception exception) {
        	System.out.println("Exception while decoding Url"+exception.getMessage());
            return "Error at decode Url" + exception.getMessage();
        }
    }

}

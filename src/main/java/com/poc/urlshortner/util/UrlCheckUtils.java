package com.poc.urlshortner.util;

import java.net.URL;

import org.springframework.util.StringUtils;
/**
 * To validate given Url is valid or not
 * @author Bala
 *
 */
public class UrlCheckUtils {

	public static boolean isValid(String url) {
		try {
			if(!StringUtils.hasLength(url) || url == null) {
				return false;
			}
			new URL(url).toURI();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}

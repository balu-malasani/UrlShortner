package com.poc.urlshortner.service;

/**
 * 
 * @author Bala
 *
 */
public interface IUrlShortnerService {

	String saveShortenUrl(String actualUrl);

	String getActualUrl(String shortenUrl);
}

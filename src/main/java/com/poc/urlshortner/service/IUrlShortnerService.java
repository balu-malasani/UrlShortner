package com.poc.urlshortner.service;

public interface IUrlShortnerService {

	String saveShortenUrl(String actualUrl);
	String getActaulUrl(String shortenUrl);
}

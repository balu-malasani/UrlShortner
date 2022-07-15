package com.poc.urlshortner.service.impl;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.poc.urlshortner.entity.UrlShortnerEntity;
import com.poc.urlshortner.repository.UrlShortnerRepository;
import com.poc.urlshortner.service.IUrlShortnerService;

@Service
public class UrlShortnerServiceImpl implements IUrlShortnerService {

	private UrlShortnerRepository repository;
	
	public UrlShortnerServiceImpl(UrlShortnerRepository urlShortnerRepository) {
		this.repository = urlShortnerRepository;
	}
	
	@Override
	public String saveShortenUrl(String actualUrl) {
		try {
		if(repository != null && !StringUtils.isEmpty(actualUrl)) {
			String encodedValue = Base64.encodeBase64String(actualUrl.getBytes());
			UrlShortnerEntity urlEntity = new UrlShortnerEntity();
			urlEntity.setId(encodedValue);
			urlEntity.setActualUrl(actualUrl);
			return repository.save(urlEntity).getId();
		}
		}catch(Exception exception) {
			System.out.println("Exception::"+exception.getMessage());
		}
		return null;
	}

	@Override
	public String getActaulUrl(String shortenUrl) {
		return null;
	}

}

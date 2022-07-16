package com.poc.urlshortner.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.poc.urlshortner.entity.UrlChecksumEntity;
import com.poc.urlshortner.entity.UrlShortnerEntity;
import com.poc.urlshortner.repository.UrlChecksumRepository;
import com.poc.urlshortner.repository.UrlShortnerRepository;
import com.poc.urlshortner.service.IUrlShortnerService;
import com.poc.urlshortner.util.ConversionUtil;

@Service
public class UrlShortnerServiceImpl implements IUrlShortnerService {

	private UrlShortnerRepository repository;

	private UrlChecksumRepository checksumRepository;

	public UrlShortnerServiceImpl(UrlShortnerRepository urlShortnerRepository,
			UrlChecksumRepository urlChecksumRepository) {
		this.repository = urlShortnerRepository;
		this.checksumRepository = urlChecksumRepository;
	}

	@Override
	public String saveShortenUrl(String actualUrl) {
		try {
			if (repository != null && checksumRepository != null && !StringUtils.isEmpty(actualUrl)) {
				ConversionUtil util = new ConversionUtil();
				String checksum = util.computeCheckSum(actualUrl);
				if (checksumRepository.existsById(checksum)) {
					return checksumRepository.findById(checksum).get().getShortUrl();
				} else {
					UrlShortnerEntity urlEntity = new UrlShortnerEntity();
					urlEntity.setActualUrl(actualUrl);
					long id = repository.save(urlEntity).getId();
					UrlChecksumEntity checksumEntity = new UrlChecksumEntity();
					String shortUrl = util.encode(id);
					checksumEntity.setId(checksum);
					checksumEntity.setShortUrl(shortUrl);
					return checksumRepository.save(checksumEntity).getShortUrl();
				}
			}
		} catch (Exception exception) {
			System.out.println("Exception::" + exception.getMessage());
		}
		return null;
	}

	@Override
	public String getActaulUrl(String shortenUrl) {
		
		return null;
	}

}

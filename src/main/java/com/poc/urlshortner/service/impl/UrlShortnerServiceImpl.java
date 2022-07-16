package com.poc.urlshortner.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.poc.urlshortner.controller.UrlShortnerController;
import com.poc.urlshortner.entity.UrlChecksumEntity;
import com.poc.urlshortner.entity.UrlShortnerEntity;
import com.poc.urlshortner.repository.UrlChecksumRepository;
import com.poc.urlshortner.repository.UrlShortnerRepository;
import com.poc.urlshortner.service.IUrlShortnerService;
import com.poc.urlshortner.util.ConversionUtil;

@Service
public class UrlShortnerServiceImpl implements IUrlShortnerService {
	private static final Logger LOG = LoggerFactory.getLogger(UrlShortnerController.class);

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
			
			if (repository != null && checksumRepository != null && StringUtils.hasLength(actualUrl)) {
				ConversionUtil util = new ConversionUtil();
				String checksum = util.computeCheckSum(actualUrl);
				LOG.info("Checksum"+checksum);
				if (checksumRepository.existsById(checksum)) {
					return checksumRepository.findById(checksum).get().getShortUrl();
				} else {
					UrlShortnerEntity urlEntity = new UrlShortnerEntity();
					urlEntity.setActualUrl(actualUrl);
					long id = repository.save(urlEntity).getId();
					LOG.info("ID::"+id);
					UrlChecksumEntity checksumEntity = new UrlChecksumEntity();
					String shortUrl = util.encode(id);
					checksumEntity.setId(checksum);
					checksumEntity.setShortUrl(shortUrl);
					return checksumRepository.save(checksumEntity).getShortUrl();
				}
			}
		} catch (Exception exception) {
			LOG.info("Exception::" + exception.getMessage());
		}
		return "";
	}

	
	@Override
	public String getActaulUrl(String shortenUrl) {
       try {
		if (StringUtils.hasLength(shortenUrl)) {
			ConversionUtil util = new ConversionUtil();
			long id = util.decode(shortenUrl);
			if (repository.existsById(id)) {
				return repository.findById(id).get().getActualUrl();
			}
		}
       }catch(Exception exception) {
    	   LOG.error("Exception while saving data::{}",exception.getMessage());
       }
		return "";
	}

}

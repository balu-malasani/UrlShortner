package com.poc.urlshortner.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.core.RepositoryCreationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.poc.urlshortner.controller.UrlShortnerController;
import com.poc.urlshortner.entity.UrlChecksumEntity;
import com.poc.urlshortner.entity.UrlShortnerEntity;
import com.poc.urlshortner.repository.UrlChecksumRepository;
import com.poc.urlshortner.repository.UrlShortnerRepository;
import com.poc.urlshortner.service.IUrlShortnerService;
import com.poc.urlshortner.util.ConversionUtil;

/**
 * Service calss to handle business logic
 * 
 * @author Bala
 *
 */
@Service
public class UrlShortnerServiceImpl implements IUrlShortnerService {
	private static final String ERROR = "error";

	private static final Logger LOG = LoggerFactory.getLogger(UrlShortnerController.class);

	private UrlShortnerRepository urlShortnerRepository;

	private UrlChecksumRepository checksumRepository;

	public UrlShortnerServiceImpl(UrlShortnerRepository urlShortnerRepository,
			UrlChecksumRepository urlChecksumRepository) {
		this.urlShortnerRepository = urlShortnerRepository;
		this.checksumRepository = urlChecksumRepository;
	}
    /**
     * To convert given url to shorten url and save o Database.
     * @param actaulUrl
     */
	@Override
	public String saveShortenUrl(String actualUrl) {
		try {
			if (urlShortnerRepository != null && checksumRepository != null && StringUtils.hasLength(actualUrl)) {
				ConversionUtil util = new ConversionUtil();
				String checksum = util.computeCheckSum(actualUrl);
				LOG.info("UrlShortnerServiceImpl::UrlShortnerServiceImpl() - Checksum value::{}", checksum);
				if (checksumRepository.existsById(checksum)) {
					return checksumRepository.findById(checksum).get().getShortUrl();
				} else {
					UrlShortnerEntity urlEntity = new UrlShortnerEntity();
					urlEntity.setActualUrl(actualUrl);
					UrlShortnerEntity entiry = urlShortnerRepository.save(urlEntity);
					long id = entiry.getId();

					UrlChecksumEntity checksumEntity = new UrlChecksumEntity();
					String shortUrl = util.encode(id);
					checksumEntity.setId(checksum);
					checksumEntity.setShortUrl(shortUrl);
					return checksumRepository.save(checksumEntity).getShortUrl();
				}
			}
		} catch (RepositoryCreationException repositoryCreationException) {
			LOG.error("UrlShortnerServiceImpl : saveShortenUrl RepositoryCreationException::{}"
					+ repositoryCreationException.getMessage());
		} catch (Exception exception) {
			LOG.error("UrlShortnerServiceImpl : saveShortenUrl Exception::{}" + exception.getMessage());
		}

		return ERROR;
	}
    /**
     * To get original url by using shorten url.
     * @param shortenUrl
     */
	@Override
	public String getActualUrl(String shortenUrl) {

		try {

			if (StringUtils.hasLength(shortenUrl)) {
				ConversionUtil util = new ConversionUtil();
				long id = util.decode(shortenUrl);
				if (urlShortnerRepository.existsById(id)) {
					return urlShortnerRepository.findById(id).get().getActualUrl();
				}
			}
		} catch (Exception exception) {
			LOG.error("UrlShortnerServiceImpl :: getActualUrl() - Exception while saving data::{}", exception.getMessage());
		}
		return ERROR;
	}

}

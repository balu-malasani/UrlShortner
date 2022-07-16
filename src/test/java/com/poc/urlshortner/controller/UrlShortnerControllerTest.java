package com.poc.urlshortner.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import com.poc.urlshortner.entity.UrlChecksumEntity;
import com.poc.urlshortner.entity.UrlShortnerEntity;
import com.poc.urlshortner.model.UrlShortenModel;
import com.poc.urlshortner.repository.UrlChecksumRepository;
import com.poc.urlshortner.repository.UrlShortnerRepository;
import com.poc.urlshortner.service.IUrlShortnerService;
import com.poc.urlshortner.service.impl.UrlShortnerServiceImpl;

@RunWith(MockitoJUnitRunner.class)
class UrlShortnerControllerTest {
	public static final String actualUrl = "http://localhost:8080/welcom";
	public static final String shortUrl = "http://localhost:8080/a";

	private UrlShortnerController urlShortnerController;

	private UrlShortenModel shortenModel;

	private UrlShortenModel emptyObj;

	Model model;

	IUrlShortnerService service;

	UrlShortnerRepository urlShortnerRepository;

	UrlChecksumRepository checksumRepository;

	@Mock
	ResponseEntity<Void> responseEntity;
	String checksum = "abcdef";

	@BeforeEach
	void setUp() throws Exception {
		urlShortnerController = mock(UrlShortnerController.class);
		service = mock(UrlShortnerServiceImpl.class);
		urlShortnerRepository = mock(UrlShortnerRepository.class);
		checksumRepository = mock(UrlChecksumRepository.class);
		shortenModel = mock(UrlShortenModel.class);
		model = mock(Model.class);
		shortenModel.setShortenUrl(shortUrl);
		shortenModel.setUrl(actualUrl);

		emptyObj = new UrlShortenModel();
		urlShortnerController = new UrlShortnerController(service);
		service = new UrlShortnerServiceImpl(urlShortnerRepository, checksumRepository);
	}

	@Test
	void testHomePage() {
		String data = urlShortnerController.homePage(model);
		assertThat(data).isEqualTo("index");
	}

	@Test
	void testRedirectToActualUrl() {
		ResponseEntity<String> entity = urlShortnerController.redirectToActualUrl(shortUrl);
		assertThat(entity.getStatusCodeValue()).isEqualTo(404);
	}

	@Test
	void testSaveShortenUrl() {

		UrlShortenModel shortenModel = new UrlShortenModel();
		shortenModel.setUrl("http://localhost:8080/welcome");
		shortenModel.setShortenUrl("b");
		UrlShortnerEntity entity = new UrlShortnerEntity();
		entity.setId(1);
		entity.setActualUrl(actualUrl);
		UrlChecksumEntity checkSum = new UrlChecksumEntity();
		checkSum.setId("hjhjhj");
		checkSum.setShortUrl("b");
		when(service.saveShortenUrl(shortenModel.getUrl())).thenReturn(actualUrl);
		when(urlShortnerRepository.save(any(UrlShortnerEntity.class))).thenReturn(entity);
		when(checksumRepository.save(any(UrlChecksumEntity.class))).thenReturn(checkSum);
		String data = urlShortnerController.saveShortenUrl(shortenModel, model);
		assertThat(data).isEqualTo(data);

	}

}

package com.poc.urlshortner.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.util.StringUtils;

import com.poc.urlshortner.entity.UrlShortnerEntity;
import com.poc.urlshortner.repository.UrlShortnerRepository;
import com.poc.urlshortner.service.IUrlShortnerService;
import com.poc.urlshortner.util.ConversionUtil;

@RunWith(MockitoJUnitRunner.class)
class UrlShortnerServiceImplTest {

	private IUrlShortnerService service;

	private ConversionUtil util;

	@BeforeEach
	void setUp() throws Exception {
		service = mock(UrlShortnerServiceImpl.class);
		util = mock(ConversionUtil.class);

	}

	@Test
	public void testSaveShortenUrl() {
		UrlShortnerEntity entity = new UrlShortnerEntity();
		entity.setId(1);
		entity.setActualUrl("http://localhost:8080/welcome");

		when(service.saveShortenUrl("http://localhost:8080/welcome")).thenReturn("b");
		String shortenUrl = service.saveShortenUrl(entity.getActualUrl());
		assertEquals("b", shortenUrl);

	}

	@Test
	void testGetActualUrl() {
		String actualUrl = "http://localhost:8080/welcome";
		String result = service.getActualUrl(actualUrl);
	}

}

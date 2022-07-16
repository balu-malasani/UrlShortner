package com.poc.urlshortner.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.poc.urlshortner.entity.UrlChecksumEntity;
import com.poc.urlshortner.entity.UrlShortnerEntity;

@RunWith(MockitoJUnitRunner.class)
class UrlChecksumRepositoryTest {

	private UrlChecksumRepository urlChecksumRepository;

	@BeforeEach
	void setUp() throws Exception {
		urlChecksumRepository = mock(UrlChecksumRepository.class);
	}

	@Test
	public void saveShortnerObject() {
		UrlChecksumEntity entity = new UrlChecksumEntity();
		entity.setId("hyurhthb");
		entity.setShortUrl("b");
		when(urlChecksumRepository.save(entity)).thenReturn(entity);
		UrlChecksumEntity entity1 = urlChecksumRepository.save(entity);
		assertEquals("b", entity1.getShortUrl());
	}
}

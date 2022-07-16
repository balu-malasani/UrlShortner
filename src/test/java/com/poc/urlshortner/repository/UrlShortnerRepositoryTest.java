package com.poc.urlshortner.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.poc.urlshortner.entity.UrlShortnerEntity;

@RunWith(MockitoJUnitRunner.class)
class UrlShortnerRepositoryTest {

	private UrlShortnerRepository urlShortnerRepository;

	@BeforeEach
	void setUp() throws Exception {
		urlShortnerRepository = mock(UrlShortnerRepository.class);
	}

	@Test
	public void saveShortnerObject() {
		UrlShortnerEntity entity = new UrlShortnerEntity();
		entity.setId(1);
		entity.setActualUrl("http://localhost:8080/welcome");
		when(urlShortnerRepository.save(entity)).thenReturn(entity);
		UrlShortnerEntity entity1 = urlShortnerRepository.save(entity);
		assertEquals("http://localhost:8080/welcome", entity1.getActualUrl());
	}
}

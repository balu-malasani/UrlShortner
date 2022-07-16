package com.poc.urlshortner.controller;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.poc.urlshortner.model.UrlShortenModel;
import com.poc.urlshortner.service.IUrlShortnerService;

@Controller
public class UrlShortnerController {
	private static final Logger LOG = LoggerFactory.getLogger(UrlShortnerController.class);

	private IUrlShortnerService service;

	public UrlShortnerController(IUrlShortnerService urlShortnerService) {
		this.service = urlShortnerService;
	}

	/*
	 * To handle default page rendering
	 */
	@GetMapping("/")
	public String homePage(Model model) {
		model.addAttribute("shortenmodel", new UrlShortenModel());
		return "index";
	}

	/*
	 * To handle shorten urls
	 */
	@GetMapping("{shortUrl}")
	public ResponseEntity<String> redirectToActualUrl(@PathVariable String shortUrl) {
		LOG.info("Short url::" + shortUrl);
		if (StringUtils.hasLength(shortUrl)) {
			String inputUrl = service.getActaulUrl(shortUrl);
			LOG.info("Inpur url:" + inputUrl);
			if (StringUtils.hasLength(inputUrl)) {
				return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(inputUrl)).build();
			}
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	/*
	 * To generate shorten url and return to results page.
	 */
	@PostMapping("/generateUrl")
	public String saveShortenUrl(@ModelAttribute UrlShortenModel shortenmodel, Model model) {
		if (service != null && shortenmodel != null && shortenmodel.getUrl() != null) {
			String shortUrl = service.saveShortenUrl(shortenmodel.getUrl());
			LOG.info("ShortUrl:::" + shortUrl);
			try {
				if (StringUtils.hasLength(shortUrl)) {
					String baseUrl = new URL(new URL(shortenmodel.getUrl()), "/").toString();
					shortenmodel.setShortenUrl(baseUrl.concat(shortUrl));
					model.addAttribute("shortenmodel", shortenmodel);
					return "results";
				}
			} catch (MalformedURLException exception) {
				LOG.error("Error ::{}", exception.getMessage());
			}

		}

		return "error";
	}
}

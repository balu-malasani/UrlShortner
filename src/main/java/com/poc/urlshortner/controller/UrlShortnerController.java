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
import com.poc.urlshortner.util.UrlCheckUtils;

/**
 * Controller to handle incoming request.
 * 
 * @author Bala
 *
 */
@Controller
public class UrlShortnerController {
	private static final String ERROR_URL = "error-url";
	private static final String ERROR = "error";

	private static final Logger LOG = LoggerFactory.getLogger(UrlShortnerController.class);

	private IUrlShortnerService service;

	public UrlShortnerController(IUrlShortnerService urlShortnerService) {
		this.service = urlShortnerService;
	}

	/**
	 * To handle default page rendering
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/")
	public String homePage(Model model) {
		model.addAttribute("shortenmodel", new UrlShortenModel());
		return "index";
	}

	/**
	 * To handle shorten urls
	 * 
	 * @param shortUrl
	 * @return
	 */
	@GetMapping("{shortUrl}")
	public ResponseEntity<String> redirectToActualUrl(@PathVariable String shortUrl) {
		LOG.info("Short url::value {}", shortUrl);
		try {
			if (StringUtils.hasLength(shortUrl)) {
				String inputUrl = service.getActualUrl(shortUrl);
				if (StringUtils.hasLength(inputUrl) && !ERROR.equalsIgnoreCase(inputUrl)) {
					return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(inputUrl)).build();
				}
			}
		} catch (Exception exception) {
			LOG.error("UrlShortnerController::Errro at redirectToActualUrl() :{}", exception.getMessage());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	/**
	 * To generate shorten url and return to results page.
	 * 
	 * @param shortenmodel
	 * @param model
	 * @return
	 */
	@PostMapping("/generateUrl")
	public String saveShortenUrl(@ModelAttribute UrlShortenModel shortenmodel, Model model) {
		String givenUrl = shortenmodel.getUrl();
		if (service != null && shortenmodel != null && UrlCheckUtils.isValid(givenUrl)) {
			try {
				String shortUrl = service.saveShortenUrl(givenUrl);
				LOG.info("UrlShortnerController : saveShortenUrl(): ShortUrl value:" + shortUrl);
				if (!ERROR.equalsIgnoreCase(shortUrl) && StringUtils.hasLength(shortUrl)) {
					String baseUrl = new URL(new URL(shortenmodel.getUrl()), "/").toString();
					shortenmodel.setShortenUrl(baseUrl.concat(shortUrl));
					model.addAttribute("shortenmodel", shortenmodel);
					return "results";
				}
			} catch (MalformedURLException exception) {
				LOG.error("UrlShortnerController : Error at saveShortenUrl() ::{}", exception.getMessage());
				return ERROR_URL;
			}

		}

		return ERROR_URL;
	}
}

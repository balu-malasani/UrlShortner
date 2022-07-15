package com.poc.urlshortner.controller;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.poc.urlshortner.model.UrlShortenModel;
import com.poc.urlshortner.service.IUrlShortnerService;

@Controller
public class UrlShortnerController {

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
	@GetMapping("/{url}")
	public String redirectToActualUrl() {
		return null;
	}

	/*
	 * To generate shorten url and return to results page.
	 */
	@PostMapping("/generateUrl")
	public String saveShortenUrl(@ModelAttribute UrlShortenModel shortenmodel, Model model) {
		if (service != null && shortenmodel != null && shortenmodel.getUrl() != null) {
			String shortUrl = service.saveShortenUrl(shortenmodel.getUrl());
			try {
				String baseUrl = new URL(new URL(shortenmodel.getUrl()), "/").toString();
				shortenmodel.setShortenUrl(baseUrl.concat(shortUrl));
				model.addAttribute("shortenmodel", shortenmodel);
				return "results";
			} catch (MalformedURLException e) {
				System.out.println("Exception " + e.getMessage());
			}

		}
		return "error";
	}
}

package com.poc.urlshortner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.poc.urlshortner.model.UrlShortenModel;

@Controller
public class UrlShortnerController {
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
	public String saveShortenUrl(@ModelAttribute UrlShortenModel shortenmodel,Model model ) {
		shortenmodel.setShortenUrl("http://localhost:8080/123"); // Need to get from DB 
		model.addAttribute("shortenmodel", shortenmodel);
		return "results";
	}
}

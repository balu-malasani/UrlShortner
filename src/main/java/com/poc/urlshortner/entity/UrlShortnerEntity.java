package com.poc.urlshortner.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UrlShortnerEntity {

	@Id
	private String id;
	@Column(name = "ACTUAL_URL",nullable = false)
	private String actualUrl;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getActualUrl() {
		return actualUrl;
	}
	public void setActualUrl(String shortenUrl) {
		this.actualUrl = shortenUrl;
	}
	
	
	
}

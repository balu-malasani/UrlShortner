package com.poc.urlshortner.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UrlChecksumEntity {

	@Id
	private String id;
	@Column(nullable = false)
	private String shortUrl;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getShortUrl() {
		return shortUrl;
	}
	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}
	@Override
	public String toString() {
		return "UrlChecksumEntity [id=" + id + ", shortUrl=" + shortUrl + "]";
	}
	
	
}

package com.poc.urlshortner.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 
 * @author Bala
 *
 */
@Entity
public class UrlShortnerEntity {

	public UrlShortnerEntity() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "ACTUAL_URL", nullable = false)
	private String actualUrl;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getActualUrl() {
		return actualUrl;
	}

	public void setActualUrl(String shortenUrl) {
		this.actualUrl = shortenUrl;
	}

	@Override
	public String toString() {
		return "UrlShortnerEntity [id=" + id + ", actualUrl=" + actualUrl + "]";
	}

}

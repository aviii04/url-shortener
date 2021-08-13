package com.github.aviii04.urlshortner.service;

/**
 * @author Avinash Thakur
 * 
 * Service layer to deal with business logic & related operations.
 */
public interface UrlShortenerService {

	/**
	 * Generates short URL for given long URL if already not exist,
	 * or else return already existing short URL.
	 * 
	 * @param longUrl - To be minify
	 * @return - short URL
	 */
	String convertToShortUrl(String longUrl);

	/**
	 * Return long URL for corresponding shortURL if exist or else NULL.
	 * 
	 * @param shortUrl
	 * @return - corresponding long URL
	 */
	String getLongUrl(String shortUrl);

}
	
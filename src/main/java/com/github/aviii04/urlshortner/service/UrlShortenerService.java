package com.github.aviii04.urlshortner.service;

import com.github.aviii04.urlshortner.domain.LongToShortURL;

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
	 * @return - LongToShortURL object.
	 */
	LongToShortURL convertToShortUrl(String longUrl);

	/**
	 * Return long URL for corresponding shortURL if exist or else NULL.
	 * 
	 * @param shortUrl
	 * @return - LongToShortURL object.
	 */
	LongToShortURL getLongUrl(String shortUrl);

}
	
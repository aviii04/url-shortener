package com.github.aviii04.urlshortner.dao;

import java.util.Optional;

import com.github.aviii04.urlshortner.domain.LongToShortURL;

/**
 * @author Avinash Thakur
 * 
 * DAO layer to handle actions related to DB operations. 
 */
public interface UrlShortenerDao {

	/**
	 * Save entry for long URL to short URL mapping.
	 * 
	 * @param longToShortURL - to be persisted.
	 */
	void saveUrl(LongToShortURL longToShortURL);

	/**
	 * Return short URL for given long URL.
	 * 
	 * @param longUrl
	 * @return - LongToShortURL object.
	 */
	Optional<LongToShortURL> findShortUrlIfExist(String longUrl);

	/**
	 * Returns longUr for corresponding shortUrl.
	 * 
	 * @param shortUrl
	 * @return LongToShortURL object.
	 */
	Optional<LongToShortURL> getLongUrl(String shortUrl);

}

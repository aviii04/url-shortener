package com.github.aviii04.urlshortner.dao;

/**
 * @author Avinash Thakur
 * 
 * DAO layer to handle actions related to DB operations. 
 */
public interface UrlShortenerDao {

	/**
	 * Save entry for long URL to short URL mapping.
	 * 
	 * @param longUrl
	 * @param shortUrl
	 */
	void saveUrl(String longUrl, String shortUrl);

	/**
	 * Return short URL for given long URL if exist or else NULL.
	 * 
	 * @param longUrl
	 * @return - shortUrl if exist else NULL
	 */
	String findShortUrlIfExist(String longUrl);

	/**
	 * Returns longUr for corresponding shortUrl if exist else NULL.
	 * 
	 * @param shortUrl
	 * @return longUrl if exist else NULL.
	 */
	String getLongUrl(String shortUrl);	

}

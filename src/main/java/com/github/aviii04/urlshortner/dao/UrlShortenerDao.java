package com.github.aviii04.urlshortner.dao;

/**
 * @author Avinash Thakur
 * 
 * DAO layer to handle actions related to DB operations. 
 */
public interface UrlShortenerDao {

	void saveUrl(String longUrl, String shortUrl);

	String findShortUrlIfExist(String longUrl);	

}

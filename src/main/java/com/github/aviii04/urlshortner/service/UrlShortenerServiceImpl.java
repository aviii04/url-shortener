
package com.github.aviii04.urlshortner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.github.aviii04.urlshortner.dao.UrlShortenerDao;
import com.github.aviii04.urlshortner.utils.UrlUtils;

/**
 * @author Avinash Thakur
 *
 */

@Service
public class UrlShortenerServiceImpl implements UrlShortenerService {
	
	@Autowired
	private UrlShortenerDao urlShortenerDao;
	
	@Value("${default.shortURL.prefix}")
	private String defaultUrlPrefix;

	@Override
	public String convertToShortUrl(String longUrl) {
		
		String shortUrl = findShortUrlIfExist(longUrl);
		if(shortUrl!=null) {
			return shortUrl;
		}
		
		shortUrl = defaultUrlPrefix + UrlUtils.getShortUrl();
		urlShortenerDao.saveUrl(longUrl, shortUrl);
		return shortUrl;
	}

	private String findShortUrlIfExist(String longUrl) {
		return urlShortenerDao.findShortUrlIfExist(longUrl);		
	}
	

}

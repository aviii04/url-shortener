
package com.github.aviii04.urlshortner.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.github.aviii04.urlshortner.dao.UrlShortenerDao;
import com.github.aviii04.urlshortner.domain.exception.UrlException;
import com.github.aviii04.urlshortner.exception.UrlShortenerException;
import com.github.aviii04.urlshortner.utils.UrlUtils;

/**
 * @author Avinash Thakur
 *
 */

@Service
public class UrlShortenerServiceImpl implements UrlShortenerService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UrlShortenerServiceImpl.class);
	
	@Autowired
	private UrlShortenerDao urlShortenerDao;
	
	@Value("${default.shortURL.prefix}")
	private String defaultUrlPrefix;

	@Override
	public String convertToShortUrl(String longUrl) {
		LOGGER.info(String.format("Initiating generation of short URL for: %s", longUrl));
		
		String shortUrl = findShortUrlIfExist(longUrl);
		if(shortUrl!=null) {
			LOGGER.info(String.format("Short URL [%s] already exist for provided URL: %s", shortUrl, longUrl));
			return shortUrl;
		}
				
		int maxRetry = 0;	// Max attempt if URL can't be created/saved.
		while(maxRetry > 0) {
			try {
				shortUrl = defaultUrlPrefix + UrlUtils.getShortUrl();
				urlShortenerDao.saveUrl(longUrl, shortUrl);
				break;
			} catch(DuplicateKeyException duplicateKeyExc) {
				maxRetry--;
				LOGGER.warn(String.format("Generated short url already exist. No. of retry attempts left: %s", maxRetry));
			}
		}
		
		if(maxRetry!=0) {
			LOGGER.info(String.format("Short URL [%s] saved successfully for provided URL: %s", shortUrl, longUrl));
		}else {
			LOGGER.error("Couldn't create short URL.");
			throw new UrlShortenerException(UrlException.MAX_RETRY_EXCEEDED);
		}
			
		return shortUrl;
	}

	private String findShortUrlIfExist(String longUrl) {
		LOGGER.info(String.format("Checking if short URL already exist for provided URL:", longUrl));
		return urlShortenerDao.findShortUrlIfExist(longUrl);		
	}
	

}

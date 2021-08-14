
package com.github.aviii04.urlshortner.service;

import java.util.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.github.aviii04.urlshortner.dao.UrlShortenerDao;
import com.github.aviii04.urlshortner.domain.LongToShortURL;
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
	public LongToShortURL convertToShortUrl(String longUrl) {
		LOGGER.info(String.format("Initiating generation of short URL for: %s", longUrl));
		
		Optional<LongToShortURL> optionalShortUrl = findShortUrlIfExist(longUrl);
		
		if(optionalShortUrl.isPresent()) {
			LOGGER.info(String.format("Short URL [%s] already exist for provided URL: %s", optionalShortUrl.get().getShortUrl(), longUrl));
			return optionalShortUrl.get();
		}
				
		String shortUrl = null;
		LongToShortURL longToShortURL = null;
		int maxRetry = 5;	// Max attempt if unique URL can't be created/saved.
		
		while(maxRetry > 0) {
			try {
				shortUrl = defaultUrlPrefix + UrlUtils.getShortUrl();
				longToShortURL = buildLongToShourURL(longUrl, shortUrl, new Date());
				urlShortenerDao.saveUrl(longToShortURL);
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
			
		return longToShortURL;
	}

	@Override
	public LongToShortURL getLongUrl(String shortUrl) {
		Optional<LongToShortURL> longUrl = urlShortenerDao.getLongUrl(shortUrl);
		if(longUrl.isEmpty()) {
			LOGGER.warn(String.format("No URL exist for given short URL [%s]. Returning NULL.", shortUrl));
			return buildLongToShourURL(null, shortUrl, null);
		}else {
			LOGGER.info(String.format("Long Url [%s] found for given short URL [%s]", longUrl, shortUrl));
			return longUrl.get();
		}
	}
	
	private Optional<LongToShortURL> findShortUrlIfExist(String longUrl) {
		LOGGER.info(String.format("Checking if short URL already exist for provided URL: %s", longUrl));
		return urlShortenerDao.findShortUrlIfExist(longUrl);		
	}
	
	private LongToShortURL buildLongToShourURL(String longUrl, String shortUrl, Date createDate) {
		LongToShortURL longToShortURL = new LongToShortURL();
		longToShortURL.setLongUrl(longUrl);
		longToShortURL.setShortUrl(shortUrl);
		longToShortURL.setCreateDate(createDate);
		return longToShortURL;		
	}

}

package com.github.aviii04.urlshortner.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;

import com.github.aviii04.urlshortner.dao.UrlShortenerDaoImpl;
import com.github.aviii04.urlshortner.domain.LongToShortURL;
import com.github.aviii04.urlshortner.domain.exception.UrlException;
import com.github.aviii04.urlshortner.exception.UrlShortenerException;

@ExtendWith(MockitoExtension.class)
class UrlShortenerServiceImplTest {
	
	@Mock
	private UrlShortenerDaoImpl urlShortenerDao;
	
	@InjectMocks
	private UrlShortenerServiceImpl urlShortenerService;
	
	private static final String LONG_URL = "https://domain.com/test/testLongUrl";
	private static final String SHORT_URL = "https://domain.com/abc";
	private static final int EXPECTED_MAX_RETRY = 5;
	
	/**
	 * Test: Return already existing short URL if already generated.
	 */
	@Test
	void testShortUrlAlreadyExist() {
		
		Optional<LongToShortURL> optionalShortUrl = Optional.of(buildLongToShourURL(LONG_URL, SHORT_URL, new Date()));
		when(urlShortenerDao.findShortUrlIfExist(LONG_URL)).thenReturn(optionalShortUrl);
		
		LongToShortURL longToShortURL = urlShortenerService.convertToShortUrl(LONG_URL);
		
		assertEquals(SHORT_URL, longToShortURL.getShortUrl());
		verify(urlShortenerDao).findShortUrlIfExist(LONG_URL);
		verifyNoMoreInteractions(urlShortenerDao);
	}
	
	/**
	 * Test: Generate short URL if already not exist.
	 */
	@Test
	void testShortUrlGenerated() {
		when(urlShortenerDao.findShortUrlIfExist(LONG_URL)).thenReturn(Optional.empty());
		
		LongToShortURL longToShortURL = urlShortenerService.convertToShortUrl(LONG_URL);
		
		assertEquals(LONG_URL, longToShortURL.getLongUrl());
		assertNotNull(longToShortURL.getShortUrl());
		assertNotNull(longToShortURL.getCreateDate());
		verify(urlShortenerDao).saveUrl(Mockito.any(LongToShortURL.class));
	}
	
	/**
	 * Test: Throw exception if max retry is exceeded.
	 */
	@Test
	void testMaxRetryExceededException() {
		when(urlShortenerDao.findShortUrlIfExist(Mockito.anyString())).thenReturn(Optional.empty());
		doThrow(DuplicateKeyException.class).when(urlShortenerDao).saveUrl(Mockito.any(LongToShortURL.class));

		UrlShortenerException urlShortenerException = assertThrows(UrlShortenerException.class, () -> {
			urlShortenerService.convertToShortUrl(Mockito.anyString());
		});
		assertEquals(UrlException.MAX_RETRY_EXCEEDED, urlShortenerException.getUrlException());
		verify(urlShortenerDao, times(EXPECTED_MAX_RETRY)).saveUrl(Mockito.any(LongToShortURL.class));
	}
	
	/**
	 * Test: Check count of Maximum retry.
	 */
	@Test
	void testMaxRetryCount() {
		when(urlShortenerDao.findShortUrlIfExist(Mockito.anyString())).thenReturn(Optional.empty());
		doThrow(DuplicateKeyException.class).when(urlShortenerDao).saveUrl(Mockito.any(LongToShortURL.class));

		try{
			urlShortenerService.convertToShortUrl(Mockito.anyString());
		} catch(UrlShortenerException ex) {
			// Do nothing.
		}
		verify(urlShortenerDao, times(EXPECTED_MAX_RETRY)).saveUrl(Mockito.any(LongToShortURL.class));
	}
	
	private LongToShortURL buildLongToShourURL(String longUrl, String shortUrl, Date createDate) {
		LongToShortURL longToShortURL = new LongToShortURL();
		longToShortURL.setLongUrl(longUrl);
		longToShortURL.setShortUrl(shortUrl);
		longToShortURL.setCreateDate(createDate);
		return longToShortURL;		
	}

}

package com.github.aviii04.urlshortner.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;

import com.github.aviii04.urlshortner.dao.UrlShortenerDaoImpl;
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
//	@Test
//	void testShortUrlAlreadyExist() {
//		when(urlShortenerDao.findShortUrlIfExist(LONG_URL)).thenReturn(SHORT_URL);
//		
//		String actualShortUrl = urlShortenerService.convertToShortUrl(LONG_URL);
//		
//		assertEquals(SHORT_URL, actualShortUrl);
//		verify(urlShortenerDao).findShortUrlIfExist(LONG_URL);
//		verifyNoMoreInteractions(urlShortenerDao);
//	}
	
	/**
	 * Test: Generate short URL if already not exist.
	 */
//	@Test
//	void testShortUrlGenerated() {
//		when(urlShortenerDao.findShortUrlIfExist(LONG_URL)).thenReturn(null);
//		
//		String actualShortUrl = urlShortenerService.convertToShortUrl(LONG_URL);
//		
//		assertNotNull(actualShortUrl);
//		verify(urlShortenerDao).saveUrl(LONG_URL, actualShortUrl);
//	}
	
	/**
	 * Test: Throw exception if max retry is exceeded.
	 */
	@Test
	void testMaxRetryExceededException() {
		when(urlShortenerDao.findShortUrlIfExist(Mockito.anyString())).thenReturn(null);
		doThrow(DuplicateKeyException.class).when(urlShortenerDao).saveUrl(Mockito.anyString(), Mockito.anyString());

		UrlShortenerException urlShortenerException = assertThrows(UrlShortenerException.class, () -> {
			urlShortenerService.convertToShortUrl(Mockito.anyString());
		});
		assertEquals(UrlException.MAX_RETRY_EXCEEDED, urlShortenerException.getUrlException());
		verify(urlShortenerDao, times(EXPECTED_MAX_RETRY)).saveUrl(Mockito.anyString(), Mockito.anyString());
	}
	
	/**
	 * Test: Check count of Maximum retry.
	 */
	@Test
	void testMaxRetryCount() {
		when(urlShortenerDao.findShortUrlIfExist(Mockito.anyString())).thenReturn(null);
		doThrow(DuplicateKeyException.class).when(urlShortenerDao).saveUrl(Mockito.anyString(), Mockito.anyString());

		try{
			urlShortenerService.convertToShortUrl(Mockito.anyString());
		} catch(UrlShortenerException ex) {
			// Do nothing.
		}
		verify(urlShortenerDao, times(EXPECTED_MAX_RETRY)).saveUrl(Mockito.anyString(), Mockito.anyString());
	}

}

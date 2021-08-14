package com.github.aviii04.urlshortner.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UrlUtilsTest {
	
	private static final int EXPECTED_SHORT_URL_LENGTH = 7;

	/**
	 * Test: Fixed length of generated short URL.
	 */
	@Test
	void testShortUrlFixedLength() {
		String shortUrl = UrlUtils.getShortUrl();
		
		assertNotNull(shortUrl);
		assertEquals(EXPECTED_SHORT_URL_LENGTH, shortUrl.length());	
	}

}

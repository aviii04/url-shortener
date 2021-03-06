package com.github.aviii04.urlshortner.utils;

import java.util.Random;

public final class UrlUtils {
	
	private static final String BASE_62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final int BOUND = BASE_62.length();
	private static final int SHORT_URL_LENGTH = 7;

	
	/*
	 * As it is utility class no instance should be created for this class.
	 */
	private UrlUtils() {}
	
	/*
	 * Generates fixed length random short url.
	 */
	public static String getShortUrl() {
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		
		for(int i = 0; i < SHORT_URL_LENGTH; i++) {
			sb.append(BASE_62.charAt(random.nextInt(BOUND)));
		}
		
		return sb.toString();			
	}

}

package com.github.aviii04.urlshortner.exception;

import com.github.aviii04.urlshortner.domain.exception.UrlException;

/**
 * @author Avinash Thakur
 * 
 * Generic Custom Exception for Module.
 */
public class UrlShortenerException extends RuntimeException {
	
	public UrlShortenerException(UrlException urlException) {
		super(urlException.getErrMsgWithCode());
	}

}

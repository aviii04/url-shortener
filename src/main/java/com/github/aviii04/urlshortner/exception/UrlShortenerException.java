package com.github.aviii04.urlshortner.exception;

import com.github.aviii04.urlshortner.domain.exception.UrlException;

/**
 * @author Avinash Thakur
 * 
 * Generic Custom Exception for Module.
 */
public class UrlShortenerException extends RuntimeException {
	
	private UrlException urlException;
	
	public UrlShortenerException(UrlException urlException) {
		super(urlException.getErrMsgWithCode());
		this.urlException = urlException;
	}

	public UrlException getUrlException() {
		return urlException;
	}

}

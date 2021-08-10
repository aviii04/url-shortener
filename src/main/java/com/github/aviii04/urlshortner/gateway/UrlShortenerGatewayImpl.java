package com.github.aviii04.urlshortner.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.aviii04.urlshortner.service.UrlShortenerService;

@Component
public class UrlShortenerGatewayImpl implements UrlShortenerGateway {
	
	@Autowired
	private UrlShortenerService urlShortenerService;

}

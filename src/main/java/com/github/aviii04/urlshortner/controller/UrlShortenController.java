package com.github.aviii04.urlshortner.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.aviii04.urlshortner.gateway.UrlShortenerGateway;

@RestController
public class UrlShortenController {
	
	@Autowired
	private UrlShortenerGateway urlShortenerGateway;

    @RequestMapping(value="/tinyurl", method = RequestMethod.POST)
    public String tinyUrl(@RequestBody Map<String, String> requestBody) throws Exception{
    	String longUrl = requestBody.get("longUrl");
    	return urlShortenerGateway.convertToShortUrl(longUrl);
    }
    
}

package com.github.aviii04.urlshortner.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.aviii04.urlshortner.gateway.UrlShortenerGateway;

@RestController
public class UrlShortenController {
	
	@Autowired
	private UrlShortenerGateway urlShortenerGateway;

	/**
	 * Translates long URL to short URL.
	 * 
	 * @param requestBody - URL to be minify.
	 * @return - shortUrl
	 * @throws Exception
	 */
    @RequestMapping(value="/tinyurl", method = RequestMethod.POST)
    public String convertToShortUrl(@RequestBody Map<String, String> requestBody) throws Exception{
    	String longUrl = requestBody.get("longUrl");
    	return urlShortenerGateway.convertToShortUrl(longUrl);
    }
    
    /**
     * Returns Long URL for corresponding short URL if exist.
     * 
     * @param shortUrl
     * @return corresponding long URL.
     */
    @GetMapping("/tinyurl")
    public String getLongUrl(@RequestParam String shortUrl) {
    	return urlShortenerGateway.getLongUrl(shortUrl);
    }
    
}

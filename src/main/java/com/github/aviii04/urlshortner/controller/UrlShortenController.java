package com.github.aviii04.urlshortner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.aviii04.urlshortner.gateway.UrlShortenerGateway;

@RestController
public class UrlShortenController {
	
	@Autowired
	private UrlShortenerGateway urlShortenerGateway;

    @PostMapping("/tinyurl")
    public String tinyUrl(@RequestBody String longUrl){
//    	Call service via gateway.
        return "shortUrl";
    }
}

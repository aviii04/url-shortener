
package com.github.aviii04.urlshortner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.github.aviii04.urlshortner.dao.UrlShortenerDao;

/**
 * @author Avinash Thakur
 *
 */

@Service
public class UrlShortenerServiceImpl implements UrlShortenerService {
	
	@Autowired
	private UrlShortenerDao urlShortenerDao;
	

}

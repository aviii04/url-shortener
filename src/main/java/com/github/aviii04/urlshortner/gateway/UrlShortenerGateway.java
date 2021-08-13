
package com.github.aviii04.urlshortner.gateway;

/**
 * @author Avinash Thakur
 *
 * Gateway to handle all incoming API request & directs it to specific service.
 */
public interface UrlShortenerGateway {

	String convertToShortUrl(String longUrl);

	String getLongUrl(String shortUrl);

}

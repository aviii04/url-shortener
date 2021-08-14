
package com.github.aviii04.urlshortner.gateway;

import com.github.aviii04.urlshortner.domain.LongToShortURL;

/**
 * @author Avinash Thakur
 *
 * Gateway to handle all incoming API request & directs it to specific service.
 */
public interface UrlShortenerGateway {

	LongToShortURL convertToShortUrl(String longUrl);

	LongToShortURL getLongUrl(String shortUrl);

}

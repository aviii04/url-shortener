package com.github.aviii04.urlshortner.domain;

import java.util.Date;

/**
 * Represents 'URL Mapping' entity/table.
 * 
 * @author Avinash Thakur
 *
 */
public class LongToShortURL {
	
	private String longUrl;
	private String shortUrl;
	private Date createDate;
	
	public String getLongUrl() {
		return longUrl;
	}
	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}
	public String getShortUrl() {
		return shortUrl;
	}
	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@Override
	public String toString() {
		return "LongToShortURL ["
				+ "longUrl=" + longUrl + 
				", shortUrl=" + shortUrl + 
				", createDate=" + createDate +
				"]";
	}	

}

package com.github.aviii04.urlshortner.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class UrlShortenerDaoImpl implements UrlShortenerDao {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public void saveUrl(String longUrl, String shortUrl) {
		String query = "insert into UrlMapping (longurl, shorturl, createdate)\r\n"
				+ "values(:longUrl, :shortUrl, current_timestamp)";
		
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("longUrl", longUrl);
		namedParameters.addValue("shortUrl", shortUrl);
		
		jdbcTemplate.update(query, namedParameters);		
	}

	@Override
	public String findShortUrlIfExist(String longUrl) {
		String query = "select u.shorturl from UrlMapping u where u.longurl = :longUrl";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("longUrl", longUrl);
		
		return executeQuery(query, namedParameters);								
	}

	@Override
	public String getLongUrl(String shortUrl) {
		String query = "select u.longurl from UrlMapping u where u.shorturl = :shortUrl";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("shortUrl", shortUrl);
		
		return executeQuery(query, namedParameters);
	}
	
	private String executeQuery(String query, MapSqlParameterSource queryParams) {
		try {
			return jdbcTemplate.queryForObject(query, queryParams, String.class);
		} catch(EmptyResultDataAccessException emptyEx) {
			return null;
		}
	}

}

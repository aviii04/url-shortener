package com.github.aviii04.urlshortner.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.github.aviii04.urlshortner.domain.LongToShortURL;

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
	public Optional<LongToShortURL> findShortUrlIfExist(String longUrl) {
		String query = "select u.longurl, u.shortUrl, u.createDate from UrlMapping u where u.longurl = :longUrl";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("longUrl", longUrl);
		
		try {
			LongToShortURL longToShortURL = jdbcTemplate.queryForObject(query, namedParameters, new BeanPropertyRowMapper<>(LongToShortURL.class));
			return Optional.of(longToShortURL);
		} catch(EmptyResultDataAccessException emptyEx) {
			return Optional.empty();
		}		
	}

	@Override
	public Optional<LongToShortURL> getLongUrl(String shortUrl) {
		String query = "select u.longurl, u.shortUrl, u.createDate from UrlMapping u where u.shorturl = :shortUrl";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("shortUrl", shortUrl);
		
		try {			
			LongToShortURL longToShortURL = jdbcTemplate.queryForObject(query, namedParameters, new BeanPropertyRowMapper<>(LongToShortURL.class));
			return Optional.of(longToShortURL);
			
		} catch(EmptyResultDataAccessException emptyEx) {
			return Optional.empty();
		}
	}

}

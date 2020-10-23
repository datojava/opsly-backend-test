package com.opsly.newsfeed.entities;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.List;
import java.util.concurrent.Callable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Twitter extends SocialNetwork<Twitter> implements Callable<List<Twitter>> {

	private final Logger log = LogManager.getLogger(getClass());

	private String username;

	private String tweet;

	private HttpRequest httpReq;

	private HttpClient client;

	public Twitter() {
		super(null);
	}

	public Twitter(ObjectMapper mapper, HttpClient client, HttpRequest httpReq) {
		super(mapper);
		this.httpReq = httpReq;
		this.client = client;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTweet() {
		return tweet;
	}

	public void setTweet(String tweet) {
		this.tweet = tweet;
	}

	@Override
	public List<Twitter> call() throws Exception {

		log.info("Collecting items for {}", Twitter.class.getName());

		return collect(client, httpReq);
	}
}

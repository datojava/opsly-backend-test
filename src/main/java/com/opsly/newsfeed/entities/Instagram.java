package com.opsly.newsfeed.entities;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.List;
import java.util.concurrent.Callable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Instagram extends SocialNetwork<Instagram> implements Callable<List<Instagram>> {

	private final Logger log = LogManager.getLogger(getClass());

	private HttpRequest httpReq;

	private HttpClient client;

	private String data;

	public Instagram() {
		super(null);
	}

	public Instagram(ObjectMapper mapper, HttpClient client, HttpRequest httpReq) {
		super(mapper);
		this.client = client;
		this.httpReq = httpReq;
	}

	@Override
	public List<Instagram> call() throws Exception {

		log.info("Not implemented");

		return collect(client, httpReq);
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}

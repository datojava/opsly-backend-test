package com.opsly.newsfeed.entities;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.List;
import java.util.concurrent.Callable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Facebook extends SocialNetwork<Facebook> implements Callable<List<Facebook>> {

	private final Logger log = LogManager.getLogger(getClass());

	private String name;

	private String status;

	private HttpRequest httpReq;

	private HttpClient client;

	public Facebook() {
		super(null);
	}

	public Facebook(ObjectMapper mapper, HttpClient client, HttpRequest httpReq) {
		super(mapper);
		this.client = client;
		this.httpReq = httpReq;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public List<Facebook> call() throws Exception {

		log.info("Collecting items for {}", Facebook.class.getName());

		return collect(client, httpReq);
	}

}

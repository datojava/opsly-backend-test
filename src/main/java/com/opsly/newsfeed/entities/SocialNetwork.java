package com.opsly.newsfeed.entities;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SocialNetwork<T> {

	private final Logger log = LogManager.getLogger(getClass());

	private ObjectMapper mapper;

	public SocialNetwork(ObjectMapper mapper) {
		this.mapper = mapper;

		if (mapper == null) {
			mapper = new ObjectMapper();
		}
	}

	public List<T> collect(HttpClient client, HttpRequest httpReq) throws Exception {

		log.info("Collecting items ");

		HttpResponse<String> httpResponse = client.send(httpReq, HttpResponse.BodyHandlers.ofString());

		log.info("Got http response");

		HttpStatus.Series status = HttpStatus.Series.resolve(httpResponse.statusCode());

		if (status == HttpStatus.Series.SUCCESSFUL) {

			return mapper.readValue(httpResponse.body(), new TypeReference<List<T>>() {
			});

		} else {
			log.error("Unable to get items, URL = {}, http status code : {}", httpReq.uri().toURL(),
					httpResponse.statusCode());

			return null;
		}

	}
}
package com.opsly.newsfeed.app;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opsly.newsfeed.configs.URLConfig;

@SpringBootApplication
@ComponentScan(basePackages = { "com.opsly.newsfeed" })
public class Application {

	private final Logger log = LogManager.getLogger(getClass());

	@Autowired
	private URLConfig config;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean("objMapper")
	public ObjectMapper objMapper() {

		log.info("Creating {} instance", ObjectMapper.class.getName());

		return new ObjectMapper();
	}

	@Bean("httpClient")
	public HttpClient httpClient() {

		log.info("Creating {} instance", HttpClient.class.getName());

		return HttpClient.newBuilder().priority(1).build();
	}

	@Bean("twitterReq")
	public HttpRequest twitterHttpRequest() {

		log.info("Creating {} instance for twitter", HttpRequest.class.getName());

		return HttpRequest.newBuilder().uri(URI.create(config.getTwitter())).build();
	}

	@Bean("facebookReq")
	public HttpRequest facebookHttpRequest() {

		log.info("Creating {} instance for facebook", HttpRequest.class.getName());

		return HttpRequest.newBuilder().uri(URI.create(config.getFacebook())).build();
	}
	
	@Bean("instagramReq")
	public HttpRequest instagramHttpRequest() {

		log.info("Creating {} instance for instagram", HttpRequest.class.getName());

		return HttpRequest.newBuilder().uri(URI.create(config.getInstagram())).build();
	}

}

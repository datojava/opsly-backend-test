package com.opsly.newsfeed.tests;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.InetAddress;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opsly.newsfeed.app.Application;
import com.opsly.newsfeed.configs.URLConfig;
import com.opsly.newsfeed.entities.ErrorType;
import com.opsly.newsfeed.i18n.MSGBundle;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = { Application.class, URLConfig.class })
public class SimpleTests {

	private final Logger log = LogManager.getLogger(getClass());

	private final int REACHABLE_TIMEOUT = 5000;

	@Autowired
	private URLConfig config;

	@Autowired
	private ObjectMapper objMapper;

	@Autowired
	private HttpClient httpClient;

	@Autowired
	private HttpRequest twitterReq;

	@Autowired
	private HttpRequest facebookReq;

	@Autowired
	private HttpRequest instagramReq;

	@Test
	@Order(1)
	public void contextLoads() {

		log.info("Context loadeded");

	}

	@Test
	@Order(2)
	public void injectObjectsNotNull() throws Throwable {

		log.info("Checking if injected objects are null");

		assertNotNull(config);
		assertNotNull(objMapper);
		assertNotNull(httpClient);
		assertNotNull(twitterReq);
		assertNotNull(facebookReq);
		assertNotNull(instagramReq);
	}

	@Test
	@Order(3)
	public void messagesExist() {

		log.info("Checking if messages exist in message bundle");

		assertNotNull(MSGBundle.getInstance());
		assertNotNull(ErrorType.ALL_DATA_EMPTY.message());
		assertNotNull(ErrorType.SOMETHING_WENT_WRONG.message());

	}

	@Test
	@Order(4)
	public void hostReachable() throws Throwable {

		log.info("Checking if base host is reachable");

		assertTrue(InetAddress.getByName(config.getHost()).isReachable(REACHABLE_TIMEOUT));
	}

	@Test
	@Order(5)
	public void urlsExist() {

		log.info("Checking if URL s exist ");

		assertTrue(config.getTwitter() != null && config.getTwitter().trim().length() != 0);
		assertTrue(config.getFacebook() != null && config.getFacebook().trim().length() != 0);
		assertTrue(config.getInstagram() != null && config.getInstagram().trim().length() != 0);
	}
}

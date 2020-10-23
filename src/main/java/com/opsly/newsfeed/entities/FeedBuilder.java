package com.opsly.newsfeed.entities;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

public class FeedBuilder {

	private ExecutorService executor;

	private HttpClient client;

	private ObjectMapper objMapper;

	private Future<List<Twitter>> tweetFeature;

	private Future<List<Facebook>> facebookFeature;

	private Future<List<Instagram>> instagramFeature;

	private final Logger log = LogManager.getLogger(getClass());

	public FeedBuilder(ObjectMapper objMapper, HttpClient client) {
		this.client = client;
		this.objMapper = objMapper;
		this.executor = Executors.newFixedThreadPool(3);
	}

	public FeedBuilder withTweets(HttpRequest httpReq) throws Exception {

		log.info("Executing {}", Twitter.class.getName());

		tweetFeature = executor.submit(new Twitter(objMapper, client, httpReq));

		return this;
	}

	public FeedBuilder withStatuses(HttpRequest httpReq) throws Exception {

		log.info("Executing {}", Facebook.class.getName());

		facebookFeature = executor.submit(new Facebook(objMapper, client, httpReq));

		return this;
	}

	public FeedBuilder withImages(HttpRequest httpReq) throws Exception {

		log.info("Executing {}", Instagram.class.getName());

		instagramFeature = executor.submit(new Instagram(objMapper, client, httpReq));

		return this;
	}

	public Feed build() throws Exception {

		Feed feed = new Feed();

		log.info("Generating all feed");

		if (facebookFeature != null) {
			feed.setStatuses(facebookFeature.get());
		}

		if (tweetFeature != null) {
			feed.setTweets(tweetFeature.get());
		}

		if (instagramFeature != null) {
			feed.setImages(instagramFeature.get());
		}

		log.info("Executor job completed");

		return feed;
	}
}

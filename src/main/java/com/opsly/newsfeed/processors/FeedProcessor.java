package com.opsly.newsfeed.processors;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opsly.newsfeed.entities.ErrorType;
import com.opsly.newsfeed.entities.Feed;
import com.opsly.newsfeed.entities.FeedBuilder;
import com.opsly.newsfeed.errors.OpslyException;

@Component
public class FeedProcessor {

	@Autowired
	private HttpClient httpClient;

	@Autowired
	private HttpRequest facebookReq;

	@Autowired
	private HttpRequest twitterReq;

	@Autowired
	private HttpRequest instagramReq;

	@Autowired
	private ObjectMapper objMapper;

	private final Logger log = LogManager.getLogger(getClass());

	public Feed load() throws Exception {

		Feed feed = null;

		try {
			log.info("Collecting all feed data");

			feed = new FeedBuilder(objMapper, httpClient).
					withTweets(twitterReq).withStatuses(facebookReq)
					.withImages(instagramReq).build();

			if (allDataEmpty(feed)) {
				throw new OpslyException(ErrorType.ALL_DATA_EMPTY);
			}

			log.info("Feed data collected");

		} catch (OpslyException e) {

			throw e;

		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);

			throw new OpslyException(ErrorType.SOMETHING_WENT_WRONG.message());
		}

		return feed;
	}

	private boolean allDataEmpty(Feed feed) {
		return (feed.getStatuses() == null || feed.getStatuses().isEmpty())
				&& (feed.getTweets() == null || feed.getTweets().isEmpty())
				&& (feed.getImages() == null || feed.getImages().isEmpty());
	}

}

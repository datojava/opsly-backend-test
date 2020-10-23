package com.opsly.newsfeed.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.opsly.newsfeed.entities.Feed;
import com.opsly.newsfeed.processors.FeedProcessor;

@RestController
public class FeedController {

	private final Logger log = LogManager.getLogger(getClass());

	@Autowired
	private FeedProcessor parseProc;

	@GetMapping(produces = "application/json")
	public Feed newsFeed() throws Exception {

		log.info("Parsing data");

		return parseProc.load();
	}

}

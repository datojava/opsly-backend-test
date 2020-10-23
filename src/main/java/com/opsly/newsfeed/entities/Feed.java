package com.opsly.newsfeed.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Feed {

	@JsonProperty("twitter")
	private List<Twitter> tweets;

	@JsonProperty("facebook")
	private List<Facebook> statuses;

	@JsonProperty("instagram")
	private List<Instagram> images;

	public List<Twitter> getTweets() {
		return tweets;
	}

	public void setTweets(List<Twitter> tweets) {
		this.tweets = tweets;
	}

	public List<Facebook> getStatuses() {
		return statuses;
	}

	public void setStatuses(List<Facebook> statuses) {
		this.statuses = statuses;
	}

	public List<Instagram> getImages() {
		return images;
	}

	public void setImages(List<Instagram> images) {
		this.images = images;
	}

}

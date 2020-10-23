package com.opsly.newsfeed.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("url")
public class URLConfig {

	private String host;

	private String twitter;

	private String facebook;

	private String instagram;

	public String getHost() {
		return host;
	}

	public String getTwitter() {
		return twitter;
	}

	public String getFacebook() {
		return facebook;
	}

	public String getInstagram() {
		return instagram;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}

	
}

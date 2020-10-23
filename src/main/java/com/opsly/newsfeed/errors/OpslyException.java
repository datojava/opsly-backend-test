package com.opsly.newsfeed.errors;

import com.opsly.newsfeed.entities.ErrorType;

@SuppressWarnings("serial")
public class OpslyException extends Exception {

	private ErrorType response;

	public OpslyException() {

	}

	public OpslyException(ErrorType response) {
		super(response.message());
		this.response = response;
	}

	public OpslyException(String message) {
		super(message);
	}

	public OpslyException(Throwable t) {
		super(t);
	}

	public OpslyException(String message, Throwable t) {
		super(message, t);
	}

	public ErrorType getErrorType() {
		return response;
	}
}

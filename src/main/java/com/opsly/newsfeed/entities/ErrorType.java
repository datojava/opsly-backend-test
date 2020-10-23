package com.opsly.newsfeed.entities;

import com.opsly.newsfeed.i18n.MSGBundle;

public enum ErrorType {

	SOMETHING_WENT_WRONG((short) 1, "com.opslycloud.response.code.something.wrong"),
	ALL_DATA_EMPTY((short) 2, "com.opslycloud.response.code.alldata.empty");

	private short code;

	private String messageCode;

	private MSGBundle bundle = MSGBundle.getInstance();

	private ErrorType(short code, String messageCode) {
		this.code = code;
		this.messageCode = messageCode;
	}

	public short getCode() {
		return code;
	}

	public String getMsgCode() {
		return messageCode;
	}

	public String message() {
		return bundle.get(messageCode);
	}
}

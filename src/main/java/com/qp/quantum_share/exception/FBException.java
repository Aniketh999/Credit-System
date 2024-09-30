package com.qp.quantum_share.exception;

import com.restfb.exception.FacebookException;

public class FBException extends FacebookException {
	String message;
	String platform;

	public FBException(String message, String platform) {
		super(message);
		this.message = message;
		this.platform = platform;
	}

	public String getPlatform() {
		return platform;
	}

}

package com.qp.quantum_share.exception;

public class NullPointerExcep extends RuntimeException {
	String message;
	
	public NullPointerExcep(String message) {
		this.message = message;
	}
}

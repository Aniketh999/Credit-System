package com.qp.quantum_share.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseWrapper {
	SuccessResponse success;
	ErrorResponse failed;
	String message;
	ResponseStructure<String> structure;
	
	public ResponseWrapper() {
	}
	
	public ResponseWrapper(ResponseStructure<String> structure) {
		this.structure = structure;
	}

	public ResponseWrapper(SuccessResponse success) {
		this.success = success;
	}

	public ResponseWrapper(ErrorResponse failed) {
		this.failed = failed;
	}
	
	public ResponseWrapper(String message) {
		this.message = message;
	}
	
	

}

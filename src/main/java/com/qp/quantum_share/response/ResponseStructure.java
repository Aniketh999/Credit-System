package com.qp.quantum_share.response;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseStructure<T> {
	String message;
	String status;
	int code;
	String platform;
	Object data;
}

package com.qp.quantum_share.exception;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.qp.quantum_share.response.ResponseStructure;

import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import twitter4j.TwitterException;

@RestControllerAdvice
public class MainExceptionHandler extends RuntimeException {

	@Autowired
	ResponseStructure<String> structure;

	private static String extractMessageValue(String jsonData) {
		int startIndex = jsonData.indexOf("message\":\"");
		if (startIndex != -1) {
			String remainingString = jsonData.substring(startIndex + "message\":\"".length());
			int endIndex = remainingString.indexOf(".");
			if (endIndex != -1) {
				return remainingString.substring(0, endIndex);
			}
		}
		return null;
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ResponseStructure<String>> handleBadRequest(BadRequestException exception) {
		exception.printStackTrace();
		structure.setMessage(exception.getMessage());
		structure.setCode(HttpStatus.NOT_ACCEPTABLE.value());
		structure.setStatus("error");
		structure.setPlatform(null);
		structure.setData(exception.getMessage());

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(SignatureException.class)
	public ResponseEntity<ResponseStructure<String>> handleSessionError(SignatureException exception) {
		structure.setMessage("Missing or invalid authorization token");
		structure.setCode(115);
		structure.setStatus("error");
		structure.setPlatform(null);
		structure.setData(exception.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(MalformedJwtException.class)
	public ResponseEntity<ResponseStructure<String>> handleSessionError(MalformedJwtException exception) {
		structure.setMessage("Missing or invalid authorization token");
		structure.setCode(115);
		structure.setStatus("error");
		structure.setPlatform(null);
		structure.setData(exception.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<ResponseStructure<String>> handleNullPointerException(NullPointerException exception) {

		structure.setMessage(exception.getMessage());
		structure.setCode(HttpStatus.BAD_REQUEST.value());
		structure.setStatus("error");
		structure.setData("Bad Request");
		structure.setData(null);
		structure.setPlatform(null);
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(CommonException.class)
	public ResponseEntity<ResponseStructure<String>> handleCommonException(CommonException exception) {
		exception.printStackTrace();
		structure.setMessage(exception.message);
		structure.setCode(HttpStatus.NOT_ACCEPTABLE.value());
		structure.setStatus("error");
		structure.setData(exception.getCause());
		structure.setPlatform(null);
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_ACCEPTABLE);
	}

	
//	@ExceptionHandler(FacebookOAuthException.class)
//	public ResponseEntity<ResponseStructure<String>> handleFacebookOAuthException(FacebookOAuthException exception) {
//		System.out.println("FacebookException  ");
//		structure.setMessage(exception.getMessage());
//		structure.setCode(HttpStatus.CONFLICT.value());
//		structure.setStatus("error");
//
//		try {
//			String m = exception.getMessage();
//			String messageValue = extractMessageValue(m);
//			structure.setData(messageValue);
//		} catch (Exception e) {
//			structure.setData(e.getMessage());
//		}
//
//		return new ResponseEntity<>(structure, HttpStatus.BAD_REQUEST);
//	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ResponseStructure<String>> handleIllegalArgumentExce(IllegalArgumentException exception) {

		structure.setMessage(exception.getMessage());
		structure.setCode(HttpStatus.BAD_REQUEST.value());
		structure.setStatus("error");
		structure.setData(exception);
//		try {
//			String m = exception.getMessage();
//			String messageValue = extractMessageValue(m);
//			structure.setData(messageValue);
//		} catch (Exception e) {
//			structure.setData("Error processing error message: " + e.getMessage());
//
//		}

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(IOException.class)
	public ResponseEntity<ResponseStructure<String>> handleIOException(IOException exception) {
		structure.setMessage(exception.getMessage());
		structure.setCode(HttpStatus.NOT_FOUND.value());
		structure.setStatus("error");

		try {
			String m = exception.getMessage();
			String messageValue = extractMessageValue(m);
			structure.setData(messageValue);
		} catch (Exception e) {
			structure.setData(e.getMessage());
		}

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<ResponseStructure<String>> handleIllegalStateException(IllegalStateException exception) {
		structure.setMessage(exception.getMessage());
		structure.setCode(HttpStatus.NOT_FOUND.value());
		structure.setStatus("error");

		try {
			String m = exception.getMessage();
			String messageValue = extractMessageValue(m);
			structure.setData(messageValue);
		} catch (Exception e) {
			structure.setData(e.getMessage());
		}

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(FBException.class)
	public ResponseEntity<ResponseStructure<String>> handleFBException(FBException exception) {
		String message = null;
		if(exception.message.contains("The aspect ratio is not supported.")) {
			structure.setMessage("Unsupported aspect ratio. Please use one of Instagram's formats: 4:5, 1:1, or 1.91:1.");
			structure.setCode(116);
			structure.setStatus("error");
			structure.setPlatform(exception.getPlatform());
			structure.setData(exception.getLocalizedMessage());
			return new ResponseEntity<>(structure, HttpStatus.OK);
		}
		if (exception.getMessage().contains(":")) {
			message = exception.getMessage().split(":")[1];
		}
		structure.setMessage(message);
		structure.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		structure.setStatus("error");
		structure.setPlatform(exception.getPlatform());
		structure.setData(exception.getLocalizedMessage());
		return new ResponseEntity<>(structure, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseStructure<String>> handleException(Exception exception) {
		String message = null;
		if (exception.toString().contains(":")) {
			message = exception.toString().split(":")[1];
		}
		exception.printStackTrace();
		structure.setMessage(message);
		structure.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		structure.setStatus("error");
		structure.setData(exception.getLocalizedMessage());
		return new ResponseEntity<>(structure, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(TwitterException.class)
	public ResponseEntity<ResponseStructure<String>> handleTwitterException(TwitterException exception) {
		structure.setMessage(exception.getMessage());
		structure.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		structure.setStatus("error");
		structure.setData(exception.getCause());
		return new ResponseEntity<>(structure, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// @ExceptionHandler(Exception.class)
//	public ResponseEntity<ResponseStructure<String>> handleFBException(Exception exception) {
//
//		System.out.println("******Coming*************");
//		structure.setMessage(exception.getMessage());
//		structure.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
//		structure.setStatus("error");
//		try {
//			String m = exception.getMessage();
//			String messageValue = extractMessageValue(m);
//			structure.setData(messageValue);
//		} catch (Exception e) {
//			structure.setData(e.getMessage());
//		}
//		return new ResponseEntity<>(structure, HttpStatus.INTERNAL_SERVER_ERROR);
//	}
}

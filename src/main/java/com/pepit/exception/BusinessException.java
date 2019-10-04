package com.pepit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class BusinessException extends Exception {

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException() {
		super();
	}

}

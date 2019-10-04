package com.pepit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR, reason="Erreur Interne")  // 500
public class BusinessException extends Exception {
	
	public BusinessException(String message) {
		super(message);
	}
	
	public BusinessException() {
		super();
	}

}

package com.pepit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No Result")  // 404
public class NoResultException extends BusinessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoResultException() {
		super();
	}
	
	public NoResultException(String message) {
		super(message);
	}

}

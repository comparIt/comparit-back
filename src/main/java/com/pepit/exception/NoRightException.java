package com.pepit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.UNAUTHORIZED, reason="No Right")  // 
public class NoRightException extends BusinessException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoRightException() {
		super();
	}
	
	public NoRightException(String message) {
		super(message);
	}


}

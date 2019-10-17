package com.pepit.exception;

public class UnauthorizedException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public UnauthorizedException() {
		super();
	}
	
	public UnauthorizedException(String message) {
		super(message);
	}


}

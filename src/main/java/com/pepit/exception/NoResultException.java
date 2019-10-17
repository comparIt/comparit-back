package com.pepit.exception;

public class NoResultException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public NoResultException() {
		super();
	}
	
	public NoResultException(String message) {
		super(message);
	}

}

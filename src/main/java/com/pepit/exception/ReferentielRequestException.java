package com.pepit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ReferentielRequestException extends BusinessException {

	private static final long serialVersionUID = 1L;
	
	public ReferentielRequestException() {
		super();
	}
	
	public ReferentielRequestException(String message) {
		super(message);
	}

}

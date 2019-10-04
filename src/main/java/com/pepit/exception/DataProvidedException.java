package com.pepit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR, reason="Donnée fournies non conforme")  // 500
public class DataProvidedException extends BusinessException {

	public DataProvidedException(String message) {
		super(message);
	}

	public DataProvidedException() {
		super();
	}

}

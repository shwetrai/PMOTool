package com.pmo.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="Duplicate Record Found")
public class DuplicateRecordFoundException extends RuntimeException{
	
	
	private static final long serialVersionUID = 1L;

	public DuplicateRecordFoundException(String exception) {
		    super(exception);
	}
	public DuplicateRecordFoundException(String exception, Throwable t) {
	    super(exception, t);
	}

}

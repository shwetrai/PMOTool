package com.ibm.api.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	  public final ResponseEntity<ErrorRecord> handleAllExceptions(Exception ex, WebRequest request) {
	    ErrorRecord errorDetails = new ErrorRecord(new Date(), ex.getMessage(),
	        request.getDescription(false));
	    return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	@ExceptionHandler(RuntimeException.class)
	  public final ResponseEntity<ErrorRecord> handleRuntimeExceptions(Exception ex, WebRequest request) {
	    ErrorRecord errorDetails = new ErrorRecord(new Date(), ex.getMessage(),
	        request.getDescription(false));
	    return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	
	

	  @ExceptionHandler(ResourceNotFoundException.class)
	  public final ResponseEntity<ErrorRecord> handlerResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
	    ErrorRecord errorDetails = new ErrorRecord(new Date(), ex.getMessage(),
	        request.getDescription(false));
	    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	  }

}

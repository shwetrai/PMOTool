package com.pmo.api.exceptions;



import java.util.Date;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	  public final ResponseEntity<ErrorRecord> handleAllExceptions(Exception ex, WebRequest request) {
	    ErrorRecord errorDetails = new ErrorRecord(new Date(), ex.getMessage(),
	        request.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR);
	    return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	  }

	  @ExceptionHandler(ResourceNotFoundException.class)
	  public final ResponseEntity<Object> handlerResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
//	    ErrorRecord errorDetails = new ErrorRecord(new Date(), ex.getMessage(),
//	        request.getDescription(false), HttpStatus.NOT_FOUND);
	    return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	  }
	  
	  @ExceptionHandler(org.springframework.dao.DuplicateKeyException.class)
	  public final ResponseEntity<String> handlerDuplicateRecordFoundException(DuplicateKeyException ex, WebRequest request) {
//	    ErrorRecord errorDetails = new ErrorRecord(new Date(), "Duplicate Record Found",
//	        request.getDescription(false), HttpStatus.CONFLICT);
	    System.out.println("Error occurred -> handlerDuplicateRecordFoundException");
	    return new ResponseEntity<>("Duplicate Record Found", HttpStatus.CONFLICT);
	  }
	  
//	  @ExceptionHandler(org.springframework.dao.DuplicateKeyException.class)
//	  @ResponseStatus(value = HttpStatus.CONFLICT)
//	  @ResponseBody
//	  public Map<String, Object> handleDuplicateKeyException(DuplicateKeyException e) {
//	    String entity = null;
//	    String message = null;
//	    String invalidValue = null;
//	    String property = null;
//
//	    String errorMessage = e.getMessage();
//
//	    Pattern pattern = Pattern.compile("\\.(.*?) index: (.*?) dup key: \\{ : \\\\\"(.*?)\\\\\"");
//	    Matcher matcher = pattern.matcher(errorMessage);
//	    if (matcher.find()) {
//	      entity = WordUtils.capitalize(matcher.group(1));
//	      property = matcher.group(2);
//	      invalidValue = matcher.group(3);
//	    }
//
//	    message = WordUtils.capitalize(property) + " must be unique";
//
//	    Map<String, String> uniqueIndexViolation = new HashMap<>();
//	    uniqueIndexViolation.put("entity", entity);
//	    uniqueIndexViolation.put("message", message);
//	    uniqueIndexViolation.put("invalidValue", invalidValue);
//	    uniqueIndexViolation.put("property", property);
//
//	    List<Object> errors = new ArrayList<Object>();
//	    errors.add(uniqueIndexViolation);
//
//	    Map<String, Object> responseBody = new HashMap<>();
//	    responseBody.put("errors", errors);
//
//	    return responseBody;
//	  }

}

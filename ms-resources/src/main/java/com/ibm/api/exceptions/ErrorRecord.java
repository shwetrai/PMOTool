package com.ibm.api.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;

public class ErrorRecord {
	
	 private Date timestamp;
	  private String message;
	  private String details;
	  private HttpStatus status;

	  public ErrorRecord(Date timestamp, String message, String details) {
	    super();
	    this.timestamp = timestamp;
	    this.message = message;
	    this.details = details;
	  }

	  public Date getTimestamp() {
	    return timestamp;
	  }

	  public String getMessage() {
	    return message;
	  }

	  public String getDetails() {
	    return details;
	  }

	  public HttpStatus getStatus() {
			return status;
		}

		public void setStatus(HttpStatus status) {
			this.status = status;
		}
}

package com.ibm.api.exceptions;

import com.mongodb.MongoException;

public class CustomMongoExceptions extends MongoException {

	public CustomMongoExceptions(int code, String msg) {
		super(code, msg);
		// TODO Auto-generated constructor stub
	}
	
	public CustomMongoExceptions(int code, String msg,Throwable t) {
		super(code, msg,t);
		// TODO Auto-generated constructor stub
	}

	public CustomMongoExceptions(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

	public CustomMongoExceptions(String msg, Throwable t) {
        super(msg, t);
        
    }

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

}

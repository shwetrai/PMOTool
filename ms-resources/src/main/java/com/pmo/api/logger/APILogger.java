package com.pmo.api.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.stereotype.Component;

@Component
public class APILogger {
	
static Logger logger = LogManager.getLogger(APILogger.class) ;
	
	public APILogger() {
		
	}
	
	public void logDebug(String message) {
	    logger.debug(message);
	}
	
	public void logTrace(String message) {
		logger.trace(message);
	}
	
	public void logInfo(String message) {
		logger.info(message);
		
	}
	
	public void logWarn(String message) {
		 logger.warn(message);
	}
	
	public void logError(String message) {
		logger.error(message);
	}
	
	
}

package com.pmo.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ConfigurationProperties("spring.data.mongodb")
public class DBConfiguration {
	
	private String host;
	private String port;
	private String database;
	
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getDatabase() {
		return database;
	}
	public void setDatabase(String database) {
		this.database = database;
	}
	
	@Profile("dev")
	@Bean
	public String devDatabaseConnection() {
		System.out.println("******* Dev Host..."+getHost());
		return "devDatabaseConnection";
	}
	@Profile("prod")
	@Bean
	public String prodDatabaseConnection() {
		System.out.println("******* prod Host..."+getHost());
		return "prodDatabaseConnection";
	}

}

package com.pmo.api.beans;

import java.util.UUID;

//import org.springframework.data.mongodb.core.mapping.Document;
//import org.springframework.data.mongodb.core.mapping.Field;

//@Document(collection = "resource_tracker_comments")
public class Comments {

    private UUID id;
	private String commentsText;
	private String commentsDate;
	private String commentsBy;
	private String resourceId;
	
	
	public String getCommentsDate() {
		return commentsDate;
	}
	public void setCommentsDate(String commentsDate) {
		this.commentsDate = commentsDate;
	}
	public String getCommentsBy() {
		return commentsBy;
	}
	public void setCommentsBy(String commentsBy) {
		this.commentsBy = commentsBy;
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	
	public String getCommentsText() {
		return commentsText;
	}
	public void setCommentsText(String commentsText) {
		this.commentsText = commentsText;
	}
	
	public String getResourceId() {
		return resourceId;
	}
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
		
}

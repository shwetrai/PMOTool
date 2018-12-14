package com.pmo.api.beans;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "resource_tracker_comments")
public class Comments {

	@Id
    private String id;
	
	@Field("commentsText")
	private String commentsText;
	
	@Field("commentsDate")
	private String commentsDate;
	
	
	@Field("commentsBy")
	private String commentsBy;
	
	
//	@Field("resourceId")
//	private String resourceId;
	
	
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getCommentsText() {
		return commentsText;
	}
	public void setCommentsText(String commentsText) {
		this.commentsText = commentsText;
	}
	
//	public String getResourceId() {
//		return resourceId;
//	}
//	public void setResourceId(String resourceId) {
//		this.resourceId = resourceId;
//	}
		
}

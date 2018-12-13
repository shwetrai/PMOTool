package com.pmo.api.beans;

public class ResourceStatus {
	
	private String resourceId;
	private String resourceName;
	private String id;
	private String toolingStatus;
	private String assetsOverview;
	private String inductionStatus;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	public String getResourceId() {
		return resourceId;
	}
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	public String getResourceName() {
		return resourceName;
	}
	public String getAssetsOverview() {
		return assetsOverview;
	}
	public void setAssetsOverview(String assetsOverview) {
		this.assetsOverview = assetsOverview;
	}
	public String getInductionStatus() {
		return inductionStatus;
	}
	public void setInductionStatus(String inductionStatus) {
		this.inductionStatus = inductionStatus;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getToolingStatus() {
		return toolingStatus;
	}
	public void setToolingStatus(String toolingStatus) {
		this.toolingStatus = toolingStatus;
	}
	

}

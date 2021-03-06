package com.pmo.api.beans;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document(collection = "resources_tracker")
public class Resources {
	
	@Id
    private String id;
	
	
//	@Field("resourceId")
	@Indexed(unique = true)
	private String resourceId;
	
	@Field("name")
	private String name;
	
	@Field("creationDate")
	private Date creationDate;
	
	@Field("toolingActivities")
	private ToolingActivity toolingActivities = new ToolingActivity();
	
	@Field("inductionStatus")
	private InductionStatus inductionStatus = new InductionStatus();
	
	@Field("assetOverview")
	private AssetsOverview assetOverview = new AssetsOverview();
	
	@Field("band")
	private String band;
	
	@Field("baseLocation")
	private String baseLocation;
	
	@Field("currentLocation")
	private String currentLocation;

	@Field("commentList")
	private List<Comments> commentList;
	
	@Field("nbsid")
	private String nbsid;
	
	@Field("rate")
	private String rate;
	
	@Field("ibmid")
	private String ibmid;
	
	@Field("role")
	private String role;

	
	public List<Comments> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<Comments> commentList) {
		this.commentList = commentList;
	}
	public String getResourceId() {
		return resourceId;
	}
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	public ToolingActivity getToolingActivities() {
		return toolingActivities;
	}
	public void setToolingActivities(ToolingActivity toolingActivities) {
		this.toolingActivities = toolingActivities;
	}
	public InductionStatus getInductionStatus() {
		return inductionStatus;
	}
	public void setInductionStatus(InductionStatus inductionStatus) {
		this.inductionStatus = inductionStatus;
	}
	public AssetsOverview getAssetOverview() {
		return assetOverview;
	}
	public void setAssetOverview(AssetsOverview assetOverview) {
		this.assetOverview = assetOverview;
	}
	public String getBand() {
		return band;
	}
	public void setBand(String band) {
		this.band = band;
	}
	public String getBaseLocation() {
		return baseLocation;
	}
	public void setBaseLocation(String baseLocation) {
		this.baseLocation = baseLocation;
	}
	public String getCurrentLocation() {
		return currentLocation;
	}
	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}
	public String getNbsid() {
		return nbsid;
	}
	public void setNbsid(String nbsid) {
		this.nbsid = nbsid;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getIbmid() {
		return ibmid;
	}
	public void setIbmid(String ibmid) {
		this.ibmid = ibmid;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	

}

package com.ibm.api.beans;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "cells")
public class Cells {
	
	@Id
	private String id;
	
	@Field("cellId")
	private String cellId;
	
	
	public String getCellId() {
		return cellId;
	}
	public void setCellId(String cellId) {
		this.cellId = cellId;
	}
	
	@Field("cellName")
	private String cellName;
	
	@Field("cellCreationDate")
	private Date cellCreationDate;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCellName() {
		return cellName;
	}
	public void setCellName(String cellName) {
		this.cellName = cellName;
	}
	public Date getCellCreationDate() {
		return cellCreationDate;
	}
	public void setCellCreationDate(Date cellCreationDate) {
		this.cellCreationDate = cellCreationDate;
	}


}

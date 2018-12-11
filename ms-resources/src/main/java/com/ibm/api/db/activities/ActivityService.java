package com.ibm.api.db.activities;

import java.util.Optional;

import com.ibm.api.beans.ToolingActivity;

public interface ActivityService {
	
	void create(ToolingActivity todo);
	Optional<ToolingActivity> findById(String id);

}

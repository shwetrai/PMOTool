package com.ibm.api.repository.resources;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ibm.api.beans.ToolingActivity;

public interface ActivityRepository extends MongoRepository<ToolingActivity, String>{

}

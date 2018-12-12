package com.ibm.api.db.activities;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.api.beans.ToolingActivity;
import com.ibm.api.repository.resources.ActivityRepository;

@Service
public class ActivityServiceImplementation implements ActivityService{

	@Autowired
	private final ActivityRepository activityRepository;

	
	 @Autowired
	 ActivityServiceImplementation(ActivityRepository activityRepository) {
	        this.activityRepository = activityRepository;
	   }


	@Override
	public void create(ToolingActivity todo) {
		
		activityRepository.save(todo);
	}


	@Override
	public Optional<ToolingActivity> findById(String id) {
		return activityRepository.findById(id);
	}
	

}

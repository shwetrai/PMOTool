package com.ibm.api.db.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.api.beans.Resources;
import com.ibm.api.exceptions.CustomMongoExceptions;
import com.ibm.api.exceptions.DuplicateRecordFoundException;
import com.ibm.api.repository.resources.ResourcesRepository;
import com.mongodb.MongoWriteConcernException;

@Service
public class ResourcesServiceImplementation implements ResourcesService {

	@Autowired
	private final ResourcesRepository resourcesRepository;
	
	 @Autowired
	 ResourcesServiceImplementation(ResourcesRepository resourcesRepository) {
	        this.resourcesRepository = resourcesRepository;
	   }

	
	@Override
	public void create(Resources todo) {
		// TODO Auto-generated method stub
//		Roles roles = new Roles();
//		roles.setId("5bd4693772cea12190a2b1eb");
//		todo.setResourceRole(roles);
		
		try {
			resourcesRepository.save(todo);
			
		} catch (Exception e) {
			System.out.println("ResourcesServiceImplementation.create->Error Occurred ==> "+e.getMessage());
		}
		
	}

	
	@Override
	public Optional<Resources> findById(String id) {
		// TODO Auto-generated method stub
		return resourcesRepository.findById(id);
	}


	@Override
	public Optional<Resources> findByResourceName(String resourceName) {
		// TODO Auto-generated method stub
		return resourcesRepository.findByModel(resourceName);
	}


	@Override
	public void update(String id, Resources resource) {
		// TODO Auto-generated method stub
		resource.setId(id);
		Resources r1 = resourcesRepository.save(resource);
		System.out.println("Resource Name......"+r1.getName());
		
	}


	@Override
	public Optional<Resources> findByResourceId(String resourceId) {
		// TODO Auto-generated method stub
		return resourcesRepository.findByResourceId(resourceId);
		
	}


	@Override
	public List<Resources> findAllResources() {
		// TODO Auto-generated method stub
		return resourcesRepository.findAll();
		
	}

}

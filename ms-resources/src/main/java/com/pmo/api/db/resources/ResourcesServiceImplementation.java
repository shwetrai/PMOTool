package com.pmo.api.db.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.DuplicateKeyException;
import com.mongodb.MongoWriteConcernException;
import com.mongodb.MongoWriteException;
import com.pmo.api.beans.Resources;
import com.pmo.api.exceptions.DuplicateRecordFoundException;
import com.pmo.api.repository.resources.ResourcesRepository;

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
			
		try{
			resourcesRepository.save(todo);
		}catch(DuplicateKeyException duplicateKeyException) {
			System.out.println("in duplicateKeyException...");
			throw new DuplicateRecordFoundException("Duplicate Record Found for "+todo.getResourceId());
		}catch(MongoWriteConcernException  mongoWriteConcernException) {
			System.out.println("in MongoException...");
			System.out.println("mongoWriteConcernException.getCode()....."+mongoWriteConcernException.getCode());
			throw new DuplicateRecordFoundException("Duplicate Record Found for "+todo.getResourceId());
			//throw mongoWriteConcernException;
		}catch(MongoWriteException  MongoWriteException) {
			System.out.println("in MongoWriteException...");
			System.out.println("MongoWriteException.getCode()....."+MongoWriteException.getCode());
			throw new DuplicateRecordFoundException("Duplicate Record Found for "+todo.getResourceId());
			//throw mongoWriteConcernException;
		}
		catch(Exception ex) {
			System.out.println("in Exception...");
			throw ex;
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

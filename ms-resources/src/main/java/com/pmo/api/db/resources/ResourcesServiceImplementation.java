package com.pmo.api.db.resources;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pmo.api.beans.Comments;
import com.pmo.api.beans.Resources;
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
		}catch(Exception ex) {
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
		resource.setId(id);
		
		 List<Comments> commentList = resource.getCommentList();
		 System.out.println("ResourcesServiceImplementation...commentList is null "+(resource.getCommentList()==null));
		 
		 if(commentList != null && commentList.size()>0) {
			 for (Comments commentItem: commentList) {
						commentItem.setId(UUID.randomUUID());
			}
			 System.out.println("ResourcesServiceImplementation...comment text -->"+commentList.get(0).getCommentsText());
		 }else {
			 System.out.println("ResourcesServiceImplementation...Comments1..."+commentList);
		 }
		
		 Optional<Resources> existingResource = resourcesRepository.findByResourceId(resource.getResourceId());
			if (existingResource.isPresent()) {
				Resources existingData = existingResource.get();
				List<Comments> existingComments = existingData.getCommentList();
				List<Comments> inputCommentsList = resource.getCommentList();
				if(inputCommentsList != null && inputCommentsList.size()>0) {
					
					for (Comments commentItem: inputCommentsList) {
						if(existingComments != null && existingComments.size()>0) {
							existingComments.add(commentItem);
							resource.setCommentList(existingComments);
						}
					}
				}
			}
			
			
			
		Resources r1 = resourcesRepository.save(resource);
		System.out.println("Resource Name......"+r1.getName());
		
	}


	@Override
	public Optional<Resources> findByResourceId(String resourceId) {
		return resourcesRepository.findByResourceId(resourceId);
		
	}


	@Override
	public List<Resources> findAllResources() {
		return resourcesRepository.findAll();
		
	}

}

package com.pmo.api.db.resources;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pmo.api.beans.Comments;
import com.pmo.api.beans.Resources;
import com.pmo.api.logger.APILogger;
import com.pmo.api.repository.resources.ResourcesRepository;

@Service
public class ResourcesServiceImplementation implements ResourcesService {

	@Autowired
	private ResourcesRepository resourcesRepository;
	

	@Autowired
	private APILogger apiLogger;
	
	 @Autowired
	 ResourcesServiceImplementation(ResourcesRepository resourcesRepository) {
	        this.resourcesRepository = resourcesRepository;
	 }
	 
	 public ResourcesServiceImplementation(APILogger apiLogger) {
		 this.apiLogger = apiLogger;
	 }
	 
	
	@Override
	public void create(Resources todo) {
		apiLogger.logInfo("<-Entering ResourcesServiceImplementation.create->"+todo.getResourceId());
		try{
			
			resourcesRepository.save(todo);
		}catch(Exception ex) {
			apiLogger.logError("ResourcesServiceImplementation.create->"+ex.getMessage());
			System.out.println("Duplicate =="+ex.getMessage().contains("E11000"));
			throw ex;
		}
		apiLogger.logInfo("<-Exiting from ResourcesServiceImplementation.create->");
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
		apiLogger.logInfo("<-Entering ResourcesServiceImplementation.update->"+id);
		Resources r1;
		try {
			
			resource.setId(id);
			
			 List<Comments> commentList = resource.getCommentList();
			 apiLogger.logInfo("ResourcesServiceImplementation...commentList is null "+(resource.getCommentList()==null));
			 
			 if(commentList != null && commentList.size()>0) {
				 for (Comments commentItem: commentList) {
							commentItem.setId(UUID.randomUUID());
				}
				 apiLogger.logInfo("ResourcesServiceImplementation...comment text -->"+commentList.get(0).getCommentsText());
			 }else {
				 apiLogger.logInfo("ResourcesServiceImplementation...Comments1..."+commentList);
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
				
				
				
			r1 = resourcesRepository.save(resource);
			apiLogger.logInfo("Exiting ResourcesServiceImplementation.update->"+r1.getName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			apiLogger.logError("ResourcesServiceImplementation.update->"+e.getMessage());
			
		}
		
		
	}


	@Override
	public Optional<Resources> findByResourceId(String resourceId) {
		apiLogger.logInfo("Entered into ResourcesServiceImplementation.findByResourceId.resourceId->"+resourceId);
		Optional<Resources> resources;
		try {
			resources = resourcesRepository.findByResourceId(resourceId);
			apiLogger.logInfo("Exiting from ResourcesServiceImplementation.findByResourceId.resourceId->"+resourceId);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			apiLogger.logInfo("ResourcesServiceImplementation.findByResourceId.Exception ->"+resourceId+","+e.getMessage());
			throw e;
		}
		
		return resources;
		
	}


	@Override
	public List<Resources> findAllResources() {
		apiLogger.logInfo("Entered into ResourcesServiceImplementation.findAllResources");
		return resourcesRepository.findAll();
		
	}

}

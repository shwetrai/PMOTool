package com.pmo.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pmo.api.beans.AssetsOverview;
import com.pmo.api.beans.Comments;
import com.pmo.api.beans.InductionStatus;
import com.pmo.api.beans.ResourceStatus;
import com.pmo.api.beans.Resources;
import com.pmo.api.beans.ToolingActivity;
import com.pmo.api.db.comments.CommentsServiceImplementation;
import com.pmo.api.db.resources.ResourcesServiceImplementation;
import com.pmo.api.exceptions.DuplicateRecordFoundException;
import com.pmo.api.exceptions.ResourceNotFoundException;



@RestController
@RequestMapping("/isp/v1")
public class ResourceActivityManagement {

	@Autowired
	ResourcesServiceImplementation resourcesServiceImplementation;

	@Autowired
    CommentsServiceImplementation commentsServiceImplementation;
 
	  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value="/resources/create")
	    public void create(@RequestBody Resources resources) {
		 
		 System.out.println("**** Entered into create() ******");
		 System.out.println("Creating resource for :"+resources.getName());
		 
		 resources.setCreationDate(new Date());
		 
		 System.out.println("ResourceId.."+resources.getResourceId());
		 System.out.println("Name.."+resources.getName());
		 
		 
		 try{
			 	
			 List<Comments> commentList = resources.getCommentList();
			 System.out.println("commentList is null "+(resources.getCommentList()==null));
			 
			 if(commentList != null && commentList.size()>0) {
//				 commentsServiceImplementation.createAll(commentList);
				 for (Comments commentItem: commentList) {
							commentItem.setId(UUID.randomUUID());
				}
				 
				 System.out.println("comment text -->"+commentList.get(0).getCommentsText());
				 
			 }else {
				 System.out.println("Comments1..."+commentList);
			 }
			 
			 resources.setCommentList(commentList);
			 resourcesServiceImplementation.create(resources);
			 
		 }catch(DuplicateRecordFoundException drfe) {
			 System.out.println("Dup found");
			 throw drfe;
		 }catch(org.springframework.dao.DuplicateKeyException ex) {
			 System.out.println("DuplicateKeyException found...."+ex.getLocalizedMessage());
			 throw ex;
		 }catch(Exception ex) {
			 System.out.println("ex found...."+ex.getLocalizedMessage());
			 throw ex;
		 }
		 
		 System.out.println("Exiting from create resource ****");
	  }
	 
		 
	 @GetMapping(value="/greet/{name}")
	    public String hello(@PathVariable String name) {
	        return "hello "+name;
	    }
	 
	 @GetMapping(value="/resources/{resourceId}")
	    public Optional<Resources> getResourcesByResourceId(@PathVariable String resourceId) {
		    System.out.println("**** Entered into getResourcesByResourceId() ******");
	        Optional<Resources>  resource = resourcesServiceImplementation.findByResourceId(resourceId);
	        System.out.println("Internal ID:"+resource.get().getId());
	        System.out.println("ResourceId :"+resource.get().getResourceId());
	        
	        if (!resource.isPresent())
	        	throw new ResourceNotFoundException("Record not found for -"+resourceId);
	        
			return resource;
	    }
	 
	 @GetMapping(value="/resources/name/{resourceName}")
	    public Optional<Resources> getResourceDetailsByName(@PathVariable String resourceName) {
		 System.out.println("**** Entered into getResourceDetailsByName() ******");
		 Optional<Resources> resourceResult = resourcesServiceImplementation.findByResourceName(resourceName);
		
		 System.out.println("Resource Result = "+resourceResult);
			 if (!resourceResult.isPresent()) {
				 System.out.println("Throwing exception....");
				 throw new ResourceNotFoundException("Record not found for '"+resourceName+"'");
			 }
			return resourceResult;
	    }
	 
	  
	 
	 
	 @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value="/resources/{id}")
	    public void update(@PathVariable String id, @RequestBody Resources resources) {
		 
		 System.out.println("**** Entered into ResourceActivityManagement.update() ******");

		 resources.setCreationDate(new Date());
//		 List<Comments> commentList = resources.getCommentList();
//
//		 System.out.println("commentList is null "+(resources.getCommentList()==null));
//		 
//		 if(commentList != null && commentList.size()>0) {
//			 commentsServiceImplementation.createAll(commentList);
//			 
//			 System.out.println("comment text -->"+commentList.get(0).getCommentsText());
//		 }else {
//			 System.out.println("Comments1..."+commentList);
//		 }
//		 
//		 resources.setCommentList(commentList);
		 
		
		 
		 resourcesServiceImplementation.update(id, resources);
		 
		 System.out.println("**** Exiting  from ResourceActivityManagement.update() ******");
	  }
	 
	 @GetMapping(value="/resources/all")
	    public List<ResourceStatus> getAllResourcesStatus() {
		 System.out.println("**** Entered into getAllResourcesStatus() ******");
		 ResourceStatus resourceStatus = null;
		 List<ResourceStatus> resourceList = new ArrayList<ResourceStatus> ();
		 
		 List<Resources> resources = resourcesServiceImplementation.findAllResources();
		 
		 if(resources != null && resources.size()>=0) {
			 System.out.println("All Resource - Record Found...size===>"+resources.size());
			 for (Resources resourceItem: resources) {
				 
				 resourceStatus = new ResourceStatus();
				 
				 resourceStatus.setResourceId(resourceItem.getResourceId());
				 resourceStatus.setResourceName(resourceItem.getName());
				 resourceStatus.setId(resourceItem.getId());
				 
				 AssetsOverview assetsOverview = resourceItem.getAssetOverview();
				 ToolingActivity toolingActivities = resourceItem.getToolingActivities();
				 InductionStatus inductionStatus =resourceItem.getInductionStatus();
				 
				 
				 
				 if(	
						 "Yes".equalsIgnoreCase(assetsOverview.getCodeCoverage()) &&
						  "Yes".equalsIgnoreCase(assetsOverview.getDataDrivenTesting()) &&
						 "Yes".equalsIgnoreCase(assetsOverview.getEsqlGenerator()) && 
						 "Yes".equalsIgnoreCase(assetsOverview.getLoggingFramework())
						 
					) {
					 	resourceStatus.setAssetsOverview("Yes");
				 	}else {
				 		resourceStatus.setAssetsOverview("No");
				 	}
				 
				 if(
						 "Yes".equalsIgnoreCase(toolingActivities.getAdcTool()) && "Yes".equalsIgnoreCase(toolingActivities.getAlm()) &&
						 "Yes".equalsIgnoreCase(toolingActivities.getArdTool()) && "Yes".equalsIgnoreCase(toolingActivities.getDb2()) &&
						 "Yes".equalsIgnoreCase(toolingActivities.getDb2explorer()) && "Yes".equalsIgnoreCase(toolingActivities.getIib()) &&
						 "Yes".equalsIgnoreCase(toolingActivities.getJenkins()) && "Yes".equalsIgnoreCase(toolingActivities.getMq()) &&
						 "Yes".equalsIgnoreCase(toolingActivities.getMqexplorer()) && "Yes".equalsIgnoreCase(toolingActivities.getPutty()) &&
						 "Yes".equalsIgnoreCase(toolingActivities.getReadyAPI()) 						 
				   ) {
					 resourceStatus.setToolingStatus("Yes");
				 }else {
					 resourceStatus.setToolingStatus("No");
				 }
				 if(
						 "Yes".equalsIgnoreCase(inductionStatus.getAgileTraining()) &&
						 "Yes".equalsIgnoreCase(inductionStatus.getArchitecturalInduction()) &&
						 "Yes".equalsIgnoreCase(inductionStatus.getClientInduction()) &&
						 "Yes".equalsIgnoreCase(inductionStatus.getCodeWalkthrough()) &&
						 "Yes".equalsIgnoreCase(inductionStatus.getCommonPatterns()) &&
						 "Yes".equalsIgnoreCase(inductionStatus.getDevTech()) &&
						 "Yes".equalsIgnoreCase(inductionStatus.getSisInduction()) &&
						 "Yes".equalsIgnoreCase(inductionStatus.getTestingFramework())
						 
				   ) {
					 resourceStatus.setInductionStatus("Yes");
					 
				 }else {
					 resourceStatus.setInductionStatus("No");
				 }
				 resourceList.add(resourceStatus);
		      } // end of For Loop
		 } else {
			 System.out.println("All Resource - No Record Found");
		 }
		return resourceList;
		 
	  }
	 	
}

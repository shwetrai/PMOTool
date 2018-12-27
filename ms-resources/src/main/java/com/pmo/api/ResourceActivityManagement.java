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
import com.pmo.api.logger.APILogger;



@RestController
@RequestMapping("/isp/v1")
public class ResourceActivityManagement {

	@Autowired
	ResourcesServiceImplementation resourcesServiceImplementation;
	
	@Autowired
	APILogger apiLogger;
	

	@Autowired
    CommentsServiceImplementation commentsServiceImplementation;

 
	  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value="/resources/create")
	   public void create(@RequestBody Resources resources) {
		 
		 apiLogger.logInfo("Entered into ResourceActivityManagement.create().Id->"+resources.getResourceId());
		 
		 resources.setCreationDate(new Date());
		 
		 try{
			 	
			 List<Comments> commentList = resources.getCommentList();
			 apiLogger.logInfo("commentList is null "+(resources.getCommentList()==null));
			 
			 if(commentList != null && commentList.size()>0) {
				 for (Comments commentItem: commentList) {
							commentItem.setId(UUID.randomUUID());
				}
				 apiLogger.logInfo("comment text -->"+commentList.get(0).getCommentsText());
				 
			 }else {
				 apiLogger.logInfo("Comments1..."+commentList);
			 }
			 
			 resources.setCommentList(commentList);
			 resourcesServiceImplementation.create(resources);
			 
		 }catch(org.springframework.dao.DuplicateKeyException ex) {
			 apiLogger.logError("Exception Block_22->ResourceActivityManagement.create().DuplicateKeyException->"+ex.getMessage());
			 throw ex;
		 }catch(Exception ex) {
			 apiLogger.logError("Exception Block_33->ResourceActivityManagement.create().Exception->"+ex.getMessage());
			 throw ex;
		 }
		 
		 apiLogger.logInfo("Exiting from ResourceActivityManagement.create().ID->"+resources.getResourceId());
	  }
	 
		 
	 
	 @GetMapping(value="/greet/{name}")
	 public String hello(@PathVariable String name) {
		 	apiLogger.logInfo("inf.."+name);
		 	apiLogger.logDebug("debug testing..."+name);
	        return "hello "+name;
	 }
	 
	 @GetMapping(value="/resources/{resourceId}")
	    public Optional<Resources> getResourcesByResourceId(@PathVariable String resourceId) {
		    apiLogger.logInfo("Entered into ResourceActivityManagement.getResourcesByResourceId()->"+resourceId);
	        Optional<Resources>  resource = resourcesServiceImplementation.findByResourceId(resourceId);
	        System.out.println("Resources.........."+resource);
	        if (!resource.isPresent()) {
	        	throw new ResourceNotFoundException("Record not found for -"+resourceId);
	        }else {
	        	System.out.println("Record Found for "+resource.get().getName());
	        }
	        apiLogger.logInfo("Exiting from ResourceActivityManagement.getResourcesByResourceId()->"+resourceId);
			return resource;
	    }
	 
	 @GetMapping(value="/resources/name/{resourceName}")
	    public Optional<Resources> getResourceDetailsByName(@PathVariable String resourceName) {
		 apiLogger.logInfo("Entered into ResourceActivityManagement.getResourceDetailsByName().resourceName->"+resourceName);
			 Optional<Resources> resourceResult = resourcesServiceImplementation.findByResourceName(resourceName);
			 if (!resourceResult.isPresent()) {
				 apiLogger.logError("ResourceActivityManagement.getResourceDetailsByName().ResourceNotFoundException for Resource Name - "+resourceName);
				 throw new ResourceNotFoundException("Record not found for '"+resourceName+"'");
			 }
		 apiLogger.logInfo("Exiting from ResourceActivityManagement.getResourceDetailsByName()->"+resourceName);
			return resourceResult;
	    }
	 
	  
	 
	 
	 @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value="/resources/{id}")
	    public void update(@PathVariable String id, @RequestBody Resources resources) {
		 
		 apiLogger.logInfo("Entered into ResourceActivityManagement.update().Id->"+resources.getResourceId());

		 resources.setCreationDate(new Date());
		 resourcesServiceImplementation.update(id, resources);
		 
		 apiLogger.logInfo("Exiting from ResourceActivityManagement.update().Id->"+resources.getResourceId());
	  }
	 
	 @GetMapping(value="/resources/all")
	    public List<ResourceStatus> getAllResourcesStatus() {
		 apiLogger.logInfo("Entered into ResourceActivityManagement.getAllResourcesStatus()");
		 ResourceStatus resourceStatus = null;
		 List<ResourceStatus> resourceList = new ArrayList<ResourceStatus> ();
		 
		 try {
			List<Resources> resources = resourcesServiceImplementation.findAllResources();
			 
			 if(resources != null && resources.size()>=0) {
				 apiLogger.logInfo("All Resource - Record Found...size===>"+resources.size());
				 for (Resources resourceItem: resources) {
					 
					 resourceStatus = new ResourceStatus();
					 
					 resourceStatus.setResourceId(resourceItem.getResourceId());
					 resourceStatus.setResourceName(resourceItem.getName());
					 resourceStatus.setId(resourceItem.getId());
					 
					 AssetsOverview assetsOverview = resourceItem.getAssetOverview();
					 ToolingActivity toolingActivities = resourceItem.getToolingActivities();
					 InductionStatus inductionStatus =resourceItem.getInductionStatus();
					 
					 
					 
					 if(	
							( "Yes".equalsIgnoreCase(assetsOverview.getCodeCoverage()) || "NA".equalsIgnoreCase(assetsOverview.getCodeCoverage()))&&
							 ( "Yes".equalsIgnoreCase(assetsOverview.getDataDrivenTesting()) || "NA".equalsIgnoreCase(assetsOverview.getDataDrivenTesting())) &&
							 ( "Yes".equalsIgnoreCase(assetsOverview.getEsqlGenerator()) || "NA".equalsIgnoreCase(assetsOverview.getEsqlGenerator())) &&
							( "Yes".equalsIgnoreCase(assetsOverview.getLoggingFramework()) || "NA".equalsIgnoreCase(assetsOverview.getLoggingFramework()))
							 
						) {
						 	resourceStatus.setAssetsOverview("Yes");
					 	}else {
					 		resourceStatus.setAssetsOverview("No");
					 	}
					 
					 if(
							 ("Yes".equalsIgnoreCase(toolingActivities.getAdcTool()) || "NA".equalsIgnoreCase(toolingActivities.getAdcTool())) && 
							 ("Yes".equalsIgnoreCase(toolingActivities.getAlm()) || "NA".equalsIgnoreCase(toolingActivities.getAlm())) && 
							 ("Yes".equalsIgnoreCase(toolingActivities.getArdTool()) || "NA".equalsIgnoreCase(toolingActivities.getArdTool())) &&
							 ( "Yes".equalsIgnoreCase(toolingActivities.getDb2()) || "NA".equalsIgnoreCase(toolingActivities.getDb2())) &&
							 ("Yes".equalsIgnoreCase(toolingActivities.getDb2explorer()) || "NA".equalsIgnoreCase(toolingActivities.getDb2explorer())) &&
							 ( "Yes".equalsIgnoreCase(toolingActivities.getIib()) || "NA".equalsIgnoreCase(toolingActivities.getIib())) &&
							 ("Yes".equalsIgnoreCase(toolingActivities.getJenkins()) || "NA".equalsIgnoreCase(toolingActivities.getJenkins())) &&
							 ("Yes".equalsIgnoreCase(toolingActivities.getMq()) || "NA".equalsIgnoreCase(toolingActivities.getMq())) &&
							 ("Yes".equalsIgnoreCase(toolingActivities.getMqexplorer()) || "NA".equalsIgnoreCase(toolingActivities.getMqexplorer())) &&
							 ("Yes".equalsIgnoreCase(toolingActivities.getPutty()) || "NA".equalsIgnoreCase(toolingActivities.getPutty())) &&
							 ("Yes".equalsIgnoreCase(toolingActivities.getReadyAPI()) || "NA".equalsIgnoreCase(toolingActivities.getReadyAPI())) 						 
					   ) {
						 resourceStatus.setToolingStatus("Yes");
					 }else {
						 resourceStatus.setToolingStatus("No");
					 }
					 if(
							 ("Yes".equalsIgnoreCase(inductionStatus.getAgileTraining()) || "NA".equalsIgnoreCase(inductionStatus.getAgileTraining())) &&
							 ("Yes".equalsIgnoreCase(inductionStatus.getArchitecturalInduction()) || "NA".equalsIgnoreCase(inductionStatus.getArchitecturalInduction())) &&
							 ("Yes".equalsIgnoreCase(inductionStatus.getClientInduction()) || "NA".equalsIgnoreCase(inductionStatus.getClientInduction())) &&
							 ("Yes".equalsIgnoreCase(inductionStatus.getCodeWalkthrough()) || "NA".equalsIgnoreCase(inductionStatus.getCodeWalkthrough())) &&
							 ("Yes".equalsIgnoreCase(inductionStatus.getCommonPatterns()) || "NA".equalsIgnoreCase(inductionStatus.getCommonPatterns())) &&
							 ("Yes".equalsIgnoreCase(inductionStatus.getDevTech()) || "NA".equalsIgnoreCase(inductionStatus.getDevTech())) &&
							 ("Yes".equalsIgnoreCase(inductionStatus.getSisInduction()) || "NA".equalsIgnoreCase(inductionStatus.getSisInduction())) &&
							 ("Yes".equalsIgnoreCase(inductionStatus.getTestingFramework()) || "NA".equalsIgnoreCase(inductionStatus.getTestingFramework())) &&
							 ("Yes".equalsIgnoreCase(inductionStatus.getBuildProcess()) || "NA".equalsIgnoreCase(inductionStatus.getBuildProcess())) &&
							 ("Yes".equalsIgnoreCase(inductionStatus.getGovernanceTool()) || "NA".equalsIgnoreCase(inductionStatus.getGovernanceTool())) &&
							 ("Yes".equalsIgnoreCase(inductionStatus.getCicd()) || "NA".equalsIgnoreCase(inductionStatus.getCicd())) &&
							 ("Yes".equalsIgnoreCase(inductionStatus.getCodingStd()) || "NA".equalsIgnoreCase(inductionStatus.getCodingStd()))
							 
					   ) {
						 resourceStatus.setInductionStatus("Yes");
						 
					 }else {
						 resourceStatus.setInductionStatus("No");
					 }
					 resourceList.add(resourceStatus);
			      } // end of For Loop
			 } else {
				 apiLogger.logInfo("All Resource - No Record Found");
			 }
		} catch (Exception e) {
			apiLogger.logError("getAllResourcesStatus() -> Some Error Occurred - "+e.getMessage());
			resourceList=null;
			e.printStackTrace();
		}
		 apiLogger.logInfo("Exiting from ResourceActivityManagement.getAllResourcesStatus");
		return resourceList;
		 
	  }
	 	
	 public ResourceActivityManagement() {
		 if(this.apiLogger==null)  this.apiLogger= new APILogger();
	 }
	 
}

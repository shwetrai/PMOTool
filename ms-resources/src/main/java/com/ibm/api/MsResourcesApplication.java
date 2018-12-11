package com.ibm.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.api.beans.AssetsOverview;
import com.ibm.api.beans.Cells;
import com.ibm.api.beans.InductionStatus;
import com.ibm.api.beans.ResourceStatus;
import com.ibm.api.beans.Resources;
import com.ibm.api.beans.Roles;
import com.ibm.api.beans.ToolingActivity;
import com.ibm.api.db.cells.CellServicesImplementation;
import com.ibm.api.db.resources.ResourcesServiceImplementation;
import com.ibm.api.db.roles.RoleServiceImplementation;
import com.ibm.api.exceptions.DuplicateRecordFoundException;
import com.ibm.api.exceptions.ResourceNotFoundException;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@RestController
@RequestMapping("/isp/v1")
public class MsResourcesApplication {

	
	@Autowired	
	CellServicesImplementation cellServicesImplementation;
	
	@Autowired	
	RoleServiceImplementation roleServiceImplementation;
	
	@Autowired
	ResourcesServiceImplementation resourcesServiceImplementation;
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(MsResourcesApplication.class, args);
		System.out.println("Context................."+SpringApplication.DEFAULT_CONTEXT_CLASS);
	}
	
	 @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, value="/cell/create")
	    public void create(@RequestBody Cells cellDetails) {
		 cellDetails.setCellCreationDate(new Date());
		 cellServicesImplementation.create(cellDetails);
	  }
	 
	 @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, value="/roles/create")
	    public void create(@RequestBody Roles roles) {
		 roles.setCreationDate(new Date());
		 roleServiceImplementation.create(roles);
	  }
	 
	 @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, value="/resources/create")
	    public void create(@RequestBody Resources resources) {
		 
		 System.out.println("**** Entered into create() ******");
		 
		 System.out.println("Creating resource for :"+resources.getName());
		 resources.setCreationDate(new Date());
		 System.out.println("Band.."+resources.getBand());
		 System.out.println("Base.."+resources.getBaseLocation());
		 System.out.println("getCodeCoverage.."+resources.getAssetOverview().getCodeCoverage());
		 System.out.println("getArchitecturalInduction.."+resources.getInductionStatus().getArchitecturalInduction());
		 System.out.println("Tooling Activities.ADC Tool.."+resources.getToolingActivities().getAdcTool());
		 
		 try{
			 resourcesServiceImplementation.create(resources);
		 }catch(DuplicateRecordFoundException drfe) {
			 throw drfe;
		 }catch(Exception ex) {
			 throw ex;
		 }
		 
		 System.out.println("Exiting from create resource ****");
	  }
	 
	 @RequestMapping(method=RequestMethod.GET, value="/cell/{id}")
	    public Optional<Cells> getCells(@PathVariable String id) {
	        return cellServicesImplementation.findById(id);
	    }
	 
	 @RequestMapping(method=RequestMethod.GET, value="/greet/{name}")
	    public String hello(@PathVariable String name) {
	        return "hello "+name;
	    }
	 
	 @RequestMapping(method=RequestMethod.GET, value="/roles/{id}")
	    public Optional<Roles> getRoles(@PathVariable String id) {
	        return roleServiceImplementation.findById(id);
	    }
	 
//	 @RequestMapping(method=RequestMethod.GET, value="/resources/{id}")
//	    public Optional<Resources> getResourcesByInternalId(@PathVariable String id) {
//		 
//	        Optional<Resources>  resource = resourcesServiceImplementation.findById(id);
//	        
//	        if (!resource.isPresent())
//	            throw new ResourceNotFoundException("id-" + id);
//	        
//			return resource;
//	        
//	        
//	    }
	 
	 @RequestMapping(method=RequestMethod.GET, value="/resources/{resourceId}")
	    public Optional<Resources> getResourcesByResourceId(@PathVariable String resourceId) {
		    System.out.println("**** Entered into getResourcesByResourceId() ******");
	        Optional<Resources>  resource = resourcesServiceImplementation.findByResourceId(resourceId);
	        System.out.println("Internal ID:"+resource.get().getId());
	        System.out.println("ResourceId :"+resource.get().getResourceId());
	        
	        if (!resource.isPresent())
	            throw new ResourceNotFoundException("id-" + resourceId);
	        
			return resource;
	    }
	 
	 @RequestMapping(method=RequestMethod.GET, value="/resources/name/{resourceName}")
	    public Optional<Resources> getResourceDetailsByName(@PathVariable String resourceName) {
		 System.out.println("**** Entered into getResourceDetailsByName() ******");
		 return resourcesServiceImplementation.findByResourceName(resourceName);
	    }
	 
	  
	 
	 
	 @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, value="/resources/{id}")
	    public void update(@PathVariable String id, @RequestBody Resources resources) {
		 System.out.println("**** Entered into update() ******");
		 resources.setCreationDate(new Date());
		 resourcesServiceImplementation.update(id, resources);
		 
	  }
	 
	 @RequestMapping(method = RequestMethod.GET, value="/resources/all")
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
						  "Yes".equalsIgnoreCase(assetsOverview.getCodeReview()) &&
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
	 
	 	@ExceptionHandler
	    @ResponseStatus(HttpStatus.NOT_FOUND)
	    public void handleTodoNotFound(Exception ex) {
	       System.out.println(ex.getMessage());
	}
	
}

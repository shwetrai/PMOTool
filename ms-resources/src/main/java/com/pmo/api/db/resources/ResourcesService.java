package com.pmo.api.db.resources;

import java.util.List;
import java.util.Optional;


import com.pmo.api.beans.Resources;

public interface ResourcesService {
	
	void create(Resources todo);
	Optional<Resources> findById(String id);
	Optional<Resources> findByResourceName(String resourceName);
	void update(String id, Resources resource);
	Optional<Resources> findByResourceId(String resourceId);
	List<Resources> findAllResources();

}

package com.ibm.api.repository.resources;



import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.ibm.api.beans.Resources;

public interface ResourcesRepository extends MongoRepository<Resources, String>{
	
	@Override
	Optional<Resources> findById(String id);
	
    @Query("{ 'name' : ?0 }")
    Optional<Resources> findByModel(String name);
    
    @Query("{ 'resourceId' : ?0 }")
    Optional<Resources> findByResourceId(String name);

    @Override
    void delete(Resources deleted);


}

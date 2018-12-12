package com.ibm.api.repository.roles;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ibm.api.beans.Roles;

public interface RolesRepository  extends MongoRepository<Roles, String>{

	@Override
	Optional<Roles> findById(String id);

    @Override
    void delete(Roles deleted);
	
}

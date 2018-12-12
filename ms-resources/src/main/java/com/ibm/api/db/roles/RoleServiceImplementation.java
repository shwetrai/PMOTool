package com.ibm.api.db.roles;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.api.beans.Roles;
import com.ibm.api.repository.roles.RolesRepository;

@Service
public class RoleServiceImplementation implements  RoleService{
	
	private final RolesRepository rolesRepository;
	
	 @Autowired
	 RoleServiceImplementation(RolesRepository rolesRepository) {
	        this.rolesRepository = rolesRepository;
	   }

	@Override
	public void create(Roles todo) {
		// TODO Auto-generated method stub
		rolesRepository.save(todo);
		
	}

	@Override
	public Optional<Roles> findById(String id) {
		return rolesRepository.findById(id);
	}
	
	

}

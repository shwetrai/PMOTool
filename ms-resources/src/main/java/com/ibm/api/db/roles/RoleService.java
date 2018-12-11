package com.ibm.api.db.roles;

import java.util.Optional;

import com.ibm.api.beans.Roles;

public interface RoleService {
	
	void create(Roles todo);
	Optional<Roles> findById(String id);

}

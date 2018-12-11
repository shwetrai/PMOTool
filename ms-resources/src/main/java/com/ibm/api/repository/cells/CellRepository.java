package com.ibm.api.repository.cells;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ibm.api.beans.Cells;

public interface CellRepository extends MongoRepository<Cells, String> {
	
	@Override
	Optional<Cells> findById(String id);

    @Override
    void delete(Cells deleted);

}

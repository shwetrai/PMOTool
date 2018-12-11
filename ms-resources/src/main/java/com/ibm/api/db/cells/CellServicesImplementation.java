package com.ibm.api.db.cells;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.api.beans.Cells;
import com.ibm.api.repository.cells.CellRepository;

@Service
public class CellServicesImplementation implements CellsServices {

	
	private final CellRepository cellsRepository;
	
	 @Autowired
	 CellServicesImplementation(CellRepository cellsRepository) {
	        this.cellsRepository = cellsRepository;
	   }
	
	@Override
	public void create(Cells todo) {
		// TODO Auto-generated method stub
		cellsRepository.save(todo);

	}

	@Override
	public Optional<Cells> findById(String id) {
		// TODO Auto-generated method stub
		return cellsRepository.findById(id);
	}

}

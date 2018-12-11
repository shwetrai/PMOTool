package com.ibm.api.db.cells;

import java.util.Optional;

import com.ibm.api.beans.Cells;

public interface CellsServices {
	
	void create(Cells todo);
	Optional<Cells> findById(String id);

}

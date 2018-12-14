package com.pmo.api.repository.comments;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.pmo.api.beans.Comments;


public interface CommentsRepository extends MongoRepository<Comments, String>{
	
	@Override
	Optional<Comments> findById(String id);
	
	@Query("{ 'resourceTrackerId' : ?0 }")
    List<Comments> findByCommentsByResourceTrackerId(String resourceId);
	
	void delete(Comments deleted);

}

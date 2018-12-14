package com.pmo.api.db.comments;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pmo.api.beans.Comments;
import com.pmo.api.repository.comments.CommentsRepository;

@Service
public class CommentsServiceImplementation implements CommentsService {

	@Autowired
	private final CommentsRepository commentsRepository;
	
	 @Autowired
	 CommentsServiceImplementation(CommentsRepository commentsRepository) {
	        this.commentsRepository = commentsRepository;
	   }

	 
	@Override
	public void create(Comments todo) {
		// TODO Auto-generated method stub
		commentsRepository.save(todo);

	}
	

	@Override
	public List<Comments> findCommentsByResourceTrackerId(String resourceId) {
		// TODO Auto-generated method stub
		return commentsRepository.findByCommentsByResourceTrackerId(resourceId);
	}


	@Override
	public void createAll(List<Comments> commentsList) {
		// TODO Auto-generated method stub
		commentsRepository.saveAll(commentsList);
		
	}


}

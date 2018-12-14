package com.pmo.api.db.comments;

import java.util.List;

import com.pmo.api.beans.Comments;

public interface CommentsService {
	
	void create(Comments todo);
	
	void createAll(List<Comments> commentsList);
	
	List<Comments> findCommentsByResourceTrackerId(String resourceId);

}

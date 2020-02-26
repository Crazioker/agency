package gdufs.agency.service;

import java.util.List;

import gdufs.agency.entity.Comment;

public interface CommentService {
	int addComment(Comment comment);

	List<Comment> getComments(Integer indentId);
}

package gdufs.agency.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gdufs.agency.dao.CommentMapper;
import gdufs.agency.entity.Comment;
import gdufs.agency.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private CommentMapper commentDao;
	
	@Override
	public int addComment(Comment comment) {
		// TODO Auto-generated method stub
		return commentDao.insert(comment);
	}

	@Override
	public List<Comment> getComments(Integer indentId) {
		// TODO Auto-generated method stub
		return commentDao.getCommentsByIndentId(indentId);
	}

}

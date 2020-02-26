package gdufs.agency.service;

import java.util.List;

import gdufs.agency.entity.Indent;
import gdufs.agency.entity.IndentAccept;

public interface IndentService {

	boolean addIntent(int type,float price,String description,String address,
			int state,String publishId,String publishTime,String planTime);
	
	boolean updateState(Indent indent) ;
	
	List<Indent> getIndents(String publishId);
	Indent getIndentById(Integer intentId);
	List<Indent> getIndentsByType(String studentId,Integer type);
	
	boolean deleteIndent(Integer indentId);

	boolean updateIndent(int indentId);
	
	boolean updateIndentByPublishHasAccepted(int indentId);
	
//	List<Indent> getIndentsByPublish(String publishId);
	List<IndentAccept> getIndentsByPublish(String publishId);
}

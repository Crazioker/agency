package gdufs.agency.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.type.DeclaredType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gdufs.agency.dao.AcceptanceMapper;
import gdufs.agency.dao.IndentMapper;
import gdufs.agency.entity.Acceptance;
import gdufs.agency.entity.Indent;
import gdufs.agency.entity.IndentAccept;
import gdufs.agency.service.IndentService;

@Service
public class IndentServiceImpl implements IndentService{

	@Autowired
	private IndentMapper indentDao;
	@Autowired
	private AcceptanceMapper acceptanceDao;

	@Override
	public boolean addIntent(int type, float price, String description, String address, int state, String publishId,
			String publishTime, String planTime) {
		// TODO Auto-generated method stub
		Indent indent = new Indent();
		indent.setType(type);
		indent.setPrice(price);
		indent.setDescription(description);
		indent.setAddress(address);
		indent.setState(state);
		indent.setPublishid(publishId);
		indent.setPublishtime(publishTime);
		indent.setPlantime(planTime);
		int result = indentDao.insert(indent);
		if (result == 1) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public List<Indent> getIndents(String publishId) {
		// TODO Auto-generated method stub
		return indentDao.selectByState(publishId);
	}

	@Override
	public Indent getIndentById(Integer indentId) {
		// TODO Auto-generated method stub
		return indentDao.selectByPrimaryKey(indentId);
	}

	@Override
	public List<Indent> getIndentsByType(String studentId, Integer type) {
		// TODO Auto-generated method stub
		
		return indentDao.selectIndentsByType(studentId, type);
	}

	@Override
	public boolean updateState(Indent indent) {
		// TODO Auto-generated method stub
		int row;
		row=indentDao.updateByPrimaryKeySelective(indent);
		if(row==1) {
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public boolean deleteIndent(Integer indentId) {
		// TODO Auto-generated method stub
		int result = indentDao.deleteByPrimaryKey(indentId);
		if (result == 1) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean updateIndent(int indentId) {
		// TODO Auto-generated method stub
		Indent indent = new Indent();
		indent.setState(0);
		indent.setIndentid(indentId);
		int result = indentDao.updateByPrimaryKeySelective(indent);
		if (result == 1) {
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public boolean updateIndentByPublishHasAccepted(int indentId) {
		// TODO Auto-generated method stub
		Indent indent = new Indent();
		indent.setState(2);
		indent.setIndentid(indentId);
		int result = indentDao.updateByPrimaryKeySelective(indent);
		if (result == 1) {
			return true;
		}else {
			return false;
		}
	}

	@Override
//	public List<Indent> getIndentsByPublish(String publishId) {
//		 TODO Auto-generated method stub
//		
//		return indentDao.selectByPublishId(publishId);
//	}
	public List<IndentAccept> getIndentsByPublish(String publishId) {
		// TODO Auto-generated method stub
		List<Indent> indents=new ArrayList<Indent>();
		List<IndentAccept> indentsAccepts=new ArrayList<IndentAccept>();
		indents=indentDao.selectByPublishId(publishId);
		for(Indent indent:indents) {
			int indentId=indent.getIndentid();
			Acceptance acceptance=acceptanceDao.selectByIndetId(indentId);
			IndentAccept indentAccept=new IndentAccept();
			indentAccept.setIndentid(indentId);
			indentAccept.setType(indent.getType());
			indentAccept.setPrice(indent.getPrice());
			indentAccept.setDescription(indent.getDescription());
			indentAccept.setAddress(indent.getAddress());
			indentAccept.setState(indent.getState());
			indentAccept.setPublishid(indent.getPublishid());
			indentAccept.setPublishtime(indent.getPublishtime());
			indentAccept.setPlantime(indent.getPlantime());
			if(acceptance!=null)
				indentAccept.setAcceptid(acceptance.getAcceptid());
			else 
				indentAccept.setAcceptid("0");
			indentsAccepts.add(indentAccept);
			
		}
		return indentsAccepts;
	}
}

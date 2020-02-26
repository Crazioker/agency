package gdufs.agency.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gdufs.agency.dao.AcceptanceMapper;
import gdufs.agency.dao.IndentMapper;
import gdufs.agency.entity.Acceptance;
import gdufs.agency.entity.AcceptanceKey;
import gdufs.agency.entity.Indent;
import gdufs.agency.entity.IndentAccept;
import gdufs.agency.service.AcceptanceService;


@Service
public class AcceptanceServiceImpl implements AcceptanceService{
	
	@Autowired
	private AcceptanceMapper acceptanceDao;
	@Autowired
	private IndentMapper indentDao;
	
	@Override
	public boolean addAcceptance(Acceptance acceptance) {
		
		int row=0;
		row=acceptanceDao.insertSelective(acceptance);
		
		if (row==1) {
			return true;
		}else {
			return false;
		}
		
	}
	
	@Override
	public int deleteAcceptance(String acceptId, Integer indentId) {
		// TODO Auto-generated method stub
		AcceptanceKey key = new AcceptanceKey();
		key.setAcceptid(acceptId);
		key.setIndentid(indentId);
		int result = acceptanceDao.deleteByPrimaryKey(key);
		return result;
	}

	@Override
	public int updateAcceptance(String acceptId, Integer indentId,String finishedTime) {
		// TODO Auto-generated method stub
		Acceptance key = new Acceptance();
		key.setAcceptid(acceptId);
		key.setIndentid(indentId);
		key.setFinishedtime(finishedTime);
		key.setState(1);
		int result = acceptanceDao.updateByPrimaryKeySelective(key);
		return result;
	}
	@Override
	public List<IndentAccept> getAcceptances(String acceptId){
		List<Acceptance> acceptances=new ArrayList<Acceptance>();
		List<IndentAccept> indentsAccepts=new ArrayList<IndentAccept>();
		acceptances=acceptanceDao.selectByAcceptId(acceptId);
		for(Acceptance acceptance:acceptances) {
			int indentId=acceptance.getIndentid();
			
			Indent indent=indentDao.selectByPrimaryKey(indentId);
			IndentAccept indentAccept=new IndentAccept();
			indentAccept.setIndentid(indentId);
			indentAccept.setType(indent.getType());
			indentAccept.setPrice(indent.getPrice());
			indentAccept.setDescription(indent.getDescription());
			indentAccept.setAddress(indent.getAddress());
			indentAccept.setState(acceptance.getState());
			indentAccept.setPublishid(indent.getPublishid());
			indentAccept.setPublishtime(indent.getPublishtime());
			indentAccept.setPlantime(indent.getPlantime());
			indentAccept.setAcceptid(acceptId);

			indentsAccepts.add(indentAccept);
			
		}
		return indentsAccepts;
	}

}

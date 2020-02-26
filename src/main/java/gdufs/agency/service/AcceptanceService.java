package gdufs.agency.service;

import java.util.List;

import gdufs.agency.entity.Acceptance;
import gdufs.agency.entity.IndentAccept;

public interface AcceptanceService {
	boolean addAcceptance(Acceptance acceptance);

	int deleteAcceptance(String acceptId,Integer indentId);
	int updateAcceptance(String acceptId,Integer indentId,String finishedTime);
	
	List<IndentAccept> getAcceptances(String acceptId);
}

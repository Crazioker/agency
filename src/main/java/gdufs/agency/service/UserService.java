package gdufs.agency.service;

import gdufs.agency.entity.User;

public interface UserService {
	
	boolean login(String studentid,String password);
	User getUser(String studentid);
	boolean updateUser(User user);

}

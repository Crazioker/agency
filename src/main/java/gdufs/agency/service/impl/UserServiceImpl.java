package gdufs.agency.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gdufs.agency.dao.UserMapper;
import gdufs.agency.entity.User;
import gdufs.agency.service.UserService;
import gdufs.agency.util.LoginUtil;
import gdufs.agency.util.MD5Util;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userDao;

	@Override
	public boolean login(String studentid, String password) {
		// TODO Auto-generated method stub


		User user = userDao.selectByPrimaryKey(studentid);
//		System.out.println(user);
		if (user == null) {
			int row = 0;
//			System.out.println(user);
			// 模拟登录学校教务系统
			if (LoginUtil.Verify(studentid, password)) {
				password = MD5Util.encoderByMd5(password);
				User record = new User();
				record.setStudentid(studentid);
				record.setPassword(MD5Util.encoderByMd5(password));
				record.setBalance(0.0);
				row = userDao.insert(record);
				if (row == 1)
					return true;
				else
					return false;
			} else
				return false;
		} else {
			password = MD5Util.encoderByMd5(password);
			String pwd = user.getPassword();
			if (pwd.trim().equals(password))
				return true;
			else
				return false;
		}

	}

	@Override
	public User getUser(String studentid) {
		// TODO Auto-generated method stub
		User user = userDao.selectByPrimaryKey(studentid);
		return user;
	}

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		int row = userDao.updateByPrimaryKeySelective(user);
		if (row == 1)
			return true;
		else
			return false;
	}

}

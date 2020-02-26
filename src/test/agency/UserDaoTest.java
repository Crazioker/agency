package agency;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import gdufs.agency.dao.UserMapper;
import gdufs.agency.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-dao.xml")
public class UserDaoTest {
	@Resource
    private UserMapper userDao;
	
	@Test
	public void getUserById() {
		String userId="20161003414";
		User user=userDao.selectByPrimaryKey(userId);
		System.out.print(user.getUsername());
	}
}

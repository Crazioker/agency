package agency;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import gdufs.agency.entity.User;
import gdufs.agency.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-dao.xml")
public class UserServiceTest {
	@Autowired
	private UserService userService;
	
	@Test
	public void getUserById() {
		String studentId="20161003414";
		//User user =userService.getById(studentId);
		//System.out.print(user.getUsername());
	}
}

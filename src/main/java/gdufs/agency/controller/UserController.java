package gdufs.agency.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.auth0.jwt.interfaces.Claim;
import com.fasterxml.jackson.databind.ObjectMapper;

import gdufs.agency.entity.User;
import gdufs.agency.service.UserService;
import gdufs.agency.util.MD5Util;
import gdufs.agency.util.Token;

@Controller
@Scope(value = "prototype")
@RequestMapping("/user")
public class UserController {
	@Resource
	private UserService userService;

	// 用户登录
	// @RequestMapping(value = "/login", method = RequestMethod.POST)
	@RequestMapping("/login")
	public void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String no = request.getParameter("no");
		String pwd = request.getParameter("pwd");

//		System.out.println(request.getCharacterEncoding());

//    	String no=user.getStudentid();
//    	String pwd=user.getPassword();
//		System.out.println(no + " " + pwd);
		try {
			boolean reslut = userService.login(no, pwd);
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			if (reslut) {
				Token t = new Token();
				String cookie = t.createToken(no);
				response.setHeader("Cookie", cookie);
				out.print(1);
			} else
				out.print(0);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

//根据用户ID返回用户信息
	@RequestMapping(value = "/getUser", produces = "text/html;charset=UTF-8")
	public @ResponseBody String getUser(HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/json");
		String studentId = request.getParameter("studentId");
		Map<String, String> map = new HashMap<String, String>();
		ObjectMapper mapper = new ObjectMapper();
		String resultString = "";

		try {
			User user = userService.getUser(studentId);
			map.put("username", user.getUsername());
			map.put("phoneNum", user.getPhonenum());
			map.put("academy", user.getAcademy());
			System.out.println(user.getAcademy());
			map.put("address", user.getAddress());
			System.out.println(user.getAddress());
			map.put("balance", user.getBalance().toString());

			resultString = mapper.writeValueAsString(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultString;
	}

//判断session是否过期
	@RequestMapping("/checkSession")
	public void checkSession(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String cookie = request.getHeader("Cookie");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		Token c = new Token();

		try {
			Map<String, Claim> claims = c.verifyToken(cookie);
			String studentid = claims.get("user_id").asString();

			out.print(1);

		} catch (Exception e) {
			out.print(0);
		}
	}

//更新用户信息
	@RequestMapping("/updateUser")
	public void updateUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		System.out.println("before"+request.getCharacterEncoding());
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String studentId = request.getParameter("studentId");
		String username = request.getParameter("username");
		String phoneNum = request.getParameter("phoneNum");
		String academy = request.getParameter("academy");
		String address = request.getParameter("address");

		System.out.println("studentId: " + studentId + "\n"+"username: " + username + "\n" + "phoneNum: " + phoneNum + "\n"
				+ "academy: " + academy + "\n" + "address: " + address + "\n");
		try {
			User user=new User();
			user.setStudentid(studentId);
			user.setUsername(username);
			user.setPhonenum(phoneNum);
			user.setAcademy(academy);
			user.setAddress(address);
			
			boolean result=userService.updateUser(user);
			if(result)
				out.print(1);
			else
				out.print(0);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//更新用户密码
		@RequestMapping("/updatePwd")
		public void updatePwd(HttpServletRequest request, HttpServletResponse response) throws Exception {
//			System.out.println("before"+request.getCharacterEncoding());
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			
			String studentId = request.getParameter("studentId");
			String password = request.getParameter("password");

			System.out.println("studentId: " + studentId + "\n" + "password: " + password + "\n" );
			try {
				password = MD5Util.encoderByMd5(password);
				User user=new User();
				user.setStudentid(studentId);
				user.setPassword(password);
				
				boolean result=userService.updateUser(user);
				if(result)
					out.print(1);
				else
					out.print(0);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//更新用户余额
		@RequestMapping("/updateBalance")
		public void updateBalance(HttpServletRequest request, HttpServletResponse response) throws Exception {
//			System.out.println("before"+request.getCharacterEncoding());
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			
			try {
				String studentId = request.getParameter("studentId");
				Double balance = Double.parseDouble(request.getParameter("balance"));
				System.out.println("studentId: " + studentId + "\n"+"balance: " + balance + "\n" );
				User user=new User();
				user.setStudentid(studentId);
				user.setBalance(balance);
				
				boolean result=userService.updateUser(user);
				if(result)
					out.print(1);
				else
					out.print(0);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}

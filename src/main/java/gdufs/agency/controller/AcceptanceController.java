package gdufs.agency.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

import gdufs.agency.entity.Acceptance;
import gdufs.agency.entity.IndentAccept;
import gdufs.agency.service.AcceptanceService;

@Controller
@Scope(value = "prototype")
@RequestMapping("/accept")
public class AcceptanceController {

	@Resource
	private AcceptanceService acceptanceService;
	
	@RequestMapping("/addAcceptance")
	public void addAcceptance(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		try {
			String acceptId=request.getParameter("studentId");
			int indentId = Integer.parseInt(request.getParameter("indentId").trim());
			String aceptedTime=request.getParameter("acceptedTime");
			int state=0;
			Acceptance acceptance=new Acceptance();
			acceptance.setAcceptid(acceptId);
			acceptance.setIndentid(indentId);
			acceptance.setAcceptedtime(aceptedTime);
			acceptance.setState(state);
			boolean result=acceptanceService.addAcceptance(acceptance);
			if (result) {
				RequestDispatcher dispatcher =  request.getRequestDispatcher("/indent/updateState?indentId="+indentId+"&state=1");
				 dispatcher.forward(request, response);
			}else {
				out.print(0);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@RequestMapping("/updateAcceptance")
	public void updateAcceptance(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			int indentId = Integer.parseInt(request.getParameter("indentId").trim());
			String acceptId = request.getParameter("acceptId");
			String finishedTime = request.getParameter("finishedTime");
			double price = Double.parseDouble(request.getParameter("price"));
			int reslut = acceptanceService.updateAcceptance(acceptId,indentId,finishedTime);
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			if (reslut == 1) {
//				out.print(1);
				RequestDispatcher dispatcher =  request.getRequestDispatcher("/user/updateBalance?studentId="+acceptId+"&balance="+price);
				 dispatcher.forward(request, response);
			} else
				out.print(0);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	@RequestMapping("/deleteAcceptance")
	public void deleteAcceptance(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			int indentId = Integer.parseInt(request.getParameter("indentId").trim());
			String acceptId = request.getParameter("acceptId");
			int reslut = acceptanceService.deleteAcceptance(acceptId,indentId);
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			if (reslut == 1) {
				out.print(1);
			} else
				out.print(0);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	@RequestMapping("/getAcceptances")
	public void getAcceptances(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			String acceptId = request.getParameter("acceptId");
			
			List<IndentAccept> indentAccepts = acceptanceService.getAcceptances(acceptId);
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			
			Gson gson=new Gson();
			
			String resultString=gson.toJson(indentAccepts);
			
			out.print(resultString);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
}

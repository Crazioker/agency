package gdufs.agency.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

import gdufs.agency.entity.Indent;
import gdufs.agency.entity.IndentAccept;
import gdufs.agency.service.IndentService;

@Controller
@Scope(value = "prototype")
@RequestMapping("/indent")
public class IndentController {
	
	@Resource
	private IndentService indentService;
	
	//@RequestMapping(value = "/indent", method = RequestMethod.POST)
		@RequestMapping("/addIndent")
		public void addIndent(HttpServletRequest request, HttpServletResponse response) throws Exception {
//			System.out.println("before"+request.getCharacterEncoding());
			request.setCharacterEncoding("utf-8");
			try {
			int type = Integer.parseInt(request.getParameter("type"));
			float price = Float.parseFloat(request.getParameter("price"));
			String description = request.getParameter("description");
			String address = request.getParameter("address");
			int state = Integer.parseInt(request.getParameter("state"));
			String publishId = request.getParameter("publishId");
			String publishTime = request.getParameter("publishTime");
			String planTime = request.getParameter("planTime");
			System.out.println("after"+request.getCharacterEncoding());
			System.out.println(
					"type: " + type + "\n"+
					"price: " + price + "\n"+
					"description: " + description + "\n"+ 
					"address: " + address + "\n"+
					"state: " + state + "\n"+
					"publishId: " + publishId + "\n"+
					"publishTime: " + publishTime + "\n"+
					"planTime: " + planTime + "\n");
			
				boolean result = indentService.addIntent(type, price, description, address, state, publishId, publishTime, planTime);
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				if (result) {
//					out.print(1);
					
					RequestDispatcher dispatcher =  request.getRequestDispatcher("/user/updateBalance?studentId="+publishId+"&balance=-"+price);
					 dispatcher.forward(request, response);
				} else
					out.print(0);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@RequestMapping(value = "/getIndents",produces = "text/html;charset=UTF-8")
		public void getIndents(HttpServletRequest request, HttpServletResponse response) throws Exception {
//			System.out.println("before"+request.getCharacterEncoding());
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/json");
			response.setCharacterEncoding("UTF-8");
			
			try {
				String publishId = request.getParameter("studentId");
				List<Indent> indents = indentService.getIndents(publishId);
				
				for(Indent indent:indents) {
					System.out.println(indent.getDescription());
				}

				PrintWriter out = response.getWriter();
				
				Gson gson=new Gson();
				
				String resultString=gson.toJson(indents);
				
				out.print(resultString);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@RequestMapping(value = "/getIndentsByPubish",produces = "text/html;charset=UTF-8")
//		public void getIndentsByPubish(HttpServletRequest request, HttpServletResponse response) throws Exception {
////			System.out.println("before"+request.getCharacterEncoding());
//			request.setCharacterEncoding("utf-8");
//			response.setContentType("text/json");
//			response.setCharacterEncoding("UTF-8");
//			
//			try {
//				String publishId = request.getParameter("studentId");
//				List<Indent> indents = indentService.getIndentsByPublish(publishId);
//				
////				for(Indent indent:indents) {
////					System.out.println(indent.getDescription());
////				}
//
//				PrintWriter out = response.getWriter();
//				
//				Gson gson=new Gson();
//				
//				String resultString=gson.toJson(indents);
//				
//				out.print(resultString);
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
		
		public void getIndentsByPubish(HttpServletRequest request, HttpServletResponse response) throws Exception {
//			System.out.println("before"+request.getCharacterEncoding());
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/json");
			response.setCharacterEncoding("UTF-8");
			
			try {
				String publishId = request.getParameter("studentId");
				List<IndentAccept> indents = indentService.getIndentsByPublish(publishId);
				
//				for(Indent indent:indents) {
//					System.out.println(indent.getDescription());
//				}

				PrintWriter out = response.getWriter();
				
				Gson gson=new Gson();
				
				String resultString=gson.toJson(indents);
				
				out.print(resultString);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@RequestMapping(value = "/getIndentById",produces = "text/html;charset=UTF-8")
		public void getIndentById(HttpServletRequest request, HttpServletResponse response) throws Exception {
//			System.out.println("before"+request.getCharacterEncoding());
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/json");
			response.setCharacterEncoding("UTF-8");
			try {
				int indentId = Integer.parseInt(request.getParameter("indentId").trim());
				Indent indent = indentService.getIndentById(indentId);
				


				PrintWriter out = response.getWriter();
				
				Gson gson=new Gson();
				
				String resultString=gson.toJson(indent);
				
				out.print(resultString);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@RequestMapping(value = "/getIndentsByType",produces = "text/html;charset=UTF-8")
		public void getIndentsByType(HttpServletRequest request, HttpServletResponse response) throws Exception {
//			System.out.println("before"+request.getCharacterEncoding());
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/json");
			response.setCharacterEncoding("UTF-8");
			try {
				String studentId = request.getParameter("studentId");
				int type = Integer.parseInt(request.getParameter("type").trim());
				List<Indent> indent = indentService.getIndentsByType(studentId, type);
				


				PrintWriter out = response.getWriter();
				
				Gson gson=new Gson();
				
				String resultString=gson.toJson(indent);
				
				out.print(resultString);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@RequestMapping("/updateState")
		public void updateState(HttpServletRequest request, HttpServletResponse response) throws Exception {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			try {
				int indentId = Integer.parseInt(request.getParameter("indentId").trim());
				int state = Integer.parseInt(request.getParameter("state").trim());
				Indent indent=new Indent();
				indent.setIndentid(indentId);
				indent.setState(state);
				boolean result=indentService.updateState(indent);
				
				if(result)
					out.print(1);
				else {
					out.print(0);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		@RequestMapping("/cancelIndentByPublishNotAccepted")
		//如果发布方发布的订单还未被接单,自己就想取消时,需要删除indent表中的记录以及更新user表中自己的余额
		public void cancelIndentByPublishNotAccepted(HttpServletRequest request, HttpServletResponse response) throws Exception {
			request.setCharacterEncoding("utf-8");
			try {
			int indentId = Integer.parseInt(request.getParameter("indentId").trim());
			String studentId = request.getParameter("studentId");
			double price = Double.parseDouble(request.getParameter("price"));
			System.out.println("indentId: " + indentId) ;
			
				boolean result = indentService.deleteIndent(indentId);
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				if (result) {
//					out.print(1);
					RequestDispatcher dispatcher =  request.getRequestDispatcher("/user/updateBalance?studentId="+studentId+"&balance="+price);
					dispatcher.forward(request, response);
				} else
					out.print(0);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@RequestMapping("/cancelIndentByPublishHasAccepted")
		//如果发布方发布的订单已经被接单,别人想取消时,需要改变indent表中记录的状态以及删除acceptance中的记录
		public void cancelIndentByPublishHasAccepted(HttpServletRequest request, HttpServletResponse response) throws Exception {
			request.setCharacterEncoding("utf-8");
			try {
			int indentId = Integer.parseInt(request.getParameter("indentId"));
			String acceptId = request.getParameter("acceptId");
			System.out.println("indentId: " + indentId) ;
			
				boolean result = indentService.updateIndent(indentId);
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				if (result) {
//					out.print(1);
					RequestDispatcher dispatcher =  request.getRequestDispatcher("/accept/deleteAcceptance?indentId="+indentId+"&acceptId="+acceptId);
					dispatcher.forward(request, response);
				} else
					out.print(0);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@RequestMapping("/updateIndentByPublishHasAccepted")
		//如果发布方发布的订单已经被接单,并且别人已经完成任务时,自己想点确认送达，需要改变indent表中记录的状态，改变acceptance中记录的状态以及改变user表中接单人的余额
		public void updateIndentByPublishHasAccepted(HttpServletRequest request, HttpServletResponse response) throws Exception {
			request.setCharacterEncoding("utf-8");
			try {
				int indentId = Integer.parseInt(request.getParameter("indentId"));
				String acceptId = request.getParameter("acceptId");
				String finishedTime = request.getParameter("finishedTime");
				double price = Double.parseDouble(request.getParameter("price"));
				System.out.println("indentId: " + indentId) ;
			
				boolean result = indentService.updateIndentByPublishHasAccepted(indentId);
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				if (result) {
//					out.print(1);
					RequestDispatcher dispatcher =  request.getRequestDispatcher("/accept/updateAcceptance?indentId="+indentId+"&acceptId="
																				+acceptId+"&price="+price+"&finishedTime="+finishedTime);
					dispatcher.forward(request, response);
				} else
					out.print(0);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}
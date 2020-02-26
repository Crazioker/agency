package gdufs.agency.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

import gdufs.agency.entity.Comment;
import gdufs.agency.service.CommentService;

@Controller
@Scope(value = "prototype")
@RequestMapping("/comment")
public class CommentController {
	@Resource
	private CommentService commentService;

	@RequestMapping("/addComment")
	public void addComment(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String acceptId = request.getParameter("acceptId");
			int indentId = Integer.parseInt(request.getParameter("indentId").trim());
			String content = request.getParameter("content");
			
			Comment comment = new Comment();
			comment.setAcceptid(acceptId);
			comment.setContent(content);
			comment.setIndentid(indentId);
			comment.setPublishid("0");
			int reslut = commentService.addComment(comment);
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.print(reslut);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	@RequestMapping("/getComments")
	public void getComments(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			int indentId = Integer.parseInt(request.getParameter("indentId").trim());
			
			List<Comment> comments = commentService.getComments(indentId);
			
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			
			Gson gson=new Gson();
			
			String resultString=gson.toJson(comments);
			
			out.print(resultString);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
}

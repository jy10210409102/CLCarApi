package com.nong.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zhcl.log.L;

/**
 * Servlet implementation class API
 */
@WebServlet(asyncSupported = true, description = "HelloForm", urlPatterns = {"/helloform" })
public class HelloForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String tag = "HelloForm";
    /**
     * Default constructor. 
     */
    public HelloForm() {
    	L.i(tag, "api");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		L.i(tag, "doPost");
		String key = request.getParameter("key");
		String value = request.getParameter("value");
		L.i(tag, "key = " + key + " value = " + value);
		
		// 如果不存在 session 会话，则创建一个 session 对象
		HttpSession session = request.getSession(true);
		// 获取 session 创建时间
		Date createTime = new Date(session.getCreationTime());
		// 获取该网页的最后一次访问时间
		Date lastAccessTime = new Date(session.getLastAccessedTime());
		String title = "欢迎回到我的网站";
		int visitCount = 0;
		String visitCountKey = "visitCount";
		String userIDKey = "userID";
		String userID = "ABCD";
		// 检查网页上是否有新的访问者
		if (session.getAttribute(visitCountKey) == null) {
			title = "欢迎来到我的网站";
			session.setAttribute(userIDKey, userID);
			session.setMaxInactiveInterval(5);					//设置session有效时间，已在web.xml中配置
			L.i(tag, "第一次创建");
		} else {
			visitCount = (Integer) session.getAttribute(visitCountKey);
			visitCount = visitCount + 1;
			userID = (String) session.getAttribute(userIDKey);
		}
		session.setAttribute(visitCountKey, visitCount);
		if("chenli".equals(key)){
			session.invalidate();
		}
		 // 设置响应内容类型
	      response.setContentType("text/html");
	      PrintWriter out = response.getWriter();

	      String docType =
	      "<!doctype html public \"-//w3c//dtd html 4.0 " +
	      "transitional//en\">\n";
	      out.println(docType +
	                "<html>\n" +
	                "<head><title>" + title + "</title></head>\n" +
	                "<body bgcolor=\"#f0f0f0\">\n" +
	                "<h1 align=\"center\">" + title + "</h1>\n" +
	                 "<h2 align=\"center\">Session 信息</h2>\n" +
	                "<table border=\"1\" align=\"center\">\n" +
	                "<tr bgcolor=\"#949494\">\n" +
	                "  <th>Session 信息</th><th>值</th></tr>\n" +
	                "<tr>\n" +
	                "  <td>id</td>\n" +
	                "  <td>" + session.getId() + "</td></tr>\n" +
	                "<tr>\n" +
	                "  <td>Creation Time</td>\n" +
	                "  <td>" + createTime + 
	                "  </td></tr>\n" +
	                "<tr>\n" +
	                "  <td>Time of Last Access</td>\n" +
	                "  <td>" + lastAccessTime + 
	                "  </td></tr>\n" +
	                "<tr>\n" +
	                "  <td>User ID</td>\n" +
	                "  <td>" + userID + 
	                "  </td></tr>\n" +
	                "<tr>\n" +
	                "  <td>Number of visits</td>\n" +
	                "  <td>" + visitCount + "</td></tr>\n" +
	                "</table>\n" +
	                "</body></html>");
	}

}

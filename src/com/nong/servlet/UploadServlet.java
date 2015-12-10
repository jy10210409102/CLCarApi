/**
 * 
 */
package com.nong.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.zhcl.log.L;

/** 
 * 如下jar与上传下载相关（由Apache提供）：
 * 	1、commons-fileupload-1.3.1.jar
 * 		下载地址：http://commons.apache.org/fileupload/   里面含源码
 * 	2、   commons-io-2.4.jar
 * 		下载地址：http://commons.apache.org/io/ 		        里面含源码
 * @ClassName: UploadServlet 
 * @author zhonghong.chenli
 * @date 2015年12月10日 下午4:50:23  
 */
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String tag = "UploadServlet";
	/** 是否多选文件*/
	private boolean isMultipart;
	/** 文件存储位置 */
	private String dirPath;
	/** 文件最大大小 */
	private int maxFileSize = 50 * 1024;
	/** 缓存大小 */
	private int maxMemSize = 4 * 1024;
	private File file;
	
	
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		// 获取文件将被存储的位置
		dirPath =  getServletContext().getInitParameter("file-upload"); 
		L.i(tag, "文件保存路径：" + dirPath);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		L.i(tag, "doPost");
		// 检查我们有一个文件上传请求
		isMultipart = ServletFileUpload.isMultipartContent(req);
		L.i(tag, "isMultipart = " + isMultipart);
		resp.setContentType("text/html");
		java.io.PrintWriter out = resp.getWriter();
		if (!isMultipart) {
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet upload</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<p>No file uploaded</p>");
			out.println("</body>");
			out.println("</html>");
			return;
		}

		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 文件大小的最大值将被存储在内存中
		factory.setSizeThreshold(maxMemSize);
		// Location to save data that is larger than maxMemSize.
		factory.setRepository(new File(dirPath + "/temp"));

		// 创建一个新的文件上传处理程序
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 允许上传的文件大小的最大值
		upload.setSizeMax(maxFileSize);

		try {
			// 解析请求，获取文件项
			List<FileItem> fileItems = upload.parseRequest(req);
			// 处理上传的文件项
			Iterator<FileItem> i = fileItems.iterator();

			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet upload</title>");
			out.println("</head>");
			out.println("<body>");
			while (i.hasNext()) {
				FileItem fi = (FileItem) i.next();
				if (!fi.isFormField()) {
					// 获取上传文件的参数
					String fieldName = fi.getFieldName();
					String fileName = fi.getName();
					String contentType = fi.getContentType();
					boolean isInMemory = fi.isInMemory();
					long sizeInBytes = fi.getSize();
					// 写入文件
					if (fileName.lastIndexOf("\\") >= 0) {
						file = new File(dirPath + fileName.substring(fileName.lastIndexOf("\\")));
					} else {
						file = new File(dirPath + fileName.substring(fileName .lastIndexOf("\\") + 1));
					}
					L.i(tag, "file :" + file.getAbsolutePath());
					if(!file.getParentFile().exists()){
						file.getParentFile().mkdirs();
					}
					fi.write(file);
					out.println("Uploaded Filename: " + fileName + "<br>");
				}
			}
			out.println("</body>");
			out.println("</html>");
			
			
//			// 设置响应内容类型
//			resp.setContentType("text/html");
//			// 要重定向的新位置
//			String site = new String("http://www.w3cschool.cc");
//			resp.setStatus(resp.SC_MOVED_TEMPORARILY);
//			resp.setHeader("Location", site); 	
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
}

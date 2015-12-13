/**
 * 
 */
package com.nong.servlet.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.zhcl.utils.log.L;

/**
 * 过滤测试
 * 
 * @ClassName: HelloFilter
 * @author zhonghong.chenli
 * @date 2015年12月10日 上午10:25:26
 */
public class HelloFilter implements Filter {
	private static final String tag = "HelloFilter";

	/**
	 * 该方法由 Web 容器调用，指示一个过滤器被放入服务。
	 */
	@Override
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
		L.i(tag, "init");
		// 获取初始化参数
		String testParam = config.getInitParameter("test-param");
		// 输出初始化参数
		System.out.println("Test Param: " + testParam);
		
		String dirPath =  config.getServletContext().getInitParameter("file-upload"); 
		L.i(tag, "文件保存路径：" + dirPath);
	}

	/**
	 * 该方法在每次一个请求/响应对因客户端在链的末端请求资源而通过链传递时由容器调用。
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		L.i(tag, "doFilter");
		// 获取客户机的 IP 地址
		String ipAddress = request.getRemoteAddr();
		// 记录 IP 地址和当前时间戳
//		L.i(tag, "IP " + ipAddress + ", Time " + new Date().toString());
		// 把请求传回过滤链
		response.setContentType("text/html;charset=UTF-8");
		filterChain.doFilter(request, response);
	}

	/**
	 * 该方法由 Web 容器调用，指示一个过滤器被取出服务。
	 */
	@Override
	public void destroy() {
		L.i(tag, "destroy");
	}

}

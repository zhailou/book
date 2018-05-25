package cn.edu.nyist.bookMv1.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//用过滤器解决代码重复问题
//表达出谁被拦截
//表示拦截所有，但login和login.jsp不能拦截，所以下面得放行
@WebFilter("/*")
public class AuthFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//强转
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse resp=(HttpServletResponse)response;
		// 把重复代码放这里
		//login和login.jsp，js,css,验证码等不能拦截，所以下面得放行
		//获取URL
		String url=req.getRequestURI();
		if(url.endsWith("login")||url.endsWith("login.jsp")||url.contains("/bower_components/")||url.endsWith("vcode.png")) {
			//放行
			chain.doFilter(request, response);
			return;//下面的不执行
		}
		// 权限拦截，如果登陆不成功就不能进行增删改等操作
		if (req.getSession().getAttribute("loginSuccess") == null|| !req.getSession().getAttribute("loginSuccess").equals("1")) {
			// 表示没注册过，或登陆失败
			resp.sendRedirect("login.jsp");// 转到登陆页面
			return;// 不让程序往下执行
		}else {
			//让流程继续走
			chain.doFilter(request, response);
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}

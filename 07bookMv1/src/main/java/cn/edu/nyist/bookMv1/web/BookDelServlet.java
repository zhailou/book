package cn.edu.nyist.bookMv1.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ParseConversionEvent;

import cn.edu.nyist.bookMv1.biz.BookBiz;
import cn.edu.nyist.bookMv1.biz.impl.BookBizImpl;


@WebServlet("/bookDel")
public class BookDelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public BookDelServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//权限拦截，如果登陆不成功就不能进行增删改等操作
				if(request.getSession().getAttribute("loginSuccess")==null||!request.getSession().getAttribute("loginSuccess").equals("1")) {
					//表示没注册过，或登陆失败
					response.sendRedirect("login.jsp");//转到登陆页面
					return;//不让程序往下执行
				}
		//获取参数
		String strid=request.getParameter("id");
		int id=Integer.parseInt(strid);
		//调用业务层
		BookBiz bookBiz=new BookBizImpl();
		boolean ret=bookBiz.delById(id);
		//给用户反馈
		//！！！！！！！！！需完善删除失败给用户的响应
		if(!ret) {
			request.setAttribute("msg", "删除失败！！");
		}else {
			request.getRequestDispatcher("bookList").forward(request, response);
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

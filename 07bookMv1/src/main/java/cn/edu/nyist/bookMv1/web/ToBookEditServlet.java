package cn.edu.nyist.bookMv1.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.nyist.bookMv1.biz.BookBiz;
import cn.edu.nyist.bookMv1.biz.impl.BookBizImpl;
import cn.edu.nyist.bookMv1.vo.BookVo;

@WebServlet("/toBookEdit")
public class ToBookEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public ToBookEditServlet() {
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
				BookVo bookVo=bookBiz.findBookById(id);
				//转发到编辑页面
				request.setAttribute("bookVo", bookVo);
				request.getRequestDispatcher("bookEdit.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

package cn.edu.nyist.bookMv1.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.nyist.bookMv1.biz.BookBiz;
import cn.edu.nyist.bookMv1.biz.impl.BookBizImpl;
import cn.edu.nyist.bookMv1.vo.BookVo;
import cn.edu.nyist.bookMv1.vo.PageConstant;
import cn.edu.nyist.bookMv1.vo.TypeVo;
//
@WebServlet("/bookList")
public class BookListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public BookListServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//权限拦截，如果登陆不成功就不能进行增删改等操作
				if(request.getSession().getAttribute("loginSuccess")==null||!request.getSession().getAttribute("loginSuccess").equals("1")) {
					//表示没注册过，或登陆失败
					response.sendRedirect("login.jsp");//转到登陆页面
					return;//不让程序往下执行
				}
		//1、获取参数
		String strpageNo=request.getParameter("pageNo");
		//当前页
		int pageNo;
		try {
			pageNo = Integer.parseInt(strpageNo);
		} catch (NumberFormatException e) {
			pageNo=1;//没传参默认看第一页
		}
		//获取tid，name
				String name=request.getParameter("name");
				String strTid=request.getParameter("tid");
				int   tid=-1;
				 try {
					tid=Integer.parseInt(strTid);
				} catch (NumberFormatException e) {

				}
		
		//调用业务层
		BookBiz bookBiz=new BookBizImpl();
		List<BookVo> ls=bookBiz.findAllBooks(pageNo,name,tid);
		int total=bookBiz.findTotal(name,tid);
		List<TypeVo> types=bookBiz.findAllTypes();
		if(total%PageConstant.PAGE_SIZE==0) {
			request.setAttribute("totalPage", total/PageConstant.PAGE_SIZE);
			
		}else {
			request.setAttribute("totalPage", total/PageConstant.PAGE_SIZE+1);
		}
		
		//给出反应
		request.setAttribute("name", name);
		request.setAttribute("tid", tid);
		request.setAttribute("types", types);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("ls", ls);
		request.getRequestDispatcher("bookList.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

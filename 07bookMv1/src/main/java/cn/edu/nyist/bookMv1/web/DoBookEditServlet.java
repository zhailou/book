package cn.edu.nyist.bookMv1.web;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import cn.edu.nyist.bookMv1.biz.BookBiz;
import cn.edu.nyist.bookMv1.biz.impl.BookBizImpl;
import cn.edu.nyist.bookMv1.util.MyBeanUtils;
import cn.edu.nyist.bookMv1.vo.BookVo;

@WebServlet("/doBookEdit")
//鏂囦欢涓婁紶蹇呴』鍔�
@MultipartConfig
public class DoBookEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public DoBookEditServlet() {
        super();
      
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//权限拦截，如果登陆不成功就不能进行增删改等操作
				if(request.getSession().getAttribute("loginSuccess")==null||!request.getSession().getAttribute("loginSuccess").equals("1")) {
					//表示没注册过，或登陆失败
					response.sendRedirect("login.jsp");//转到登陆页面
					return;//不让程序往下执行
				}
		request.setCharacterEncoding("utf-8");
		Part part = request.getPart("photo");
		//获取上传文件
		String fileName = part.getHeader("Content-Disposition").split(";")[2].split("=")[1].replace("\"", "");
		//当fileName不是空串时上传文件
		String newFileName ="";
		if(!fileName.equals("")) {
			// 解决IE下路径问题
			fileName = fileName.lastIndexOf("\\") == -1 ? fileName : fileName.substring(fileName.lastIndexOf("\\") + 1);
			//解决名字重复问题
			String ext = fileName.substring(fileName.lastIndexOf('.') + 1);
			newFileName = UUID.randomUUID().toString() + "." + ext;
			part.write(request.getServletContext().getRealPath("upload/" + newFileName));
		}
		
		
		//自动获取参数
		BookVo bookAddVo=new BookVo();
		MyBeanUtils.populate(bookAddVo, request.getParameterMap(), "yyyy-MM-dd");
		if(!fileName.equals("")) {
			bookAddVo.setPhoto(newFileName);
		}
		
		//调用业务层
		BookBiz bookAddBiz=new BookBizImpl();
		int ret=bookAddBiz.editBook(bookAddVo);
		//给用户界面
		response.setContentType("text/html;Charset=utf-8");
		if(ret>0) {
			//response.getWriter().write("添加成功");
			response.sendRedirect("bookList");
		}else {
			request.setAttribute("msg", "修改失败");
			request.setAttribute("bookVo", bookAddVo);
			request.getRequestDispatcher("bookEdit.jsp").forward(request, response);
		}
	}
	

}

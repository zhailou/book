package cn.edu.nyist.bookMv1.web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import cn.edu.nyist.bookMv1.biz.BookAddBiz;
import cn.edu.nyist.bookMv1.biz.impl.BookAddBizImpl;

@WebServlet("/bookAdd")
//文件上传必须加
@MultipartConfig
public class bookAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public bookAddServlet() {
        super();
      
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//解决文件上传问题
		request.setCharacterEncoding("utf-8");//解决乱码问题
		Part part = request.getPart("photo");
		//获取文件名
		String fileName = part.getHeader("Content-Disposition").split(";")[2].split("=")[1].replace("\"", "");
		// 解决IE下错误问题
		fileName = fileName.lastIndexOf("\\") == -1 ? fileName : fileName.substring(fileName.lastIndexOf("\\") + 1);
		//存在hibernate.cfg.xml这种文件名
		String ext = fileName.substring(fileName.lastIndexOf('.') + 1);
		String newFileName = UUID.randomUUID().toString() + "." + ext;
		part.write(request.getServletContext().getRealPath("upload/" + newFileName));
		
		//获取参数
		String strtid=request.getParameter("tid");
		int tid=Integer.parseInt(strtid);
		String name=request.getParameter("name");
		String descri=request.getParameter("descri");
		String strprice=request.getParameter("price");
		double price=Double.parseDouble(strprice);
		String author=request.getParameter("author");
		//将获取的字符串转化为Date类型
		String strpubDate=request.getParameter("pubDate");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date pubDate=null;
		try {
			pubDate=sdf.parse(strpubDate);
		} catch (ParseException e) {
			System.out.println("****************");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		try {
//			System.out.println("****************************");
//			pubDate = sdf.parse(strPubDate);
//			
//		} catch (ParseException e) {
//			//System.out.println("****************************");
//			e.printStackTrace();
//		}
		
	
		
		//调用业务层
		BookAddBiz bookAddBiz=new BookAddBizImpl();
		int ret=bookAddBiz.saveBook(tid,name,descri,price,author,newFileName,pubDate);
		//根据查询结果返回给用户信息
		response.setContentType("text/html;Charset=utf-8");
		if(ret>0) {
			response.getWriter().write("书本添加成功");
		}else {
			request.setAttribute("msg", "书本添加失败");
			request.getRequestDispatcher("bookAdd.jsp").forward(request, response);
		}
	}
	

}

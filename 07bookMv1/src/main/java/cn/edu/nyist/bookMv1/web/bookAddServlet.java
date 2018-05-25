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

@WebServlet("/bookAdd")
//鏂囦欢涓婁紶蹇呴』鍔�
@MultipartConfig
public class bookAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public bookAddServlet() {
        super();
      
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//权限拦截，如果登陆不成功就不能进行增删改等操作
		if(request.getSession().getAttribute("loginSuccess")==null||!request.getSession().getAttribute("loginSuccess").equals("1")) {
			//表示没注册过，或登陆失败
			response.sendRedirect("login.jsp");//转到登陆页面
			return;//不让程序往下执行
		}
		//瑙ｅ喅鏂囦欢涓婁紶闂
		request.setCharacterEncoding("utf-8");//瑙ｅ喅涔辩爜闂
		Part part = request.getPart("photo");
		//鑾峰彇鏂囦欢鍚�
		String fileName = part.getHeader("Content-Disposition").split(";")[2].split("=")[1].replace("\"", "");
		// 瑙ｅ喅IE涓嬮敊璇棶棰�
		fileName = fileName.lastIndexOf("\\") == -1 ? fileName : fileName.substring(fileName.lastIndexOf("\\") + 1);
		//瀛樺湪hibernate.cfg.xml杩欑鏂囦欢鍚�
		String ext = fileName.substring(fileName.lastIndexOf('.') + 1);
		String newFileName = UUID.randomUUID().toString() + "." + ext;
		part.write(request.getServletContext().getRealPath("upload/" + newFileName));
		
		//鑾峰彇鍙傛暟
		/*
		String strtid=request.getParameter("tid");
		int tid=Integer.parseInt(strtid);
		String name=request.getParameter("name");
		String descri=request.getParameter("descri");
		String strprice=request.getParameter("price");
		double price=Double.parseDouble(strprice);
		String author=request.getParameter("author");
		//灏嗚幏鍙栫殑瀛楃涓茶浆鍖栦负Date绫诲瀷
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
		*/
		//自动获取参数
		BookVo bookAddVo=new BookVo();
		MyBeanUtils.populate(bookAddVo, request.getParameterMap(), "yyyy-MM-dd");
		bookAddVo.setPhoto(newFileName);
		//璋冪敤涓氬姟灞�
		BookBiz bookAddBiz=new BookBizImpl();
		int ret=bookAddBiz.saveBook(bookAddVo);
		//鏍规嵁鏌ヨ缁撴灉杩斿洖缁欑敤鎴蜂俊鎭�
		response.setContentType("text/html;Charset=utf-8");
		if(ret>0) {
			response.getWriter().write("添加成功");
		}else {
			request.setAttribute("msg", "添加失败");
			request.getRequestDispatcher("bookAdd.jsp").forward(request, response);
		}
	}
	

}

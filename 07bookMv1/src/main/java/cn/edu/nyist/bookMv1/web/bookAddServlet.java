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
//�ļ��ϴ������
@MultipartConfig
public class bookAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public bookAddServlet() {
        super();
      
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//����ļ��ϴ�����
		request.setCharacterEncoding("utf-8");//�����������
		Part part = request.getPart("photo");
		//��ȡ�ļ���
		String fileName = part.getHeader("Content-Disposition").split(";")[2].split("=")[1].replace("\"", "");
		// ���IE�´�������
		fileName = fileName.lastIndexOf("\\") == -1 ? fileName : fileName.substring(fileName.lastIndexOf("\\") + 1);
		//����hibernate.cfg.xml�����ļ���
		String ext = fileName.substring(fileName.lastIndexOf('.') + 1);
		String newFileName = UUID.randomUUID().toString() + "." + ext;
		part.write(request.getServletContext().getRealPath("upload/" + newFileName));
		
		//��ȡ����
		String strtid=request.getParameter("tid");
		int tid=Integer.parseInt(strtid);
		String name=request.getParameter("name");
		String descri=request.getParameter("descri");
		String strprice=request.getParameter("price");
		double price=Double.parseDouble(strprice);
		String author=request.getParameter("author");
		//����ȡ���ַ���ת��ΪDate����
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
		
	
		
		//����ҵ���
		BookAddBiz bookAddBiz=new BookAddBizImpl();
		int ret=bookAddBiz.saveBook(tid,name,descri,price,author,newFileName,pubDate);
		//���ݲ�ѯ������ظ��û���Ϣ
		response.setContentType("text/html;Charset=utf-8");
		if(ret>0) {
			response.getWriter().write("�鱾��ӳɹ�");
		}else {
			request.setAttribute("msg", "�鱾���ʧ��");
			request.getRequestDispatcher("bookAdd.jsp").forward(request, response);
		}
	}
	

}

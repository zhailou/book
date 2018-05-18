package cn.edu.nyist.bookMv1.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.nyist.bookMv1.biz.TypeBiz;
import cn.edu.nyist.bookMv1.biz.impl.TypeBizImpl;
import cn.edu.nyist.bookMv1.vo.TypeVo;
//动态改变书本类型选项
@WebServlet("/findAllTypes")
public class FindAllTypesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public FindAllTypesServlet() {
        super();
       
    }

//动态获取图书类型
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1、获取参数,此处不用做了
		//2、 调用业务层层
		TypeBiz typeBiz=new TypeBizImpl();
		List<TypeVo> ls=typeBiz.findAllTypes();
		response.setContentType("text/javascript;charset=utf-8");
		String js="[";
		for(int i=0;i<ls.size();i++) {
			js+="{id:"+ls.get(i).getId()+",name:'"+ls.get(i).getName()+"'}";
			if(i<ls.size()-1) {
				js+=",";
			}
		}
		js+="]";
		response.getWriter().write("fillSel("+js+")");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

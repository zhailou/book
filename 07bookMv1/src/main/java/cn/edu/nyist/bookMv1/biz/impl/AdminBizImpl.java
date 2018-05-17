package cn.edu.nyist.bookMv1.biz.impl;

import cn.edu.nyist.bookMv1.biz.AdminBiz;
import cn.edu.nyist.bookMv1.dao.AdminDao;
import cn.edu.nyist.bookMv1.dao.impl.AdminDaoJdbcImpl;

public class AdminBizImpl implements AdminBiz {

	@Override
	public boolean findAdminByNameAndPwd(String name,String pwd) {
		//µ÷ÓÃdao²ã
		AdminDao adminDao=new AdminDaoJdbcImpl();
		
		return adminDao.get(name,pwd);
	}

}

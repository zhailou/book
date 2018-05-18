package cn.edu.nyist.bookMv1.biz.impl;

import java.util.Date;

import cn.edu.nyist.bookMv1.biz.BookAddBiz;
import cn.edu.nyist.bookMv1.dao.BookAddDao;
import cn.edu.nyist.bookMv1.dao.impl.BookAddDaoJdbcImpl;
import cn.edu.nyist.bookMv1.vo.BookAddVo;

public class BookAddBizImpl implements BookAddBiz {

	

	@Override
	public int saveBook(BookAddVo bookAddVo) {
		BookAddDao bookAddDao=new BookAddDaoJdbcImpl();
		return bookAddDao.save(bookAddVo);
	}

}

package cn.edu.nyist.bookMv1.biz.impl;

import java.util.Date;

import cn.edu.nyist.bookMv1.biz.BookAddBiz;
import cn.edu.nyist.bookMv1.dao.BookAddDao;
import cn.edu.nyist.bookMv1.dao.impl.BookAddDaoJdbcImpl;

public class BookAddBizImpl implements BookAddBiz {

	

	@Override
	public int saveBook(int tid, String name, String descri, double price, String author, String newFileName,Date pubDate) {
		BookAddDao bookAddDao=new BookAddDaoJdbcImpl();
		return bookAddDao.save(tid,name,descri,price,author,newFileName,pubDate);
	}

}

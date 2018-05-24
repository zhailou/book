package cn.edu.nyist.bookMv1.biz.impl;

import java.util.List;

import cn.edu.nyist.bookMv1.biz.BookBiz;
import cn.edu.nyist.bookMv1.dao.BookDao;
import cn.edu.nyist.bookMv1.dao.TypeDao;
import cn.edu.nyist.bookMv1.dao.impl.BookDaoJdbcImpl;
import cn.edu.nyist.bookMv1.dao.impl.TypeDaoJdbcImpl;
import cn.edu.nyist.bookMv1.vo.BookVo;
import cn.edu.nyist.bookMv1.vo.TypeVo;

public class BookBizImpl implements BookBiz {

	

	@Override
	public int saveBook(BookVo bookAddVo) {
		BookDao bookAddDao=new BookDaoJdbcImpl();
		return bookAddDao.save(bookAddVo);
	}

	@Override
	public List<BookVo> findAllBooks(int pageNo,String name,int tid) {
		BookDao bookAddDao=new BookDaoJdbcImpl();
		return bookAddDao.findAll(pageNo,name,tid);
	}

	@Override
	public int findTotal(String name,int tid) {
		BookDao bookAddDao=new BookDaoJdbcImpl();
		return bookAddDao.getTotal(name,tid);
	}

	@Override
	public List<TypeVo> findAllTypes() {
		TypeDao typeDao=new TypeDaoJdbcImpl();
		return typeDao.findAll();
	}

}

package cn.edu.nyist.bookMv1.dao;

import java.util.List;

import cn.edu.nyist.bookMv1.vo.BookVo;

public interface BookDao {

	int save(BookVo bookAddVo);

	List<BookVo> findAll(int pageNo, String name, int tid);

	int getTotal(String name, int tid);

	boolean del(int id);

	BookVo getBook(int id);

	int edit(BookVo bookAddVo);
	

}

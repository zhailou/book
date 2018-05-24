package cn.edu.nyist.bookMv1.biz;

import java.util.List;

import cn.edu.nyist.bookMv1.vo.BookVo;
import cn.edu.nyist.bookMv1.vo.TypeVo;

public interface BookBiz {

	int saveBook(BookVo bookAddVo);

	List<BookVo> findAllBooks(int pageNo, String name, int tid);

	int findTotal(String name, int tid);

	List<TypeVo> findAllTypes();

	boolean delById(int id);


}

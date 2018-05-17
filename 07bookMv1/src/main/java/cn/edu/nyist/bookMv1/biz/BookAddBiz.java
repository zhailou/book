package cn.edu.nyist.bookMv1.biz;

import java.util.Date;

public interface BookAddBiz {

	int saveBook(int tid, String name, String descri, double price, String author, String newFileName, Date pubDate);

}

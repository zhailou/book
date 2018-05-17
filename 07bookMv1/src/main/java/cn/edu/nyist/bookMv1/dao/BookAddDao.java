package cn.edu.nyist.bookMv1.dao;

import java.util.Date;

public interface BookAddDao {

	int save(int tid, String name, String descri, double price, String author, String newFileName, Date pubDate);

}

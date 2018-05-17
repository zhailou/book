package cn.edu.nyist.bookMv1.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import cn.edu.nyist.bookMv1.dao.BookAddDao;
import cn.edu.nyist.bookMv1.util.DsUtil;

public class BookAddDaoJdbcImpl implements BookAddDao {

	public int save(int tid, String name, String descri, double price, String author, String newFileName,Date pubDate) {
		//连接、查询数据库
		Connection conn=null;
		PreparedStatement stmt=null;
		try {
			conn=DsUtil.getconn();
			String sql="insert into t_book(tid,name,descri,price,author,photo,pubDate) values(?,?,?,?,?,?,?) ";
			stmt=conn.prepareStatement(sql);
			stmt.setInt(1, tid);
			stmt.setString(2, name);
			stmt.setString(3,descri );
			stmt.setDouble(4, price);
			stmt.setString(5, author);
			stmt.setString(6, newFileName);
			//将java.util类型的pubDate，转为java.sql类型的
			stmt.setDate(7, new java.sql.Date(pubDate.getTime()));
			int ret=stmt.executeUpdate();
			return ret;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DsUtil.free(stmt, conn);
		}
		
		return 0;
	}

}

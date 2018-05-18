package cn.edu.nyist.bookMv1.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import cn.edu.nyist.bookMv1.dao.BookAddDao;
import cn.edu.nyist.bookMv1.util.DsUtil;
import cn.edu.nyist.bookMv1.vo.BookAddVo;

public class BookAddDaoJdbcImpl implements BookAddDao {

	public int save(BookAddVo bookAddVo) {
		//���ӡ���ѯ���ݿ�
		Connection conn=null;
		PreparedStatement stmt=null;
		try {
			conn=DsUtil.getconn();
			String sql="insert into t_book(tid,name,descri,price,author,photo,pubDate) values(?,?,?,?,?,?,?) ";
			stmt=conn.prepareStatement(sql);
			stmt.setInt(1, bookAddVo.getTid());
			stmt.setString(2, bookAddVo.getName());
			stmt.setString(3,bookAddVo.getDescri() );
			stmt.setDouble(4, bookAddVo.getPrice());
			stmt.setString(5, bookAddVo.getAuthor());
			stmt.setString(6, bookAddVo.getPhoto());
			//��java.util���͵�pubDate��תΪjava.sql���͵�
			stmt.setDate(7, new java.sql.Date(bookAddVo.getPubDate().getTime()));
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

package cn.edu.nyist.bookMv1.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.edu.nyist.bookMv1.dao.BookDao;
import cn.edu.nyist.bookMv1.util.DsUtil;
import cn.edu.nyist.bookMv1.vo.BookVo;
import cn.edu.nyist.bookMv1.vo.PageConstant;

public class BookDaoJdbcImpl implements BookDao {

	public int save(BookVo bookAddVo) {
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

	@Override
	public List<BookVo> findAll(int pageNo,String name, int tid ) {
		//连接数据库查询
				Connection conn=null;
				Statement stmt=null;
				ResultSet rs=null;
				try {
					conn=DsUtil.getconn();
					stmt=conn.createStatement();
					String sql="select * from t_book where 2=2 ";
					if(tid!=-1) {
						sql+=" and tid ="+tid;
					}
					if(name!=null&&!name.equals("")) {
						sql+="  and  name like '%"+name+"%' ";
					}
					
					sql+=" limit "+((pageNo-1)*PageConstant.PAGE_SIZE+1-1)+","+PageConstant.PAGE_SIZE;
					rs=stmt.executeQuery(sql);
					//System.out.println("select * from t_book limit此处有空格"+((pageNo-1)*PageConstant.PAGE_SIZE+1-1)+","+PageConstant.PAGE_SIZE);
					//System.out.println(sql);
					List<BookVo> ls=new ArrayList<>();
					while(rs.next()) {
						BookVo bookVo=new BookVo();
						bookVo.setId(rs.getInt("id"));
						bookVo.setName(rs.getString("name"));
						bookVo.setAuthor(rs.getString("author"));
						bookVo.setDescri(rs.getString("descri"));
						bookVo.setPhoto(rs.getString("photo"));
						bookVo.setPrice(rs.getDouble("price"));
						bookVo.setPubDate(rs.getDate("pubDate"));
						bookVo.setTid(rs.getInt("id"));
						ls.add(bookVo);
					}
					return ls;
				} catch (SQLException e) {
					
					e.printStackTrace();
				}finally {
					DsUtil.free(rs, stmt, conn);
				}
				
				
				return null;
		
	}

	@Override
	public int getTotal(String name, int tid) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DsUtil.getconn();
			String sql = "select count(*) from t_book where 1=1 ";
			if (tid!=-1) {
				sql+=" and  tid="+tid;
			}
			
			if (name!=null&&!name.equals("")) {
				sql+="  and  name like '%"+name+"%' ";
			}
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DsUtil.free(rs, stmt, conn);
		}
		return 0;
	}

	@Override
	public boolean del(int id) {
		Connection conn=null;
		PreparedStatement stmt=null;
		try {
			conn=DsUtil.getconn();
			String sql="delete from t_book where id="+id;
			stmt=conn.prepareStatement(sql);
			int ret=stmt.executeUpdate();
			if(ret>0) {
				return true;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DsUtil.free(stmt, conn);
		}
		
		//return 0;
		return false;
	}

	@Override
	public BookVo getBook(int id) {
		//连接数据库查询
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			conn=DsUtil.getconn();
			stmt=conn.createStatement();
			String sql="select * from t_book where id="+id;
			rs=stmt.executeQuery(sql);
			if(rs.next()) {
				BookVo bookVo=new BookVo();
				bookVo.setId(rs.getInt("id"));
				bookVo.setName(rs.getString("name"));
				bookVo.setAuthor(rs.getString("author"));
				bookVo.setDescri(rs.getString("descri"));
				bookVo.setPhoto(rs.getString("photo"));
				bookVo.setPrice(rs.getDouble("price"));
				bookVo.setPubDate(rs.getDate("pubDate"));
				bookVo.setTid(rs.getInt("id"));
				return bookVo;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DsUtil.free(rs, stmt, conn);
		}
		
		
		return null;
	}

	@Override
	public int edit(BookVo bookAddVo) {
		Connection conn=null;
		PreparedStatement stmt=null;
		try {
			conn=DsUtil.getconn();
			//该情况表示不需要修改
			if(bookAddVo.getPhoto()==null||bookAddVo.getPhoto().equals("")) {
				String sql="update  t_book set tid=?,name=?,descri=?,price=?,author=?,pubDate=? where id=? ";
				stmt=conn.prepareStatement(sql);
				stmt.setInt(1, bookAddVo.getTid());
				stmt.setString(2, bookAddVo.getName());
				stmt.setString(3,bookAddVo.getDescri() );
				stmt.setDouble(4, bookAddVo.getPrice());
				stmt.setString(5, bookAddVo.getAuthor());
				stmt.setDate(6, new java.sql.Date(bookAddVo.getPubDate().getTime()));
				stmt.setInt(7, bookAddVo.getId());
				int ret=stmt.executeUpdate();
				return ret;
			}
			else {
				String sql="update  t_book set tid=?,name=?,descri=?,price=?,author=?,photo=?,pubDate=? where id=? ";
				stmt=conn.prepareStatement(sql);
				stmt.setInt(1, bookAddVo.getTid());
				stmt.setString(2, bookAddVo.getName());
				stmt.setString(3,bookAddVo.getDescri() );
				stmt.setDouble(4, bookAddVo.getPrice());
				stmt.setString(5, bookAddVo.getAuthor());
				stmt.setString(6, bookAddVo.getPhoto());
				stmt.setDate(7, new java.sql.Date(bookAddVo.getPubDate().getTime()));
				stmt.setInt(8, bookAddVo.getId());
				int ret=stmt.executeUpdate();
				return ret;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DsUtil.free(stmt, conn);
		}
		return 0;
	}

}

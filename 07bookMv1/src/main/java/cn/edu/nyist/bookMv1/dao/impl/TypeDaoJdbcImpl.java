package cn.edu.nyist.bookMv1.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.edu.nyist.bookMv1.dao.TypeDao;
import cn.edu.nyist.bookMv1.util.DsUtil;
import cn.edu.nyist.bookMv1.vo.TypeVo;

public class TypeDaoJdbcImpl implements TypeDao {
//到数据库拿到所有类型，再转到jsp
	@Override
	public List<TypeVo> findAll() {
		//连接数据库查询
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			conn=DsUtil.getconn();
			stmt=conn.createStatement();
			rs=stmt.executeQuery("select * from t_type");
			List<TypeVo> ls=new ArrayList<>();
			while(rs.next()) {
				TypeVo typeVo=new TypeVo();
				typeVo.setId(rs.getInt("id"));
				typeVo.setName(rs.getString("name"));
				ls.add(typeVo);
			}
			return ls;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DsUtil.free(rs, stmt, conn);
		}
		
		
		return null;
	}

}

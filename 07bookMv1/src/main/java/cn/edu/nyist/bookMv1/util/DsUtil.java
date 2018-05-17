package cn.edu.nyist.bookMv1.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DsUtil {
	//整个项目公用一个连接池，所以用static
private static ComboPooledDataSource cpds=new ComboPooledDataSource();
public static Connection getconn() throws SQLException {
	return cpds.getConnection();
}
public static void free(Statement stmt,Connection conn) {
	if(stmt!=null) {
		try {
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	if(conn!=null) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
public static void free(ResultSet rs,Statement stmt,Connection conn) {
	if(rs!=null) {
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	if(stmt!=null) {
		try {
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	if(conn!=null) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
}

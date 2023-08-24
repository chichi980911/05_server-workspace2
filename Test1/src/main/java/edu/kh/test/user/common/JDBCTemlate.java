package edu.kh.test.user.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCTemlate {
	public static Connection getConnection() {
		Connection conn = null;
		
		Properties prop = new Properties();
		
		String filepath = JDBCTemlate.class.getResource("db/driver.properies").getPath();
	
		try {
			prop.load(new FileInputStream(filepath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Class.forName(prop.getProperty("driver"));
			conn = DriverManager.getConnection(prop.getProperty("url"),
												prop.getProperty("username"),
												prop.getProperty("password")
								);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
		}
	
	
	

public static void commit(Connection conn) {
	try {
		if(conn != null && conn.isClosed()) {
			conn.commit();
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	
public static void rollbakc(Connection conn) {
	try {
		if(conn != null && conn.isClosed()) {
			conn.rollback();
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	
}


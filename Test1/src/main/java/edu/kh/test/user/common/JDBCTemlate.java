package edu.kh.test.user.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemlate {
	
	
	//1.커넥션
	public static Connection getConnection() {
		
		Connection conn = null; //3
		
		//1.클래스 파일로 등록
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");   //1
			
			//2. 커넥션 객체 생성 (url,계정명, 비번) //DriverManager.getConnection
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "TEST1", "TEST1");  //2
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
		
		
	
		
	}
	
	
	//2. 반납 (커넥션,stmt,rset)
	
	public static void close(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Statement stmt) {
		try {
			if(stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rset) {
		try {
			if(rset != null && !rset.isClosed()) {
				rset.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

package com.kh.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {
	// 1.Connection 객체 생성 한 후 해당 Connection 객체를 반환시켜주는 getConnection메서드

	public static Connection getConnection() {

		Connection conn = null; //connection 객체생성
		
		
		Properties prop = new Properties(); //Map 계열 컬렉션 (key - value)
		
		//읽어 들이고자 하는 classes 폴더내에 driver.properties파일의 물리적인 경로
		String filepath = JDBCTemplate.class.getResource("/db/driver/driver.properties").getPath();
//		c:/05_server-workspace2/jspProject/WebContent/classes/db/driver/driver/driver.properties
		
		System.out.println("filepath : " + filepath);
		
		try {
			prop.load(new FileInputStream(filepath));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
//		

		
		
		try {

			// jdbc driver 등록
//			Class.forName("oracle.jdbc.driver.OracleDriver"); //"oracle.jdbc.driver.OracleDriver"
			Class.forName(prop.getProperty("driver"));

			// 접속하고자 하는 db의 url , 계정명 , 비밀번호 제시해서 Connection 객체생성
	
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
//												"SERVER",
//												"SERVER");
			
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SERVER", "SERVER");
			conn = DriverManager.getConnection(prop.getProperty("url"),
												prop.getProperty("username"),
												prop.getProperty("password"));

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;

	}

	// 2-1. Connection 객체 전달 받아서 commit 시켜주는 commit 메서드
	
	public static void commit(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	// 2_2. " rollback 시켜주는 rollback 메서드"
	
	public static void rollback(Connection conn) {
		try {
			if(conn != null && conn.isClosed()) {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	// 3_1. Connection 객체 전달받아서 반납시켜주는 close 메서드
	
	public static void close(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	// 3_2. Statement 객체 전달 받아서 반납시켜주는 close 메서드
	
	public static void close(Statement stmt) {
		try {
			if(stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 3_3. ResultSet 객체 전달 받아서 반납시켜주는 close 메서드
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

package edu.kh.test.user.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.kh.test.user.common.JDBCTemlate;
import edu.kh.test.user.model.vo.User;

public class UserDao {//쿼리돌림
	
	public User selectUser(Connection conn , int userNo) {
		// select 문 = Resultset 객체 => 한행조회 => User객체
		
		//1)jdbc용 객체 생성
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		User user = null;
		
		//2)sql 쿼리 작성
		String sql = "SELECT * FROM TB_USER WHERE USER_NO = ? ";
		
		
		
		try {
			//3) pstmt, rset 채워넣기
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, userNo);
			
			rset = pstmt.executeQuery(); //뭐라도 있거나 | null
			
			if(rset.next()) {
				user = new User(rset.getInt("user_no"), 
								rset.getString("user_id"), 
								rset.getString("user_name"), 
								rset.getInt("user_age"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemlate.close(rset);
			JDBCTemlate.close(pstmt);
			
		}
		
		return user;
		
	}
	
	public User searchuser(Connection conn, String userId, int age ) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		User user = null;
		
		//2)sql 쿼리 작성
		String sql = "SELECT * FROM TB_USER WHERE USER_ID = ? AND USER AGE = ?";
		
		
		
		try {
			//3) pstmt, rset 채워넣기
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			pstmt.setInt(2, age);
			
			
			rset = pstmt.executeQuery(); //뭐라도 있거나 | null
			
			if(rset.next()) {
				user = new User(rset.getInt("user_no"), 
								rset.getString("user_id"), 
								rset.getString("user_name"), 
								rset.getInt("user_age"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemlate.close(rset);
			JDBCTemlate.close(pstmt);
			
		}
		
		return user;
		
	}

}

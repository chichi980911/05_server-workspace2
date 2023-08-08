package edu.kh.test.user.model.service;

import java.sql.Connection;

import edu.kh.test.user.common.JDBCTemlate;
import edu.kh.test.user.model.dao.UserDao;
import edu.kh.test.user.model.vo.User;

public class UserService {
	
	public User selectUser(int userNo) {
		
		// 서비스역할 (.common생성)
		// 1 커넥션 객체 생성
		Connection conn = JDBCTemlate.getConnection();
		
		// 2 DAO호출 및 결과 받기 (Db직접적 연결x)
		
		User user = new UserDao().selectUser(conn, userNo);
		
		System.out.println("user : " + user);
		
		//3 커넥션 반납
		JDBCTemlate.close(conn);
		
		return user;
	}
	
	public User searchuser(String userId , int age) {
		Connection conn = JDBCTemlate.getConnection();
		
		User user = new UserDao().searchuser(conn, userId , age);
		
		System.out.println("!@!@!2"+userId + age);
		JDBCTemlate.close(conn);
		
		return user;
		
		
	}

}

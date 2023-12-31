package edu.kh.test.user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.test.user.model.dao.UserDao;
import edu.kh.test.user.model.service.UserService;
import edu.kh.test.user.model.vo.User;

/**
 * Servlet implementation class Loginmember
 */
@WebServlet("/login.do")
public class Loginmember extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Loginmember() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("userId");
		int age = Integer.parseInt(request.getParameter("age"));
		
		User user = new UserService().searchuser(userId , age);
		
		if(user == null) {
			request.setAttribute("errormessage", "조회된 결과가 없습니다.");
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/searchFail.jsp");
			view.forward(request, response);
		}else{
			request.setAttribute("user", user);
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/searchSuccess.jsp");
			view.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package com.kh.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.kh.member.model.sevice.MemberService;

/**
 * Servlet implementation class DeleteMember
 */
@WebServlet("/delete.me")
public class DeleteMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteMember() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		
		HttpSession session = request.getSession();
		
		int result = new MemberService().deleteMember(userId, userPwd);
		
		if(result > 0 ) {
			session.setAttribute("alertMsg", "회원탈퇴 성공, 그동안 이용해주셔서 감사합니다");
			session.removeAttribute("loginMember");
			response.sendRedirect("/jspProject");
			
		}else {
			session.setAttribute("alertMsg", "회원탈퇴 실패");
			response.sendRedirect(request.getContextPath() + "/mypage.me");
//			
//		if(result > 0 ) {
//			session.setAttribute("alertMsg", "회원탈퇴 성공, 그동안 이용해주셔서 감사합니다");
//			session.invalidate();
//			response.sendRedirect(request.getContextPath());
//		}else {
//			session.setAttribute("alertMsg", "회원탈퇴 실패");
//			response.sendRedirect(request.getContextPath() + "/mypage.me");
//			
			
			
			
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

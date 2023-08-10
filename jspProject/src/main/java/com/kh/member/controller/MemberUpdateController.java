package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.sevice.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class MemberUpdateController
 */
@WebServlet("/update.me")
public class MemberUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		//요청시 전달 값 뽑아서 변수 및 객체에 담기
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String[] interestArr = request.getParameterValues("interest");
		
		String interest = "";
		if(interestArr != null) {
			interest = String.join(",", interestArr);
		}
		
		Member m = new Member(userId,userName, phone, email, address, interest);
		
		System.out.println(m);
		
		//서비스 호출
		Member updateMem = new MemberService().updateMember(m);
		
		if(updateMem != null) { // 성공
			
			//세션에 담겨있는 loginMember를 바꿔치기 작업
			
			
			HttpSession session = request.getSession();
			
			session.setAttribute("loginMember", updateMem);
			
			session.setAttribute("alertMsg", "성공적으로 회원 정보를 수정 했습니다.");
			
			//마이페이지 = /jsp/mypage.me url재요청
			response.sendRedirect(request.getContextPath()+ "/mypage.me" );
			
			
			
		}else { // 실패
			request.setAttribute("errorMsg", "회원수정에 실패 했습니다");
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
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

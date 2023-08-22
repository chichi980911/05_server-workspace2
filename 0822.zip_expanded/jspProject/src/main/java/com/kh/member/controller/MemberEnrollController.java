package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class MemberEnrollController
 */
@WebServlet("/insert.me")
public class MemberEnrollController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberEnrollController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// 2) 요청시 전달값 뽑아서 변수 및 객체에 기록하기
		String userId = request.getParameter("userId"); // "아이디"
		String userPwd = request.getParameter("userPwd"); // "비밀번호"
		String userName = request.getParameter("userName"); // "이름"
		String phone = request.getParameter("phone"); // "번호" | ""
		String email = request.getParameter("email"); // "이메일" | ""
		String address = request.getParameter("address"); // "주소" | ""
		String[] interestArr = request.getParameterValues("interest"); // "[취미, ...]" | null
		
		// 기본생성자로 생성 후 setter 메소드 이용해서 담기 => 값이 소량일때
		
		// 매개변수 생성자로 생성과 동시에 담기 => 다량
		
		// String[] --> String
		String interest = "";
		if(interestArr != null) {
			interest = String.join(",", interestArr);
		}
		
		Member m = new Member(userId, userPwd, userName, phone, email, address, interest);
		
		// 3) 요청처리(db에 sql문 실행) => 서비스 메소드 호출 및 결과 받기
		int result = new MemberService().insertMember(m);
		
		if(result> 0) {
			request.getSession().setAttribute("alertMsg", "회원가입이 성공적으로 완료했습니다");
			// request는 포워딩 방식일때만 가능
			
			// 성공 => index 페이지 => url 재요청
			response.sendRedirect(request.getContextPath());
			
		}else {
			request.setAttribute("errorMsg", "회원가입이 실패했습니다.");
			
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

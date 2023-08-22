package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login.me")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
    	
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	/**
	 *
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1) 전달값에 '한글이 있을 경우' 인코딩 처리해야됨
		// request.setCharacterEncoding("UTF-8");
		
		// 2) 요청시 전달값 뽑아서 변수 또는 객체에 기록하기
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		// 3) 요청처리 (db에 sql문 실행)
		// 	  해당 요청을 처리하는 서비스 클래스의 메소드 호출 및 결과 받기
		Member loginMember = new MemberService().loginMember(userId,userPwd);
		
		// 4) 처리된 결과를 가지고 사용자가 보게될 응답뷰(jsp) 지정 후 포워딩 또는 url 재요청
		/*
		 * 응답페이지에 전달할 값이 있을 경우 어딘가에 담아야됨 (담을 수 있는 영역 == JSP 내장객체 4종류)
		 * 1) application : 담긴 데이터는 웹 애플리케이션 전역에서 다 꺼내 쓸 수 있음
		 * 2) session : 담긴 데이터는 내가 직접 지우기전까지, 세션이 만료(서버가 멈추거나, 브라우저 종료) 되기전까지
		 * 				어떤 jsp든, servlet이든 꺼내 쓸 수 있다
		 * 3) request : 담긴 데이터는 request객체를 "포워딩한 응답 jsp에서만" 꺼내 쓸 수 있다(일회성 느낌)
		 * 4) page : 해당 jsp에서 담고 그 jsp에서만 꺼내 쓸 수 있음
		 * 
		 * 공통적으로 데이터를 담고자한다면 .setAttribute("키", 벨류);
		 * 		  데이터를 꺼내고자 한다면 .getAttribute("키") : object 타입으로 벨류
		 * 		  데이터를 지우고싶다면 .removeAttribute("키")
		 */
		
		if(loginMember == null) {
			// 조회결과 없음 == 로그인 실패 => 에러문구가 보이는 에러페이지 응답
			request.setAttribute("errorMsg", "아이디 또는 비밀번호가 맞지않습니다");
			// 응답페이지(jsp)에게 위임시 필요한 객체 => RequestDispatcher
			// 포워딩 방식
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
			view.forward(request, response);
		}else {
			// 조회결과 있음 == 로그인 성공 => 메인페이지 응답(index.jsp)
			
			// 로그인한 회원정보 (loginMember)를 session에 담기 (여러 곳에서 사용가능)
			
			// Servlet 에서 session에 접근하려면 객체를 얻어와야함
			HttpSession session = request.getSession();
			
			session.setAttribute("loginMember", loginMember);
			
			// 1. 포워딩 방식 응답 뷰 출력
			// 선택된 jsp가 보여질 뿐 url에는 현재 서블릿 매핑값이 남아있음
			// localhost:8001/jsp/login.me
			
			// RequestDispatcher view = request.getRequestDispatcher("index.jsp");
			// view.forward(request, response);
			
			// 2. url 재요청 방식 (sendRedirect 방식)
			// 기존에 페이지를 응답하는 url이 존재한다면 사용가능
			// localhost:8001/jsp
			
			// response.sendRedirect("/jsp");
			
			response.sendRedirect(request.getContextPath());
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

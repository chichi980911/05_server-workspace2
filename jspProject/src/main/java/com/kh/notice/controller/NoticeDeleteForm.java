package com.kh.notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.notice.model.service.NoticeServeice;

/**
 * Servlet implementation class NoticeDeleteForm
 */
@WebServlet("/deleteForm.no")
public class NoticeDeleteForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeDeleteForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num")); 
		
		int result = new NoticeServeice().deleteNotice(num);
		
		if (result > 0) {
			HttpSession session = request.getSession();
			session.setAttribute("alertMsg", "공지사항을 성공적으로 삭제했습니다");
			
			response.sendRedirect(request.getContextPath() + "/list.no");
			
		}else {
			request.setAttribute("errorMsg", "삭제에 실패 했습니다");
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

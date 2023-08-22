package com.kh.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;
import com.kh.common.model.vo.PageInfo;

/**
 * Servlet implementation class BoardListController
 */
@WebServlet("/list.bo")
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// -----------------페이징 처리-----------------
		// 난이도 최상  원리를 파악 => 공식을 암기
		
		int listCount;	 // 현재 총 게시글 개수
		int currentPage; // 현재 페이지(사용자가 요청한 페이지)
		int pageLimit;	 // 페이지 하단에 보여질 페이징바의 페이지 최대개수
		int boardLimit;	 // 한 페이지내에 보여질 게시글 최대 개수
		
		// 위의 4개 변수를 이용하여 값을 구해낼 변수
		int maxPage;	 // 가장 마지막페이지 (총 페이지 수)
		int startPage;	 // 페이징바의 시작수
		int endPage;	 // 페이징바의 끝수
		
		// * listCount : 총 게시글 개수
		listCount = new BoardService().selectListCount();
		
		// * currentPage : 현재 페이지(사용자가 요청한 페이지)
		currentPage = Integer.parseInt(request.getParameter("cpage"));
		
		// * pageLimit : 페이징바의 페이지 최대 개수
		pageLimit = 10;
		
		// * boardLimit : 게시글의 최대 개수
		boardLimit = 10;
		
		/*
		 * * maxPage : 제일 마지막 페이지 수 (총 페이지 수)
		 * 
		 * listCount, boardLimit 에 영향을 받음
		 * 
		 * ex) 게시글이 10개 단위씩 보여진다는 가정하에
		 * 
		 * 	listCount	boardLimit				maxPage
		 * 	   100.0	    10	     => 10.0	  10
		 *     101.0        10       => 10.1      11
		 *     105.0		10 		 => 10.5   	  11
		 *     110.0        10       => 11.0	  11 
		 * 
		 * ex) 게시글이 5개 단위씩 보여진다는 가정하에
		 * 
		 * 	listCount	boardLimit				maxPage
		 * 	   10.0	    	 5	     => 2.0		   2
		 *     11.0          5       => 2.1        3
		 *     14.0		     5 		 => 2.4   	   3
		 *     15.0          5       => 3.0	       3
		 *     
		 *  총게시글(실수형) / boardLimit => 올림처리
		 */
		
		maxPage = (int)Math.ceil((double)listCount / boardLimit);
		
		/*
		 * startPage : 페이징바의 시작수
		 * 
		 * pageLimit, currentPage에 영향 받음
		 * 
		 * ex) 페이징바의 목록이 10개 단위
		 * 	   startPage : 1, 11, 21, 31, ...
		 * 					=>  curentPage-1 / pageLimit * pageLimit + 1
		 * 
		 * currentPage		startPage
		 *      1				1  			=> 0 * pageLimit + 1 => n = 0
		 *      5				1			=> 0 * pageLimit + 1 => n = 0
		 *     10				1			=> 0 * pageLimit + 1 => n = 0
		 *     11			   11			=> 1 * pageLimit + 1 => n = 1
		 *     
		 *     15			   11           => 1 * pageLimit + 1 => n = 1
		 *     20              11           => 1 * pageLimit + 1 => n = 1
		 *     
		 *   1~10		=> n = 0
		 *  11~20		=> n = 1
		 *  21~30		=> n = 2
		 *  
		 *  curentPage-1 / pageLimit => n
		 *   0~9	/	pageLimit	
		 *  10~19	/	pageLimit
		 *  20~29	/	pageLimit
		 *  
		 *  stratPage = n * pageLimit + 1
		 *  (curentPage-1) / pageLimit * pageLimit + 1
		 */
		
		startPage = (currentPage-1) / pageLimit * pageLimit + 1;
		
		/*
		 * * endPage : 페이징바의 끝수
		 * startPage, pageLimit에 영향 받음 (maxPage에도 영향을 받음)
		 * 
		 * ex) pageLimit : 10 가정하에
		 * 
		 * startPage : 1 	=> endPage : 10
		 * startPage : 11	=> endPage : 20
		 * startPage : 21	=> endPage : 30
		 * 
		 * endPage = startPage + pageLimit - 1
		 */
		
		endPage = startPage + pageLimit - 1;
		
		// startPage가 11이면 endPage는 20이다 
		// maxPage가 13밖에 안된다면?
		if(endPage >maxPage) {
			endPage = maxPage;
		}
		
		// com.kh.common.model.vo.PageInfo
		// * 페이징바를 만들 때 필요한 객체
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);
		
		// * 현재 요청한 페이지(currentPage)에 보여질 게시글 리스트 boardLimit 수 만큼 조회해가기
		ArrayList<Board> list = new BoardService().seletList(pi);
		
		request.setAttribute("pi", pi);
		request.setAttribute("list", list);
		request.getRequestDispatcher("views/board/boardListView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

package com.kh.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Attachment;
import com.kh.board.model.vo.Board;
import com.kh.common.MyFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class BoardUpdateController
 */
@WebServlet("/update.bo")
public class BoardUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdateController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		if(ServletFileUpload.isMultipartContent(request)) {
			
			// 1_1. 전달되는 파일 용량 제한 (int maxSize)
			int maxSize = 10*1024*1024;
			
			// 1_2. 전달되는 파일을 저장시킬 서버의 폴더 물리적인 경로 (String savePath)
			String savePath = request.getSession().getServletContext().getRealPath("/resources/board_upfiles/");
													// 어플리케이션 영역
			
			// 2. 전달된 파일명 수정작업 후 서버에 업로드
			// HttpServletRequest => MultipartRequest
			// MultipartRequest(request, 저장경로, 파일용량, 인코딩방식, 파일명변경객체)
			MultipartRequest mr = new MultipartRequest(request, savePath, maxSize, "utf-8", new MyFileRenamePolicy());
			
			// 3.SQL문 실행시 필요한 요소를 가져와서 VO에 기록
			
			// >> 공통적으로 수행 : update board
			
			int boardNo = Integer.parseInt(mr.getParameter("bno"));
			String category = mr.getParameter("category");
			String boardTitle = mr.getParameter("title");
			String boardContent = mr.getParameter("content");
			
			
			Board b = new Board();
			b.setBoardNo(boardNo);
			b.setCategory(category);
			b.setBoardTitle(boardTitle);
			b.setBoardContent(boardContent);
			
			Attachment at = null;
			// null 로 초기화, 전달받은 첨부파일이 있을 경우 생성
			
			if(mr.getOriginalFileName("upfile") != null) {
				// 새로 넘어온 첨부파일이 있을 경우 => 객체 생성
				at = new Attachment();
				at.setOriginName(mr.getOriginalFileName("upfile"));
				at.setChangeName(mr.getFilesystemName("upfile"));
				at.setFilePath("resources/board_upfiles");
				
				if(mr.getParameter("originFileNo") != null) {
					// 기존에 첨부파일이 있을 경우 => update attachment (기존 첨부파일 번호)
					at.setFileNo(Integer.parseInt(mr.getParameter("originFileNo")));
				}else {
					// 기존에 첨부파일이 없을 경우 => insert attachment (현재 게시글 번호)
					at.setRefBoardNo(boardNo);
				}
			}
			
			
			int result = new BoardService().updateBoard(b, at);
			// 새로운 첨부파일이 없다면 at는 null
			// at가 null 이면 Board Update
			
			// 새로운 첨부파일 o, 기존파일 o
			// fileNo 담긴 at => Board Update + Attachment Update
			
			// 새로운 첨부파일 o, 기존파일 x
			// refBoardNo 담긴 at => Board UPdate + Attachment Insert
		
			if(result > 0) {
				// 성공 => /jsp/detail.bo?bno=해당 게시글번호 url 재요청 => 상세조회 페이지		
				request.getSession().setAttribute("alertMsg", "성공적으로 수정되었습니다");
				response.sendRedirect(request.getContextPath() + "/detail.bo?bno=" + boardNo);
			}else {
				// 실패
				request.setAttribute("errorMsg", "일반게시판 수정에 실패했습니다.");
				RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
				view.forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

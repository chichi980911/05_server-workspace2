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
 * Servlet implementation class BoardInsertlController
 */
@WebServlet("/insert.bo")
public class BoardInsertlController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardInsertlController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// 일반적인 방식이 아닌 multipart/form-data로 전송하는 경우 request로 값을 받아올 수 없음
		// String boardTitle = request.getParameter("title");
	
		// enctype이 multipart/form-data로 잘 전송되었을 경우 전반적인 내용들이 수행되도록
		if(ServletFileUpload.isMultipartContent(request)) {
			
			// 파일 업로드를 위한 라이브러리 : cos.jar (com.oreilly.servlert의 약자)
			// http://www.servlets.com
			
			// 1. 전달되는 파일 처리할 작업 내용 (파일의 용량제한, 저장폴더 경로)
			// 1_1) 파일의 용량 제한 (int maxSize) => 10Mbyte로 제한
			/*
			 * byte => kbyte => mbyte => gbyte => tbyte ....
			 * 
			 * 1kbyte == 1024byte
			 * 
			 * 1mbyte == 1024kbyte == 1024*1024byte
			 * 10mbyte == 10*1024*1024byte;
			 * 
			 */
			int maxSize = 10*1024*1024;
			
			// 1_2) 전달된 파일을 저장시킬 폴더의 경로 알아내기 (String savePath)
			String savePath = request.getSession().getServletContext().getRealPath("/resources/board_upfiles/");
			
			// 2. 전달된 파일의 파일명 수정 및 서버에 업로드 작업
			/*
			 *      >> httpServletRequest request => MultipartRequest multiRequest 변환
			 *		위의 구문 실행만으로 넘어온 첨부파일이 해당 폴더에 업로드 됨
			 *		
			 *		업로드시 파일명은 수정해줘야함 (파일명 수정시켜주는 객체 제시)
			 *		=>같은 파일명이 존재할 경우 덮어씌워짐, 파일명에 한글/특문/띄어쓰기가 포함되면 서버에따라 오류발생
			 *		
			 *		기본적으로 파일명이 중복 안되도록 수정 작업해주는 객체
			 *		=> FileRenamePolicy 객체 (cos.jar에서 제공하는 객체)
			 *		=> 내부적으로 해당 클래스에 rename() 메소드 실행되면서 파일명 수정 후 업로드
			 *		   rename(원본파일){
			 *				기존에 동일한 파일명이 존재할 경우
			 *				파일명 뒤에 카운팅 된 숫자를 붙여줌
			 *				ex) aaa.jpg, aaa1.jpg, aaa2.jpg
			 *					한글을 수정해주지는 않음
			 *				return 수정파일
			 *		   }
			 *
			 *		안겹치도록 rename 하기 위해 FileRenamePolicy 클래스 생성(rename 메소드 재정의)
			 *		공통적으로 사용하기위해 common 폴더에 생성
			 */
			
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			// 3. DB에 기록할 데이터를 뽑아서 VO에 담기
			// > Board : 카테고리번호, 제목, 내용, 작성자 회원번호 뽑아서 BOARD INSERT
				
			
			// > AttachMent : 첨부파일이 있으면 원본명, 수정명, 저장폴더경로 ATTACHMENT INSERT
			
			String category = multiRequest.getParameter("category");
			String boardTitle = multiRequest.getParameter("title");
			String boardContent = multiRequest.getParameter("content");
			String boardWriter = multiRequest.getParameter("userNo");
			
			Board b = new Board();
			b.setCategory(category);
			b.setBoardTitle(boardTitle);
			b.setBoardContent(boardContent);
			b.setBoardWriter(boardWriter);
			
			Attachment at = null; // 첨에는 null로 초기화, 넘어온 첨부파일 있다면 생성
			// multiRequest.getOriginalFileName("키값") : 넘어온 첨부파일이 있을 경우 "원본명" | 없으면 null
			if(multiRequest.getOriginalFileName("upfile") != null) { // 첨부파일 존재
				at = new Attachment();
				at.setOriginName(multiRequest.getOriginalFileName("upfile"));
				at.setChangeName(multiRequest.getFilesystemName("upfile"));
				at.setFilePath("resources/board_upfiles");
			}
			
			// 4. 서비스 요청 (요청 처리)
			int result = new BoardService().insertBoard(b, at);
			
			// 5. 응답뷰지정
			if(result > 0) {
				response.sendRedirect(request.getContextPath() + "/list.bo?cpage=1");
			}else {
				request.setAttribute("errorMsg", "게시글 등록에 실패했습니다.");
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

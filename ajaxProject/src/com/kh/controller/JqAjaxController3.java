package com.kh.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.kh.model.vo.Member;

/**
 * Servlet implementation class JqAjaxController3
 */
@WebServlet("/jqAjax3.do")
public class JqAjaxController3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JqAjaxController3() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userNo = Integer.parseInt(request.getParameter("no"));
		
		//db다녀왔다고 가정
		//위의 Member 객체에 각 필드에 조회된 데이터들이 담겨있을 것!
		Member m = new Member(1,"원숭이",30,"남"); //조회된 데이터가 다음과 같다는 가정하에
		
//		response.getWriter().print(m); /*vo 객체를 곧바로 응답시 . to String()의 문자열이 응답*/
		
		//jsonobject {key : value,key:value}
		/*
		JSONObject jobj = new JSONObject();
		jobj.put("userNo",m.getUserNo());
		jobj.put("userName",m.getUserName());
		jobj.put("age", m.getAge());
		jobj.put("gender", m.getGender());
		
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(jobj);
		*/
		
		//더간단한 방법 : 위의 과정을 알아서 해주는 GSON라이브러리 사용
		//GSON : Google JSON 
		response.setContentType("application/json; charset=UTF-8");
//		Gson gson = new Gson(); //Gson 객체.toJson(응답할 자바객체, 응답할스트림);
		//gson.toJson(응답할자바객체,응답할스트림);
		new Gson().toJson(m,response.getWriter());
		
		//gson을 이용해서 vo 객체 하나만 응답시 JSONOBject{key:value,key:value,...}형태로 만들어서 응답
		//이때 key는 해당 vo객체의 필드명으로 알아서 세팅
		
		//	자바배열 또는 ArrayList로 응답시 JSONArray[value,value,...] 형태로 만들어서 응답
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

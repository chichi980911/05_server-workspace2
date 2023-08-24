package com.kh.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Servlet implementation class JqAjaxController2
 */
@WebServlet("/jqAjax2.do")
public class JqAjaxController2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JqAjaxController2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		
		int age = Integer.parseInt(request.getParameter("age"));
		
		
		// 요청처리가 다 됐다는 가정하에 응답할 데이터
		//v1.하나의 문자열로 리턴
//		String responseData = "이름 : " + name + "나이 : " + age;
//		
//		//응답데이터에 한글이 있을경우
//		response.setContentType("text/html; charset=UTF-8");
//		
//		response.getWriter().print(responseData);
		
		//v2. 응답데이터 여러개일 경우
//		response.setContentType("text/html; charset=UTF-8");
//		response.getWriter().print(name);
//		response.getWriter().print(age);
		//=>success:funtion의 매개변수에 연이어서 하나의 문자열로 담겨있을거다
		
		/*
		JSONArray jArr = new JSONArray(); //[]
		
		jArr.add(name); //["차은우"]
		jArr.add(age); //["차은우",20]
		
		response.setContentType("application/json; charset=UTF-8"); //JSON 데이터를 응답시에
		response.getWriter().print(jArr);
		*/
		
		JSONObject jobj = new JSONObject(); // {}
		
		jobj.put("name",name); // {name:"차은우"}
		jobj.put("age", age); // {name:"차은우",age:20}
		
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(jobj);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

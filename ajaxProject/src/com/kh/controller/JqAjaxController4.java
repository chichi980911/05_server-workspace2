package com.kh.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.kh.model.vo.Member;

/**
 * Servlet implementation class JqAjaxController4
 */
@WebServlet("/jqAjax4.do")
public class JqAjaxController4 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JqAjaxController4() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		ArrayList<Member> list = new MemeberService().selectmemberList();
		//db다녀왔다고 가정
		ArrayList<Member> list = new ArrayList<Member>();// [] =>jsonArray
		list.add(new Member(1,"박철수",30,"남")); //JSONobject{}
		list.add(new Member(2,"원숭이",10,"여")); //JSONobject{}
		list.add(new Member(3,"김상호",77,"남")); //JSONobject{}
		
		//jsonArray[{},{},{}]
		
		/*JSONArray jArr = new JSONArray();
		for(Member m : list) {
			JSONObject jobj = new JSONObject();
			jobj.put("userNo", m.getUserNo());
			jobj.put("userName", m.getUserName());
			jobj.put("age", m.getAge());
			jobj.put("gender", m.getGender());
			
			jArr.add(jobj); 
		}*/
		
		response.setContentType("application/json; charset=UTF-8");
		new Gson().toJson(list,response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

<%@page import="edu.kh.test.user.model.vo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	User user = (User)request.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h1>회원정보</h1>
    <table border="1">
        <tr>
            <th>회원번호</th>
            <th>회원아이디</th>
            <th>회원이름</th>
            <th>회원나이</th>
        </tr>
        <tr>
            <th><%= user.getUserNo() %></th>
            <th><%= user.getUserId() %></th>
            <th><%= user.getUserName() %></th>
            <th><%= user.getAge() %></th>
        </tr>

    </table>
    <a href="/Test1">메인페이지로 돌아가기</a>
</body>
</html>
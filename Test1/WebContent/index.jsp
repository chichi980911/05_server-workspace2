<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원정보검색(회원 번호 검색)</h1>
	<form action="/Test1/test.do" method="get">
		<input type="text" name="userNo" placeholder="회원 번호 입력">
		<input type="submit" value="조회"></button>
	</form>
	
	
	<form action="/Test/login.do" method="get">
		아이디 : <input type="text" name="userId"><br>
		나이 : <input type="number" name="age"><br>
		<input type="submit">
		
	</form>
	
</body>

</html>
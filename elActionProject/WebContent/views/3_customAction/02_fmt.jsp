<%@page import="java.util.Date"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>1.formatNumber</h3>
	
	<p>
		숫자데이터의 포맷(형식) 지정 <br>
		-표현하고자 하는 숫자데이터의 형식을 통화기호, %등 원하는 쓰임에 맞게 형식을 지정하는 태그 <br>
		
		(fmt:formatNumber value="" groupingUsed="true|false" type="percent|currency" currencySymbol="통화기호문자")
		
	</p>
	
	<c:set var="num1" value="123456789"/>
	<c:set var="num2" value="0.75"/>
	<c:set var="num3" value="50000"/>
	
	그냥 출력 : ${num1}<br>
	세자리마다 구분하여 출력: <fmt:formatNumber value="${num1 }"/><br>
	숫자 그대로 출력 : <fmt:formatNumber value="${num1 }" groupingUsed="false"/><br>
	<!-- groupingUsed :세자리 마다 구분자(,) 표시여부 지정 -->
	
	percent : <fmt:formatNumber value="${num2 }" type="percent"/><br>
	currency : <fmt:formatNumber value="${num3 }" type="currency" groupingUsed="false"/><br>
	currency : <fmt:formatNumber value="${num3 }" type="currency" currencySymbol="$"/><br>
	<!-- type : 백분육(%), 통화기호 형식 지정
		 currencySymbol :통화기호 문자 지정 -->
		 
		 <hr>
		 
		 <h3>2.formatDate</h3>
			<p>날짜 및 시간의 데이터의 포맷 지정 (단 java.util.Date 객체 사용)
			<c:set var="current" value="<%= new Date() %>" /><br>
			
			그냥 출력 : ${current}
			
			<ul>
			<li>현재날짜: <fmt:formatDate value="${current}"/> </li> <!-- type생략시 기본값이 date -->
			<li>현재시간: <fmt:formatDate value="${current}" type="time"/></li>
			<li>둘다: <fmt:formatDate value="${current}" type="both"/></li> <!-- date,timestyle 생략시 기본값이 medium -->
			<li>Medium : <fmt:formatDate value="${current }" type="both" dateStyle="medium" timeStyle="medium"/> </li>
			<li>Long : <fmt:formatDate value="${current }" type="both" dateStyle="long" timeStyle="long"/></li>
			<li>full :<fmt:formatDate value="${current }" type="both" dateStyle="full" timeStyle="full"/></li>
			<li>short : <fmt:formatDate value="${current }" type="both" dateStyle="short" timeStyle="short"/></li>
			<li>pattern<fmt:formatDate value="${current }" type="both" pattern="yyyy-MM-dd(E) HH:mm:ss(a)"/></li>
			<li></li>
			<li></li>
			
				
			</ul>







</body>
</html>
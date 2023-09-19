<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h1>JSTL 이란?</h1>
	<p>
		JSTL (JSP Standard Tag Library)의 약자로 JSP 에서 사용되는 커스텀 액션태그로<br>
		공통적으로 자주 사용되는 코드들을 집합하여 보다 쉽게 사용할 수 있도록 태그화 해서 표준으로 제공하는 라이브러리
		
	</p>
	
	<h3>라이브러리 다운로드 후 추가</h3>
	1) 아파치 톰캣 사이트 접속 <br>
	2) 메뉴바 Taglibs 메뉴 접속 <br>
	3) 4개의 라이브러리 다 다운로드 <br>
	4) WEB_INF/lib 안에 넣기
	
	<h3>*JSTL 선언 방법</h3>
	<p>
		JSTL을 사용하고자 하는 해당 jsp 페이지 상단에 <br>
		tablib 지시어를 사용해서 선언함 <br><br>
		
		&it;%@ taglib prefix="접두어" uri="해당 라이브러리 파일상의 url주소" %&gt;
	</p>
	
	<h4>1.JSTL core Library</h4>
	<p>변수와 조건문, 반복문 등의 로직과 관련된 문법을 제공</p>
	<a href="01_core.jsp">core library </a>
	
	<br>
	<h4>2.JSTL Formattion Library</h4>
	<p>숫자 날짜 데이터 출력형식 지정할때</p>
	<a href="02_fmt.jsp">fmt liblary</a>
	
	<br>
	<h4>3.JSTL</h4>
	
	
	
	
	
	
	
	
</body>
</html>
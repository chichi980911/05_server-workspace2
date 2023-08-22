<%@page import="com.kh.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();

	Member loginMember = (Member)session.getAttribute("loginMember");
	// 로그인 시도 전 menubar.jsp 로딩시 : null
	// 로그인 성공 후 menubar.jsp 로딩시 : 로그인 성공한 회원의 정보가 담겨있는 Meber 객체
	
	String alertMsg = (String)session.getAttribute("alertMsg");
	// 서비스 요청 전 menubar.jsp 로딩시 : null
	// 서비스 성공 후 menubar.jsp 로딩시 : alert로 띄워줄 메시지 문구
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    .login-area>*{float: right;}

    .nav-area{background-color: black;}

    .menu{
        width: 150px;
        height: 50px;
        display: table-cell;
    }
    
    .menu a{
        text-decoration: none;
        color: white;
        font-size: 20px;
        font-weight: bold;
        display: block;
        width: 100%;
        height: 100%;
        line-height: 50px;
    }
    
    .menu a:hover{
        background-color: gray;
    }
</style>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>

<!-- Popper JS -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
		<% if(alertMsg != null){ %>
			<script>
				alert("<%= alertMsg %>");
			</script>
			<% session.removeAttribute("alertMsg"); %>
		<% } %>

        <h1 align="center">Welcome</h1>

        <div class="login-area">

		<% if(loginMember == null){ %>
            <!-- case1. 로그인 전 -->
            <form action="<%= contextPath %>/login.me" method="post">
                <table>
                    <tr>
                        <th>아이디</th><td><input type="text" name="userId" required></td>
                    </tr>
                    <tr>
                        <th>비밀번호</th><td><input type="password" name="userPwd" required></td>
                    </tr>
                    <tr>
                        <th colspan="2">
                            <button type="submit">로그인</button>
                            <button type="button" onclick="enrollPage();">회원가입</button>
                        </th>
                    </tr>
                </table>
            </form>

        <script>
            function enrollPage(){
            	// location.href = "<%= contextPath %>/views/member/memberEnrollForm.jsp";
          		// 웹 애플리케이션의 디렉토리 구조가 url에 노출되면 보안에 취약
          		
          		// 단순한 페이지 요청도 servlet 호출 (url에는 서블릿 매핑값만 노출)
          		location.href = "<%= contextPath%>/enrollForm.me";
            }
        </script>

		<% }else { %>	
		
            <!-- case2. 로그인 후 -->
            <div>
                <b><%= loginMember.getUserName() %></b>의 방문을 환영합니다. <br><br>
                <div align="center">
                    <a href="<%= contextPath %>/myPage.me">마이페이지</a>
                    <a href="<%= contextPath %>/logout.me">로그아웃</a>
                </div>
            </div>
            
		<% } %>
        </div>
        
        <br clear="both">      
        <br>
                
            <div class="nav-area" align="center">
                <div class="menu"><a href="<%= contextPath%>">HOME</a></div>
                <div class="menu"><a href="<%= contextPath%>/list.no">공지사항</a></div>
                <div class="menu"><a href="<%= contextPath%>/list.bo?cpage=1">일반게시판</a></div>
                <div class="menu"><a href="<%= contextPath%>/list.th">사진게시판</a></div>
            </div>
</body>
</html>
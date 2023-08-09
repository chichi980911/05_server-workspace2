<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
    .outer{background-color: black;
            color: white;
            width: 1000px;
            margin: auto;
            margin-top: 50px;        
    }
    #mypage-form table{margin: auto;}
    #mypage-form input{margin: 5px;}


</style>
</head>
<body>

<%@ include file = "../common/menubar.jsp" %>
	
	<%
		String userId =  loginMember.getUserId();
		String userName = loginMember.getUserName();
		String phone = (loginMember.getPhone() == null) ? "" : loginMember.getPhone();
		String email = (loginMember.getEmail() == null) ? "" : loginMember.getEmail();
		String address = (loginMember.getAddress() == null) ? "" : loginMember.getAddress();
		String interest = (loginMember.getInterest() == null) ? "" :loginMember.getInterest();
	%>
	
    <div class="outer">


        <br>
        <h2 align = "center">마이페이지</h2>

        <form action="" method="post" id="mypage-form">
            <table>
                <tr>
                    <td>* 아이디</td>
                    <td><input type="text" name="userId" maxlength="12" value="<%=userId %>" readonly ></td>
                    
                </tr>
                <tr>
                    <td>* 이름</td>
                    <td><input type="text" name="userName" maxlength="6" value="<%=userName %>" required></td>
                    <td></td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp;전화번호</td>
                    <td><input type="text" name="phone" placeholder="- 포함해서 입력" value="<%=phone%>"></td>
                    <td></td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp;이메일</td>
                    <td><input type="email" name="email" value="<%= email%>"></td>
                    <td></td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp;주소</td>
                    <td><input type="text" name="address" value="<%= address%>"></td>
                    <td></td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp;관심분야</td>
                    <td colspan="2">
                        <input type="checkbox" name="interest" id="sports" value="<%=interest %>>" >
                        <label for="sports">운동</label>

                        <input type="checkbox" name="interest" id="hiking" value="<%=interest %>">
                        <label for="hiking">등산</label>

                        <input type="checkbox" name="interest" id="fishing" value="<%=interest %>" >
                        <label for="fishing">낚시</label>
                        <br>
                        <input type="checkbox" name="interest" id="cooking" value="<%=interest %>">
                        <label for="cooking">요리</label>

                        <input type="checkbox" name="interest" id="game" value="<%=interest %>">
                        <label for="game">게임</label>

                        <input type="checkbox" name="interest" id="movie" value="<%=interest %>">
                        <label for="movie">영화</label>
                    </td>
                </tr>
            </table>

            <br><br>
                <div align = "center">
                    <button type="submit">정보변경</button>
                    <button type="button">비밀번호변경</button>
                    <button type="button">회원탈퇴</button>

                </div>
		</form>


</body>
</html>
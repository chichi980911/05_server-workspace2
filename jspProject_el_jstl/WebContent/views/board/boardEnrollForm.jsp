<%@page import="com.kh.board.model.vo.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    .outer{
        background-color: black;
        color: white;
        width: 1000px;
        height: 600px;
        margin: auto;
        margin-top: 50px;

    }

    #enroll-form table{
        border: 1px solid white;
    }

    #enroll-form input, #enroll-form textarea{
        width: 100%;
        box-sizing: border-box;
    }
</style>
</head>
<body>

<jsp:include page="../common/menubar.jsp"/>

<div class="outer">
    <br>
    <h2 align = "center">일반게시판 작성하기</h2>
    <br>
    <form id="enroll-form" action="insert.bo" method="post" enctype="multipart/form-data">

        <!-- 카테고리, 제목, 내용, 첨부파일 한개 , 로그인한 회원번호-->
        <input type = "hidden" name = "userNo" value="${loginMember.userNo }">
        <table align="center">
            <tr>
                <th width="70">카테고리</th>
                <td width="500"> 
                    <select name="category" id="">
                    <!-- 카테고리테이블로부터 조회해오기 -->
                    <c:forEach var="c" items="${list }">
                     
                    <option value="${c.categoryNo }">${c.categoryName }</option>
                
                    </c:forEach>
                   
                    

                </select></td>
               
            </tr>
            <tr>
                <th>제목</th>
                <td><input type="text" name="title" required></td>
            </tr>
            <tr>
                <th>내용</th>
                <td><textarea name="content" id="" cols="30" rows="10" style="resize: none;"></textarea></td>
            </tr>
            <tr>
                <th>첨부파일</th>
                <td><input type="file" name="upfile"></td>
            </tr>

        </table>
        <br>
        <div align="center">
            <button type="submit">작성하기</button>
            <button type="reset">취소하기</button>H
        </div>


    </form>
</div>

</body>
</html>
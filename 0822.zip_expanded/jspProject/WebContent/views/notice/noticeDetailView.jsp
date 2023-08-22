<%@page import="com.kh.notice.model.vo.Notice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Notice n = (Notice)request.getAttribute("n");
%>
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
        height: 500px;
        margin: auto;
        margin-top: 50px;
    }
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
    <div class="outer" align="center">
        <br>
        <h2 align="center">공지사항 상세보기</h2>
        <br>

        <table id="detail-area" border="1">
            <tr>
                <th width="70">제목</th>
                <td colspan="3" width="430"><%= n.getNoticeTitle() %><div class=""></div></td>
            </tr>
            <tr>
                <th>작성자</th>
                <td><%= n.getNoticeWriter() %></td>
                <th>작성일</th>
                <td><%= n.getCreateDate() %></td>
            </tr>
            <tr>
                <th>내용</th>
                <td colspan="3">
                    <p style="height: 150px;">
                     	<%= n.getNoticeContent() %>
                    </p>
                </td>
            </tr>
        </table>
        <br><br>

        <div>
            <a href="<%= contextPath %>/list.no" class="btn btn-sm btn-secondary">목록가기</a>
            <% if(loginMember != null && n.getNoticeWriter().equals(loginMember.getUserId())){  %>
            <!-- 현재 로그인한 사용자가 해당 글을 쓴 본인일 경우 -->
            <a href="<%= contextPath %>/updateForm.no?num=<%= n.getNoticeNo() %>" class="btn btn-sm btn-warning">수정하기</a>
            <a href="<%= contextPath %>/delete.no?num=<%= n.getNoticeNo() %>" class="btn btn-sm btn-danger">삭제하기</a>
           
            <% } %>
        </div>
    </div>

</body>
</html>
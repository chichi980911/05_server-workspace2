<%@page import="com.kh.notice.model.vo.Notice"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
.outer {
	background-color: black;
	color: white;
	width: 1000px;
	height: 500px;
	margin: auto;
	margin-top: 50px;
}

.list-area {
	border: 1px solid white;
	text-align: center;
}

.list-area>tbody>tr:hover {
	background-color: gray;
	cursor: pointer;
}
</style>
<body>
	<%@ include file="../common/menubar.jsp"%>
	<div class="outer">
		<h2 align="center">공지사항</h2>



		<!-- 현재 로그인한 사용자가 관리자 일 경우 보여짐 -->
		
		<c:if test="${not empty loginMember and loginMember.userId eq 'admin'}">
		<div align="right" style="width: 850px;">
			<!-- <button class="btn btn-sm btn-secondary">글작성</button> -->
			<a href="enrollForm.no" class="btn btn-sm btn-secondary">글작성</a>
			<br><br>
		</div>
		</c:if>
		<br>
		<table class="list-area" align="center">
			<thead>
				<tr>
					<th>글번호</th>
					<th width="400">글제목</th>
					<th width="100">작성자</th>
					<th>조회수</th>
					<th width="100">작성일</th>
				</tr>
			</thead>
			<tbody>

				<c:choose>
					<c:when test="${empty list }">
						<!-- case1. 공지글이 없을 경우-->
						<tr>
							<td colspan="5">존재하는 공지사항이 없습니다.</td>
						</tr>
					</c:when>
					<c:otherwise>
						<!--case.2 공지글이 있는 경우  -->
						<c:forEach var="n" items="${list }">
							<tr>
								<td>${n.noticeNo }</td>
								<td>${n.noticeTitle }</td>
								<td>${n.noticeWriter }</td>
								<td>${n.count}</td>
								<td>${n.createDate }</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>

	</div>
	
	<script>
		$(function(){
			$(".list-area>tbody>tr").click(function(){
				const num= $(this).children().eq(0).text();
				location.href = 'detail.no?num='+num;
			})
		
		})
	</script>
	
</body>
</html>
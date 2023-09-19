<%@page import="com.kh.board.model.vo.Board"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.kh.common.model.vo.PageInfo"%>
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
    .list-area{
        border: 1px solid white;
        text-align: center;
    }

	.list-area>tbody>tr:hover{
		background: gray;
		cursour:pointer;
	}
	
</style>
</head>
<body>
	
	<jsp:include page="../common/menubar.jsp"/>

    <div class="outer">
        <br>
        <h2 align = "center"> 일반게시판</h2>
        <br>
        <c:if test="${loginMember !=null }">
            <div align = "right">
            <a href="enrollform.bo" class = "btn btn-sm btn-secondary" style="width: 80px;">글작성</a>
            </div>
        </c:if>
            <br>
            <br>
   
        <table align = "center" class="list-area">
            <thead>
                <tr>
                    <th width = "70">글번호</th>
                    <th width = "80">카테고리</th>
                    <th width = "300">제목</th>
                    <th width = "100">작성자</th>
                    <th width = "50">조회수</th>
                    <th width = "100">작성일</th>
                </tr>
            </thead>
            <tbody>
            <c:choose>
				<c:when test="${empty list }">
	                <tr>
	                    <td colspan="6">조회된 게시글이 없습니다.</td>
	                </tr>
                </c:when>
                <c:otherwise>
               <c:forEach var="b" items="${list}">
                <tr>
                    <td>${b.boardNo }</td>
                    <td>${b.category }</td>
                    <td>${b.boardTitle }</td>
                    <td>${b.boardWriter}</td>
                    <td>${b.count }</td>
                    <td>${b.createDate }</td>
                </tr>
                </c:forEach>
                </c:otherwise>
			</c:choose>
            </tbody>
        </table>
        <script>
        $(function(){
        	$(".list-area>tbody>tr").click(function(){
        		location.href = 'detail.bo?bno=' +$(this).children().eq(0).text()
        		
        	})
        })
        </script>

            <br><br>

            <div class="paging=area" align="center">
            
            	<c:if test="${pi.currentPage ne 1 }">
                <button onclick="location.href='list.bo?cpage=${pi.currentPage-1}'"> &lt; </button>
                </c:if>
                
                <c:forEach var="p" begin="${pi.startPage}" end="${pi.endPage}">
                	<c:choose>
	                	<c:when test="${p == pi.currentPage }">
	                		<button disabled>${p}</button>
	                	</c:when>
	                	<c:otherwise>
	                		<button onclick="location.href='list.bo?cpage=${p}'">${p}</button>
	                	</c:otherwise>
                	</c:choose>
                </c:forEach>
                
                <c:if test="${currentPage != maxPage }">
                	 <button onclick="location.href='list.bo?cpage=${currentpage+1}'"> &gt; </button>
                </c:if>
                
                
                
                
            </div>

    </div>


</body>
</html>
<%@page import="com.kh.board.model.vo.Attachment"%>
<%@page import="com.kh.board.model.vo.Board"%>
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
    .outer {
        background-color: black;
        color: white;
        width: 1000px;
        height: 550px;
        margin: auto;
        margin-top: 50px;
    }

    #update-form table {
        border: 1px solid white;
    }
    #update-form input, #update-form textarea {
        width: 100%;
        box-sizing: border-box;
    }
</style>
</head>
<body>

<jsp:include page="../common/menubar.jsp"/>	
	<div class="outer">
        <br>
        <h2 align="center">일반게시판 수정하기</h2>
        <br>

        <form id="update-form" action="update.bo" method="post" enctype="multipart/form-data">
			<!-- 수정에 필요한 요소들 : 카테고리번호, 제목, 내용, 첨부파일 한개, 게시글번호 -->
            <input type="hidden" name="bno" value="${b.boardNo }">
            <table align="center">
            
                <tr>
                    <th width="70">카테고리</th>
                    <td width="500">
                        <select name="category">
                            <!-- category 테이블로부터 조회해오기 -->
                            <c:if test="${not empty list }">
                            	<c:forEach var="c" items="${list }">
                            		<option value="${c.categoryNo }">${c.categoryName }</option>
                            	</c:forEach>
                            </c:if>
                            
                           
                        </select>
                       <script>
                       	$(function(){
                       		$("#update-form option").each(function(){
                       			if($(this).text() == "${b.categoryName}"){
                       				$(this).attr("selectd",true);
                       				
                       			}
                       			
                       		})
                       	})
                       </script> 
                    </td>
                </tr>
                <tr>
                    <th>제목</th>
                    <td><input type="text" name="title" size="28" required value="${b.boardTitle }"></td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td><textarea name="content" cols="30" rows="10" style="resize: none;" required>${b.boardContent }</textarea></td>
                </tr>
                <tr>
                    <th>첨부파일</th>
                    <td>
                     	<input type="file" name="upfile">
                     	 <input type="hidden" name="originFileNo" value="${at.fileNo }">
                     	 <c:if test="${at != null }">
                     	 	${at.originName }
                     	 </c:if>
						
						
                       
                        	
                    </td>
                </tr>
            </table>
            <br>

            <div align="center">
                <button type="submit">수정하기</button>
                <button type="reset">취소하기</button>
            </div>

        </form>

    </div>
	
</body>
</html>
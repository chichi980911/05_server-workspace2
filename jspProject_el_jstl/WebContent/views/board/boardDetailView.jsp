<%@page import="com.kh.board.model.vo.Attachment"%>
<%@page import="com.kh.board.model.vo.Board"%>
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
        height: auto;
        margin: auto;
        margin-top: 50px;
    }
</style>
</head>
<body>

<jsp:include page="../common/menubar.jsp"/>

    <div class="outer">
        <br>
        <h2 align="center">일반게시판 상세보기</h2>
        <br>

        <table id="detail-area" border="1" align="center">
            <tr>
                <th width="70">카테고리</th>
                <td width="70">${b.category }</td>
                <th width="70">제목</th>
                <td width="350">${b.boardTitle }</td>
            </tr>
            <tr>
                <th>작성자</th>
                <td>${b.boardWriter }</td>
                <th>작성일</th>
                <td>${b.createDate }></td>
            </tr>
            <tr>
                <th>내용</th>
                <td colspan="3">
                    <p style="height: 200px;">${b.boardContent }</p></td>
                
            </tr>
            <tr>
                <th>첨부파일</th>
                <td colspan="3">
               
                <c:when test="${at == null  }">
               
                	첨부파일이 없다요
                </c:when>
                <c:otherwise>
                <!-- 첨부파일이 있는경우 -->
                	 <a download="${originName }" href="${filePath }/${changeName}">${originName }</a>
                 </c:otherwise>
                </td>
                <!-- case.1 첨부파일이 없을경우 -->
                <!-- -->
                
            </tr>


        </table>

        <br>
        <div align="center">
            <a href="list.bo?cpage=1" class="btn btn-sm btn-secondary">목록가기</a>

            <!-- 로그인한 사용자가 게시글 작성자일경우 -->
            <c:if test="${loginMember != null and loginMember.userId eq b.boardWriter}">
            <a href="updateForm.bo?bno=${b.boardNo }" class="btn btn-sm btn-warning">수정하기</a>
            <a href="#" class="btn btn-sm btn-danger">삭제하기</a>
			</c:if>
        </div>

        <br>
        
        <div id="reply-area" >

            <table border="1" align="center">
                <thead>
                    <tr>
						 
                        <th>댓글작성</th>
                        <c:if test="${loginMember != null }">
                       
                        <td>
                            <textarea name="" id="replyContent" cols="50" rows="3" style="resize: none;"></textarea>
                        </td>
                        <td><button onclick="insertReply();">댓글등록버튼</button></td>
                        </c:if>
                       	<c:otherwise>
                           <td>
                            <textarea placeholder="로그인 후 이용 가능한 서비스 입니다" id="" cols="50" rows="3" style="resize: none;" readonly ></textarea>
                        </td>
                        <td>
                            <button disabled>댓글등록</button>
                        </td>
                       </c:otherwise>
                    </tr>
                </thead>
                <tbody>
                   
                </tbody>
            
            </table>
            
            <script>
            	$(function(){
            		selectReplyList();
            		
            		setInterval(selectReplyList,1000)
            	})
            	//ajax로 댓글 작성용
            	function insertReply(){
            		$.ajax({
            			url:"rinsert.bo"
            			,data:{content:$("#replyContent").val(),
            					bno:${b.boardNo}
            					,type:"post"
            					,success:function(result){
            						console.log(result)
            						if(result > 0 ){//댓글작성성공 =>갱신된 댓글 리스트 조회
            							selectReplyList();
            							$("#replyContent").val("")
            						}
            						
            					}
            					,error:function(){
            						console.log("댓글작성용 ajax 통신 실패");
            					}
            			
            		})
            	}
            	//ajax로 해당 게시글에 딸린 댓글 목록 조회용
            	function selectReplyList(){
            		$.ajax({
            			url:"rlist.bo"
            			,data:{bno:${b.boardNo}}
            			,success:function(list){
            				let result = "";
            				for(let i = 0; i<list.length; i++){
            					result += "<tr>"
            							+ "<td>" + list[i].replyWriter +"</td>"
            							+ "<td>" + list[i].replyContent +"</td>"
            							+ "<td>" + list[i].createDate +"</td>"
            							+ "</tr>";
            				}
            				
            				$("#reply-area tbody").html(result);
            				
            			}
            			,error:function(){
            				console.log("댓글 조회용 ajax 통신 실패!")
            				
            			}
            		})
            	}
            	
            </script>
        </div>

    </div>

</body>
</html>
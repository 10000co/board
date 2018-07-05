<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글읽기</title>
<script type="text/javascript">
//글 삭제를 위한 함수-1
function boardDelete(boardnum) {
	if (confirm('정말 삭제하시겠습니까?')) {
		location.href = "deleteboard?boardnum=" + boardnum;
	}
}

//글 수정
function boardUpdate(boardnum) {
	location.href = "updateboard?boardnum=" + boardnum;
}
	
//댓글 달기
function replyCheck() {
	var replytext = document.getElementById("replytext");
	
	if(replytext.value == '') {
		alert("댓글을 입력하세요");
		return false;
	}
	return true;
}

// 댓글 삭제
function deleteReply(replynum, boardnum) {
	alert("댓글 삭제");
	if (confirm('정말 삭제하시겠습니까?')) {
		location.href = "board?action=deleteReplay&replynum=" + replynum + "&boardnum=" + boardnum;
	}
}
</script>
</head>
<body>
<div id="wrapper">
	<h2>[ 게시판 글 읽기 ]</h2>
	<table class="board">
		<tr>
			<th>번호</th>
			<td>${board.boardnum}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${board.userid}</td>
		</tr>
		<tr>
			<th>작성일</th>
			<td>${board.regdate }</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${board.hitcount }</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${board.title}</td>
		</tr>
		<tr>
			<th>글내용</th>
			<td><pre>${board.content}</pre></td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td>
				<c:if test="${not empty board.originalfile}">
					<a href="download?boardnum=${board.boardnum}">${board.originalfile}</a><br />
				</c:if>
				<c:if test="${empty board.originalfile}">
					 : 첨부된 파일이 없습니다.
				</c:if>
				<!-- 섬네일 -->
				<c:if test="${not empty type}">
					<img style="width:100px; height:100px" src="download?boardnum=${board.boardnum}" />
				</c:if>
			</td>
		</tr>
	</table>

	<div id="navigator">
		<a href="listboard">목록</a>
		<c:if test="${sessionScope.loginId eq board.userid }">
			<a href="javascript:boardUpdate('${board.boardnum}')">수정</a> 
			<a href="javascript:boardDelete('${board.boardnum}')">글삭제</a>
			<br/>
			<%-- <a id="del" data-item="${board.boardnum }" href="javascript:void(0);">삭제</a> --%>
		</c:if>
	</div>
	
	<!-- 댓글 폼 영역 -->
	<div id="replyForm">
	<c:if test="${not empty loginId}">
		<div id="replyForm">
		<form action="board" method="post" onsubmit="return replyCheck()" >
			<input type="hidden" name="action" value="insertReply" />
			<input type="hidden" name="boardnum" value="${board.boardnum}" />
			<input type="text" id="replytext" name="replytext" style="width:500px">
			<input type="submit" value="댓글달기">
		</form>
		</div>
	</c:if>
	
	<!-- 댓글 목록 영역 -->
	<table class="replyList">
		<c:forEach var="reply" items="${replyList}">
		<tr>
			<td class="replyid">${reply.userid}</td>
			<td class="replytext">${reply.replytext}</td>
			<td class="replydate">${reply.regdate}</td>
			<td class="replybutton">
				<c:if test="${loginId == reply.userid}">
				<a href="javascript:update('${board.boardnum}')">수정</a>
				<a href="javascript:deleteReply(${reply.replynum}, ${reply.boardnum})">삭제</a>
				</c:if>
			</td>
		</tr>
		</c:forEach>
	</table>
	</div> <!--  end #reply -->
</div> <!-- end #wrapper -->
</body>
</html>
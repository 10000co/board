<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글읽기</title>
<style type="text/css">
	div#wrapper{
		width : 800px;
		margin : 0 auto;
	}
	div#wrapper>h2 {
		text-align : center;
	}
	div#wrapper table {
		margin:0 auto;
		width : 800px;
	}
	th {
		width: 100px;
	}
	pre {
		width : 600px;
		height : 200px;
		overflow: auto;
	}
	table.reply {
		width : 800px;
	}
	input[name='text'] {
		width : 600px;
	}
	span {
		display: inline-block;

		margin : 5px;
	}
	td.replycontent {
		width : auto;
	}
	td.replytext {
		width : 600px;
		text-align : left;
	}
	td.replysub {
		width : 80px;
		text-align : right;
	}
	td.replybtn{
		width : 100px;
		text-align : right;
	}
</style>
<script type="text/javascript">

	function replydelete(replynum) {
		var answer = confirm("댓글을 삭제하시겠습니까?");
		if(answer) {
			location.href="replyDelete?replynum="+replynum+"&boardnum="+"${board.boardnum}";
		}
		return;
	}
	function replymodify(replynum, text) {
		document.getElementById("reply_txt").value = text;
		document.getElementById("reply_submit").value = "댓글 수정";
		
		document.getElementById("reply_submit").onclick = function() {
			if(document.getElementById("reply_submit").value == "댓글 수정") {
				var updatetext = document.getElementById("reply_txt").value;
				location.href="replyUpdate?replynum="+replynum+"&boardnum="+"${board.boardnum}&replytext="+updatetext;
			}
		}
	
		return false; //submit 기본액션이 없음
	}
	
	function replywrite() {
		var writetext = document.getElementById("reply_txt").value;
		
		if(writetext.length == 0) {
			alert("댓글을 입력하세요");
			return;
		}
		
		document.getElementById("replyWrite").submit();
		
	}
	
	function boardUpdate() {
		location.href="updateboard?boardnum="+${board.boardnum};
	}
	
	function boardDelete() {
		var answer = confirm("게시글을 삭제하시겠습니까?")	;
		if(answer) {
			location.href="deleteboard?boardnum="+${board.boardnum};
		}
	}
	
	function boardList() {
		var targetPlace = "listboard?currentPage=${navi.currentPage}&searchItem=${searchItem}&searchWord=${searchWord}";
		location.href=targetPlace;
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
				<c:if test="${not empty type and sessionScope.loginId eq board.userid}">
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
			<a id="del" data-item="${board.boardnum }" href="javascript:void(0);">삭제</a>
		</c:if>
	</div>
	
	<br />
	<!-- 댓글 입력 -->
	<form id="replyWrite" action="replyWrite" method="POST">
	<input type="hidden" name="boardnum" value="${board.boardnum }" />
	<table id="replyinput" class="reply">
		<tr>
			<td>
			<input id="reply_txt" name="replytext" type="text" name="text" />
			<input id="reply_submit" type="button" value="댓글 입력" onclick="replywrite()" />
			</td>
		</tr>
	</table>
	</form>
	
	<!-- 댓글 출력 -->
	<div id="replydisplay">
	<table class="reply">
		<c:forEach var="reply" items="${replyList}">
		<tr>
			<td class="replytext">
				${reply.replytext}
			</td>
			<td class="sub replyid">
				<span>${reply.userid} </span>
			</td>
			<td class="sub replydate">
				<span>${reply.regdate}</span>
			</td>
			<c:if test="${loginId == reply.userid }">
			<td class="replybtn">
				<input type="button" value="삭제" onclick="replydelete('${reply.replynum}')"/>
				<input type="button" value="수정" onclick="replymodify('${reply.replynum}', '${reply.replytext}')"/>
			</td>
			</c:if>
		</tr>
		</c:forEach>
	</table>
	</div> <!-- end #replydisplay -->
	
</div>
	
</body>
</html>
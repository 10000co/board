<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
<script type="text/javascript">
function formCheck() {
	var title = document.getElementById("title");
	var content = document.getElementById("content");
	
	if (title.value == '' || content.value == '') {
		alert('제목과 내용을 입력하세요.');
		return false;
	}
	return true;
}
function boardList() {
	location.href = "";
}
</script>
</head>
<body>
<div id="wrapper">
	<h2>[ 게시판 글 쓰기 ]</h2>
	<form action="writeboard" method="post" enctype="multipart/form-data" onsubmit="return formCheck()">
	<input type="hidden" name="userid" value="${sessionScope.loginId}">
		<table>
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" id="title"></td>
			</tr>
			
			<tr>
				<th>내용</th>
				<td>
					<textarea rows="10" cols="50" name="content" id="content" ></textarea>
				</td>
			</tr>
			<tr>
				<th>글쓴이</th>
				<td>${loginId}</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td><input type="file" name="upload" /></td>
			</tr>
			<tr>
				<td class="btn" colspan="2" >
					<input type="button" value="목록" onclick="boardList()" />
					<input type="submit" value="저장">
				</td>
			</tr>
		</table>
	</form>
</div>	
</body>
</html>
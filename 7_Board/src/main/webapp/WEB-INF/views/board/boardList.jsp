<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link href="css/board/board.css" rel="stylesheet" />
<script type="text/javascript">
	function read(boardnum) {
		location.href = "detailboard?boardnum=" + parseInt(boardnum);
	}
</script>
</head>
<body>
<div id="wrapper">
	<h2>[ 게시판 ]</h2>
		<div class="left">
			<a class="btn" href="${pageContext.request.contextPath}">
				<img alt="Home" src="img/home.png" />
			</a>
		</div>
	<div>
		<!-- 검색 폼 -->
		<form class="right" action="listboard" method="get">
			<select name="searchItem">
				<option value="userid" ${searchItem == 'userid' ? 'selected' : '' }>작성자</option>
				<option value="title" ${searchItem == 'title' ? 'selected' : '' }>제목</option>
				<option value="content" ${searchItem == 'content' ? 'selected' : '' }>내용</option>
			</select>
			<input type="text" name="searchWord" value="${searchWord}" />
			<input type="submit" value="검색" />
		</form>	
	<table class="board">
		<tr>
			<th>번호</th>
			<th class="mainTitle">제목</th>
			<th>작성자</th>
			<th>조회수</th>
			<th>작성일</th>
		</tr>
		
		<c:if test="${empty list}">
			<tr>
				<td colspan="5">글이 없습니다.</td>
			</tr>	
		</c:if>
		<c:if test="${not empty list}">
			<%-- 이하 글 목록 반복 --%>
			<c:forEach var="board" items="${list}" varStatus="status">
				<tr>
					<td class="center">${status.count + navi.startRecord}</td>
					<td class="title">
						<a href="javascript:read('${board.boardnum}')">
							${board.title}
						</a>
						<c:if test="${not empty board.originalfile}">
							<img src="img/attachment.png" />
						</c:if>
					</td>
					<td>${board.userid}</td>
					<td class="center">${board.hitcount}</td>
					<td>${board.regdate}</td>
				</tr>
			</c:forEach>			
		</c:if>		
	</table>
	
	<!-- 로그인 하지 않으면 글쓰기를 할 수 없음 -->
<%-- 	<c:if test="${not empty sessionScope.loginId}"> --%>
		<div class="right">
			<a href="writeboard">글쓰기</a>
		</div>
<%-- 	</c:if> --%>
	
	<div class="boardfooter">
		<a href="listboard?currentPage=${navi.currentPage - navi.pagePerGroup}&searchItem=${searchItem}&searchWord=${searchWord}">◁◁</a>
		<a href="listboard?currentPage=${navi.currentPage - 1}&searchItem=${searchItem}&searchWord=${searchWord}">◀</a>
		&nbsp;&nbsp;
		<c:forEach var="page" begin="${navi.startPageGroup}" end="${navi.endPageGroup}">
			<c:if test="${page == currentPage}">
				<a style="color: #FF0000; font-weight: bold;">${page}</a>
			</c:if>
			<c:if test="${page != currentPage}">
				<a href="listboard?currentPage=${page}&searchItem=${searchItem}&searchWord=${searchWord}">${page}</a>
			</c:if>			 
		</c:forEach>
		&nbsp;&nbsp;
		<a href="listboard?currentPage=${navi.currentPage + 1}&searchItem=${searchItem}&searchWord=${searchWord}">▶</a>
		<a href="listboard?currentPage=${navi.currentPage + navi.pagePerGroup}&searchItem=${searchItem}&searchWord=${searchWord}">▷▷</a>
	</div>
	
</div>
</div>
</body>
</html>
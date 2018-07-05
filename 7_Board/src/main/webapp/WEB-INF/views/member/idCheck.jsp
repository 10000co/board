<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[ ID 중복확인 ]</title>
<script src="js/member/idCheck.js" type="text/javascript"></script>
</head>
<body>
	<div align="center">
		<h2>[ ID 중복확인 ]</h2>
		<form action="idCheck" method="post" onsubmit="return formCheck()">
			<input type="text" id="userid" name="userid" placeholder="아이디를 입력하세요" />
			<input type="submit" value="검색" />
		</form>
	</div>
	
	<br/>
	
	<div align="center">
		<c:if test="${m == null and pageContext.request.method eq 'POST'}">
			"${userid}"는 사용가능 합니다.
			<br/><br/>
			<input type="button" value="사용하기" onclick="return idSelect('${userid}')" />
		</c:if>
		<c:if test="${m != null and pageContext.request.method eq 'POST'}">
			"${userid}"는 사용할 수 없습니다.
		</c:if>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="refresh" content="5;url=${pageContext.request.contextPath}">
<title>Exception Handler</title>
<style type="text/css">
	div#wrapper {
		width: 700px;
		height: auto;
		margin: 0 auto;
		text-align: center;
	}
</style>
</head>
<body>

	<br/>
	<div id="wrapper">
		<img src="img/scull.png">
		<h2>[ 오류 발생 ]</h2>
		<p>${errorMsg}</p>
		<p>잠시 후 다시 이용해 주세요</p>
		<input type="button" value="Home" onclick="location.href='${pageContext.request.contextPath}'" />
	</div>
	
</body>
</html>
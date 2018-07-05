<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[ 로그인 ]</title>
<link href="css/member/loginForm.js" rel="stylesheet" />
<script src="js/member/loginForm.js" type="text/javascript" ></script>
</head>
<body>
	<div>	
	
		<h2>[ 로그인 ]</h2>
		
		<c:if test="${message != null}">
			<p id="msg" style="color: #FF0000;">
				${message}
			</p>
		</c:if>
		
		<c:set var="checked" value="${checked}" />
		
		<form action="login" method="post" onsubmit="return checkData()">
			<table>
				<tr>
					<td>아이디 : </td>
					<td>
						<input id="userid" name="userid" type="text" value="${cookie['saveid'].value}" />
						<input type="checkbox" name="saveid" ${checked} />아이디 저장
					</td>
				</tr>
				<tr>
					<td>비밀번호 : </td>
					<td><input id="userpwd" name="userpwd" type="password" /></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="로그인" />
					</td>
				</tr>
			</table>
		</form>
			
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script src="js/member/joinForm.js" type="text/javascript"></script>
</head>
<body>
<h2>[ 회원 가입 ]</h2>

<!-- 가입폼 시작 -->
<form id="form" action="join" method="post" >
	<table border="1">
		<tr>
			<th>아이디</th>
			<td>
				<input id="userid" type="text" value="" name="userid" readonly="readonly"/>
				<input type="button" value="ID중복확인" onclick="idCheckOpen()">
			</td>
		</tr>
 		<tr>
			<th>비밀번호</th>
			<td><input type="password" id="userpwd" name="userpwd" /></td>
		</tr>
		<tr>
			<th>이름</th>
			<td><input type="text" id="username" name="username" /></td>
		</tr>
		<tr>
			<th>성별</th>
			<td>
			<input class="gender" type="radio" name="gender" value="male" checked /> 남자
			<input class="gender" type="radio" name="gender" value="female" /> 여자
			</td>
		</tr>
		<tr>
			<th>취미</th>
			<td>
			<input class="hobby" type="checkbox" value="reading" /> 독서
			<input class="hobby" type="checkbox" value="fishing" /> 낚시
			<input class="hobby" type="checkbox" value="sleeping" /> 잠자기
			<input class="hobby" type="checkbox" value="watchingTV" /> TV 시청
			<input id="hobby" type="hidden" name="hobby" value="" />
			</td>
		</tr>
		<tr>
			<th>생년월일</th>
			<td>
				<input type="date" id="birth" name="birth" />
			</td>
		</tr>
		<tr>
			<th>나이</th>
			<td>
				<input type="text" id="age" name="age" />
			</td>
		</tr>
		<tr>
			<th>스마트폰</th>
			<td>
				<select id="phone1">
					<option value="010">010</option>
					<option value="019">019</option>
					<option value="018">018</option>
					<option value="017">017</option>
				</select>
				<input type="text" id="phone2" placeholder="-없이 숫자만 입력" />
				<input id="phone" name="phone" type="hidden" value="" />
			</td>
		</tr>
		<tr>
			<th>주소</th>
			<td>
				<select id="addr1">
					<option value="강남구">강남구</option>
					<option value="송파구">송파구</option>
					<option value="동작구">동작구</option>
					<option value="관악구">관악구</option>
					<option value="강동구">강동구</option>
				</select>
				<input type="text" id="addr2" placeholder="나머지 주소 입력" />
				<input id="address" name="address" type="hidden" value="" />
			</td>
		</tr>
		<tr>
			<th>자기소개</th>
			<td>
				<textarea name="introduce" cols="80" rows="8"></textarea>
			</td>
		</tr>					
		<tr>
			<th colspan="2">
				<input type="submit" value="회원가입" onclick="return dataCheck()" />
				<input type="reset" value="지우기" />
			</th>
		</tr>
	</table>
	</form>
</body>
</html>
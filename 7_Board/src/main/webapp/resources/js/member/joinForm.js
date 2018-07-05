function dataCheck() {
	var userid = document.getElementById("userid");
	var userpwd = document.getElementById("userpwd");
	var username = document.getElementById("username");
	var hobby = document.getElementsByClassName("hobby");
	var age = document.getElementById("age");
	var phone1 = document.getElementById("phone1");
	var phone2 = document.getElementById("phone2");
	var addr1 = document.getElementById("addr1");
	var addr2 = document.getElementById("addr2");
	
	if( userid.value.trim().length == 0 ) {
		alert("아이디를 입력하세요");
		return false;
	}
	
	if( userpwd.value.length < 3 || userpwd.value.length > 10 ) {
		alert("비밀번호는 3~10글자 크기로 정하세요");
		userpwd.select();
		userpwd.focus();
		return false;
	}
	if(username.value.trim().length == 0) {
		alert("이름을 입력하세요");
		username.select();
		username.focus();
		return false;
	}
	
	var h = "";
	for(var i=0; i<hobby.length; i++) {
		if(hobby[i].checked)
			h += hobby[i].value + " ";			
	}
	
	document.getElementById("hobby").value = h;
	
	if(isNaN(age.value)) {
		alert("나이는 숫자만 입력해주세요.");
		age.select();
		age.focus();
		return false;
	}
	
	if(addr2.value.trim().length == 0) {
		alert("주소를 입력해주세요.");
		addr2.select();
		addr2.focus();
		return false;
	}
	
	if(phone2.value.trim().length == 0) {
		alert("전화번호를 입력해주세요.");
		phone2.select();
		phone2.focus();
		return false;
	}
	
	var address = addr1.value + " " + addr2.value;	
	document.getElementById("address").value = address;
	
	var phone = phone1.value + phone2.value;
	document.getElementById("phone").value = phone;
	
	return true;
}

// ID중복확인 창 열기
function idCheckOpen() {
	window.open("idCheck", "newidwindow", "top=200,left=300,width=400,height=200");
}

// 중복되지 않는 아이디를 입력
function sendId(userid) {
	if(userid != null) {
		document.getElementById(userid).value = userid;	
	}
}

userpwd.value = userpwd.toLowerCase();


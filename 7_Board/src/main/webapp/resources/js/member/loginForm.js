function checkData() {
	var userid = document.getElementById("userid");
	var userpwd = document.getElementById("userpwd");
	
	if(userid.value.trim().length == 0) {
		alert("아이디를 입력하세요.");
		return false;
	}
	
	if(userpwd.value.trim().length == 0) {
		alert("비밀번호를 입력하세요.");
		return false;
	}
	
	userpwd.value = userpwd.value.toLowerCase();
}
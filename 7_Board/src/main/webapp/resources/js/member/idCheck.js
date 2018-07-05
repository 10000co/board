function formCheck() {
	var userid = document.getElementById("userid");
	
	if(userid.value.toLowerCase().trim().length == "") {
		userid.focus();
		return false;
	}
	
	if( userid.value.toLowerCase().length < 3 ) {
		alert('검색할 ID를 3자 이상 입력하세요.')
			return false;
		}
	}
	
	function idSelect(userid) {
		
		window.opener.document.getElementById("userid").value = userid.toLowerCase();
		this.close();
}



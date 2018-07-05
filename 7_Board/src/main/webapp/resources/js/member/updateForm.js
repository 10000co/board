window.onload = function() {
	
	var hobby = document.getElementById("hobby");
	var phone1 = document.getElementById("phone1");
	var phone2 = document.getElementById("phone2");
	var phone = document.getElementById("phone");
	var addr1 = document.getElementById("addr1");
	var addr2 = document.getElementById("addr2");
	var address = document.getElementById("address");
	var birth = document.getElementById("birth");
	
	var hobbyClass = document.getElementsByClassName("hobby");
	var hb = hobby.value.split(" ");
	
	var phone1Set = phone.value.substring(0,3);
	var phone2Set = phone.value.substring(3);
	
	var addrSplit = address.value.split(" ");

	var addr2Set = "";
	
	for(var i in hb) {
		switch(hb[i]) {
			case "reading" :
				hobbyClass[0].checked = true;
				break;
			case "fishing" :
				hobbyClass[1].checked = true;
				break;
			case "sleeping" :
				hobbyClass[2].checked = true;
				break;
			case "watchingTV" :
				hobbyClass[3].checked = true;
				break;
		}
	}
	
	switch(phone1Set) {
		case "010" : 
			phone1.selectedIndex = "0";
			break;
		case "019" : 
			phone1.selectedIndex = "1";
			break;
		case "018" : 
			phone1.selectedIndex = "2";
			break;
		case "017" : 
			phone1.selectedIndex = "3";
			break;
	}
	
	phone2.value = phone2Set;
	
	for(var i=0; i<addrSplit.length; i++) {
		if(i == 0) {
			switch(addrSplit[0]) {
				case "강남구" : 
					addr1.selectedIndex = "0";
					break;
				case "송파구" : 
					addr1.selectedIndex = "1";
					break;
				case "동작구" : 
					addr1.selectedIndex = "2";
					break;
				case "관악구" : 
					addr1.selectedIndex = "3";
					break;
				case "강동구" : 
					addr1.selectedIndex = "4";
					break;
			}
		}
		else {
			addr2Set += addrSplit[i];
			if(i < addrSplit.length-1) {
				addr2Set += " ";	
			}
		}
	}
	
	addr2.value = addr2Set;
	birth.value = birth.value.substring(0,11);
}

function dataCheck() {
	
	var userpwd = document.getElementById("userpwd");
	var phone1 = document.getElementById("phone1");
	var phone2 = document.getElementById("phone2");
	var phone = document.getElementById("phone");
	var addr1 = document.getElementById("addr1");
	var addr2 = document.getElementById("addr2");
	var address = document.getElementById("address");
	
	var hobby = document.getElementById("hobby");
	var hobbyClass = document.getElementsByClassName("hobby");
	var hb = hobby.value.split(" ");
	
	if( userpwd.value.length < 3 || userpwd.value.length > 10 ) {
		alert("비밀번호는 3~10글자 크기로 정하세요");
		userpwd.select();
		userpwd.focus();
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
	
	var hobbyClass = document.getElementsByClassName("hobby");
	hobby.value = "";
	
	for(var i=0; i<hobbyClass.length; i++) {
		if(hobbyClass[i].checked == true) {
			hobby.value += hobbyClass[i].value + " ";
		}
	}
	
	phone.value = phone1.value + phone2.value;
	address.value = addr1.value + " " + addr2.value;
	
	return true;
}

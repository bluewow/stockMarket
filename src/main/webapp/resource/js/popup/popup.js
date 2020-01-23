window.addEventListener("load", function(){
	
	//이메일주소 정규식
	function checkEmail(email){
		
		if(!/^[a-z0-9_+.-]+@([a-z0-9-]+\.)+[a-z0-9]{2,4}$/.test(email)){
			alert('올바른 이메일 주소를 입력하세요.');	
			return false;	
			}
		return true;
	}
	//비밀번호 정규식
	function checkPassword(id,password){
	
		if(!/^[a-zA-Z0-9]{8,16}$/.test(password)){
			alert('숫자와 영문자 조합으로 8~16자리를 사용해야 합니다.');	
			return false;	
			}
	
		var checkNumber = password.search(/[0-9]/g);	
		var checkEnglish = password.search(/[a-z]/ig);

		if(checkNumber <0 || checkEnglish <0){
			alert("숫자와 영문자를 혼용하여야 합니다.");
			return false;
			}
	
		if(/(\w)\1\1\1/.test(password)){
			alert('444같은 문자를 4번 이상 사용하실 수 없습니다.');
			return false;
			}
		
		if(password.search(id) > -1){
			alert("비밀번호에 아이디가 포함되었습니다.");
			return false;
			}
		return true;
	}
	
	profilePhotoFunc();
	loginFunc();	//로그인 팝업
	buttonFunc();	//로그인/로그아웃/프로필 버튼 
	hiddenFunc();	//팝업 hidden
	signUpFunc();	//회원가입 팝업
	profileFunc();	//프로필설정 팝업
	
	////////////////////////////
	//로그인 팝업 
	////////////////////////////
	function loginFunc() {
	    var loginPopup = document.querySelector(".pop-up");
	    var signupOn = document.querySelector(".sign-up-pop-up");
	    
	    loginPopup.onclick = function(e) {
	        if(e.target.nodeName != "INPUT")
	            return;

	        //prevent Event Bubble
	        e.preventDefault();

	        if(e.target.value =="로그인") {
	            //TODO 이메일 양식 비밀번호 체크
	        	var form = loginPopup.querySelector(".login-box");
//	        	document.querySelector("#login").submit();

	        	form.submit();
	            
	        }
	        else if(e.target.value == "무료회원가입") {
	            loginPopup.style.visibility = "hidden";
	            signupOn.style.visibility = "visible";

	        } else if(e.target.value == "이메일/비밀번호 찾기") {
	            alert("이메일");
	        } 
	    }
	}
	
	////////////////////////////
	//로그인/로그아웃/프로필 버튼 관련 함수
	////////////////////////////
	function buttonFunc() {
	    var popupOn = document.querySelector(".personal");
	    var wrapper = document.querySelector(".pop-up-wrapper");

	    popupOn.onclick = function(e) {
	        //로그인, 로그아웃, UserName filter
	        if(e.target.nodeName != "INPUT")
	            return;

	        //prevent Event Bubble
	        e.preventDefault();

	        if(e.target.value == "로그인") {
	            //로그인 창 및 background color visible
	            var loginPopup = document.querySelector(".pop-up");
	            wrapper.style.visibility = "visible";
	            loginPopup.style.visibility = "visible";

	        } else if(e.target.value == "로그아웃") {
	            //로그아웃시 session 만료
	            var request = new XMLHttpRequest();
	            request.open("GET", "/login?loginStatus=logout");
	            request.onload = function() {
	                location.reload(true);
	            }
	            request.send();

	        } else {
	            //프로필
	             var profilePopup = document.querySelector(".profile-pop-up");
	             wrapper.style.visibility = "visible";
	             profilePopup.style.visibility = "visible";
	        }
	    }
	}
	
	
	////////////////////////////
	//팝업 hidden 처리
	////////////////////////////
	function hiddenFunc() {
	    window.onmousedown = function(e) {
	        var wrapper = document.querySelector(".pop-up-wrapper");
	        var loginPopup = document.querySelector(".pop-up");
	        var signupPopup = document.querySelector(".sign-up-pop-up");
		    var profileImage = document.querySelector(".pop-up-profile-image");
		    var profilePopup = document.querySelector(".profile-pop-up");
	        if (e.target == wrapper) {
	            wrapper.style.visibility = "hidden";
	            loginPopup.style.visibility = "hidden";
	            signupPopup.style.visibility = "hidden";
	            profilePopup.style.visibility = "hidden";
	            profileImage.style.visibility = "hidden";
	        }
	    }
	}
	
	////////////////////////////
	//회원가입 팝업
	////////////////////////////
	function signUpFunc() {
	    var signupPopup = document.querySelector(".sign-up-pop-up");
	    var email = signupPopup.querySelector(".signup-email");
	    var nickname = signupPopup.querySelector(".signup-nickname");
	    var pwd = signupPopup.querySelector(".signup-pwd");
	    var pwdConfirm = signupPopup.querySelector(".signup-pwd-confirm");

		var duplicatedStateSpan = document.querySelector("#duplicated-state");
		var tid = null;
		var idChecked = false;
		
	    nickname.oninput = function(e){
			var request = new XMLHttpRequest();
			request.open("GET", "../../member-profile?nickname="+nickname.value);
			request.onload = function(){
				if(request.responseText == "false"){
					idChecked = true;
					duplicatedStateSpan.innerText = "사용가능한 닉네임입니다.";
				}
				else{
					idChecked = false;
					duplicatedStateSpan.innerText = "이미 사용중인 닉네임입니다.";
				}
			};
			
			if(tid != null){
				clearTimeout(tid);
				tid = null;
			}
			
			tid = setTimeout(function() {							
    			request.send();
    			tid = null;
			}, 500);
		};

	    
	    
	    
	    signupPopup.onclick = function(e) {
	        if(e.target.nodeName != "INPUT")
	            return;

	        //prevent Event Bubble
	        e.preventDefault();

	        if(e.target.nodeName == "userEmail") {
	            alert("TEST");
	        } else if(e.target.nodeName == "nickName") {

	        } else if(e.target.nodeName == "pwd") {
	        
	        } else if(e.target.nodeName == "checkPwd") {
	        
	        } else if(e.target.value == "회원가입") {
			    
			    checkEmail(email.value);
			    checkPassword(email.value, pwd.value);


	        	document.querySelector("#signup").submit();

	        }
	    }
	}
	
	////////////////////////////
	//처음 프로필 화면
	////////////////////////////
	function profilePhotoFunc() {
		var loginStatus = document.querySelector(".personal").childNodes[1].nextElementSibling;
	    var profilePopup = document.querySelector(".profile-pop-up");
	    var profileImage = profilePopup.querySelector(".pop-up-top-image");
        var changePhoto = profileImage.getElementsByClassName("profile-photo-modi")[0];


		if(loginStatus.value!="로그인") {
			var userId = loginStatus.value;
			var sendData = "loginNickname="+userId;
	
			var request = new XMLHttpRequest(); 
			request.open("POST", "../../member-profile", true);
			request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
			request.send(sendData);
			
			request.onload = function () {
				var photoImg = JSON.parse(request.responseText);
			    var profilePhoto = document.querySelector(".profile-photo");
				
			    profilePhoto.parentNode.innerHTML =
			    	`<img src="/images/profile/${photoImg}.png" 
			    	alt="profile photo" class="circle float-left profile-photo"
			    	 width="60" height="auto">
			    	<input class="small animation-2" type="button" value="${userId}">
			    	 <input class="animation-2" type="button" value="로그아웃">`;
		        changePhoto.parentNode.innerHTML = 
		        	`<img src="/images/profile/${photoImg}.png" 
		        	alt="profile photo" class="circle float-left profile-photo-modi"
		        	data-id="${photoImg}">`;
			}
		}
	}
	////////////////////////////
	//프로필설정 팝업
	///////////////////////////
	function profileFunc() {

        var wrapper = document.querySelector(".pop-up-wrapper");
	    var profilePopup = document.querySelector(".profile-pop-up");
	    var profileImage = profilePopup.querySelector(".pop-up-top-image");	
	    var sectionImg = document.querySelector(".pop-up-profile-image");
	    var profileImageList = sectionImg.querySelector(".profile-image-list");
        var submitButton = profilePopup.querySelector(".login-box");
        var imgSelectButton = sectionImg.querySelector(".profile-img-select-submit");
        var profileImg = profilePopup.querySelector(".profile-photo-modi");
    	var currentPwd = profilePopup.querySelector(".currentPwd");
    	var newPwd = profilePopup.querySelector(".newPwd");
    	var checkPwd = profilePopup.querySelector(".checkPwd");
	    var currentSelect;
	    //프로필 이미지 클릭 시
	    profileImage.onclick = function(e) {
	        if(e.target.nodeName != "IMG")
	            return;

	        //prevent Event Bubble
	        e.preventDefault();
	        var nowImg = e.target.dataset.id;
	        sectionImg.style.visibility = "visible";
	        var list = "";
	        	for(var i=1; i<=36; i++) {
	        		if(i==nowImg) 
		        		var photos =  `<img src="/images/profile/${i}.png" 
			        	alt="profile photo" class="images image-selected"
			        	 data-id="${i}">`
	        		else if(i!=nowImg) 
		        		var photos =  `<img src="/images/profile/${i}.png" 
			        	alt="profile photo" class="images"
			        	 data-id="${i}">`
	        	        list = list + photos;
	        	}

	        profileImageList.innerHTML = list;
	        currentSelect = profileImageList.getElementsByClassName("image-selected")[0];
	    }
	    
	    //확인버튼 클릭 시
	    submitButton.onclick = function(e) {
        if(e.target.nodeName != "INPUT")
            return;

        //prevent Event Bubble
        e.preventDefault();
        
        //데이터 준비
    	var data = [
			["currentPwd", currentPwd.value],
			["newPwd", newPwd.value]
			]
		var sendData = [];

		for (var i = 0; i < data.length; i++) {
			sendData[i] = data[i].join("=");
			}
		
		sendData = sendData.join("&");

		//데이터 전송
		var request = new XMLHttpRequest();
		request.open("POST", "../../member-profile", true);
		request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		request.send(sendData);	
		
		//결과를 응답받고 출력
		request.onload = function () {
			var returnData = request.responseText;
			if(returnData=="1"){
				alert("비밀번호가 변경되었습니다.")
				currentPwd.value=null;
				newPwd.value=null;
				checkPwd.value=null;
	            wrapper.style.visibility = "hidden";
	            profilePopup.style.visibility = "hidden";
	            
			} else if(returnData=="wrong") {
				alert("현재 비밀번호가 맞지 않습니다.")
				currentPwd.value=null;
				newPwd.value=null;
				checkPwd.value=null;
				
			} else if(returnData=="same"){
				alert("현재 비밀번호가 변경하려는 비밀번호와 동일합니다.")
				currentPwd.value=null;
				newPwd.value=null;
				checkPwd.value=null;
		}
		
		}
	    }

	    //프로필 이미지 리스트중 하나를 클릭했을 시
	    profileImageList.onclick = function(e) {
	        if(e.target.nodeName != "IMG")
	            return;
	        e.preventDefault();
	        
	        currentSelect.classList.remove("image-selected");
	        e.target.classList.add("image-selected");
	        currentSelect = profileImageList.getElementsByClassName("image-selected")[0];
	    }
	    
	    //프로필 이미지 선택 후 확인버튼 클릭시
        imgSelectButton.onclick = function(e) {
	        if(e.target.nodeName != "INPUT")
	            return;
	        e.preventDefault();
	        var selectPhoto = currentSelect.dataset.id;
	        var changePhoto = profileImage.getElementsByClassName("profile-photo-modi")[0];
	        changePhoto.parentNode.innerHTML = 
	        	`<img src="/images/profile/${selectPhoto}.png" 
	        	alt="profile photo" class="circle float-left profile-photo-modi"
	        	data-id="${selectPhoto}">`;
	        //데이터 준비
	    	var data = [
				["profileImg", selectPhoto]
				]
			var sendData = [];

			for (var i = 0; i < data.length; i++) {
				sendData[i] = data[i].join("=");
				}
			
			sendData = sendData.join("&");

			//데이터 전송
			var request = new XMLHttpRequest();
			request.open("POST", "../../member-profile", true);
			request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
			request.send(sendData);	
			
			request.onload = function () {
				var loginStatus = document.querySelector(".personal").childNodes[1].nextElementSibling;
				var userId = loginStatus.value;
				var lastReplyNum = request.responseText;
				alert("프로필 이미지가 변경되었습니다.");
			    var profilePhoto = document.querySelector(".profile-photo");
			    
			    //변경된 이미지를 프로필 팝업과 메인페이지 변경하는 코드
			    profilePhoto.parentNode.innerHTML =
			    	`<img src="/images/profile/${selectPhoto}.png" 
			    	alt="profile photo" class="circle float-left profile-photo"
			    	 width="50" height="auto">
			    	<input class="small animation-2" type="button" value="${userId}">
			    	 <input class="animation-2" type="button" value="로그아웃">`;
			   
			    //변경된 이미지를 랭크보드로 넘겨주는 코드
			    var rankingBoardWindow = parent.document.querySelector("#ranking-board-window");
			    var sendData = ["selectPhoto", selectPhoto]
			    rankingBoardWindow.contentWindow.postMessage(sendData,
				"http://localhost:8080/card/rank/ranking.jsp");
		
				sectionImg.style.visibility = "hidden";
			}
	        
        }
	    
	}
});







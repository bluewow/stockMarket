window.addEventListener("load", function() {
	
	//쿠키 삭제하는 함수
	function deleteCookie( cookieName ) {
		var expireDate = new Date();
		//어제 날짜를 쿠키 소멸 날짜로 설정한다.
		expireDate.setDate( expireDate.getDate() - 1 );
		document.cookie = cookieName + "= " + "; expires=" + expireDate.toGMTString() + "; path=/";
	}

		//메인페이지에서 검색버튼 누르면 자동검색
		var searchText = document.querySelector("#search-text");
		var textSub = document.cookie.substr(8,20);
		searchText.setAttribute('value', textSub);
		if(textSub!=""){
			document.querySelector(".search-button").click();
			textSub="";
			deleteCookie("keyword");
			}
		
	// codeNum 전달이벤트
	
	/*---------------------------------------------------------*/
	(function(){
		var links = document.querySelectorAll("#stockItemName");
		  for(var i=0; i < links.length; i++){
		     var a = links[i];
		     if(a.title !== ''){
		       a.addEventListener('mouseenter',createTip);
		       a.addEventListener('mouseleave',cancelTip);
		     }
		    //console.log(a);
		  } 
		  function createTip(ev){
		      var title = this.title;
		      this.title = '';
		      this.setAttribute("tooltip", title);
		      var tooltipWrap = document.createElement("div"); //creates div
		      tooltipWrap.className = 'tooltip'; //adds class
		      tooltipWrap.appendChild(document.createTextNode(title)); //add the text node to the newly created div.

		      var firstChild = document.body.firstChild;//gets the first elem after body
		      firstChild.parentNode.insertBefore(tooltipWrap, firstChild); //adds tt before elem 
		      var padding = 5;
		      var linkProps = this.getBoundingClientRect();
		      var tooltipProps = tooltipWrap.getBoundingClientRect(); 
		      var topPos = linkProps.top - (tooltipProps.height + padding);
		      tooltipWrap.setAttribute('style','top:'+topPos+'px;'+'left:'+linkProps.left+'px;');
		    setTimeout(()=>{
		 tooltipWrap.style.transform = "translateY(-100%) scale(1)"
		      
		},300)
		   
		      
		  }
		  function cancelTip(ev){
		      var title = this.getAttribute("tooltip");
		      this.title = title;
		      this.removeAttribute("tooltip");
		      document.querySelector(".tooltip").remove();
		  }
		})();
	
	
	
	
	
	/*---------------------------------------------------------*/
	
	sendEvent();

	function sendEvent() {
		var companyName = document.querySelectorAll(".companyName");
		if (companyName == null)
			return;

		for (i = 0; i < companyName.length; i++) {
			companyName[i].onclick = function(e) {
				var codenum = e.target.dataset.codenum;
				var stockBoardWindow = parent.document
						.querySelector("#stock-board-window");
				var analysisWindow = parent.document
						.querySelector("#analysis-window");
				var tradeWindow = parent.document
						.querySelector("#trade-window");
				var interestWindow = parent.document
				.querySelector("#interestlist-window");

				stockBoardWindow.contentWindow.postMessage(codenum,
						"http://localhost:8080/card/board/stock_board.jsp");
				analysisWindow.contentWindow.postMessage(codenum,
						"http://localhost:8080/card/trade/analysis.jsp");
				tradeWindow.contentWindow.postMessage(codenum,
						"http://localhost:8080/card/trade/trading.jsp");
				interestWindow.contentWindow.postMessage(codenum,
				"http://localhost:8080/card/managestocks/interestlist.jsp");
				
			}
		}
	}

	var UncertifiedLogin = document.querySelectorAll("#UncertifiedLogin");
	var certifiedLoing = document.querySelectorAll("#certifiedLoing");
	// 로그인이 안됐을 경우
	if (UncertifiedLogin != null) {
		for (var i = 0; i < UncertifiedLogin.length; i++) {
			UncertifiedLogin[i].onclick = function() {
				alert("로그인이 필요한 서비스입니다.");
			};
		}
	}
	;
	// 로그인이 됐을 경우
	if (certifiedLoing != null) {
		// 관심을 querySelectorAll로 전체 선택한 다음에 사용자가 클릭 했을 경우 클릭한 회사 종목 코드를 관심목록 카드로
		// 넘기는 코드
		for (var i = 0; i < certifiedLoing.length; i++) {
			certifiedLoing[i].onclick = function(e) {
				var checking = true;
				var attention = e.target.dataset.attention;
				var interestlistWindow = parent.document
						.querySelector("#interestlist-window");

				//console.log(attention);

				interestlistWindow.contentWindow.postMessage(attention,
						"http://localhost:8080/card/board/stock_board.jsp");

				// ajax로 종목 코드를 컨트롤러에 전달한 뒤에 데이터 베이스에 저장하는 코드

				// ========= 즐겨찾기 추가 ==================
				if (e.target.classList.contains("interest_no")) {
					var data = [ [ "attention", attention ],
							[ "status", "insert" ] ]
					
					var sendData = [];

					for (var i = 0; i < data.length; i++)
						sendData[i] = data[i].join("=");

					sendData = sendData.join("&");

					var request = new XMLHttpRequest();
					request.open("POST", "../../card/company/list-json");
					request.setRequestHeader('Content-Type',
							'application/x-www-form-urlencoded');
					request.send(sendData);

					request.onload = function() {
						alert("추가되었습니다.");
						e.target.classList.remove("interest_no");
						e.target.classList.add("interest_yes");
					}

				} else if (e.target.classList.contains("interest_yes")) {

					var data = [ [ "attention", attention ],
							[ "status", "delete" ] ]
					var sendData = [];
					console.log(attention);
					for (var i = 0; i < data.length; i++) {
						sendData[i] = data[i].join("=");
					}
					sendData = sendData.join("&");

					var request = new XMLHttpRequest();
					request.open("POST", "../../card/company/list-json", true);
					request.setRequestHeader('Content-Type',
							'application/x-www-form-urlencoded');
					request.send(sendData);

					// 3. 요청이 완료되었는지 결과를 확인한다.
					request.onload = function() {
						alert("삭제되었습니다.");
						e.target.classList.remove("interest_yes");
						e.target.classList.add("interest_no");
					};

				}

			};
		};
	};
	
	

});

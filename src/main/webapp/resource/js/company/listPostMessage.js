window.addEventListener("message", function(e) {
		

	codeNum = e.data;
	//console.log(codeNum);
	
	
	

		// 쿼리 셀렉터를 이용해서 class comapanyName을 찾는다.
		if (codeNum.indexOf('del') != -1) {
			// attention안에 del 코드가 있기 때문에

			var test = document.querySelectorAll(".companyName");
			//console.log(test[0].textContent); // 회사명
			//console.log(test[0].dataset.codenum); // 회사명에 있는 종목코드를 뽑는 것
			
			
			// 관심종목에서 넘어온 codeNum을 검색창에 있는 관심 목록을 삭제
			console.log("del있으면 출력")
			
			var codeNum = codeNum.split(',');
			var attention = codeNum[1];
			
			//console.log(attention); //회사명이 출력
			
			
			for ( var index in test ) {
				//console.log(`${companyName}`);
				console.log(test[index].textContent); //공백제거 , 회사명
				
				var replaceTest = test[index].textContent;
				
				
				//console.log(replaceTest.replace(/\s*$/gi, "")); 
				
				//.replace(/\s$/gi,"")
//				if (attention == test[index].textContent) {
//					console.log(test[index].dataset.codenum);
//				}
				
				
				//console.log(attention.match(test[index].textContent));
			}
			
			

//			var data = [ [ "attention", attention ], [ "status", "delete" ] ]
//			var sendData = [];
//			console.log(attention);
//			for (var i = 0; i < data.length; i++) {
//				sendData[i] = data[i].join("=");
//			}
//			sendData = sendData.join("&");
//
//			var request = new XMLHttpRequest();
//			request.open("POST", "../../card/company/list-json", true);
//			request.setRequestHeader('Content-Type',
//					'application/x-www-form-urlencoded');
//			request.send(sendData);
//
//			// 3. 요청이 완료되었는지 결과를 확인한다.
//			request.onload = function() {
//				alert("삭제되었습니다.");
//				e.target.classList.remove("interest_yes");
//				e.target.classList.add("interest_no");
//			};

		} else if (codeNum.indexOf('del') == -1) {
			
			var stockBoardWindow = parent.document
			.querySelector("#stock-board-window");
			var analysisWindow = parent.document
					.querySelector("#analysis-window");
			var tradeWindow = parent.document
					.querySelector("#trade-window");
			var interestWindow = parent.document
			.querySelector("#interestlist-window");
					
			stockBoardWindow.contentWindow.postMessage(codeNum,
					parent.stockURL + "/card/board/stock_board");
			analysisWindow.contentWindow.postMessage(codeNum,
					parent.stockURL + "/card/trade/analysis");
			tradeWindow.contentWindow.postMessage(codeNum,
					parent.stockURL + "/card/trade/trading");
			interestWindow.contentWindow.postMessage(codeNum,
					parent.stockURL + "/card/managestocks/interestlist");


		}
	

});

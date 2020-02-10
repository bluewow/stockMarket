window.addEventListener("message", function(e) {
		

	codeNum = e.data;
	//console.log(codeNum);
	
		// 쿼리 셀렉터를 이용해서 class comapanyName을 찾는다.
		if (codeNum.indexOf('del') != -1) {
			// 삭제해야될 코드는 codeNum 앞에 del이 붙어서 온다.

			var companyNames = document.querySelectorAll(".companyName");
			//console.log(test[0].textContent); // 회사명
			//console.log(test[0].dataset.codenum); // 회사명에 있는 종목코드를 뽑는 것
			
			// 관심종목에서 넘어온 codeNum을 검색창에 있는 관심 목록을 삭제
			
			var codeNum = codeNum.split(','); // , 기준으로 String을 짜른뒤에
			var attentionSplit = codeNum[1]; // 앞에 del, 는 날리고 그 뒤에 있는 종목명만 변수에 담아준다.
			
			for ( var index in companyNames ) {
				
				var replaceTest = companyNames[index].textContent; // companyNames에서 회사명만 변수에 담는다.
				replaceTest = replaceTest.replace(/\s*$/, "") // 회사명 공백 제거
				
				if (replaceTest == attentionSplit) { 
					//console.log(companyNames[index]);
					var attention = companyNames[index].dataset.codenum;
					
					var data = [ [ "attention", attention ], [ "status", "delete" ] ]
					var sendData = [];
					console.log(attention);
					for (var i = 0; i < data.length; i++) {
						sendData[i] = data[i].join("=");
					}
					sendData = sendData.join("&");

					var request = new XMLHttpRequest();
					request.open("POST", "../../card/company/list-json");
					request.setRequestHeader('Content-Type',
							'application/x-www-form-urlencoded');
					request.send(sendData);

					// 3. 요청이 완료되었는지 결과를 확인한다.
					request.onload = function() {
						//alert("post Message 삭제되었습니다.");
						//e.target.classList.remove("interest_yes");
						//e.target.classList.add("interest_no");
						
						var interest_yes = document.querySelectorAll(".interest_yes");
						//console.log(attention);
						//console.log(interest_yes[0].dataset.attention);
						//.dataset.attention
						
						for ( var innerIndex in interest_yes) {
							if (interest_yes[innerIndex].dataset.attention == attention){
								
								//var interestYesDataset =  interest_yes[innerIndex].dataset;
								//console.log(interestYesDataset);
								interest_yes[innerIndex].classList.remove("interest_yes");
								interest_yes[innerIndex].classList.add("interest_no");
							}
							
							
						}
						
					};
				}
				
				
				
			}
			
			

			

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

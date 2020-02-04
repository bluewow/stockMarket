window.addEventListener("message", function (e) {
	 //codeNum 확인
	if (e.data && (e.data.length == 6)){
		//console.log("stockBoard : " + e.data);

	var sectionTop = document.querySelector("#stockTop");
	var selectStock = sectionTop.querySelector("#selected-stock");
	var myButton = sectionTop.querySelector("#my-button");
	var interestButton = sectionTop.querySelector("#favo-button");
	var section = document.querySelector("#stockScroll");
	var tbody = section.querySelector("table tbody");
	var pager = section.querySelector(".pager");
	var regBoard = sectionTop.querySelector("#reg-button");
	var regBoardForm = document.querySelector(".pop-up-reg");
	var regSubmit = regBoardForm.querySelector(".pop-up-content-row");
	var stockCode = e.data;
	var sortBoard = "";

	//마이버튼 클릭	
	myButton.onclick = function (e) {
		if (e.target.nodeName != "A")
			return;

		e.preventDefault();
		if(sortBoard=="") {
			sortBoard = "my";
			myButton.classList.add("button-on");
		} else if(sortBoard=="interest") {
			sortBoard = "my";
			myButton.classList.add("button-on");
			interestButton.classList.remove("button-on");
		} else if(sortBoard=="my") {
			sortBoard = "";
			myButton.classList.remove("button-on");
		}
		load(1, stockCode, sortBoard);
	};
	
	//즐겨찾기버튼 클릭	
	interestButton.onclick = function (e) {
		if (e.target.nodeName != "A")
			return;

		e.preventDefault();
		if(sortBoard=="") {
			sortBoard = "interest";
			interestButton.classList.add("button-on");
		} else if(sortBoard=="my") {
			sortBoard = "interest";
			interestButton.classList.add("button-on");
			myButton.classList.remove("button-on");
		} else if(sortBoard=="interest") {
			sortBoard = "";
			interestButton.classList.remove("button-on");
		}
		load(1, stockCode, sortBoard);
	};
	
	//리스트 불러오기	
	load = function (page, stockCode, sortBoard) {

		var request = new XMLHttpRequest();
		if(sortBoard=="")
			request.open("GET", "../../card/board/stock_board_list?p=" + page+"&s="+stockCode);
		else if(sortBoard=="my")
			request.open("GET", "../../card/board/stock_board_list?f=writerId&q=my&p=" + page+"&s="+stockCode);
		else if(sortBoard=="interest")
			request.open("GET", "../../card/board/stock_board_list?f=interest&p=" + page+"&s="+stockCode);

		
		request.onload = function () {
			//페이지 번호 넘버링
			var startNum = (page - 2);
			if (startNum <= 1)
				startNum = 1;
			
			pager.querySelector(".pn1").innerText = startNum+0;
			pager.querySelector(".pn2").innerText = startNum+1;
			pager.querySelector(".pn3").innerText = startNum+2;
			pager.querySelector(".pn4").innerText = startNum+3;
			pager.querySelector(".pn5").innerText = startNum+4;
			
			if(page==startNum+0){
				pager.querySelector(".pn1").classList.add("select-page");
				pager.querySelector(".pn2").classList.remove("select-page");
				pager.querySelector(".pn3").classList.remove("select-page");
				pager.querySelector(".pn4").classList.remove("select-page");
				pager.querySelector(".pn5").classList.remove("select-page");
			}
			else if(page==startNum+1){
				pager.querySelector(".pn1").classList.remove("select-page");
				pager.querySelector(".pn2").classList.add("select-page");
				pager.querySelector(".pn3").classList.remove("select-page");
				pager.querySelector(".pn4").classList.remove("select-page");
				pager.querySelector(".pn5").classList.remove("select-page");
			}
			else if(page==startNum+2){
				pager.querySelector(".pn1").classList.remove("select-page");
				pager.querySelector(".pn2").classList.remove("select-page");
				pager.querySelector(".pn3").classList.add("select-page");
				pager.querySelector(".pn4").classList.remove("select-page");
				pager.querySelector(".pn5").classList.remove("select-page");
			}
			else if(page==startNum+3){
				pager.querySelector(".pn1").classList.remove("select-page");
				pager.querySelector(".pn2").classList.remove("select-page");
				pager.querySelector(".pn3").classList.remove("select-page");
				pager.querySelector(".pn4").classList.add("select-page");
				pager.querySelector(".pn5").classList.remove("select-page");
			}
			else if(page==startNum+4){
				pager.querySelector(".pn1").classList.remove("select-page");
				pager.querySelector(".pn2").classList.remove("select-page");
				pager.querySelector(".pn3").classList.remove("select-page");
				pager.querySelector(".pn4").classList.remove("select-page");
				pager.querySelector(".pn5").classList.add("select-page");
			}		
			
			var listData = JSON.parse(request.responseText);
			var trTemplate = section.querySelector(".tr-template");
			var loginUser = listData.loginUser;
			selectStock.innerText = listData.stockName;
			tbody.innerHTML = "";
			for (var i = 0; i < listData.list.length; i++) {
				var cloneTr = document.importNode(trTemplate.content, true);
				var tds = cloneTr.querySelectorAll("td");

				tds[0].innerText = listData.list[i].id;
				var aTagDetail = tds[1].firstElementChild;
				aTagDetail.innerHTML = '<span class="stock-name">'
					+ "[" + listData.list[i].companyName + "]"
					+ '</span>'
					+ '<span class="title"> '
					+ listData.list[i].title
					+ ' </span>'
					+ '(<span class="reply-cnt">'
					+ listData.list[i].replyCnt + '</span>)';
				aTagDetail.dataset.id = listData.list[i].id;
				aTagDetail.dataset.replyCnt = listData.list[i].replyCnt;
				tds[2].innerText = listData.list[i].regdate;
				tds[3].innerText = listData.list[i].hit;
				tds[4].innerHTML = '<a href class="interest_no" data-id="' + listData.list[i].id + '"></a>';
				if(listData.list[i].interest != '0')
					tds[4].innerHTML = '<a href class="interest_yes" data-id="' + listData.list[i].id + '"></a>';
				
				tds[5].innerHTML = '<a href class="del-content" data-id="' + listData.list[i].id + '"></a>';
				if (loginUser != listData.list[i].writerId)
					tds[5].innerHTML = '<td></td>'
				tds[6].innerText = listData.list[i].writerId;
			
				tbody.append(cloneTr);
			}
			;
		};
		request.send();
	}
	load(1, stockCode, sortBoard);

	pager.onclick = function (e) {
		if (e.target.nodeName != "A")
			return;

		e.preventDefault();
		load(e.target.innerText, stockCode, sortBoard);
	};

	// ========= 글내용과 댓글 불러오기 ==================
	var titleClickHandler = function (e) {
		var currentTr = e.target.parentNode.parentNode.parentNode;
		var target = currentTr.nextElementSibling;
		var nextTr = target.nextElementSibling;

		// 이미 내용이 있으면 닫아주세요.
		if(nextTr) {
			if (nextTr.classList.contains("content-row")) {
				var row = nextTr.nextElementSibling;
				row.parentNode.removeChild(row);
				nextTr.parentNode.removeChild(nextTr);
				return;
				};
		};
		// 내용을 로딩중이면 표시해주세요.
		if (e.target.parentNode.parentNode.lastChild.nodeName == "IMG") {
			alert("로딩중입니다~");
			return;
		}

		var id = e.target.parentNode.dataset.id;
		// 로딩을 표시
		var ajaxIcon = document.createElement("img");
		ajaxIcon.src = "../../../../resource/images/delay-icon.gif";
		e.target.parentNode.parentNode.append(ajaxIcon);

		// 데이터를 요청
		var request = new XMLHttpRequest();
		request.open("GET", "../../card/board/detail?id=" + id, true);

		// 요청값을 받아오면 실행
		request.onload = function () {
			var detail = JSON.parse(request.responseText);
			var template = section.querySelector(".detail-template");
			var cloneTr = document.importNode(template.content, true);
			var td = cloneTr.querySelector(".content-row td");

			if(detail.loginUser == detail.board.writerId){
				td.innerHTML = '<span class="content-detail">'
					+ detail.board.content + '</span><br><a href="" class="content-modi" data-id="'
					+ detail.board.id + '">수정</a>';
			} else {
				td.innerHTML = '<span class="content-detail">'
					+ detail.board.content + '</span><br><a href="" class="content-modi hidden" data-id="'
					+ detail.board.id + '">수정</a>';
			}
			
			var replyContent = cloneTr.querySelector(".replyTable tbody tr td");
			var contentSum = "";
			var aTagDetail = cloneTr.querySelector(".reg-reply-button");
			aTagDetail.dataset.id = id;
			aTagDetail.dataset.writerId = detail.loginUser;
			for (var i = 0; i < detail.replys.length; i++) {
				if(detail.loginUser == detail.replys[i].writerId){
					contentSum += '<div><span class="re-writer">'
						+ detail.replys[i].writerId
						+ ' : </span><span class="re-content">'
						+ detail.replys[i].reContent
						+ '</span><input type="text" class="reply-modi-content hidden" name="title" maxlength="200" placeholder="주제와 무관한 댓글, 악플은 징계 대상이 됩니다." value="'
						+ detail.replys[i].reContent
						+ '"></input><span class="modi-box"><a href="" class="re-modi" data-id="'
						+ detail.replys[i].id
						+ '">수정</a>  <a href="" class="re-del" data-id="'
						+ detail.replys[i].id
						+ '">삭제</a><a href="" class="re-commit hidden" data-id="'
						+ detail.replys[i].id
						+ '">확인</a>  <a href="" class="re-cancel hidden" data-id="'
						+ detail.replys[i].id
						+ '">취소</a></span></div>';
				} else {
					contentSum += '<div><span class="re-writer">'
						+ detail.replys[i].writerId
						+ ' : </span><span class="re-content">'
						+ detail.replys[i].reContent
						+ '</span><input type="text" class="reply-modi-content hidden" name="title" maxlength="200" placeholder="주제와 무관한 댓글, 악플은 징계 대상이 됩니다." value="'
						+ detail.replys[i].reContent
						+ '"></input></div>';
				}				

			}
			replyContent.innerHTML = contentSum;
			targetContent = cloneTr.querySelector(".content-row");
			targetreply = cloneTr.querySelector(".reply-content-row");
			target.insertAdjacentElement('afterend', targetreply);
			target.insertAdjacentElement('afterend', targetContent);

			ajaxIcon.remove();
			ajaxIcon = undefined;
		};
		request.send();
	};

	// ========= 댓글쓰기 ==================
	var regReplyButtonClickHandler = function (e) {

		// 1. 입력한 값을 얻어온다.

		var boardId = e.target.dataset.id;
		var reContent = e.target.parentNode.parentNode.querySelector('.reply-content').value;

		//태그 없애는 정규식
		reContent = reContent.replace(/(<([^>]+)>)/ig,"");
		
		var reContentEncode = encodeURI(reContent);
		var changeReplyCnt = e.target.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode.querySelector('.reply-cnt');

		String.prototype.trim = function () {
			return this.replace(/^\s+|\s+$/g, "");
		}

		var str = reContent;
		str = str.trim();

		// 댓글내용이 없으면 알림을 하고 되돌아간다.
		if (str == "") {
			alert("내용을 작성하세요");
			return;
		}

		var data = [["boardId", boardId],
		["reContent", reContentEncode],]
		var sendData = [];

		for (var i = 0; i < data.length; i++) {
			sendData[i] = data[i].join("=");
		}
		sendData = sendData.join("&");
		// 2. 값을 서버에 보낸다.

		var request = new XMLHttpRequest();
		request.open("POST", "../../card/board/reply_insert", true);
		request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		request.send(sendData);

		// 3. 요청이 완료되었는지 결과를 확인한다.
		request.onload = function () {
			var lastReplyNum = JSON.parse(request.responseText);
			alert("등록되었습니다.");

			// 텍스트박스 지우기
			e.target.parentNode.parentNode.querySelector('.reply-content').value = null;
			// 4. 결과를 확인하고 결과를 표시한다.
			var currentTr = e.target.parentNode.parentNode;
			var nextTr = currentTr.parentNode.nextElementSibling.firstElementChild;

			var template = section.querySelector(".detail-template");
			var cloneTr = document.importNode(template.content,
				true);

			var replyContent = cloneTr.querySelector(".replyTable tbody tr td");
			var div = document.createElement("div");
			var content = '<div><span class="re-writer">'
				+ e.target.dataset.writerId + ' : </span><span class="re-content">'
				+ reContent + '</span><span class="modi-box"><a href="" class="re-modi" data-id="'
				+ lastReplyNum + '">수정</a>  <a href="" class="re-del" data-id="'
				+ lastReplyNum + '">삭제</a><a href="" class="re-commit hidden" data-id="'
				+ lastReplyNum + '">확인</a>  <a href="" class="re-cancel hidden" data-id="'
				+ lastReplyNum + '">취소</a></span></div>';
			changeReplyCnt.innerText = parseInt(changeReplyCnt.innerText) + 1;
			div.innerHTML = content;
			nextTr.firstElementChild.prepend(div);
		}
	};

	// ========= 댓글수정 ==================
	var replyModiClickHandler = function (e) {
		var replyId = e.target.dataset.id;
		var targetContent = e.target.parentNode.previousSibling.previousSibling;
		var targetContentBox = e.target.parentNode.previousSibling;
		var targetSpan = e.target.parentNode;

		// ========= 댓글내용부분 히든으로 교체==================
		var content = targetContent.querySelector(".re-content");
		var contentBox = targetContentBox.querySelector(".reply-modi-content");

		targetContent.classList = "re-content hidden";
		targetContentBox.classList = "reply-modi-content";

		// ========= 댓글버튼부분 히든으로 교체==================
		var modi = targetSpan.querySelector(".re-modi");
		var del = targetSpan.querySelector(".re-del");
		var commit = targetSpan.querySelector(".re-commit");
		var cancel = targetSpan.querySelector(".re-cancel");

		modi.classList = "re-modi hidden";
		del.classList = "re-del hidden";
		commit.classList = "re-commit";
		cancel.classList = "re-cancel";
	}

	// ========= 댓글수정확인 ==================
	var replyCommitClickHandler = function (e) {
		var replyId = e.target.dataset.id;
		var targetContent = e.target.parentNode.previousSibling.previousSibling;
		var targetContentBox = e.target.parentNode.previousSibling;
		var targetSpan = e.target.parentNode;

		var reContent = targetContentBox.value;
		
		//태그 없애는 정규식
		reContent = reContent.replace(/(<([^>]+)>)/ig,"");
		
		var reContentEncode = encodeURI(reContent);

		String.prototype.trim = function () {
			return this.replace(/^\s+|\s+$/g, "");
		}

		var str = reContent;
		str = str.trim();

		// 댓글내용이 없으면 알림을 하고 되돌아간다.
		if (str == "") {
			alert("내용을 작성하세요");
			return;
		}
		var data = [["replyId", replyId],
		["reContent", reContentEncode],
		["status", "modi"],]
		var sendData = [];

		for (var i = 0; i < data.length; i++) {
			sendData[i] = data[i].join("=");
		}
		sendData = sendData.join("&");

		// 2. 값을 서버에 보낸다.

		var request = new XMLHttpRequest();
		request.open("POST", "../../card/board/reply_update", true);
		request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		request.send(sendData);

		// 3. 요청이 완료되었는지 결과를 확인한다.
		request.onload = function () {
			targetContent.innerText = reContent;
			targetContentBox.value = reContent;

			// ========= 댓글내용부분 히든으로 교체==================
			var content = targetContent.querySelector(".re-content");
			var contentBox = targetContentBox.querySelector(".reply-modi-content");

			targetContent.classList = "re-content";
			targetContentBox.classList = "reply-modi-content hidden";

			// ========= 댓글버튼부분 히든으로 교체==================
			var modi = targetSpan.querySelector(".re-modi");
			var del = targetSpan.querySelector(".re-del");
			var commit = targetSpan.querySelector(".re-commit");
			var cancel = targetSpan.querySelector(".re-cancel");

			modi.classList = "re-modi";
			del.classList = "re-del";
			commit.classList = "re-commit hidden";
			cancel.classList = "re-cancel hidden";

			alert("수정되었습니다.");
		}
	}

	// ========= 댓글수정 취소==================
	var replyCancelClickHandler = function (e) {

		var replyId = e.target.dataset.id;
		var targetContent = e.target.parentNode.previousSibling.previousSibling;
		var targetContentBox = e.target.parentNode.previousSibling;
		var targetSpan = e.target.parentNode;

		// ========= 댓글내용부분 히든으로 교체==================
		var content = targetContent.querySelector(".re-content");
		var contentBox = targetContentBox.querySelector(".reply-modi-content");

		targetContent.classList = "re-content";
		targetContentBox.classList = "reply-modi-content hidden";

		// ========= 댓글버튼부분 히든으로 교체==================
		var modi = targetSpan.querySelector(".re-modi");
		var del = targetSpan.querySelector(".re-del");
		var commit = targetSpan.querySelector(".re-commit");
		var cancel = targetSpan.querySelector(".re-cancel");

		modi.classList = "re-modi";
		del.classList = "re-del";
		commit.classList = "re-commit hidden";
		cancel.classList = "re-cancel hidden";
	}

	// ========= 댓글삭제 ==================
	var replyDelClickHandler = function (e) {
		var changeReplyCnt = e.target.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode.querySelector('.reply-cnt');
		var replyId = e.target.dataset.id;
		var targetDiv = e.target.parentNode.parentNode;
		var data = [["replyId", replyId],
		["status", "del"],]
		var sendData = [];

		for (var i = 0; i < data.length; i++) {
			sendData[i] = data[i].join("=");
		}
		sendData = sendData.join("&");

		var request = new XMLHttpRequest();
		request.open("POST", "../../card/board/reply_delete", true);
		request.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded');
		request.send(sendData);

		// 3. 요청이 완료되었는지 결과를 확인한다.
		request.onload = function () {
			changeReplyCnt.innerText = parseInt(changeReplyCnt.innerText) - 1;
			alert("삭제되었습니다.");
			targetDiv.parentNode.removeChild(targetDiv);
		}
	}

	// ========= 게시글 수정 ==================
	var modiContentClickHandler = function (e) {

		var regBoardForm = document.querySelector(".pop-up-reg");
		var regSubmit = regBoardForm.querySelector(".pop-up-content-row");

		var targetTitle = e.target.parentNode.parentNode.previousElementSibling.previousElementSibling.querySelector(".title");
		var title = targetTitle.innerText;
		var titleEncode = encodeURI(title);

		var targetContent = e.target.parentNode.parentNode.querySelector(".content-detail");
		var content = targetContent.innerText;
		var contentEncode = encodeURI(content);

		regBoardForm.style.visibility = "visible";
		regBoardForm.querySelector(".reg-title").value = title;
		regBoardForm.querySelector(".reg-content").value = content;
		var regBtData = regBoardForm.querySelector(".reg-submit");
		regBtData.dataset.id = e.target.dataset.id;

	}

	// ========= 게시글 삭제 ==================
	var delContentClickHandler = function (e) {
		var targetDiv = e.target.parentNode.parentNode;
		var targetDivPre = e.target.parentNode.parentNode.previousSibling.previousSibling;
		var boardId = e.target.dataset.id;
		var data = [
			["boardId", boardId],
			["status", "del"]
		]
		var sendData = [];

		for (var i = 0; i < data.length; i++) {
			sendData[i] = data[i].join("=");
		}
		sendData = sendData.join("&");

		var request = new XMLHttpRequest();
		request.open("POST", "../../card/board/stock_delete_board", true);
		request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		request.send(sendData);

		// 3. 요청이 완료되었는지 결과를 확인한다.
		request.onload = function () {
			alert("삭제되었습니다.");
			load(1,stockCode, sortBoard);
		}
	}

	// ========= 즐겨찾기 추가 ==================
	var interestNoClickHandler = function (e) {
		var boardId = e.target.dataset.id;
		var data = [
			["boardId", boardId],
			["status", "insert"]
		]
		var sendData = [];

		for (var i = 0; i < data.length; i++) {
			sendData[i] = data[i].join("=");
		}
		sendData = sendData.join("&");

		var request = new XMLHttpRequest();
		request.open("POST", "../../card/board/interest_insert", true);
		request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		request.send(sendData);

		// 3. 요청이 완료되었는지 결과를 확인한다.
		request.onload = function () {
			alert("추가되었습니다.");
			e.target.classList.remove("interest_no");
			e.target.classList.add("interest_yes");
			
		}
	}

	// ========= 즐겨찾기 삭제 ==================
	var interestYesClickHandler = function (e) {
		var boardId = e.target.dataset.id;
		var data = [
			["boardId", boardId],
			["status", "delete"]
		]
		var sendData = [];

		for (var i = 0; i < data.length; i++) {
			sendData[i] = data[i].join("=");
		}
		sendData = sendData.join("&");

		var request = new XMLHttpRequest();
		request.open("POST", "../../card/board/interest_delete", true);
		request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		request.send(sendData);

		// 3. 요청이 완료되었는지 결과를 확인한다.
		request.onload = function () {
			alert("삭제되었습니다.");
			e.target.classList.remove("interest_yes");
			e.target.classList.add("interest_no");
			
		}
	}

	// ========= 내용 클릭 핸들러 ==================
	tbody.onclick = function (e) {
		e.preventDefault();

		if (e.target.parentNode.parentNode.classList
			.contains("board-title"))
			titleClickHandler(e);

		else if (e.target.parentNode.classList
			.contains("reply-submit-button"))
			regReplyButtonClickHandler(e);

		else if (e.target.classList.contains("re-modi"))
			replyModiClickHandler(e);

		else if (e.target.classList.contains("re-del"))
			replyDelClickHandler(e);

		else if (e.target.classList.contains("re-commit"))
			replyCommitClickHandler(e);

		else if (e.target.classList.contains("re-cancel"))
			replyCancelClickHandler(e);

		else if (e.target.classList.contains("del-content"))
			delContentClickHandler(e);

		else if (e.target.classList.contains("content-modi"))
			modiContentClickHandler(e);

		else if (e.target.classList.contains("interest_no"))
			interestNoClickHandler(e);

		else if (e.target.classList.contains("interest_yes"))
			interestYesClickHandler(e);

	};
	regButtonClickHandler = function (e) {

		// 1. 입력한 값을 얻어온다.

		
		var title = regBoardForm.querySelector(".reg-title").value;
		var content = regBoardForm.querySelector(".reg-content").value;
		var boardId = e.target.dataset.id;
		var status = "reg";

		if (boardId != "") {
			status = "modi";
		}
		
		//태그 없애는 정규식
		title = title.replace(/(<([^>]+)>)/ig,"");
		content = content.replace(/(<([^>]+)>)/ig,"");
		
		//인코딩

		String.prototype.trim = function () {
			return this.replace(/^\s+|\s+$/g, "");
		}

		var str1 = title;
		str1 = str1.trim();
		var str2 = content;
		str2 = str2.trim();

		// 댓글내용이 없으면 알림을 하고 되돌아간다.
		if (str1 == "") {
			alert("제목을 작성하세요");
			return;
		} else if (str2 == "") {
			alert("내용을 작성하세요");
			return;
		} else {
			title = encodeURI(title);
			content = encodeURI(content);
			
			//게시글 등록
			if(boardId == "") {
			var data = [
				["title", title],
				["content", content],
				["stockCode", stockCode]
			]
			var sendData = [];
			for (var i = 0; i < data.length; i++) {
				sendData[i] = data[i].join("=");
			}
			sendData = sendData.join("&");
			// 2. 값을 서버에 보낸다.

			var request = new XMLHttpRequest();
			request.open("POST", "../../card/board/stock_reg_board", true);
			request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
			request.send(sendData);

			// 3. 요청이 완료되었는지 결과를 확인한다.
			e.target.parentNode.firstElementChild.value = null;
			e.target.parentNode.parentNode.firstElementChild.firstElementChild.value = null;
			alert("등록되었습니다.");

			load(1, stockCode, sortBoard);
			//게시글 수정
		} else {
			var data = [
				["boardId", boardId],
				["title", title],
				["content", content],
				["status", status],
				["stockCode", stockCode]
			]
			var sendData = [];
			console.log(data);
			for (var i = 0; i < data.length; i++) {
				sendData[i] = data[i].join("=");
			}
			sendData = sendData.join("&");
			// 2. 값을 서버에 보낸다.

			var request = new XMLHttpRequest();
			request.open("POST", "../../card/board/stock_update_board", true);
			request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
			request.send(sendData);

			// 3. 요청이 완료되었는지 결과를 확인한다.
			e.target.parentNode.firstElementChild.value = null;
			e.target.parentNode.parentNode.firstElementChild.firstElementChild.value = null;
			alert("수정되었습니다.");

			load(1, stockCode, sortBoard);
			
		}}
	};




	//========= 글쓰기 버튼 클릭==================
	regBoard.onclick = function (e) {
		if (e.target.nodeName != "A")
			return;

		e.preventDefault();
		if (e.target.id == "reg-button") {
			regBoardForm.querySelector(".reg-title").value = null;
			regBoardForm.querySelector(".reg-content").value = null;
			var regBtData = regBoardForm.querySelector(".reg-submit");
			regBtData.dataset.id = "";
			regBoardForm.style.visibility = "visible";
		}
	};

	//========= 글쓰기 취소 버튼 클릭 ==================
	regSubmit.onclick = function (e) {
		if (e.target.nodeName != "INPUT")
			return;

		e.preventDefault();

		if (e.target.name == "cancel") {
			alert("글등록이 취소되었습니다!!")
			regBoardForm.style.visibility = "hidden";
			//========= 글쓰기 등록 버튼 클릭==================
		} else if (e.target.name == "submit") {
			regButtonClickHandler(e);


			regBoardForm.style.visibility = "hidden";
		}
	};
	}
});

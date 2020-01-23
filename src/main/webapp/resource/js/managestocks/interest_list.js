window.addEventListener("message", function (e) {
	if (e.data && (e.data.length == 6)) {

		var section = this.document.querySelector(".interestList");
		var tbody = section.querySelector("table tbody");
		var delButton = section.querySelector("#deleteButton");

		function interestLoad() {

			var interestAjax = new XMLHttpRequest();
			interestAjax.open("GET", "../../card/managestocks/interestlist-json", true);

			// 서블릿의 실행이 완료되었을때 실행
			interestAjax.onload = function () {
				// var cardFooter = section.querySelector(".card-footer");

				var list = JSON.parse(interestAjax.responseText);
				tbody.innerHTML = "";
				for (var i = 0; i < list.length; i++) {

					var template = section.querySelector(".template");
					var cloneTr = document.importNode(template.content, true);
					var tds = cloneTr.querySelectorAll("td");
					var formData = section.querySelector("#deleteInput");

					tds[0].firstElementChild.innerText = list[i].stockname;
					if (list[i].gain == "상승") {
						tds[1].firstElementChild.innerText = list[i].price;
						tds[1].lastElementChild.innerText = list[i].percent;
					} else if (list[i].gain == "하락") {
						tds[2].firstElementChild.innerText = list[i].price;
						tds[2].lastElementChild.innerText = list[i].percent;
					} else {
						tds[3].firstElementChild.innerText = list[i].price;
						tds[3].lastElementChild.innerText = list[i].percent;
					}

					for (var j = 1; j <= 3; j++) {
						if (tds[j].firstElementChild.innerText == "") {
							tds[j].style.display = "none";
						}
					}

					tbody.append(cloneTr);

					tds[4].firstElementChild.dataset.delStockName = list[i].stockname;


				}
			};
			interestAjax.send();
		};

		interestLoad();

		tbody.onclick = function (e) {
			e.preventDefault();
			if (event.target.nodeName == "BUTTON") {

				var delTarget = e.target.parentNode.parentNode;
				var delStockName = e.target.dataset.delStockName;
				var data = [["delStockName", delStockName]];
				var sendData = [];

				sendData[0] = data[0].join('=');

				sendData = sendData.join('&');

				var delRequest = new XMLHttpRequest();
				delRequest.open("POST", "../../card/managestocks/interestlist-json", true)
				delRequest.setRequestHeader('Content-Type',
					'application/x-www-form-urlencoded');
				delRequest.onload = function () {
					alert("삭제되었습니다.");
					delTarget.parentNode.removeChild(delTarget);
				}
				delRequest.send(sendData);
			}
		};
	}
});

window.addEventListener("load", function () {
	var section = this.document.querySelector(".interestList");
	var tbody = section.querySelector("table tbody");
	var delButton = section.querySelector("#deleteButton");

	function interestLoad() {

		var interestAjax = new XMLHttpRequest();
		interestAjax.open("GET", "../../card/managestocks/interestlist-json", true);
		// 서블릿의 실행이 완료되었을때 실행	

		interestAjax.onload = function () {

			if (interestAjax.responseText == -1) {
				return;
			}
			else {
				// var cardFooter = section.querySelector(".card-footer");
				var list = JSON.parse(interestAjax.responseText);
				tbody.innerHTML = "";
				for (var i = 0; i < list.length; i++) {

					var template = section.querySelector(".template");
					var cloneTr = document.importNode(template.content, true);
					var tds = cloneTr.querySelectorAll("td");
					var formData = section.querySelector("#deleteInput");

					tds[0].firstElementChild.innerText = list[i].stockname;
					if (list[i].gain == "상승") {
						tds[1].firstElementChild.innerText = list[i].price;
						tds[1].lastElementChild.innerText = list[i].percent;
					} else if (list[i].gain == "하락") {
						tds[2].firstElementChild.innerText = list[i].price;
						tds[2].lastElementChild.innerText = list[i].percent;
					} else {
						tds[3].firstElementChild.innerText = list[i].price;
						tds[3].lastElementChild.innerText = list[i].percent;
					}

					for (var j = 1; j <= 3; j++) {
						if (tds[j].firstElementChild.innerText == "") {
							tds[j].style.display = "none";
						}
					}

					tbody.append(cloneTr);

					tds[4].firstElementChild.dataset.delStockName = list[i].stockname;

				};
			}

		}
		interestAjax.send();

	}


	interestLoad();

	setInterval(function () {
		interestLoad();
	}, 5000);

	tbody.onclick = function (e) {
		e.preventDefault();
		if (event.target.nodeName == "BUTTON") {

			var delTarget = e.target.parentNode.parentNode;
			var delStockName = e.target.dataset.delStockName;
			var data = [["delStockName", delStockName]];
			var sendData = [];

			sendData[0] = data[0].join('=');

			sendData = sendData.join('&');

			var delRequest = new XMLHttpRequest();
			delRequest.open("POST", "../../card/managestocks/interestlist-json", true)
			delRequest.setRequestHeader('Content-Type',
				'application/x-www-form-urlencoded');
			delRequest.onload = function () {
				alert("삭제되었습니다.");
				delTarget.parentNode.removeChild(delTarget);
			}
			delRequest.send(sendData);
		}
	};
});

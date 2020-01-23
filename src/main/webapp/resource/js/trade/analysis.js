var codeNum = "005930"; // 삼성전자

window.addEventListener("message", function(e) {
	if (e.data && (e.data.length == 6)) { // codeNum
		codeNum = e.data;
		refleshPrice();
		chartUpdate();
	
	}
});

window.addEventListener("load", function() {
	bb.defaults();
	refleshPrice();
	chartUpdate();
	captureAction();
});

function captureAction() {
	var button = document.querySelector("#capture");
	
	if(button == null)
		return;
	
	button.onclick = function(e) {
		var ajax = new XMLHttpRequest();
		ajax.open("GET", "../../card/trade/analysis?capture=on&codeNum=" + codeNum);
		ajax.onload = function() {
			//data send to capture Card
			var frame = parent.document.querySelector("#capture-window");
			frame.contentWindow.postMessage(
					{capture: ajax.responseText }, 
					"http://localhost:8080/card/capturememo/captureMemo.jsp");
			
			//캡쳐버튼시 카드이동
			parent.document.querySelector("#capture-tab").click(); 
		}
		ajax.send();
	}
}

function curStockUpdateForm(obj) {
	var stockNameDiv = document.querySelectorAll("#stockName div");
	var stockNameSpan = document.querySelectorAll("#stockName span");
	
	stockNameDiv[0].style.display = "contents";
	stockNameDiv[1].style.display = "contents";
	stockNameDiv[0].innerHTML = obj.name;
	stockNameDiv[1].innerHTML = obj.price;
	//보합
	stockNameSpan[0].classList.remove("fa", "fa-caret-up", "fa-caret-down");
	if(obj.status == "상승")
		stockNameSpan[0].classList.add("fa", "fa-caret-up");
	if(obj.status == "하락")
		stockNameSpan[0].classList.add("fa", "fa-caret-down");
	
	stockNameSpan[1].innerHTML = obj.gain;
	stockNameSpan[2].innerHTML = "(+"+ obj.ratio + "%)";
	for(var i = 0; i < stockNameSpan.length; i++) {
		if(obj.status == "상승")
			stockNameSpan[i].style.color = "red";
		if(obj.status == "하락")
			stockNameSpan[i].style.color = "blue";
		if(obj.status == "보합")
			stockNameSpan[i].style.color = "black";
	}
}

function refleshPrice() {
	var ajax = new XMLHttpRequest();
	ajax.open("GET", "../../card/trade/analysis?codeNum=" + codeNum);
	ajax.onload = function() {
		var obj = JSON.parse(ajax.responseText);
		curStockUpdateForm(obj);
	}
	ajax.send();
}

bb.defaults({
	data : {
		columns : [ [ "", 0 ] ],
		type : "gauge",
	},
	gauge : {
		fullCircle : true,
		startingAngle : 0,
		expand : false, 
		label : {
			extents : function(value, isMax) {
				return null;
			}
		}
	},
	transition : { duration : 1500 },
	legend : { show : false },
	clipPath: false,
	tooltip: { show : false },
	size : { height : 140, width : 140 },
});

function chartUpdate() {
	var chartA = bb.generate({
		bindto : "#chartA",
		color : { pattern : [ "#FF7F0E"] },
	});

	var chartB = bb.generate({
		bindto : "#chartB",
		color : { pattern : [ "#1F77B4"] },
	});
	

	var chartC = bb.generate({
		bindto : "#chartC",
		color : { pattern : [ "#FF4040"] },
	});

	var chartD = bb.generate({
		bindto : "#chartD",
		color : { pattern : [ "#2CA02C"] },
		
	});
	
	var chartE = bb.generate({
		bindto : "#chartE",
		color : { pattern : [ "#FED201"] },
	});
	
	var chartF = bb.generate({
		data: {
			type: "pie"
		},
		bindto : "#chartF",
		color : { pattern : [ "#FF4040"] },
	});
	
	
	setTimeout(function() {
		chartA.load({
			columns : [ [ "", 30 ] ]
		});
		chartB.load({
			columns : [ [ "", 70 ] ]
		});
		chartC.load({
			columns : [ [ "", 20 ] ]
		});
		chartD.load({
			columns : [ [ "", 20 ] ]
		});
		chartE.load({
			columns : [ [ "", 20 ] ]
		});
		chartF.load({
			columns : [ [ "", 100 ] ]
		});
	}, 0);
}
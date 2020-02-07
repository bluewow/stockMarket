var codeNum = "005930"; // 삼성전자

window.addEventListener("message", function(e) {
	if (e.data && (e.data.length == 6)) { // codeNum
		codeNum = e.data;
		updatePrice();
		chartUpdate();
	
	}
});

window.addEventListener("load", function() {
	bb.defaults();
    
	chartA = bb.generate({	//종목동향
		bindto : "#chartA",
		color : { pattern : [ "#FF7F0E"] },
	});

	chartB = bb.generate({	//수급
		bindto : "#chartB",
		color : { pattern : [ "#1F77B4"] },
	});
	

	chartC = bb.generate({	//영향력
		bindto : "#chartC",
		color : { pattern : [ "#FF4040"] },
	});

	chartD = bb.generate({	//컨텐츠
		bindto : "#chartD",
		color : { pattern : [ "#2CA02C"] },
		
	});
	
	chartE = bb.generate({	//규모
		bindto : "#chartE",
		color : { pattern : [ "#FED201"] },
	});
	
	updatePrice();
	chartUpdate();
	captureAction();
	textToggle();
});

function textToggle() {
	let button = document.querySelectorAll(".analysis-result");
	
	button[0].onclick = function() {
		if(button[0].value == "투자 경고") 
			button[0].value = "±15%~30%";
		else 
			button[0].value = "투자 경고";
	};
	
	button[1].onclick = function() {
		if(button[1].value == "투자 주의") 
			button[1].value = "±5%~±15%";
		else 
			button[1].value = "투자 주의";
	};
	
	button[2].onclick = function() {
		if(button[2].value == "투자 안전") 
			button[2].value = "±0% ~ ±5%";
		else 
			button[2].value = "투자 안전";
	};
}

function updatePrice() {
	var ajax = new XMLHttpRequest();
	ajax.open("GET", "/card/trade/analysisUpdate?codeNum=" + codeNum);
	ajax.onload = function() {
//		console.log(ajax.responseText); //for debugging
		var obj = JSON.parse(ajax.responseText);
		curStockUpdateForm(obj);
	}
	ajax.send();
}

function captureAction() {
	var button = document.querySelector("#capture");
	
	if(button == null)
		return;
	
	button.onclick = function(e) {
		var ajax = new XMLHttpRequest();
		ajax.open("GET", "/card/trade/capture?codeNum=" + codeNum);
		ajax.onload = function() {
			console.log(ajax.responseText); //for debugging
			//data send to capture Card
			var frame = parent.document.querySelector("#capture-window");
			frame.contentWindow.postMessage(
					{capture: ajax.responseText }, 
					"http://localhost:8080/card/capturememo/captureMemo");
//					"http://stockmarket.iptime.org:8080//card/capturememo/captureMemo.jsp");
			
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
	var ajax = new XMLHttpRequest();
	let trend, contents, supply, scale, influence, result;
	let color = document.querySelectorAll(".analysis-result");
	
	ajax.open("GET", "/card/trade/chartUpdate?codeNum=" + codeNum, false);
    ajax.onload = function() {
    	let obj = JSON.parse(ajax.responseText);
    	trend = obj.trend;
    	supply = obj.supply;
    	contents = obj.contents;
    	scale = obj.scale;
    	influence = obj.influence;
    	result = obj.result;
    }
	ajax.send();
	
	color[0].style.backgroundColor = "#DDDDDD";
	color[1].style.backgroundColor = "#DDDDDD";
	color[2].style.backgroundColor = "#DDDDDD";
	if(result > 70)
		color[0].style.backgroundColor = "#FF4040"; 
	else if(result > 50)
		color[1].style.backgroundColor = "#FF7F0E"; 
	else
		color[2].style.backgroundColor = "#2CA02C"; 
		
	setTimeout(function() {
		bb.instance[0].load({
			columns : [ [ "", trend ] ]
		});
		bb.instance[1].load({
			columns : [ [ "", supply ] ]
		});
		bb.instance[2].load({
			columns : [ [ "", influence ] ]
		});
		bb.instance[3].load({
			columns : [ [ "", contents ] ]
		});
		bb.instance[4].load({
			columns : [ [ "", scale ] ]
		});
	}, 0);
}
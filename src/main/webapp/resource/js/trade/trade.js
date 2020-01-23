//TODO 해당종목은 거래정지 종목입니다 
//TODO 거래후 수량 차트 적용
//TODO 크롤링 완료후 콜백 데이타를 적용
var codeNum = "005930"; //삼성전자

window.addEventListener("message", function(e) {
	if(e.data && (e.data.length == 6)) { //codeNum
		codeNum = e.data;
		
		update();		// 매수/매도 창 데이터 갱신
	}
});

window.addEventListener("load", function(){
	bb.defaults();
	chartSell = bb.generate({
		bindto : "#chartSell",
	    data: {
	    	x: "x",
	        columns: [	   
	        	["x"],
	        	["data"],
	        ],
	        colors:{data:"#2D9AF277"}
	    },
	});

	chartBuy = bb.generate({
		bindto : "#chartBuy",
	    data: {
	    	x: "x",
	        columns: [	            
	        	["x"],
	        	["data"],
	        ],	
	        colors:{data:"#F4000677"}
	    },
	});
	
	tick(); //페이지 호출시 1초 딜레이 없이 초기값을 적용 
	update();
	buttonEvent();
});

class Price {
	constructor(){
		this.index;
		this.price;
	}
	getPrice(index) {
		if(isNaN(index)) {
			return null;
		}
		return this.price[index];
	}
	setPrice(price) {
		this.price = price;
	}
	setIndex(index) {
		this.index = index;
	}
	getIndex() {
		return this.index;
	}
}

bb.defaults({
	data: {
		type: "bar",
		labels:{
			colors : "black",
			centered : true,
		  	position:{ x:15 },
		},
	},
	axis: {
        rotated: true,
        y: {
            show: false,
            max: 6000,
        },
        x: { type: "category" }
    },
    render: {
    	   observe: false
    	},
    onrendered: function() {
    	this.main.selectAll(".bb-texts text").each(function(d) {
    		const x = +this.getAttribute("x");

    		this.setAttribute("x", (
    			d.value >= 6000 ? 0 : 15
    		));
    	});
    },
    legend: { show: false },
    tooltip: { show: false },
    bar: { padding: 5 },
    size: {
    	width: 200,
        height: 320
    },
});


function buttonEvent() {
	var arrow = document.querySelectorAll("i");
	var text = document.querySelectorAll(".text");
	var buy = document.querySelector("#buy");
	var sell = document.querySelector("#sell");
	var data = document.querySelectorAll(".data");
	

	arrow[0].onclick = function(e) {	//단가 up
		var index = priceObj.getIndex() - 1;
		if(priceObj.getPrice(index) != null) {
			priceObj.index--;
			text[0].value = priceObj.getPrice(priceObj.index);
		}
	};
	arrow[1].onclick = function(e) {	//단가 down
		var index = priceObj.getIndex() + 1;
		if(priceObj.getPrice(index) != null) {
			priceObj.index++;
			text[0].value = priceObj.getPrice(priceObj.index);	
		}
	}
	arrow[2].onclick = function(e) {	//수량 up
		text[1].value = Number(text[1].value) + 1;
	}
	arrow[3].onclick = function(e) {	//수량 down
		if(text[1].value == 0)
			return;
		
		text[1].value = Number(text[1].value) - 1;
	}
	
	buy.onclick = function(e) {
		var asset = data[0].value;
		var price = Number(text[0].value);
		var qty = text[1].value;
		
		if(qty == "" || qty == "0" || qty < 0) {
			alert("수량을 잘못 입력하였습니다.");
			text[1].value = 0;
			return;
		}
		
		if(asset < price * qty) {
			alert("가상머니가 부족합니다");
			text[1].value = 0;
			return;
		}
		
		var ajax = new XMLHttpRequest();
	    ajax.open("GET", "../../card/trade/trade?&qty=" + qty + "&codeNum=" + codeNum + "&price=" + price );
	    ajax.onload = function() {
	    	var obj = JSON.parse(ajax.responseText);
	    	data[0].value = obj.vMoney;
	    	data[0].innerHTML = obj.vMoney.toLocaleString() + "원"; //자산상황
	    	data[1].value = obj.qty;
	        data[1].innerHTML = obj.qty.toLocaleString() + "주";	//보유수량

	        text[1].value = 0; //수량 초기화
	        buttonStatusUpdate(); //버튼 상태 업데이트
	        alert("체결이 완료되었습니다");

	        var frame = parent.document.querySelector("#holding-window");
	        frame.contentWindow.postMessage(
	        		codeNum, "http://localhost:8080/card/managestocks/holdinglist.jsp");
	    }
	    ajax.send();
		
	}
	
	sell.onclick = function(e) {
		var haveQty = data[1].value;
		var price = Number(text[0].value);
		var qty = text[1].value; //거래 수량
		
		if(qty == "" || qty == "0" || qty < 0) {
			alert("수량을 잘못 입력하였습니다");
			text[1].value = 0;
			return;
		}
		
		if(qty > haveQty) {
			alert("매도수량이 보유수량을 초과하였습니다");
			text[1].value = 0;
			return;
		}
		
		var ajax = new XMLHttpRequest();
	    ajax.open("GET", "../../card/trade/trade?&qty=" + -qty + "&codeNum=" + codeNum + "&price=" + price );
	    ajax.onload = function() {
	    	var obj = JSON.parse(ajax.responseText);
	    	data[0].value = obj.vMoney;
	    	data[0].innerHTML = obj.vMoney.toLocaleString() + "원"; //자산상황
	    	data[1].value = obj.qty;
	        data[1].innerHTML = obj.qty.toLocaleString() + "주";	//보유수량
	        
	        text[1].value = 0; //수량 초기화
	        buttonStatusUpdate();
	        alert("체결이 완료되었습니다");

	        var frame = parent.document.querySelector("#holding-window");
	        frame.contentWindow.postMessage(
	        		codeNum, "http://localhost:8080/card/managestocks/holdinglist.jsp");
	    }
	    ajax.send();
	}
}


function update() {
	var button = document.querySelector("#page-bottom-box");
	var data = button.querySelectorAll(".data");
	var text = button.querySelectorAll(".text");
	var sellButton = button.querySelector("#sell");
	var buyButton = button.querySelector("#buy");
	let titleAss = document.querySelector("#title-ass");
	priceObj = new Price();
	
	var ajax = new XMLHttpRequest();
    ajax.open("GET", "../../card/trade/trade?&codeNum=" + codeNum );
    ajax.onload = function() {
    	var obj = JSON.parse(ajax.responseText);
    	var sellPrice = new Array("x");
    	var sellQty = new Array("data");
    	var buyPrice = new Array("x");
    	var buyQty = new Array("data");
    	
    	data[0].value = obj.vMoney;
    	data[0].innerHTML = obj.vMoney.toLocaleString() + "원"; //자산상황
    	data[1].value = obj.qty;
        data[1].innerHTML = obj.qty.toLocaleString() + "주";	//보유수량
        
        if(obj.sellPrice) {	//매도잔량 데이터
	    	for(var i=0; i < obj.sellPrice.length; i++) {
	    		sellPrice.push(obj.sellPrice[i]);
	    		sellQty.push(obj.sellQty[i]);
	    	}
        } 
        if(obj.buyPrice) {	//매수잔량 데이터
	    	for(var i=0; i < obj.buyPrice.length; i++) {
	    		buyPrice.push(obj.buyPrice[i]);
	    		buyQty.push(obj.buyQty[i]);
	    	}
        }
        
        if(obj.sellPrice && obj.buyPrice) { //array 객체생성 - 단가 list
        	var array = new Array();
        	array = obj.sellPrice.concat(obj.buyPrice);
        	priceObj.setIndex(obj.sellPrice.length);
        	priceObj.setPrice(array);
        }
        
        bb.instance[0].load({	//매도잔량 차트
    		columns: [sellPrice, sellQty],
    	});
        
    	bb.instance[1].load({	//매수잔량 차트
    		columns: [buyPrice, buyQty]
    	});
    	
    	if(obj.buyPrice) 	//단가 기본세팅
    		text[0].value = (obj.buyPrice[0]!=undefined )? obj.buyPrice[0]:0;

    	//수량 기본세팅
    	text[1].value = 0;
    	//매수/매도 버튼 상태 업데이트
    	buttonStatusUpdate();
    }    
    ajax.send();
}

function buttonStatusUpdate() {
	var button = document.querySelector("#page-bottom-box");
	var data = button.querySelectorAll(".data");
	var sellButton = button.querySelector("#sell");
	var buyButton = button.querySelector("#buy");
	let titleAss = document.querySelector("#title-ass");
	
	if(titleAss.innerHTML != "") {
		sellButton.className = "event button button-button shadow";
		sellButton.disabled = true;
		buyButton.className = "event button button-button shadow";
		buyButton.disabled = true;
	} else {
		sellButton.className = "event button button-button animation";
		sellButton.disabled = false;
		buyButton.className = "event button button-button animation";
		buyButton.disabled = false;
	}
	
	if(data[1].value == 0) {    	//보유수량에 따른 버튼 상태체크
		sellButton.className = "event button button-button shadow";
		sellButton.disabled = true;
	}
}

function tick() {
	let titleAss = document.querySelector("#title-ass");
	let date = new Date();
	let week = ['일', '월', '화', '수', '목', '금', '토'];
	var dayOfWeek = week[date.getDay()];

	if(dayOfWeek == '일' || dayOfWeek =="토") {
		titleAss.innerHTML = "휴장일 입니다";
		return;
	}

	if(date.getHours() <= 9 || date.getHours() >=15) {	//9:00 ~ 15:20 거래시간
		if(date.getHours() == 15 && date.getMinutes() <= 20) {
			titleAss.innerHTML = "";
			return;
		} else {
			titleAss.innerHTML = "거래종료 (오픈시간 09:00~15:20)";
			return;
		}
		titleAss.innerHTML = "거래종료 (오픈시간 09:00~15:20)";
		return;
	}
}

setInterval(tick, 1000);



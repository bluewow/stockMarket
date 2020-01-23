class CaptureMemo {
	constructor(){
		this.prevMemo;
		this.content = $(".content");
		this.trTemplate = document.querySelector(".tr-template");
		this.chart;
	}

	setPrevMemo(prevMemo) {
		this.prevMemo = prevMemo;
	}

	loadList(callback) {
		$.getJSON("captureMemo-json")
		.done(function(list) {
			let trTemplate = document.querySelector(".tr-template-list");
			let content = $(".content");
			
			content.html("");
		
			for(let i = 0; i < list.length; i++) {
				let cloneTr = document.importNode(trTemplate.content, true);
				let tds = $(cloneTr).children();
				
				tds.children().eq(0).text(list[i].codeNumName);
				tds.children().eq(1).text(list[i].title);
				tds.children().eq(2).children().first().before(list[i].regdate);
				tds.attr("dataset.id", list[i].id);
				content.append(cloneTr);
			}
			
			if(callback != null)
				callback();
		})
		.fail(function() {
			alert("로딩 실패");
		});
	}

	createDetail(result, target){
		if (target.parent().next().attr("class") == "child-tr") {
			if (this.prevMemo.length != 0) {
				this.prevMemo.remove();
				this.prevMemo = undefined;
			}
			return;
		}
		let clone = document.importNode(this.trTemplate.content, true);
		
		let title = $(clone).find(".memo > div:first-child input");
		let memoContent = $(clone).find(".memo > hr + div textarea");
		title.val(result.title);
		memoContent.val(result.content);
		
		target.parent().get(0).after(clone);
						
		if (this.content.find(".child").first().length != 0)
			this.prevMemo = this.content.find(".child").first().parent();
	}

	chartDataCrawling(data1) {
		return new Promise(function(resovle, reject){
			$.getJSON("captureMemo-data-crawling-json?id="+data1.id)
				.done(function(data2) {
					resovle(data2);
				})
				.fail(function() {
					alert("데이터 크롤링 실패");
				});
		});
	}

	createChart(data1, data2){
		if(this.chart != undefined){
			this.chart = undefined;
			return;
		}
		
		data2 = JSON.parse(data2);
		
		this.chart = $(bb.generate({
			data: {
				columns: [
					["캡쳐일", data1.PER, data1.PBR, data1.ROE, data1.debtRatio, data1.foreignInvestors],
					["현재", data2.PER, data2.PBR, data2.ROE, data2.debtRatio, data2.foreignInvestors]
				],
				type: "bar",
				labels: true
			},
			axis: {
			    x: {
			      type: "category",
			      categories: [
			        "PER",
			        "PBR",
			        "ROE",
			        "부채 비율(%)",
			        "외국인 지분율(%)",
			        ]
			    }
			},
			bar: {
				width: {
					ratio:0.5
				}
			},
			bindto: "#radarChart"
			}));
	};

	getDetail(target){
		return new Promise(function(resovle, reject){
			let memoId = target.parent().attr("dataset.id");
			
			$.getJSON("captureMemo-detail-json?memoId=" + memoId)
			.done(function(result) {
				resovle(result);
			})
			.fail(function() {
				alert("로딩 실패");
			});
		}.bind(this));
	}

	updateDetail(target){
		var data = {};
		data.id = target.parent().attr("dataset.id");
		data.title = $(".memo > div").eq(0).children().first().val();
		data.content = $(".memo > div").eq(1).children().first().val();
		
		$.post("captureMemo-json-update", JSON.stringify(data))
		.done(function(result) {
			if(result == 1) {
				target.parent().find("td").eq(1).text($(".memo > div").eq(0).children().first().val());
			}
		})
		.fail(function() {
			alert("수정 실패");
		})
	}

	deleteDetail(target){
		return new Promise(function(resovle, reject){
			let memoId = target.parent().parent().attr("dataset.id");
			$.post("captureMemo-delete-json", "memoId=" + memoId)
			.done(function() {
				resovle();
			})
			.fail(function() {
				alert("삭제 실패");
			});
		});
	}
}

window.addEventListener("load", function() {
	let captureMemo = new CaptureMemo();
	captureMemo.loadList();

    captureMemo.content.click(function(e) {
        let target = $(e.target);

		switch(target.prop("nodeName")) {
			case "TD":	
				// detail 펼치기
				captureMemo.getDetail(target)
				.then(function(data1){
					captureMemo.createDetail(data1, target);
					
					captureMemo.chartDataCrawling(data1)
					.then(function(data2){
						captureMemo.createChart(data1, data2);
					});
					
					// 메모 수정
					$(".button").click(function() {
						captureMemo.updateDetail(target);
					});
				});
				break;
			case "SPAN":	// 메모 삭제
				captureMemo.deleteDetail(target)
				.then(function(){
					captureMemo.loadList();
				});
				break;
		}
	});
});

window.addEventListener("message", function(e) {
    //json format
    //CaptureMemo PER/PBR/ROE/debtRatio/marketCap/codeNum/memberID
    var data = e.data.capture;

    if (data) {
        var request = new XMLHttpRequest();

        request.open("POST", "captureMemo-json", true);
        request.setRequestHeader(
            "Content-Type",
            "application/x-www-form-urlencoded"
        );
        request.onload = function() {
			let captureMemo = new CaptureMemo();
            if (request.responseText == 1) captureMemo.loadList(()=>{
				let target = $(".content tr:first td").eq(1);
				
				captureMemo.getDetail(target)
				.then(function(data1){
					captureMemo.createDetail(data1, target);
					
					captureMemo.chartDataCrawling(data1)
					.then(function(data2){
						captureMemo.createChart(data1, data2);
					});
					
					// 메모 수정
					$(".button").click(function() {
						captureMemo.updateDetail(target);
					});
				});
			});
            else alert("캡쳐하기 실패");
        };

        request.send(data);
    }
});
class CaptureMemo {
	constructor(){
		this.target = null;
		this.content = $(".content");
		this.trTemplate = document.querySelector(".tr-template");
	}
	
	setTarget(target){
		this.target = target;
	}

	// 메모의 리스트를 출력하는 함수
	loadList(callback) {
		$.getJSON("captureMemo-json-list")
		.done((list) => {
			let trTemplate = document.querySelector(".tr-template-list");
			
			// 메모 초기화
			this.content.html("");
			
			// 서버로부터 받아온 메모 추가
			for(let i = 0; i < list.length; i++) {
				let cloneTr = document.importNode(trTemplate.content, true);
				let tds = $(cloneTr).children();
				
				tds.children().eq(0).text(list[i].companyName);
				tds.children().eq(1).text(list[i].title);
				tds.children().eq(2).children().first().before(list[i].regdate);
				tds.attr("dataset.id", list[i].id);
				this.content.append(cloneTr);
			}
			
			if(callback != null)
				callback();
		})
		.fail(() => {
			alert("로딩 실패");
		});
	}

	createDetail(result){
		let prevMemo = this.content.find(".child");
		// 펼쳐져 있는 메모를 누를 경우
		if(this.target.parent().next().attr("class") == "child-tr") {
			prevMemo.first().parent().remove();
			prevMemo = null;
			
			return;
		}
		
		// 펼쳐져 있는 메모가 있다면
		if(prevMemo != null) {
			this.content.find(".child").first().parent().remove();
			prevMemo = null;
		}
		
		let clone = document.importNode(this.trTemplate.content, true);
		
		let title = $(clone).find(".memo > div:first-child input");
		let memoContent = $(clone).find(".memo > hr + div textarea");
		title.val(result.title);
		memoContent.val(result.content);
		
		this.target.parent().get(0).after(clone);
	}

	chartDataCrawling() {
		let memoId = this.target.parent().attr("dataset.id");
		
		return new Promise((resovle, reject) => {
			$.getJSON("captureMemo-json-dataCrawling?id=" + memoId)
				.done((data2) => {
					resovle(data2);
				})
				.fail(() => {
					reject("데이터 크롤링 실패");
				});
		});
	}

	createChart(data1, data2){
		if($("#radarChart").length == 0 && this.content.find(".child").length == 0) {
			$("#radarChart").remove();
			
			return;
		}
		
		data2 = JSON.parse(data2);
		
		$(bb.generate({
			data: {
				columns: [
					["캡쳐일", data1.PER, data1.PBR, data1.ROE, data1.debtRatio, data1.foreignInvestors],
					["현재", data2.PER, data2.PBR, data2.ROE, data2.debtRatio, data2.foreignInvestors]
				],
				type: "bar",
				labels: true,
				colors:{"캡쳐일":"#689ABC","현재":"#BF737C"}
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

	getDetail(){
		return new Promise((resovle, reject) => {
			let memoId = this.target.parent().attr("dataset.id");
			
			$.getJSON("captureMemo-json-detail?memoId=" + memoId)
			.done((result) => {
				resovle(result);
			})
			.fail(() => {
				reject("로딩 실패");
			});
		});
	}

	updateDetail(){
		let data = {};
		data.id = this.target.parent().attr("dataset.id");
		data.title = $(".memo > div").eq(0).children().first().val();
		data.content = $(".memo > div").eq(1).children().first().val();
		
		$.ajax({
			type: "POST",
			url: "captureMemo-json-update",
			data: JSON.stringify(data),
			contentType:"application/json; charset=utf-8"
		})
		.done((result) => {
			if(result == 1) {
				this.target.parent().find("td").eq(1).text($(".memo > div").eq(0).children().first().val());
			}
		})
		.fail(() => {
			alert("수정 실패");
		})
	}

	deleteDetail(){
		return new Promise((resovle, reject) => {
			let memoId = this.target.parent().parent().attr("dataset.id");
			$.get("captureMemo-json-delete?memoId=" + memoId)
			.done(() => {
				resovle();
			})
			.fail(() => {
				reject("삭제 실패");
			});
		});
	}
}

window.addEventListener("load", function() {
	let captureMemo = new CaptureMemo();
	captureMemo.loadList();
	
	// captureMemo의 내용을 클릭시
    captureMemo.content.click(function(e) {
        let target = $(e.target);

		switch(target.prop("nodeName")) {
			case "TD":
				captureMemo.setTarget(target);
				// detail 펼치기
				Promise.all([captureMemo.getDetail(), captureMemo.chartDataCrawling()])
				.then(values => {
					captureMemo.createDetail(values[0]);
					captureMemo.createChart(values[0], values[1]);
				
					// 메모 수정
					$(".button").click(() => {
						captureMemo.updateDetail();
					});
				});
				break;
			case "SPAN":	// 메모 삭제
				captureMemo.setTarget(target);
				captureMemo.deleteDetail()
				.then(() => {
					captureMemo.loadList();
				});
				break;
		}
	});
});

window.addEventListener("message", function(e) {
    // json format
    // CaptureMemo PER/PBR/ROE/debtRatio/marketCap/codeNum/memberID
    var data = e.data.capture;

    if (data) {
        var request = new XMLHttpRequest();

        request.open("POST", "captureMemo-json-insert", true);
        request.setRequestHeader(
            "Content-Type",
            "application/json"
        );
        request.onload = function() {
			let captureMemo = new CaptureMemo();
            if (request.responseText == 1) captureMemo.loadList(()=>{
				let target = $(".content tr:first td").eq(1);
				
				captureMemo.setTarget(target);
				// detail 펼치기
				Promise.all([captureMemo.getDetail(), captureMemo.chartDataCrawling()])
				.then(values => {
					captureMemo.createDetail(values[0]);
					captureMemo.createChart(values[0], values[1]);
				
					// 메모 수정
					$(".button").click(() => {
						captureMemo.updateDetail();
					});
				});
			});
            else alert("캡쳐하기 실패");
        };

        request.send(data);
    }
});
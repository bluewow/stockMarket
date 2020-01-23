window.addEventListener("message",function(e) {
	if(e.data && (e.data.length == 6)){
					var section = this.document.querySelector(".holdingList");
					var updateButton = section.querySelector(".updateButton");
					var preArea = section.querySelector(".prearea");
					var backArea = section.querySelector(".backarea");
					var tbody = section.querySelector("table tbody");

					function addComma(num) {
						var regexp = /\B(?=(\d{3})+(?!\d))/g;
						return num.toString().replace(regexp, ',');
					}

					function updateLoad() {
						var holdingAjax = new XMLHttpRequest();

						holdingAjax.open("GET",
								"../../card/managestocks/holdinglist-json", true)

						// 서블릿의 실행이 완료되었을때 실행
						holdingAjax.onload = function() {
							// var cardFooter =
							// section.querySelector(".card-footer");

							var list = JSON.parse(holdingAjax.responseText);

							var won = "원";
							var allIncomePercent = 0;
							var allIncome = 0;
							var allSum = 0;
							tbody.innerHTML = "";

							for (var i = 0; i < list.length; i++) {

								var template = section
										.querySelector(".template");
								var cloneTr = document.importNode(
										template.content, true);
								var tds = cloneTr.querySelectorAll("td");

								var incomePercent = ((list[i].income / list[i].sum) * 100)
										.toFixed(2);
								allIncome += list[i].income;
								allSum += list[i].sum;
								allIncomePercent += incomePercent;

								list[i].income = addComma(list[i].income);
								list[i].sum = addComma(list[i].sum);

								tds[0].firstElementChild.innerText = list[i].stockName;

								if (list[i].gain == "상승") {
									tds[1].firstElementChild.innerText = list[i].price;
									tds[1].lastElementChild.innerText = list[i].percent;
									tds[5].firstElementChild.innerText = list[i].income;
									tds[5].lastElementChild.innerText = incomePercent;
								} else if (list[i].gain == "하락") {
									tds[2].firstElementChild.innerText = list[i].price;
									tds[2].lastElementChild.innerText = list[i].percent;
									tds[6].firstElementChild.innerText = list[i].income;
									tds[6].lastElementChild.innerText = incomePercent;
								} else {
									tds[3].firstElementChild.innerText = list[i].price;
									tds[3].lastElementChild.innerText = list[i].percent;
									tds[7].firstElementChild.innerText = list[i].income;
									tds[7].lastElementChild.innerText = incomePercent;
								}

								for (var j = 1; j < 8; j++) {
									if (tds[j].firstElementChild.innerText == "") {
										tds[j].style.display = "none";
									}
								}

								// if(IncomePercent>0)
								// {
								// divs[0].firstElementChild
								// }
								// console.log("tds"+i+"번째:"+tds[i].lastElementChild.innerText);
								//		        

								// for(var i =0 ; i<tds.length ;i++){
								// console.log(tds[i]);
								// }
								tds[4].firstElementChild.innerText = list[i].quantity;
								// var cloneTr =
								// document.importNode(trTemplate.Content,
								// true);
								// var tds = cloneTr.querySelectorAll("td");
								// tds[0].innertext = list[i].stockName;
								tbody.append(cloneTr);

							}

							allIncomePercent = (allIncome / allSum) * 100;
							allIncomePercent = allIncomePercent.toFixed(2);

							var Valuation = allSum + allIncome;

							if (allIncomePercent > 0) {
								preArea.firstElementChild.firstElementChild.nextElementSibling.className = "up";
								preArea.firstElementChild.firstElementChild.nextElementSibling.innerText = allIncomePercent
										+ "%";
								preArea.lastElementChild.lastElementChild.className = "up";
								preArea.lastElementChild.lastElementChild.innerText = addComma(allIncome)
										+ won;
								backArea.lastElementChild.lastElementChild.className = "up";
								backArea.lastElementChild.lastElementChild.innerText = addComma(Valuation)
										+ won;

							} else if (allIncomePercent < 0) {
								preArea.firstElementChild.firstElementChild.nextElementSibling.className = "down";
								preArea.firstElementChild.firstElementChild.nextElementSibling.innerText = allIncomePercent
										+ "%";
								preArea.lastElementChild.lastElementChild.className = "down";
								preArea.lastElementChild.lastElementChild.innerText = addComma(allIncome)
										+ won;
								backArea.lastElementChild.lastElementChild.className = "down";
								backArea.lastElementChild.lastElementChild.innerText = addComma(Valuation)
										+ won;
							} else {
								preArea.firstElementChild.firstElementChild.nextElementSibling.innerText = "0%";
								preArea.lastElementChild.lastElementChild.innerText = addComma(allIncome)
										+ won;
								backArea.lastElementChild.lastElementChild.innerText = addComma(Valuation)
										+ won;
							}
							backArea.firstElementChild.firstElementChild.nextElementSibling.style.textAlign = "right";
							backArea.firstElementChild.firstElementChild.nextElementSibling.innerText = addComma(allSum)

									+ won;
						    };
						holdingAjax.send();
					}
					;
					updateLoad();

					// ajax.onload = function() {

					// }

					// if(e.data) //codeNum
					// console.log("holding : " + e.data);
	}
				});

window
		.addEventListener(
				"load",
				function() {
					var section = this.document.querySelector(".holdingList");
					var updateButton = section.querySelector(".updateButton");
					var preArea = section.querySelector(".prearea");
					var backArea = section.querySelector(".backarea");
					var tbody = section.querySelector("table tbody");

					function addComma(num) {
						var regexp = /\B(?=(\d{3})+(?!\d))/g;
						return num.toString().replace(regexp, ',');
					}

					function holdingLoad() {
						
						var holdingRequest = new XMLHttpRequest();
						holdingRequest.open("GET",
								"../../card/managestocks/holdinglist-json",
								true);

						// 서블릿의 실행이 완료되었을때 실행
//						if(holdingRequest.responseText != "")
//						{
						holdingRequest.onload = function() {
							// var cardFooter =
							// section.querySelector(".card-footer");
							if(holdingRequest.responseText == -1){
								return;
							}
							else{
							var list = JSON.parse(holdingRequest.responseText);
							var won = "원";
							var allIncomePercent = 0;
							var allIncome = 0;
							var allSum = 0;
							tbody.innerHTML = "";

							for (var i = 0; i < list.length; i++) {

								var template = section
										.querySelector(".template");
								var cloneTr = document.importNode(
										template.content, true);
								var tds = cloneTr.querySelectorAll("td");

								var incomePercent = ((list[i].income / list[i].sum) * 100)
										.toFixed(2);
								allIncome += list[i].income;
								allSum += list[i].sum;
								allIncomePercent += incomePercent;

								list[i].income = addComma(list[i].income);
								list[i].sum = addComma(list[i].sum);

								tds[0].firstElementChild.innerText = list[i].stockName;

								if (list[i].gain == "상승") {
									tds[1].firstElementChild.innerText = list[i].price;
									tds[1].lastElementChild.innerText = list[i].percent;
									tds[5].firstElementChild.innerText = list[i].income;
									tds[5].lastElementChild.innerText = incomePercent;
								} else if (list[i].gain == "하락") {
									tds[2].firstElementChild.innerText = list[i].price;
									tds[2].lastElementChild.innerText = list[i].percent;
									tds[6].firstElementChild.innerText = list[i].income;
									tds[6].lastElementChild.innerText = incomePercent;
								} else {
									tds[3].firstElementChild.innerText = list[i].price;
									tds[3].lastElementChild.innerText = list[i].percent;
									tds[7].firstElementChild.innerText = list[i].income;
									tds[7].lastElementChild.innerText = incomePercent;
								}

								for (var j = 1; j < 8; j++) {
									if (tds[j].firstElementChild.innerText == "") {
										tds[j].style.display = "none";
									}
								}

								tds[4].firstElementChild.innerText = list[i].quantity;
								tbody.append(cloneTr);

							}

							allIncomePercent = (allIncome / allSum) * 100;
							allIncomePercent = allIncomePercent.toFixed(2);

							if (allIncome >= 0) {
								var Valuation = allIncome - allSum;
							} else {
								var Valuation = allSum + allIncome;
							}

							if (allIncomePercent > 0) {
								preArea.firstElementChild.firstElementChild.nextElementSibling.className = "up";
								preArea.firstElementChild.firstElementChild.nextElementSibling.innerText = allIncomePercent
										+ "%";
								preArea.lastElementChild.lastElementChild.className = "up";
								preArea.lastElementChild.lastElementChild.innerText = addComma(allIncome)
										+ won;
								backArea.lastElementChild.lastElementChild.className = "up";
								backArea.lastElementChild.lastElementChild.innerText = addComma(Valuation)
										+ won;

							} else if (allIncomePercent < 0) {
								preArea.firstElementChild.firstElementChild.nextElementSibling.className = "down";
								preArea.firstElementChild.firstElementChild.nextElementSibling.innerText = allIncomePercent
										+ "%";
								preArea.lastElementChild.lastElementChild.className = "down";
								preArea.lastElementChild.lastElementChild.innerText = addComma(allIncome)
										+ won;
								backArea.lastElementChild.lastElementChild.className = "down";
								backArea.lastElementChild.lastElementChild.innerText = addComma(Valuation)
										+ won;
							} else {
								preArea.firstElementChild.firstElementChild.nextElementSibling.innerText = "0%";
								preArea.lastElementChild.lastElementChild.innerText = addComma(allIncome)
										+ won;
								backArea.lastElementChild.lastElementChild.innerText = addComma(Valuation)
										+ won;
							}
							backArea.firstElementChild.firstElementChild.nextElementSibling.style.textAlign = "right";
							backArea.firstElementChild.firstElementChild.nextElementSibling.innerText = addComma(allSum)

									+ won;
						    };
						}
						holdingRequest.send();
						}
//						else{
//							return;
//						}
					
					holdingLoad();

					setInterval(function() {
						holdingLoad();
					}, 5000);
					});




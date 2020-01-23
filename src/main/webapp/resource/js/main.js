window.addEventListener("load", function() {
	var stage = document.querySelector("#dragdiv");
	var currentCard;
	function load(e) {
		var request = new XMLHttpRequest();

		request.open("GET", "../main-json", true);

		request.onload = function() {
            stage.innerHTML = "";
			var list = JSON.parse(request.responseText);
			list = list.split(",");

			var cardTemplate = document.querySelector(".cards-template");
			var cloneCards = document.importNode(cardTemplate.content, true);
			var cards = cloneCards.querySelectorAll(".card");
			var cardsPos = cloneCards.querySelectorAll(".column");
			var cardsCopy = [];
            
			for (var i = 0; i < cards.length; i++) {
				cardsCopy[i] = cards[i].cloneNode(true);
				cardsPos[i] = cards[i].parentNode;
				cards[i].remove();
			}

			for (var i = 0; i < list.length; i++) {
				var cardIndex = list[i] - 1;

				if (cardIndex == -1)
					continue;

				cardsPos[i].append(cardsCopy[cardIndex])
			}
			stage.append(cloneCards);
			allowDragable();
		};

		request.send();
	}

	load();

	function allowDragable() {
		var cards = stage.querySelectorAll(".card");
		for (var i = 0; i < cards.length; i++) {
			cards[i].draggable = true;
		}
	}

	stage.ondragstart = function(e) {
        currentCard = e.target;
	}

	stage.ondragover = function(e) {
		e.preventDefault();
	}

	stage.ondrop = function(e) {
		var targetCard = e.target.parentNode.parentNode.parentNode;
		
		if (!targetCard.classList.contains("card"))
			return;

		if (targetCard == currentCard)
			return;

		if (currentCard.nodeName == "A")
			return;

		e.preventDefault();

		targetCard = targetCard.id.substring(4);
		currentCard = currentCard.id.substring(4);
		var cardPos = stage.querySelectorAll(".column");
		var list = [];
		for(var i = 0; i < cardPos.length; i++) {
			if (cardPos[i].firstElementChild == null) {
				list[i] = "0";
				continue;
			}
				
			list[i] = cardPos[i].firstElementChild.id.substring(4);
		}
		
		function switching(){
			var a;
			var b;
			for(var i = 0; i < list.length; i++) {
				if(list[i] == currentCard)
					a = i;
				if(list[i] == targetCard)
					b = i;
			}
			
			var temp = list[a];
			list[a] = list[b];
			list[b] = temp;
		}
		
        switching();
        
//		var tempCard = targetCard.cloneNode(true);
//		var currentCardPos = currentCard.parentNode;
//		var targetCardPos = targetCard.parentNode;
//
//		targetCard.remove();
//		targetCardPos.append(currentCard);
//        currentCardPos.append(tempCard);
        var data = "cardPos=" + JSON.stringify(list);
        console.log(data);
        var request = new XMLHttpRequest();
        request.open("POST", "../main-json", true);
        request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        request.onload = function(){
            if(request.responseText == 1){
                load();
            }
            else
                alert("로그인 후 이용할 수 있는 기능입니다.");
        }
        
        request.send(data);
	}
	
	
});
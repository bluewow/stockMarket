var timer;

//인덱스창에서 엔터키가 눌렸을 때 실행할 내용
function enterkey() {
    if (window.event.keyCode == 13) {
    	var searchInput = document.querySelector(".search__input").value;

    	var EncodeData = encodeURI(searchInput);

    	location.href="./main?k="+EncodeData;
    	
    }
}

window.addEventListener("mousewheel", function(e) {
    var currentPosition = Math.round(window.pageYOffset / innerHeight);
    var stage = document.querySelectorAll(".page");
    var targetPage = 0;
    
    if(timer != null)
        return;
        
    timer = setTimeout(function(){
        // 아래로 내리기
        if(e.deltaY > 0 || e.wheelDelta < 0) {
            if(currentPosition + 1 >= stage.length)
                targetPage = stage.length - 1;
            else
                targetPage = currentPosition + 1;
        } else {
            if(currentPosition - 1 <= 0)
                targetPage = 0;
            else
                targetPage = currentPosition - 1;
    }
    
    scrollingPage(targetPage);
    
    clearTimeout(timer);
    timer = null;
    },250);
});

window.addEventListener("resize", function() {
    resizeDiv();
});

window.addEventListener("load", function() {
    resizeDiv();
    
    var navigation = document.querySelector("#index-navigator");
    var circles = navigation.querySelectorAll(".circle");
    for (var i = 0; i < circles.length; i++)
        circles[i].style.top = i * 25 + "px";

    navigation.onclick = function(e) {
        var clickCircle = e.target;

        if (clickCircle.nodeName != "DIV") {
            return;
        }

        var body = document.querySelector("body");
        var circleTop = window.getComputedStyle(clickCircle).top;
        var circleIndex = parseInt(circleTop) / 25;
        scrollingPage(circleIndex);
    }
});

function resizeDiv() {
    var pages = document.querySelectorAll(".page");

    for (var i = 0; i < pages.length; i++)
        pages[i].style.height = this.innerHeight + "px";
}

function scrollingPage(index) {
    var stage = document.querySelectorAll(".page")[index];
        var offset = stage.offsetTop;

        var agent = navigator.userAgent.toLowerCase();

        // 익스플로러
        if ((navigator.appName == "Netscape" && navigator.userAgent.search("Trident") != -1) ||
        agent.indexOf("msie") != -1) {
            var currentPosition = window.pageYOffset;
            var w = 0;
            var interval;
            
            // 아래로 내려갈 때
            if (currentPosition < offset) {
            interval = setInterval(function() {
                (w >= 30) ? w = 30 : w += 3;
                    currentPosition += w; 
                    scrollTo(0.0, Math.round(currentPosition));
                        
                    if(currentPosition >= offset){
                        clearInterval(interval);
                        scrollTo(0.0, Math.round(offset));
                    }
                }, 100/ 6);
            }
            // 위로 올라갈 때
            else if (currentPosition > offset) {
                interval = setInterval(function() {
                    (w >= 30) ? w = 30 : w += 3;
                    currentPosition -= w; 
                    scrollTo(0.0, Math.round(currentPosition));
                    
                    if(currentPosition <= offset) {
                        clearInterval(interval);
                        scrollTo(0.0, Math.round(offset));
                    }
                }, 100/ 6);
            }
        } else {    // 익스플로러가 아닐 때
            window.scroll({
                behavior: "smooth",
                left: 0,
                top: offset
            });
        }
}

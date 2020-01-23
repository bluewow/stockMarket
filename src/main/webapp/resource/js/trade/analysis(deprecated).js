window.addEventListener("message", function(e) {
	if(e.data && (e.data.length == 6)) //codeNum
		console.log("analysis : " + e.data); 
});

window.addEventListener("load", function(){ 
	//캡쳐하기
	captureButton();
	
	function captureButton() {
		var button = document.querySelector("#capture");
		
		if(button == null)
			return;
		
		button.onclick = function(e) {
			var ajax = new XMLHttpRequest();
			ajax.open("GET", "../../card/trade/analysis?capture=on");
			ajax.onload = function() {
				//data send to capture Card
				var frame = parent.document.querySelector("#capture-window");
				frame.contentWindow.postMessage(
						{capture: ajax.responseText }, 
						"http://localhost:8080/card/capturememo/captureMemo.jsp");
			}
			ajax.send();
		}
	}
	
});


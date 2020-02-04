
function interestLoadProto(){
   
   var section = this.document.querySelector(".interestList");
   var tbody = section.querySelector("table tbody");
   var delButton = section.querySelector("#deleteButton");
   
   
   function interestLoad() {
	   

      var interestAjax = new XMLHttpRequest();
      interestAjax.open("GET", "../../card/managestocks/interest_list_json", true);
      // 서블릿의 실행이 완료되었을때 실행   

      interestAjax.onload = function () {
         if (interestAjax.responseText == -1) {
        	 tbody.firstElementChild.innerHTML = '<td colspan="5">관심종목이 없습니다</td>'
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
   }, 1000 * 60 * 5);

   tbody.onclick = function (e) {
      e.preventDefault();
      if (event.target.nodeName == "BUTTON") {

         var delTarget = e.target.parentNode.parentNode;
         var delStockName = e.target.dataset.delStockName;
         var data = [["delStockName", delStockName]];
         var sendData = [];

         sendData[0] = data[0].join('=');

         sendData = sendData.join('&');
         JSON.stringify(sendData);

         var delRequest = new XMLHttpRequest();
         delRequest.open("POST", "../../card/managestocks/interest_list_json", true)
         delRequest.setRequestHeader('Content-Type',
            'application/json');
         
         delRequest.onload = function () {
             alert("삭제되었습니다.");
             delTarget.parentNode.removeChild(delTarget);
             if(tbody.firstElementChild == null)
             {
            	 tbody.innerHTML = '<tr> <td colspan="5">관심종목이 없습니다</td> </tr>'
             }
          }
          delRequest.send(sendData);
//         if (delRequest.responseText == -1) {
//        	 console.log("실행");
//        	 tbody.firstElementChild.innerHTML = '<td colspan="5">관심종목이 없습니다</td>'
//            return;
//         }
//         else{
//        	 return;
//         }
      }

   };
}

window.addEventListener("message", function (e) {
   if (e.data && (e.data.length == 6)) {
      interestLoadProto();
   }
});

window.addEventListener("load", function () {
   interestLoadProto();
   
});
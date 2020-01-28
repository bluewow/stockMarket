<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>


<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="../../../../resource/css/normalize.css">
<link rel="stylesheet" type="text/css" href="../../../../resource/css/managestocks.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="../../../../resource/js/managestocks/holding_list.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>

<body class="scrollbar custom-scrollbar-style">
<div class="holdingList">
<!-- <button class="updateButton">
데이터 갱신
</button> -->
   <table >
   <thead>
   <tr>
      <th>종목명</th>
      <th>현재가</th>
      <th>보유수량</th>
      <th>수익금</th>
   </tr>
   </thead>
   <tbody >
      <tr><td class="firstTd" colspan="5">보유한종목이 없습니다</td></tr>
   </tbody>

  <!-- <tbody>
     <c:forEach var="n" items="${list}">
      <tr>
         <td style="text-align:center">${n.stockName}</td>
         <c:choose>
         <c:when test="${n.gain=='상승'}">
         <td class="up">${n.price}<span class="fa fa-caret-up"></span><br>
         <span>${n.percent}%</span></td>
         </c:when>
         <c:when test="${n.gain=='하강'}">
         <td class="down">${n.price}<span class="fa fa-caret-down"></span><br>
         <span>-${n.percent}%</span></td>
         </c:when>
         <c:otherwise>
         <td >${n.price}<span>-</span><br>
         <span>${n.percent}%</span></td>
         </c:otherwise>
         </c:choose>
          <td>${n.quantity}주</td>   
           <td class="up"><span>4,418</span><br>
           <span>1.15%</span></td>
          </td>
      </tr> 
    </c:forEach>  
      </tbody> -->
   </table>

   <template class="template" >
   <tr>
      <td style="text-align: center">
         <span>stockName</span>
      </td>

      <td class="up">
      <span></span>
         <span class="fa fa-caret-up"></span><br>
         <span></span>%
      </td>

      <td class="down">
         <span></span>
         <span class="fa fa-caret-down"></span><br>
         -<span></span>%
      </td>

      <td>
         <span></span>
         <span></span><br>
          <span></span>%
      </td>

      <td>
         <span>-</span>
         <span>주</span>
      </td>
      
      <td class="up">
         <span></span>
        <span></span> 
         <br> 
         <span></span>
         %
      </td>
      <td class="down">
         <span></span>
         <span></span>  
         <br> 
         <span></span>
         <span></span>
         %
      </td>
       <td>
         <span></span>
         <span></span>
         <br> 
         <span></span>
         %
      </td>
   </tr>
   </template>
   <div class="card-footer">
    <div class="prearea">
    	<div>
		<div>수익률</div>
		<div>-</div>
		</div>
    	<div>
		<div>손익금</div>
		<div>-</div> 
    	</div>

	</div>
    <div class="backarea">
    	<div>
		<div>매수금</div>
		<div>-</div>  
		</div>
		<div>
		<div>평가금</div>
		<div>-</div> 
		</div> 
	</div>
   </div>
 </div>
</body>

</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>

<meta charset="utf-8">
<!-- CSS Reset --> 
<link rel="stylesheet" type="text/css" href="../../../../resource/css/normalize.css">
<!-- download fontawesome.com -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<script src="https://d3js.org/d3.v4.min.js"></script>
<link rel="stylesheet" href="../../../../resource/css/billboard.css">
<script src = "../../../../resource/js/billboard.js"></script>

<!-- ref analysis.css -->
<link rel="stylesheet" href="../../../../resource/css/trade/analysis.css">
<script type="text/javascript" src="../../../../resource/js/trade/analysis.js"></script>

</head>
<body>
<div id="analysis-container">
	<!-- --------------- page-top -------------- -->
	<header class="page-top">
		<div id="stockName">
			<div></div>	<!-- 종목명 -->
			<c:if test="${not empty sessionScope.id }"> 
				<input class="animation" id="capture" type="button" value="캡쳐하기">
 			</c:if> 
			<br>
			<div></div>		<!-- 현재가 -->
			<span></span> 	<!-- up/down 기호-->
			<span></span>	<!-- 원 -->
			<span></span>	<!-- % -->
		</div>

	</header>

	<!-- --------------- page-mid -------------- -->
	<section class="page-mid">
		<div>
			<div>
				<div class="chart-string">종목동향<div id="chartA"></div></div>
				<br>
	 			<div class="chart-string">개인수급<div id="chartB"></div></div>
	 			<br>
	 			<div class="chart-string">영향력<div id="chartC"></div></div>
			</div>
			<div>
				<div class="chart-string">컨텐츠<div id="chartD"></div></div>
				<br>
				<div class="chart-string">거래량<div id="chartE"></div></div>
				<br>
				<!-- <div class="chart-string">분석결과</div> -->
				<div></div>
				<br> 
				<input class="analysis-result" type="button" value="변동성 높음">
				<div></div><br>
				<input class="analysis-result" type="button" value="변동성 중간">
				<div></div><br>
				<input class="analysis-result" type="button" value="변동성 낮음">
				<!-- <div class="chart-string">분석결과<div id="chartF"></div></div> -->
			</div> 
	    </div>
	</section>

	<!-- --------------- page-bottom -------------- -->
	<section class="page-bottom">
	<!-- TODO 누적측정률, 최신측정률 -->
	</section>
	
</div>
</body>
    
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>

<meta charset="utf-8">
<!-- CSS Reset -->
<link rel="stylesheet" type="text/css" href="../../css/normalize.css">
<!-- download fontawesome.com -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<script src="https://d3js.org/d3.v4.min.js"></script>
<link rel="stylesheet" href="../../css/billboard.css">
<script src = "../../js/billboard.js"></script> 

<!-- ref analysis.css -->
<link rel="stylesheet" href="../../css/trade/trading.css">
<script type="text/javascript" src="../../js/trade/trade.js"></script>
	 
	 
</head>
<body>
<div id="trading-container">
	<!-- --------------- page-top -------------- -->
	<header class="page-top">
		<div id="title">호가창</div>
		<div id="title-ass"></div>
	</header>
	<!-- --------------- page-mid -------------- -->
	<section class="page-mid">
		<div class="chart-string">매도잔량<div id="chartSell"></div></div>
		<div class="chart-string">매수잔량<div id="chartBuy"></div></div>
	</section>
	
	<!-- --------------- page-bottom -------------- -->
		<section class="page-bottom">
		<form id="page-bottom-box" action="trade" method="get">
			<div class="show-button-align">
				<input class="button button-status" type="button" value="가상머니">
				<div class="data"><fmt:formatNumber value="" pattern="#,###" />원</div>

				<input class="button button-status" type="button" value="   단가   ">
				<div style="position: relative;">
					<input type="text" value="" class="text" readonly>
					<i class="fa fa-caret-up fa-lg caret-up animation-1" aria-hidden="true"></i>
					<i class="fa fa-caret-down fa-lg caret-down animation-2" aria-hidden="true"></i>
				</div>
				

				<input id="buy" class="event button button-button animation" type="button" name="trade" value="매       수">
			</div>
			<div class="show-button-align">
				<input class="button button-status" type="button" value="보유수량">
				<div class="data"><fmt:formatNumber value="" pattern="#,###" />주</div>	
				
				<input class="button button-status" type="button" value="   수량   ">
				<div style="position: relative;">
					<input type="number" class="text" value=0 >
					<i class="fa fa-caret-up fa-lg caret-up animation-1" aria-hidden="true"></i>
					<i class="fa fa-caret-down fa-lg caret-down animation-2" aria-hidden="true"></i>
				</div>
				
				<input id="sell" class="event button button-button animation" type="button" name="trade" value="매       도">
			</div>
		</form>
	</section>
</div>	

</body>
</html>
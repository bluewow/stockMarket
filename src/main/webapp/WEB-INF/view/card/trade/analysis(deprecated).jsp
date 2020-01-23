<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>

<meta charset="utf-8">
<!-- CSS Reset --> 

<link rel="stylesheet" type="text/css" href="../../css/normalize.css">
<!-- progress bar -->
<link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

<!-- download fontawesome.com -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<!-- ref analysis.css -->
<link rel="stylesheet" href="../../css/trade/analysis.css">

<!-- arrow 처리 -->
<%-- <c:if test= "${direction == 'up'}">
	<c:set var="arrow-direction" value="fa-arrow-up"/>
</c:if>
<c:if test= "${direction == 'down'}">
	<c:set var="arrow-direction" value="fa-arrow-down"/>
</c:if> --%>

<script type="text/javascript" src="../../js/trade/analysis.js"></script>

</head>
<body class="scrollbar custom-scrollbar-style">
<div id="analysis-container">
	<!-- --------------- page-top -------------- -->
	<header class="page-top">
		<div id="stockName">
			<div>${companyName }</div>
			<div>204,000</div>
		</div>
		<c:if test="${not empty sessionScope.id }">
			<input id="capture" type="button" value="캡쳐하기">
		</c:if>
	</header>

	<!-- --------------- page-mid -------------- -->
	<section class="page-mid">
		<div class="page-left-chart">차트1</div>
		<div class="page-right-chart">
			<div class="page-right-write">급등 (${predict_1 }%)</div>
			<div class="progress progress-right-chart">
	            <div class="progress-bar progress-red" style="width: ${predict_1 }%;"></div>
	        </div>
	        <div class="page-right-write">우상향 (${predict_2 }%)</div>
			<div class="progress progress-right-chart">
	            <div class="progress-bar progress-red-weak" style="width: ${predict_2 }%;"></div>
	        </div>	      
	        <div class="page-right-write">우하향 (${predict_3 }%)</div>  
			<div class="progress progress-right-chart">
	            <div class="progress-bar progress-blue" style="width: ${predict_3 }%;"></div>
	        </div>
	        <div class="page-right-write">급락 (${predict_4 }%)</div>
	        <div class="progress progress-right-chart">
	            <div class="progress-bar progress-blue-weak" style="width: ${predict_4 }%;"></div>
	        </div>	        
		</div>
	</section>

	<!-- --------------- page-bottom -------------- -->
	<section class="page-bottom">
		<div id="comment">분석 내용 </div>
		<div id="chart-bottom">
	        <div class="content margin-content">
	        	<div class="content-first">관심도
					<div class="tool-tip">
						<span><i class="fa fa-question-circle fa-lg ho" aria-hidden="true"></i></span>
						<div class="tool-tip-content">
							<p><span style="color:rgb(204,102,102);">데이터 베이스</span>를 통해 검출한 통계량을 가지고 분석한 관측치입니다.</p>
							<hr>
							<div>신뢰도 %</div>
						</div>
					</div>
	        	</div> 
	        	<div class="progress content-second">
		            <div class="progress-bar progress-red" style="width: 40%;">${content_1 }%</div>
		        </div>
	     	   <div class="content-third"><i class="fa fa-arrow-up" aria-hidden="true"></i></div>
	        </div>
	        <div class="content">
				<div class="content-first">재무상황
					<div class="tool-tip">
						<span><i class="fa fa-question-circle fa-lg ho" aria-hidden="true"></i></span>
						<div class="tool-tip-content">
							<p><span style="color:rgb(204,102,102);">데이터 베이스</span>를 통해 검출한 통계량을 가지고 분석한 관측치입니다.</p>
							<hr>
							<div>신뢰도 %</div>
						</div>
					</div>
				</div>       	
		        <div class="progress content-second">
		            <div class="progress-bar progress-red" style="width: 50%">${content_2 }%</div>
		        </div>
		        <div class="content-third"><i class="fa fa-arrow-down" aria-hidden="true"></i></div>  
	        </div>
	        <div class="content">
	        	<div class="content-first">미정
	        		<div class="tool-tip">
	        			<span><i class="fa fa-question-circle fa-lg" aria-hidden="true"></i></span>
	        			<div class="tool-tip-content">
							<p><span style="color:rgb(204,102,102);">데이터 베이스</span>를 통해 검출한 통계량을 가지고 분석한 관측치입니다.</p>
							<hr>
							<div>신뢰도  %</div>
						</div>
	        		</div>
	        	</div>
	        	 <div class="progress content-second">
		            <div class="progress-bar progress-blue" style="width: 60%">${content_3 }%</div>
		        </div>		     
	        	<div class="content-third"><i class="fa fa-arrow-down" aria-hidden="true"></i></div>
	        </div>
		</div>
	</section>
	
</div>
</body>
</html>
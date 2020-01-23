<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html>
<head>
	<meta charset="UTF-8">
	<title>stock market</title>

	<!-- Layout Styles -->

	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	<script src="https://d3js.org/d3.v5.min.js"></script>
	<link rel="stylesheet" href="../../css/myasset/billboard.css">
	<script src="../../js/asset/billboard.js"></script>
	<link rel="stylesheet" type="text/css" href="../../css/myasset/myAsset.css">
	<script src="../../js/asset/myAsset.js"></script>
</head>

<body class="scrollbar custom-scrollbar-style">

	<main id="card">
		<section id="card-top">
			<div id="titleAssetTrend" class="wrap">
				<div class="title">
					<h1>자산 추이</h1>
				</div>
				<div>
					<h2>현 보유 자산</h2>
				</div>
				<div>
					<div class="my-asset-info wrap">
						<ul class="buttons" style=display:none>
							<li class="selected-button"><a href="">일봉</a></li>
							<li><a href="">주봉</a></li>
							<li><a href="">월봉</a></li>
						</ul>
					</div>
					<p><fmt:formatNumber value="${assetPresent}"/></p>
				</div>
			</div>
			<div id="assetTrend">
				<div id="graph-assetTrend">
					<h1 class="visable-none">선형 그래프</h1>
					<div id="chart"></div>
				</div>

			</div>
		</section>
		<section id="card-bottom">
			<div class="title">
				<h1>자산 분포도</h1>
			</div>
			<div id="assetDistribution">
				<div id="graph-assetDistr">
					<h2 class="visable-none">원형그래프</h2>
					<div class="graph">
						<div id="donutChart"></div>
						<!-- <div id="donut_single" style="width: 700px; height: 300px; fill: none;"></div>
						<div id="labelOverlay">
							<p class="used-size">보유 비중<span></span></p>
							<p class="total-size">(%)</p>
						  </div> -->
					</div>
				</div>

				<div id="list-stock">
						<h2 class="visable-none">종목 리스트</h2>
						<div class="list">	
							<table>
								<tr>
									<td></td>
									<td></td>
								</tr>
							</table>
						</div>
					</div>
				</div>

				<template class="template-list-stock">
					<tr>
						<td>
							<img src="">
						</td>
						<td>
						</td>
					</tr>


				</template>
		</section>
	</main>
</body>

</html>
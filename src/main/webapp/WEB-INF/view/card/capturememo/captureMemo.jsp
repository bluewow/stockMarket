<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>

<head>
<meta charset="UTF-8">

<link rel="stylesheet" type="text/css" href="../../css/normalize.css">
<link rel="stylesheet" type="text/css" href="../../css/captureMemo.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>

<!--d3js CDN-->
<script src="https://d3js.org/d3.v4.min.js"></script>

<!--다운 받아서 포함 시키기-->
<script src = "../../js/billboard.js"></script>
<link rel="stylesheet" href="../../css/billboard.css">

<script src="../../js/capture/capture.js"></script>

<title>Insert title here</title>
</head>

<body class="scrollbar custom-scrollbar-style">
	<main id="captureScroll">
		<table id="captureTable">
			<thead>
				<tr>
					<td>종목명</td>
					<td>제목</td>
					<td>캡쳐날짜</td>
				</tr>
			</thead>
			<tbody class="content">
				<!-- <c:forEach var="item" items="${list}">
					<tr class="parent">
						<td>${item.codeNumName}</td>
						<td class="capt-title">${item.title}</td>
						<td class="del-button">${item.regdate}<span><a href=""></a></span></td>
					</tr>
				</c:forEach> -->
			</tbody>
		</table>
		<template class="tr-template-list">
		<tr class="parent">
			<td class="capt-name"></td>
			<td class="capt-title"></td>
			<td class="del-button"><span></span></td>
		</tr>
		</template>	
		<template class="tr-template">
		<tr class="child-tr">
			<td class="child" colspan="3">
				<div>
					<div id="radarChart"></div>
					<div></div>					
					<div class="memo">
						<div><input maxlength="20"
							placeholder="제목" ></textarea>
						</div>
						<hr>
						<div>
							<textarea class="scrollbar custom-scrollbar-style" id="memo-content" maxlength="800"
								placeholder="내용을 입력하세요"></textarea>
						</div>
						<div class="buttons">
							<input type="button" class="button" name="cancel" value="수정">
						</div>
					</div>
				</div>
			</td>
		</tr>
		</template>
	</main>
</body>

</html>
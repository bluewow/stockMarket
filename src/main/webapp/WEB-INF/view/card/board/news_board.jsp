<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="kr">

<head>
<title>news_board</title>
<!-- Google Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Montserrat:300,300i,400,400i,500,500i,600,600i,700"
	rel="stylesheet">

<!-- Template Styles -->
<link rel="stylesheet" type="text/css"
	href="../../css/font-awesome.min.css">

<!-- CSS Reset -->
<link rel="stylesheet" type="text/css" href="../../css/normalize.css">

<link href="../../css/board/news_board.css" type="text/css" rel="stylesheet">


</head>
<!-- ======================================================================= -->
<body class="scrollbar custom-scrollbar-style">
	<main id="contaner">
		<div>
			<c:forEach var="news" items="${news_lists}" varStatus="status">
				<table id="newsTable">
					<thead class="content">
						<tr>
							<td class="title"><a href="${news.url}" target="_blank">${news.subject}</a></td>
						</tr>

					</thead>
					<tbody>
						<tr>
							<td class="summary">${news.summary}</td>
						</tr>
					</tbody>
				</table>
			</c:forEach>
			<div id=origin>
				<p>
					출처: <a href="https://finance.naver.com/news/mainnews.nhn?date"
						target="_blank">네이버 금융</a>
				</p>
			</div>
		</div>

	</main>
</body>

</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%-- <c:set var ="n" value="${search}"/> --%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../../css/normalize.css">
<link rel="stylesheet" type="text/css" href="../../css/company/list.css">
<script src="../../js/company/list.js"></script>
<!-- <script src="../../js/company/InterestCheck.js"></script> -->
</head>
<body class="scrollbar custom-scrollbar-style">


	<section id="recommendKeyword">
		<ul>
			<li id="recommendKeyword_fixed">인기 검색어 또는 종목명을 검색해보세요.</li>
			<%-- <c:forEach var="r" items="${recommendKeyword}">
                <a href="list?companyName=${r}"><span>${r}</span></a>
                <span id = ajaxTest>${r}</span>
            </c:forEach> --%>

		</ul>
	</section>


	<div>
		<section id="search-form">
			<h1 class="d-none">주식회사검색</h1>
			<form action="list" method="get">
				<div id="search-div">
					<!-- <label for= "search-text"> -->
					<input id="search-text" type="text" name="companyName"
						value="asdsad">
					<button class="search-button"></button>
					<!-- </label> -->
				</div>
			</form>
		</section>



		<section id="search-result">
			<h1 class="d-none">주식회사 검색 결과</h1>
			<table>
				<thead>
					<tr>
						<th>번호</th>
						<th>종목명</th>
						<th>산업분류</th>
						<th>관심</th>
					</tr>
				</thead>


				<tbody>
					<c:forEach var="sector" items="${search}" varStatus="status">
						<tr>
							<td>${status.count}</td>

							<td id="companyNameWebsite">
								<div data-codenum="${sector.stockCode }" class="companyName">${sector.companyName}
									<a href="${sector.website}" target="_blank"><img
										src="/css/company/link.png"></a>
								</div>
							</td>

							<%--  <td id ="stockItemName" class="wrap">
                        <a>${sector.mainProduct}</a>
                        <div class="help">${sector.mainProduct}</div>
                    </td> --%>

							<td><a href="#" id="stockItemName"
								title="${sector.mainProduct}">${sector.mainProduct}</a></td>


							<!-- 로그인 체크 -->

							<c:if test="${logIn == -1}">
								<td class="interest_no" id="UncertifiedLogin"></td>
							</c:if>

							<c:if test="${logIn != -1}">
								<!-- 값이 들어감  -->
								<c:set target="${interestStocks}" var="interestStocksListCheck" />
								<c:choose>
									<c:when test="${not empty interestStocks }">
										<!-- 값이 들어감 -->
										<c:set var="loop_flag" value="false" />
										<c:set var="isInterest" value="false" />
										<c:forEach var="interestStocks" items="${interestStocks}"
											varStatus="status1">
											<c:if test="${not loop_flag}">
												<c:choose>
													<c:when
														test="${sector.stockCode eq interestStocks.stockCode}">

														<c:set var="isInterest" value="true" />
														<c:set var="loop_flag" value="true" />
													</c:when>
												</c:choose>
											</c:if>
										</c:forEach>
										<c:if test="${isInterest}">
											<td class="interest_yes" data-attention="${sector.stockCode}"
												id="certifiedLoing"></td>
										</c:if>
										<c:if test="${not isInterest}">
											<td class="interest_no" data-attention="${sector.stockCode}"
												id="certifiedLoing"></td>
										</c:if>
									</c:when>
									<c:otherwise>
										<td class="interest_no" data-attention="${sector.stockCode}"
											id="certifiedLoing"></td>
									</c:otherwise>
								</c:choose>
							</c:if>
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</section>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html lang="kr">

<head>
	<meta charset="UTF-8">
	
	<title>Community</title>
	<!-- Google Fonts -->
	<link href="https://fonts.googleapis.com/css?family=Montserrat:300,300i,400,400i,500,500i,600,600i,700" rel="stylesheet">
	
	<!-- Template Styles -->
	<link rel="stylesheet" type="text/css" href="../../css/font-awesome.min.css">
	
	<!-- CSS Reset -->
	<link rel="stylesheet" type="text/css" href="../../css/normalize.css">
	<link href="../../css/board/community_board.css" type="text/css" rel="stylesheet">
	<script src="../../js/board/community_board.js"></script>

</head>

<body>
	<section id="communityTop">
		<nav id="my-menu">
			<a href="" id="my-button">MY</a>
			<a href="" id="favo-button">관심</a>
			<a href="" id="reg-button" class="hidden">글쓰기</a>
		</nav>
	</section>
	<section id="communityScroll">
		<table class="communityTable">
			<thead class="subject">
				<tr>
					<th class="w10">번호</th>
					<th class="w50">제목</th>
					<th class="w20">작성일</th>
					<th class="w10">조회</th>
					<th class="w10"></th>
				</tr>
			</thead>
		</table>
		<div id="scroll" class="scrollbar custom-scrollbar-style">
		<table class="communityTable">
			<tbody class="content">
				<tr>
					<td colspan="5" style="text-align: center; height: 50px; line-height: 50px;">게시된 글이 없습니다.</td>
				</tr>
				<tr class="detail">
					<td colspan=5>데이터가 불러지지 않았습니다.
					</td>
				</tr>
			</tbody>
		</table>
		</div>
		<div class="pager">
			<a class="pn1">1</a> <a class="pn2">2</a> <a class="pn3">3</a> <a class="pn4">4</a> <a class="pn5">5</a>
		</div>

		<template class="tr-template">
		<tr>
			<td></td>
			<td rowspan="2" class="border-bottom board-title"><a href=""></a></td>
			<td rowspan="1"></td>
			<td></td>

			<td rowspan="2" class="border-bottom favo-add"><a href=""></a></td>
		</tr>
		<tr>
			<td class="border-bottom del"><a href=""></a></td>
			<td colspan="2" class="border-bottom"></td>
		</tr>
		</template>

		<template class="detail-template">
		<tr class="content-row">
			<td colspan=5>
			</td>
			</tr>
			<tr class="reply-content-row">
			<td colspan=5>
				<table class="replyTable">
					<colgroup>
						<col width="85%">
						<col width="15%">
					</colgroup>
					<thead>
						<tr>
							<td style="text-align:center; height:50px; line-height:50px;">
							<input type="text" class="reply-content" name="title" maxlength="200" placeholder="주제와 무관한 댓글이나, 악플은 경고조치없이 삭제되며 징계 대상이 됩니다.">
							</td>
							<td class="reply-submit-button"> <a href="" class="reg-reply-button">등록</a>
							</td>
						</tr>
					</thead>
					
					<tbody>
						<tr>
							<td colspan="2" style="text-align:left; height:25px; line-height:25px;">게시된 글이 없습니다.</td>
						</tr>
					</tbody>
				</table>

			</td>
		</tr>

		</template>
	</section>
		<!-- ===== 글쓰기 POPUP ========================================================================================================== -->   
 	<div class="pop-up-reg">
 		<form class="regboard-box" method="post" id="regboard">
			<div class="pop-up-title-row">
				<input type="text" class="reg-title" name="title" maxlength="40" placeholder="제목을 입력하세요">
				
			</div>
			<div class="pop-up-content-row">
				<textarea class="reg-content" maxlength="800" placeholder="내용을 입력하세요"></textarea>
				<input type="button" class="reg-submit" name="submit" value="확인">
				<input type="button" class="reg-cancel" name="cancel" value="취소">
			</div>
		</form>
	</div>
</body>

</html>
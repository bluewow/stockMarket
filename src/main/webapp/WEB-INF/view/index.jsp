
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>stock market</title>

    <link rel="stylesheet" href="./css/index-search.css" type="text/css">
    <link rel="stylesheet" href="./css/index.css" type="text/css">
    <link rel="stylesheet" href="./css/main.css" type="text/css">
    <link rel="stylesheet" href="./css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="css/normalize.css" type="text/css">
    <script src="js/index.js"></script>
</head>

<body>
    <main id="main-page" class="page">
        <div>
            <h1 id="logo">STOCK<br>MARKET</h1>
            <div class="bg-img"></div>
            <div class="main-contents">
            	<div class="title-img">
	            	<div>
	               		 <a href="./main"><img src="../images/index_title.png"/></a>
               		</div>
				</div>
                <div class="search">
					  <input type="checkbox" id="trigger" class="search__checkbox" />
					  <label class="search__label-init" for="trigger"></label>
					  <label class="search__label-active" for="trigger"></label>
					  <div class="search__border"></div>
					  <input type="text" class="search__input" onkeyup="enterkey()" style="border: none !important;"/>
					  <div class="search__close"></div>
				</div>
				<div class="notice">
					검색 아이콘을 클릭 후, 원하는 회사, 관심있는 분야, 종목을 검색해보세요.
				</div>
				<div class="link">
					<a href="./main">메인화면 바로가기</a>
				</div>
            </div>
        </div>
    </main>
    <section id="sub-page" class="page">
        <div>
            <p>주식투자를 쉽고 빠르게</p>
            <p>Easy Way to SMART TRADING</p>
        </div>
    </section>
    <nav id="index-navigator" class="wrap">
        <div class="circle circle1"></div>
        <div class="circle"></div>
        <div class="circle"></div>
    </nav>
</body>

</html>
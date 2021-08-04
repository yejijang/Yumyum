<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>

<%@ include file="/inc/asset.jsp"%>

<style>

.container-custom {
	width: 1100px;
	text-align: center;
	margin: 0px auto;
	
}

.container {
	margin-top: -20px;
}

.card01 a {
   color: black;
   text-decoration: none;
}

.card01 a:hover {
   text-decoration: none;
}

.middle-column {
	margin-bottom: 20px;
}
.item-card {
	width: 600px;
	height: 120px;
	background-color: #ffffff;
	box-shadow: 2px 2px 5px 0 rgba(0, 0, 0, 0.2);
	margin-left: 20px;
	margin-bottom: 20px;
	margin: 0px auto;
}

.card01 {
	margin-top: 5px;
}

.item-info {
	position: relative;
	top: -70px;
	left: 150px;
	margin-top: 18px;
	padding-left: 15px;
	border-left: 1px solid rgba(0, 0, 0, 0.2);
	width: 400px;
}

.item-info>p {
	line-height: 15px;
	font-size: 18px;
	margin-bottom: 15px;
}


.item-image {
	position: relative;
	top: 20px;
	left: 40px;
	width: 150px;
}

.badge-primary {
	background-color: #87b5ff;
}

.scope {
	margin-top: -2px;
	font-size: 14px !important;
}

.scope-rate {
	color: #ffe200;
	font-size: 20px;
	width: 20px;
	height: 10px;
	position: relative;
	top: 5px;
}


.search {
	width: 260px;
	margin-left: 10px;
}

form {
	float: left;
	margin-top: 10px;
	margin-left: 10px;
}

.nav-link {
	font-size: 17px;
	color: black;
}

.nav-item {
	display: inline-block;
}
.category {
	width: 1100px;
	margin: 0px auto;
	display: inline-block;
	margin-top: 50px;
}

.nav-tabs {
    border: none;
}

.pull-right {
	width: 200px;
}

.nav-menu {
	display: inline-block;
}

.info-detailmenu span {
	font-size: 16px;
}
</style>
</head>
<body>

	<%@ include file="/inc/header.jsp"%>

	<input type="hidden" id="address" name="address">
	<div class="container-custom">
		<div class="category">
			<ul class="nav nav-tabs nav-menu">
				
				<li class="nav-item">
					<a class="nav-link" href="/yumyum/shoplist.do?category=1">한식</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/yumyum/shoplist.do?category=2">분식</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/yumyum/shoplist.do?category=3">카페/디저트</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/yumyum/shoplist.do?category=4">돈가스/회/일식</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/yumyum/shoplist.do?category=5">치킨</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/yumyum/shoplist.do?category=6">피자</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/yumyum/shoplist.do?category=7">아시안/양식</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/yumyum/shoplist.do?category=8">중국집</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/yumyum/shoplist.do?category=9">족발/보쌈</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/yumyum/shoplist.do?category=10">야식</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/yumyum/shoplist.do?category=11">찜/탕</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/yumyum/shoplist.do?category=12">패스트푸드</a>
				</li>
			</ul>
			<form method="get" action="/yumyum/shoplist.do">
				<div class="search">
					<input type="text" class="form-control pull-right"
						placeholder="가게명을 입력하세요." name="searchWord" />
					<input type="submit" class="btn btn-primary" value="검색">
				</div>
			</form>
		</div>
	</div>
	
	<div class="container">
		<h1 class="page-header"></h1>

		<c:forEach items="${list}" var="dto">
		<div class="middle-column">	
			<div class="item-card card01">
				<a href="/yumyum/shop.do?seq=${dto.seq}">
					<div class="item-image">
						<img src="/yumyum/images/category/category01.png" alt="item-image" width="70" />
					</div>
					<div class="item-info">
						<p class="info-title">
							<strong>${dto.name}</strong> 
							<c:if test="${dto.event_flag == 'Y'}">
							<span class="special-tags">
								<span class="badge badge-pill badge-primary">이벤트</span>
							</span>
							</c:if>
						</p>
						<p class="scope">
							<c:choose>
								<c:when test="${fn:length(dto.explanation) gt 26}">
								<c:out value="${fn:substring(dto.explanation, 0, 25)}...">
								</c:out></c:when>
								<c:otherwise>
								<c:out value="${dto.explanation}">
								</c:out></c:otherwise>
							</c:choose>
							
						</p>
						<p class="info-detailmenu">
							<i class="material-icons scope-rate">star_rate</i> 
							<strong class="pd2">${dto.avg_score}</strong>
							<span>최소주문금액: ${dto.min_price}원</span>
						</p>
					</div>
				</a>
			</div>
		</div>
		</c:forEach>

	</div>

	<script>
	
		$('#address').val('${address}');
		
		$('a').click(function () {
			
			let anchors = document.getElementsByTagName('a');
			for (let i=0; i<anchors.length; i++) {

				anchors[i].setAttribute('href', anchors[i].getAttribute('href') + '&address=' + $('#address').val());
			}
		});
	</script>

</body>

</html>
<footer>
	<%@ include file="/inc/copyright.html"%>
</footer>
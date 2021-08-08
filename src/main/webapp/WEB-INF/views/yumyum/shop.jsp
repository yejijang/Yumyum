<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Material+Icons">
<link rel="stylesheet"
	href="https://unpkg.com/bootstrap-material-design@4.1.1/dist/css/bootstrap-material-design.min.css"
	integrity="sha384-wXznGJNEXNG1NFsbm0ugrLFMQPWswR3lds2VeinahP8N0zJw9VWSopbjv2x7WCvX"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script src="https://unpkg.com/popper.js@1.12.6/dist/umd/popper.js"
	integrity="sha384-fA23ZRQ3G/J53mElWqVJEGJzU0sTs+SvzG8fXVWP+kJQ1lwFAOkcUOysnlKJC33U"
	crossorigin="anonymous"></script>
<script
	src="https://unpkg.com/bootstrap-material-design@4.1.1/dist/js/bootstrap-material-design.js"
	integrity="sha384-CauSuKpEqAFajSpkdjv3z9t8E7RlpJ1UP0lKM/+NdtSarroVKu069AlsRPKkFBz9"
	crossorigin="anonymous"></script>
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">

<%@ include file="/inc/asset.jsp"%>

<style>
.nav {
	margin-top: -80px;
}

.nav>li>a {
	padding: 5px 5px;
}

.nav-item {
	width: 90px;
	text-align: center;
	font-size: 0.9em;
}

#main {
	width: 700px;
	border: 3px solid gray;
	font-size: 20px;
	color: #444;
	text-align: left;
}

#main input[name=rb] {
	display: none;
}

#main label {
	border: 0px solid gray;
	float: left;
	width: 25%;
	text-align: center;
	padding-top: 20px;
	padding-bottom: 20px;
	margin-bottom: 0;
	cursor: pointer;
	font-size: 20px;
}

#main #menuinfo:checked {
	display: none;
}

#main #menu:checked ~ #reviewinfo, #main #menu:checked ~ #noticeinfo,
	#main #menu:checked ~ #shopinfoinfo {
	display: none;
}

#main #review:checked ~ #menuinfo, #main #review:checked ~ #noticeinfo,
	#main #review:checked ~ #shopinfoinfo {
	display: none;
}

#main #notice:checked ~ #menuinfo, #main #notice:checked ~ #reviewinfo,
	#main #notice:checked ~ #shopinfoinfo {
	display: none;
}

#main #shopinfo:checked ~ #menuinfo, #main #shopinfo:checked ~
	#reviewinfo, #main #shopinfo:checked ~ #noticeinfo {
	display: none;
}

#menuinfo, #reviewinfo, #noticeinfo, #shopinfoinfo {
	background-color: #FD9F32;
	padding: 10px;
}

input[type=radio]:checked+label {
	background-color: #FD9F32;
	color: #444;
	text-shadow: 2px 2px 2px gray;
}

.list-group-item {
	width: 640px;
}

#noticeinfo h2 {
	text-align: center;
	border-bottom: 0px;
}

.container-custom {
    width: 800px;
    position: relative;
    margin: 0 auto;
}
.item-list {
    margin-left: 0px;
    margin-top: 10px;
}
.item-set01 {
    width: 157px;
    height: 50px;
    background-color: #efefef;  
    text-align: center;
    line-height: 50px;
}
/* Menu */
.item-menu {
    border-bottom: 1px solid #d3d3d3;
}
.item-info {
    background-color: white;
    border-top: 1px solid #d3d3d3;
    border-left: 1px solid #d3d3d3;
    border-right: 1px solid #d3d3d3;
    border-bottom: 0px;

}
.item-review {
    border-bottom: 1px solid #d3d3d3;
}

.item-content>p {
	font-size:15px;
	margin-bottom:10px;
    line-height: 15px;
}


.list-group>a {
    height: 200px;
    /* border: 2px solid red; */
}

.list-group { 
	width:450px;
}
.food-image-set {
    position: absolute;
    left: 500px;
}
.list-group>a:hover {
    background-color: #f5f5f5;
}

.item-card {
border:0px solid blue;
padding-top:-20px;
    margin-left: 0px;
    width: 550px;
 }
.item-card-main {
	border:0px solid black;
    display:flex;
   
}
.item-card-main img{ 
	width:140px;
	height:140px;
	display:flex;
}

.item-card-info {
	width: 380px;
	border:0px solid red;
	margin-left: 15px;
}
.item-card-info > p {
    font-size: 15px;
    line-height: 20px;
    color: #7c7c7c;
}
.pblack {
    color: #000000!important;
}

.item-title {
    font-size: 20px!important;
    margin-left: 5px;
    margin-top: 5px;
}
.item-score {
    margin-top: 5px;
}
.item-icon {
    width: 25px;
    font-size: 22px;
    margin-top: -13px;
    margin-left: 5px;
    position: relative;
    top: 5px;
    color: #ffe200;
}
.pay-way {
    margin-left: 5px;
}
.minimum-pay {
    margin-left: 5px;
}

.list01>p {
    margin-bottom: -5px;
}
.item-count {
    position: relative;
    left: 0px;
}
.item-count>.bt {
    background-color: white;
    border: 1px solid gray;
    width: 20px;
    cursor: pointer;
}
.item-count>.bt-count {
    border-left: -10px;
    border-right: 0px;
    cursor: default;
}

.btn-set01 {
    width: 230px;
    margin-top: 5px;
}

.review-info-grid>h4 {
    position: relative;
    top: 3px;
    left: 10px;
}
.review-text {
	font-size:16px;
    margin-left: 10px;
}
.review-comment {
	width:500px;
	background-color:#E7E3D7;
	border-radius:10px;
	margin:5px;
	padding-left:10px;
	padding-right:10px;
}
.review-comment>i { 
	margin-top: 3px;
 	font-size: 17px;
}
.review-comment>span {
	font-size: 16px;
}
.review-comment>p {
	font-size: 15px;
}
.review-stars{
	float:right;
}

.review-stars>img {
    top: -4px;
    padding-right:10px;
}
.td2 {
	font-size:10px;
	text-align:right;
	position: relative;
    margin-left: 10px; 
}

fieldset {
	border: 1px dashed #444;
}

legend {
	border-bottom: 0px solid gray;
	color: gray; margin : 20px;
	width: 120px;
	text-align: center;
	margin: 20px;
	font-size:17px;
}
.group {
	margin-left:0px;
	padding-top:10px;
	padding-bottom:20px;
	width:670px;
	list-style: none;
	border-radius:5px;
	background-color:RGBA(247,239,184,1);
}
.menugroup li {
	border-bottom: 0px solid #62760C;
	margin-left:-65px;
	list-style: none;
	padding: 0px 0px 0px 0px;
	text-align: left;
	font-size:17px;
}

#recomm, #nonrecomm{
	cursor: pointer;
}
#recomm:hover, #nonrecomm:hover{
	font-weight:bold;
}

.menugroup a {
	cursor: pointer;
	height: 70px;
}

.menugroup .hide {
	display: none;

}

.ordermenu {
	font-size:12px;
	margin-left:10px;
}

/* Modal */

.table {
	width: 100%;
}

.table tr td {
	width: 50%;
	font-size: 12px;
}

.table tr td:nth-child(2) {
	text-align: right;
	margin-right: 10px;
}

#main .table tr td label {
	width: 100%;
	font-size: 13px;
	padding: 0px;
	text-align: left;
	margin-left: 10px;
}

#main .table tr td label input {
	margin-right: 10px;
	margin-top: -1px;
  	vertical-align: middle;
}

.modal-body {
	padding: 0px;
}

.modal-body .menu_picture {
	width: 200px;
	height: 200px;
	border: 1px solid black;
	margin: 0 auto;
	margin-bottom: 20px;
	text-align: center;
	vertical-align: middle;
}

.modal-body .menu_picture img {
	width: 100%;
	height: 100%;
}

.modal-body .menu_name,
.modal-body .menu_explanation,
.modal-body .menu_quantity {
	text-align: center;
}

.modal-body .menu_explanation,
.modal-body .menu_quantity {
	font-size: 14px;
}

.modal-body .menu_quantity {
	margin-bottom: 30px;
}

.modal-body .menu_quantity input {
	width: 40px;
}

.item-content-set01 .modal-content {
	margin-top: 50px;
}

.modal-content .modal-footer {
	text-align: center;
	display: block;
	margin-bottom: 20px;
}

.item-content-set01 {
	width: 800px;
	
}

.item-content-set01 div {
	margin: 0 auto;
}

</style>

<script>
	$(document).ready(function() {
		$(".menugroup>a").click(function() {
			$(this).next("ul").toggleClass("hide");
		});
	});
</script>

</head>
<body>
	<%@ include file="/inc/header.jsp"%>

	<section class="main-section">
	<h1 class="page-header">${shopdto.category}</h1> 
		<input type="hidden" name="address" id="address">
<!-- 가게설명 -->
		<div class="item-content-set01" style="display: inline-block;">
			<div class="item-card row">
				<div class="item-card-main">
					<img src="/yumyum/images/외관사진.png">
				</div>
				<div class="item-card-info">
					<p class="item-title pblack" style="margin-top: 20px;">
						<strong>${shopdto.name}</strong>
					</p>
					<P class="item-exp">${shopdto.explanation}</p>
					<p class="item-score">
						<i class="material-icons item-icon">star_rate</i>
						<c:if test="${empty rvlist}">
							<strong class="pblack">0</strong>
						</c:if>
						<c:forEach	items="${rvlist}" var="dto" begin="0" end="0">
							<c:if test="${not empty dto.avg}">
								<strong class="pblack">${dto.avg}</strong>
							</c:if>
						</c:forEach>								
					</p>
					<p class="minimum-pay">
						<span class="pblack"> 최소주문금액</span>
						&nbsp;&nbsp;${shopdto.min_price}원
					</p>
				</div>
			</div>

<!-- 가게메뉴탭 -->
			<div id="main">
				<input type="radio" id="menu" name="rb" checked><label for="menu">메뉴</label> 
				<input type="radio" id="review" name="rb"><label for="review">리뷰</label> 
				<input type="radio" id="notice" name="rb"><label for="notice">리뷰이벤트공지</label> 
				<input type="radio" id="shopinfo" name="rb"><label for="shopinfo">가게정보</label>
				<div style="clear: both;"></div>

<!-- 음식메뉴목록 -->

				<div id="menuinfo">
					<div class="item-content">
						<c:forEach items="${mglist}" var="dto">
							<ul class="group">
								<li class="menugroup"><a>${dto.name}</a>
									<ul class="hide">
										<c:forEach items="${mlist}" var="dto2">
											<c:if test="${dto.seq == dto2.menu_group_seq}">
												<li class="menu" data-shopseq="${shopdto.seq}" data-menuseq="${dto2.seq}"><a class="list-group-item" id="menuoption"> ${dto2.name}&nbsp;&nbsp; 
													<span class="pset01">${dto2.price}원</span>
														<span class="food-image-set">
														<img src="/yumyum/images/menu/${dto2.picture}" width="80">
														</span>
												</a></li>
											</c:if>
										</c:forEach>
									</ul>
								</li>
							</ul>
						</c:forEach>
					</div>
				</div>

<!-- orderModal -->
				<div class="modal fade" id="orderModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
							</div>
							<form method="post" action="/yumyum/cart_add.do" name="cart" onsubmit="return check()">
								<div class="modal-body">
									<div class="list01">
										<div class="item-list-section item-list-section01">
											<div id="modal-menuinfo"></div>
											<div class="menu_quantity">수량: <input type="number" name="quantity" min="1" value="1"> 개</div>
										</div>
										<div class="item-list-section item-list-section02">
											<table id="test" class="table">
												<tbody></tbody>
											</table>
										</div>
									</div>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
									<button type="submit" class="btn btn-outline-success">장바구니담기</button>
								</div>
								<input type="hidden" name="shop_seq" value="${shopdto.seq}">
								<input type="hidden" name="menu_seq" id="menu_seq">
								<input type="hidden" name="users_seq" value="${seq}">
								<input type="hidden" name="menu_option_seq" id="menu_option_seq">
							</form>
						</div>
					</div>
				</div>
<!-- orderModal End -->

<!-- 소비자리뷰탭 -->
				<div id="reviewinfo">
					<!-- <div class="review-set review01 row"> -->
					<div class="review-info-grid">
						<c:forEach items="${rvlist}" var="dto">
							<h4>

								<!-- 추천/비추천 -->
								<strong>${dto.nickname}&nbsp;</strong>
								<%-- <i class="glyphicon glyphicon-thumbs-up" id="recomm">${dto.recommend}&nbsp;</i>
								<i class="glyphicon glyphicon-thumbs-down" id="nonrecomm">${dto.nonrecommend}</i> --%>

								<i class="glyphicon glyphicon-thumbs-up" id="recomm"
									onclick="count('plus')" value="+"></i> <span id="result">${dto.recommend}</span>&nbsp;&nbsp;
								<i class="glyphicon glyphicon-thumbs-down" id="nonrecomm"
									onclick="count2('minus')" value="-"></i> <span id="result2">${dto.nonrecommend}</span>


							</h4>
							<p class="review-stars">
								<img src="${dto.picture}" width="90">
							</p>
							<p class="review-text">${dto.content}</p>
							<c:forEach items="${omlist}" var="dto1">
								<c:if test="${dto1.review_seq == dto.seq}">
									<div class="ordermenu">주문메뉴:${dto1.menu_name}</div>
									<p class="ordermenu">추가옵션:${dto1.option_name}</p>
								</c:if>
							</c:forEach>
							<span class="td2">${dto.regdate}</span>
							<c:forEach items="${rvclist}" var="dto2">
								<c:if test="${dto.seq == dto2.review_seq}">
									<div class="review-comment">
										<i class="material-icons">sentiment_very_satisfied</i><span>사장님답변</span>
										<p>${dto2.content}</p>
										<p class="td2" style="font-size: 10px;">${dto2.regdate}</p>
									</div>
									<hr>
								</c:if>
							</c:forEach>
						</c:forEach>
					</div>
					<!-- 	</div> -->
				</div>

<!-- 리뷰이벤트공지탭 -->
				<div id="noticeinfo">
					<i class="material-icons">campaign</i> 사장님 리뷰이벤트 공지
					<hr>
					<c:if test="${not empty rvndto.content}">
						<h3>
							<i class="material-icons">card_giftcard</i> ${rvndto.content} <i
								class="material-icons">card_giftcard</i>
						</h3>
						<h5 style="text-align: right;">기간 : ${rvndto.start_date} ~
							${rvndto.end_date}</h5>
						<br>
						<fieldset>
							<legend>서비스 품목</legend>
							<ol>
								<c:forEach items="${rvmlist}" var="dto">
									<li style="font-size: 17px;">${dto.name}</li>
								</c:forEach>
							</ol>
						</fieldset>
					</c:if>
					<c:if test="${empty rvndto.content}">
					진행중인 이벤트가 없습니다.
					</c:if>
				</div>

<!-- 가게정보탭 -->
				<div id="shopinfoinfo">
					<i class="material-icons">campaign</i> 가게 상세 정보
					<hr>
					<div class="item-content">
						<h3>
							<strong>배달팁 안내</strong>
						</h3>
						<p>기본배달팁 : ${shopdto.tip}원</p>
						<c:if test="${pplist.size() != 0 }">
							<p>*추가배달팁 지역 => 추가배달팁*</p>
							<c:forEach items="${pplist}" var="dto">
								<p>${dto.dong}=>${dto.price}원</p>
							</c:forEach>
						</c:if>
						<c:if test="${pplist.size() == 0 }">
							<p>저희 가게는 전지역 추가배달팁 없습니다.</p>
						</c:if>
						<h3>
							<strong>사업자정보</strong>
						</h3>
						<p>대표자명 : ${userdto.name}</p>
						<p>상호명 : ${shopdto.name}</p>
						<p>사업자주소 : ${shopdto.address}</p>
						<p>사업자 등록번호 : ${shopdto.registration}</p>
					</div>
				</div>
<!-- 가게끝 -->
			</div>
			<!-- <div id="main"> -->
		</div>
		<!-- <div class="item-content-set01"> -->

	</section>

	<%@ include file="/inc/copyright.html"%>

	<script>
	
		function count(type) {
			// 결과를 표시할 element
			const resultElement = document.getElementById('result');

			// 현재 화면에 표시된 값
			let number = resultElement.innerText;

			// 더하기/빼기
			if (type === 'plus') {
				number = parseInt(number) + 1;
			}

			// 결과 출력
			resultElement.innerText = number;
		}

		function count2(type) {
			// 결과를 표시할 element
			const resultElement = document.getElementById('result2');

			// 현재 화면에 표시된 값
			let number = resultElement.innerText;

			// 더하기/빼기
			if (type === 'minus') {
				number = parseInt(number) + 1;
			}

			// 결과 출력
			resultElement.innerText = number;
		}
		
		$('#address').val('${address}');
		
		// 메뉴 클릭시 모달에 메뉴의 상세정보, 추가옵션을 출력하기 위한 함수
		$(document).on("click", ".menu", function () {
			
			var shopSeq = $(this).data('shopseq');
			var menuSeq = $(this).data('menuseq');
			
			$('#menu_seq').val(menuSeq);
		
			$.ajax({
			    url: '${contextPath}/yumyum/shop_menu.do',
			    method: 'POST',
			    dataType: 'json',
			    data: { seq: shopSeq, menuseq: menuSeq },
			    success : function (result) {
			    	
			    	$("#modal-menuinfo").html("");
			    	
			    	$(result).each(function(index, item) {	    		
						$("#modal-menuinfo").append("<div class='menu_picture'><img src='/yumyum/images/menu/" + item.picture + "'></div><div class='menu_name'>" + item.name + item.price + "원</div><div class='menu_explanation'>" + item.explanation + "</div>");
			    	});
			    	
			    },
			    error: function (request, error) {
			        alert("fail");
			    }
			});
			
			$.ajax({
			    url: '${contextPath}/yumyum/shop_menuoption.do',
			    method: 'POST',
			    dataType: 'json',
			    data: { seq: shopSeq, menuseq: menuSeq },
			    success : function (result) {
			    	
			    	$("#test tbody").html("");
			    	
			    	$(result).each(function(index, item) {	    		
						$("#test tbody").append("<tr><td><label><input type='radio' name='chk_info' value='" + item.seq + "'>" + item.name + "</label></td><td>" + item.price + "원</td></tr>");
			    	});
			    	
			    	$('#orderModal').modal('show');
			    },
			    error: function (request, error) {
			        alert("fail");
			    }
			});
			
		});
		
		// 장바구니 담기 전 로그인 유무 체크
		function check() {

			var menu_option_seq = $("input[name='chk_info']:checked").val();
			$('#menu_option_seq').val(menu_option_seq);
			
			if (${empty seq}) {
				// $('#cartModal').modal('show');
				alert("로그인 이후에 이용해주세요.");
				location.href='/yumyum/auth/login.do';
				return false;
			}
		}

	</script>
</body>
</html>
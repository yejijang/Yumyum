<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>yumyum</title>

<%@ include file="/inc/asset.jsp"%>

<style>
.container {
	width: 800px;
	padding: 0px;
}

.nav-size {
	width: 150px;
}

.content-size {
	width: 650px;
	padding-right: 0px;
}

table {
	width: 100%;
}

#content-header {
	height: 150px;
	width: 635px;
	background-color: #F1F1F1;
	font-size: 25px;
	margin: auto;
	text-align: center;
	display: table-cell;
    vertical-align: middle;
}

#content-header-title span {
	font-size: 18px;
	margin-left: 10px;
	color: #FF6505;
}

#content-header-order span {
	font-size: 14px;
	margin: 10px;
}

#content-ordermenu table th {
	width: 50%;
}

#content-ordermenu {
	text-align: center;
	margin-top: 30px;
}

#content-orderinfo,
#content-paymentinfo {
	width: 48%;
}

#content-info {
	display: flex;
	justify-content: space-between;
}

#content-info i {
	margin-right: 10px;
}

#content-info table {
	height: 300px;
}

#content-info table th {
	width: 40%;
}

#content-info table tr:nth-child(1),
#content-info table tr:nth-child(3),
#content-info table tr:nth-child(4) {
	height: 80px;
}

#content-info table td {
	text-align: right;
}

.btns {
	text-align: center;
}

</style>
</head>
<body>

	<%@ include file="/inc/header.jsp"%>

	<section class="main-section">

		<h1>주문내역<small>(주문상세)</small></h1>

		<div class="container">
			<!-- tabs left -->
			<div class="tabbable">
				<ul class="nav nav-pills nav-stacked col-md-3 nav-size">
					<li><a href="/yumyum/customer/mypage/mypage.do">My
							Page</a></li>
					<li class="active"><a href="/yumyum/customer/orderlist/cus_orderlist.do">주문내역</a></li>
					<li><a href="/yumyum/customer/customerservice/cs_question.do?cs_subject_seq=6">고객센터</a></li>
					<li><a href="/yumyum/customer/bookmark.do">즐겨찾는가게</a></li>
				</ul>
				<div class="tab-content col-md-9 content-size">
					<c:forEach items="${infoList}" var="dto">
						<div id="content-header">
							<div id="content-header-title">
								${dto.name}
								<span>
									<c:choose>
										<%-- 주문상태(주문대기: R, 접수완료: A , 주문취소: X, 배달완료: O) -> (DEFAULT 주문대기: R) --%>
										<c:when test="${dto.status eq 'R'}">[ 주문대기 ]</c:when>
										<c:when test="${dto.status eq 'A'}">[ 접수완료 ]</c:when>
										<c:when test="${dto.status eq 'X'}">
											[ 주문취소 ] - ${dto.cancel_reason}
										</c:when>
										<c:when test="${dto.status eq 'O'}">[ 배달완료 ]</c:when>
									</c:choose>
								</span>
							</div>
							<div id="content-header-order">
								<span>주문일시: ${dto.regdate}</span>
								<span>주문번호: ${dto.order_num}</span>
							</div>
						</div>
						<div id="content-ordermenu">
							<h3>주문 메뉴</h3>
							<table class="table table-bordered">
								<tr>
									<th>메뉴</th>
									<th>추가옵션</th>
								</tr>
								<c:forEach items="${menuList}" var="dto2">
									<c:if test="${dto2.orderlist_seq == dto.seq}">
										<tr>
											<td>${dto2.menu_name}</td>
											<td>
												<c:if test="${empty dto2.option_name}">-</c:if>
												${dto2.option_name}
											</td>
										</tr>
									</c:if>
								</c:forEach>
							</table>
						</div>
						<div id="content-info">
							<div id="content-orderinfo">
								<h3><i class="glyphicon glyphicon-hand-right"></i>주문 정보</h3>
								<table class="table table-bordered">
									<tr>
										<th>배달 주소</th>
										<td>${dto.delivery_address}</td>
									</tr>
									<tr>
										<th>전화 번호</th>
										<td>${dto.user_phone}</td>
									</tr>
									<tr>
										<th>요청사항(가게)</th>
										<td>
											<c:if test="${empty dto.request_owner}">-</c:if>
											${dto.request_owner}
										</td>
									</tr>
									<tr>
										<th>요청사항(라이더)</th>
										<td>
											<c:if test="${empty dto.request_rider}">-</c:if>
											${dto.request_rider}
										</td>
									</tr>
								</table>
							</div>
							<div id="content-paymentinfo">
								<h3><i class="glyphicon glyphicon-hand-right"></i>결제 정보</h3>
								<table class="table table-bordered">
									<tr>
										<th>총 주문 금액</th>
										<td>${dto.order_price}원</td>
									</tr>
									<tr>
										<th>배달팁</th>
										<td>${dto.shop_tip}원</td>
									</tr>
									<tr>
										<th>총 결제 금액</th>
										<td>${dto.charge_price}원</td>
									</tr>
									<tr>
										<th>결제방법</th>
										<td>
											<c:choose>
												<%-- 결제방법(만나서 결제(현금):A, 만나서 결제(카드):B, 비대면 결제(카드):C) --%>
												<c:when test="${dto.charge_method eq 'A'}">만나서 결제(현금)</c:when>
												<c:when test="${dto.charge_method eq 'B'}">만나서 결제(카드)</c:when>
												<c:when test="${dto.charge_method eq 'C'}">비대면 결제(카드)</c:when>
											</c:choose>
										</td>
									</tr>
								</table>
							</div>
						</div>
					</c:forEach>
					<div class="btns">
						<button type="button" class="btn btn-default" onclick="location.href='/yumyum/customer/orderlist/cus_orderlist.do';">목록으로</button>
					</div>
				</div>
			</div>
			<!-- /tabs -->
		</div>
	</section>

	<%@ include file="/inc/copyright.html"%>

	<script>
		
	</script>

</body>
</html>
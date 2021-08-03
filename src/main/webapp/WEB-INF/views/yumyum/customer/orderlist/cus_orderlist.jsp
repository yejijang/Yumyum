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
	width: 635px;
	background-color: RGBA(247,239,184, 0.3);
}

table .tr1 {
	height: 30px;
}

table .tr2 {
	height: 60px;
}

table .tr3 {
	border-bottom: 1px solid #BBBBBB;
	height: 50px;
}

table .tr1 td {
	padding-top: 15px;
}

table .tr3 td {
	padding-bottom: 30px;
}

table .tr1 td:nth-child(1) {
	width: 120px;	
}

table .tr1 td:nth-child(3) {
	width: 80px;
}

table .tr1 td:nth-child(4) {
	width: 30px;
}

table .tr1 td:nth-child(1),
table .tr3 td:nth-child(1) {
	font-size: 12px;
}

table .tr1 td:nth-child(2),
table .tr2 td:nth-child(2),
table .tr3 td:nth-child(1) {
	text-align: left;
}

table .tr2 td:nth-child(2) {
	font-size: 18px;
	padding-top: 20px;
	cursor: pointer;
}

table td i {
	display: table-cell;
	vertical-align: middle;
	cursor: pointer;
}

table td:nth-child(1) {
	text-align: center;
}

table td img {
	width: 120px;
	object-fit: contain;
}

.modal-dialog {
	width: 400px;
}

.modal-body {
	height: 100px;
	width: 400px;
	text-align: center;
	display: table-cell;
	vertical-align: middle;
}

.modal-footer {
	text-align: center;
}

.btn-delete {
	background-color: #FF6505;
	color: white;
}

.btn-delete:hover {
	background-color: #FF0000;
	color: white;
}

.pagebar {
	text-align: center;
}

</style>
</head>
<body>

	<%@ include file="/inc/header.jsp"%>

	<section class="main-section">

		<h1>주문내역</h1>

		<div class="container">
			<!-- tabs left -->
			<div class="tabbable">
				<ul class="nav nav-pills nav-stacked col-md-3 nav-size">
					<li><a href="/yumyum/customer/mypage/mypage.do">My
							Page</a></li>
					<li class="active"><a href="/yumyum/customer/orderlist/cus_orderlist.do">주문내역</a></li>
					<li><a href="/yumyum/customer/customerservice/cs_question.do">고객센터</a></li>
					<li><a href="/yumyum/customer/bookmark.do">즐겨찾는가게</a></li>
				</ul>
				<div class="tab-content col-md-9 content-size">
					<table>
						<c:forEach items="${orderList}" var="dto">
							<tr class="tr1">
								<td>
									<fmt:parseDate var="parseRegDate" value="${dto.regdate}" pattern="yyyy-MM-dd"/>
									<fmt:formatDate var="resultRegDt" value="${parseRegDate}" pattern="yyyy/MM/dd"/>
									${resultRegDt}
								</td>
								<td>
									<c:choose>
										<%-- 주문상태(주문대기: R, 접수완료: A , 주문취소: X, 배달완료: O) -> (DEFAULT 주문대기: R) --%>
										<c:when test="${dto.status eq 'R'}">주문대기</c:when>
										<c:when test="${dto.status eq 'A'}">접수완료</c:when>
										<c:when test="${dto.status eq 'X'}">주문취소</c:when>
										<c:when test="${dto.status eq 'O'}">배달완료</c:when>
									</c:choose>
								</td>
								<td>
									<button type="button" onclick="location.href='/yumyum/customer/orderlist/cus_orderlist_detail.do?seq=${dto.seq}';">주문상세</button>
								</td>
								<td>
									<i class="glyphicon glyphicon-trash open-DelOrderlistModal"
										data-toggle="modal" data-target="#delOrderlistModal" data-id="${dto.seq}"></i>
								</td>
							</tr>
							<tr class="tr2">
								<td rowspan="2">
									<img src="/yumyum/images/logo.png">
								</td>
								<td colspan="3" onclick="location.href='/yumyum/shop.do?seq=${dto.shop_seq}';">
									${dto.shop_name}<i class="glyphicon glyphicon-menu-right"></i>
								</td>
							</tr>
							<tr class="tr3">
								<td colspan="3">
									<c:set var="loop_flag" value="false" />
									<c:set var= "total" value="0"/>
									<c:forEach items="${orderMenuList}" var="dto2">
										<c:if test="${dto.seq == dto2.orderlist_seq}">
											<c:set var= "total" value="${total + dto2.cnt}"/>
										</c:if>
									</c:forEach>
									<c:forEach items="${orderMenuList}" var="dto2">
										<c:if test="${dto.seq == dto2.orderlist_seq && not loop_flag}">
											${dto2.menu_name}
											<c:if test="${total > 1}">
												외 ${total - 1}개
											</c:if>
											<c:if test="${total == 1}">
												1개
											</c:if>
											<c:set var="loop_flag" value="true" />
										</c:if>
									</c:forEach>
									${dto.charge_price}원
								</td>
							</tr>
						</c:forEach>
					</table>
					
					<div class="pagebar">${pagebar}</div>
					
					<!-- Modal -->
					<div class="modal fade" id="delOrderlistModal" tabindex="-1" role="dialog"
						aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-body">
									<div id="orderlistSeq"></div>
									해당 주문 내역을 삭제하시겠습니까?
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
									<button type="button" class="btn btn-delete" onclick="delOrderlist()">Ok</button>
								</div>
							</div>
						</div>
					</div>
					<!-- Modal End -->
				</div>
			</div>
			<!-- /tabs -->
		</div>

	</section>

	<%@ include file="/inc/copyright.html"%>

	<script>
		
		$(document).on("click", ".open-DelOrderlistModal", function () {
			var orderlistSeq = $(this).data('id');
			$(".modal-body #orderlistSeq").val( orderlistSeq );
		});
		
		function delOrderlist() {
			var seq = $(".modal-body #orderlistSeq").val();
			location.href='/yumyum/customer/orderlist/cus_orderlist_del.do?seq=' + seq;
		}
	
	</script>

</body>
</html>
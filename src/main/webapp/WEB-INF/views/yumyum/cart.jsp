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
.shop {
	margin-top: 10px;
	padding: 10px 0px;
}

.shop:hover {
	background-color: #FAFBA4;
	cursor: pointer;
}

.shop-name {
	width: 700px;
	margin: 0 auto;
	margin-bottom: 10px;
	font-size: 20px;
}

.shop-name i {
	margin-right: 10px;
}

.table {
	width: 700px;
	margin: 0 auto;
}

.table tr td:nth-child(1),
.table tr td:nth-child(3) {
	width: 250px;
}

.table tr td:nth-child(4) {
	width: 50px;
}

.table tr td:nth-child(5) {
	width: 30px;
}

.modal {
    text-align: center;
}

@media screen and (min-width: 768px) { 
	.modal:before {
	    display: inline-block;
	    vertical-align: middle;
	    content: " ";
	    height: 100%;
	}
}

.modal-dialog {
	width: 400px;
	display: inline-block;
    text-align: left;
    vertical-align: middle;
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

.table tr:last-child td:first-child {
	border-right: 1px solid #DDDDDD;
}
</style>
</head>
<body>

	<%@ include file="/inc/header.jsp"%>

	<section class="main-section">

		<h1>장바구니</h1>

		<div>
			<c:forEach items="${shoplist}" var="shop">
				<div class="shop">
					<div class="shop-name"><i class="glyphicon glyphicon-home"></i>${shop.name}</div>

					<table class="table">
						<tr>
							<th>메뉴</th>
							<th>가격</th>
							<th>메뉴추가옵션</th>
							<th>수량</th>
							<th></th>
						</tr>
						<c:set var="price_sum" value="0"/>
						<c:forEach items="${menulist}" var="menu">
							<c:if test="${shop.seq == menu.shop_seq}">
								<c:set var="price_sum" value="${price_sum + menu.menu_price * menu.cnt}"/>
								<c:set var="price_sum" value="${price_sum + menu.menu_option_price * menu.cnt}"/>
								<tr>
									<td>${menu.menu_name}</td>
									<td><fmt:formatNumber value="${menu.menu_price}" pattern="###,###"/>원</td>
									<td>
										<c:if test="${empty menu.menu_option_name}">-</c:if>
										<c:if test="${not empty menu.menu_option_name}">${menu.menu_option_name} (<fmt:formatNumber value="${menu.menu_option_price}" pattern="###,###"/>원)</c:if>
									</td>
									<td>${menu.cnt}</td>
									<td>
										<i class="glyphicon glyphicon-remove open-DelMenuModal" data-toggle="modal" data-target="#delMenuModal" data-id="${menu.seq}"></i>
									</td>
								</tr>
							</c:if>
						</c:forEach>
						<tr>
							<td colspan="2">총액</td>
							<td colspan="3"><fmt:formatNumber value="${price_sum}" pattern="###,###"/>원</td>
						</tr>
					</table>
				</div>
			</c:forEach>
		</div>
		<!-- Modal -->
		<div class="modal fade" id="delMenuModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-body">
						<div id="menuSeq"></div>
						해당 장바구니 내역을 삭제하시겠습니까?
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
						<button type="button" class="btn btn-delete" onclick="delMenu()">Ok</button>
					</div>
				</div>
			</div>
		</div>
		<!-- Modal End -->

	</section>

	<%@ include file="/inc/copyright.html"%>

	<script>
		$(document).on("click", ".open-DelMenuModal", function () {
			var menuSeq = $(this).data('id');
			$(".modal-body #menuSeq").val( menuSeq );
		});
		
		function delMenu() {
			var seq = $(".modal-body #menuSeq").val();
			location.href='/yumyum/cart_del.do?seq=' + seq;
		}
	</script>

</body>
</html>
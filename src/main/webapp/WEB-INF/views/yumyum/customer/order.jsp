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
form {
	width: 450px;
	margin: 0 auto;
}

form .title {
	margin-left: 15px;
	font-size: 18px;
}

form input.form-control {
	margin: 0 auto;
	margin-bottom: 10px;
	width: 350px;
}

.btns {
	text-align: center;
	margin-top: 20px;
}

.check {
	display: flex;
	justify-content: flex-start;
}

.check::after {
	content: '';
	display: block;
	clear: both;
}

.btn-check {
	margin-left: 5px;
	height: 34px;
	background-color: #FD9F32;
	color: white;
}

.btn-check:hover {
	background-color: #FE8C06;
	color: white;
}

form .btns .btn-order {
	background-color: #9DAB86;
	color: white;
}

form .btns .btn-order:hover {
	background-color: #62760C;
}

form .btns .btn {
	width: 180px;
}

#sale .form-control {
	height: 34px;
    margin: 0 auto;
	margin-bottom: 10px;
	width: 270px;
}

#sale {
	position: static;
}

#sale .form-control {
	position: relative;
	top: -30px;
	left: 33px;
}

fieldset {
	margin-bottom: 10px;
	border-bottom: 1px solid #e5e5e5;
}

fieldset legend {
	border: 0px;
}

#pointprice,
#couponprice {
	font-size: 12px;
	color: #FF6505;
	width: 250px;
	position: relative;
	top: -35px;
	left: 220px;
}

#pointprice {
	left: 200px;
}
</style>
</head>
<body>

	<%@ include file="/inc/header.jsp"%>

	<section class="main-section">

		<h1>주문</h1>

		<form method="post" action="/yumyum/auth/signupok.do" name="order"
			onsubmit="return check()">
			
			<fieldset>
				<legend>주문정보</legend>
				<input type="text" name="address" id="address" class="form-control" required readonly placeholder="클릭하여 주소를 검색 해주세요." onchange="inputAddressChk()">
					
				<div class="title">상세주소 :</div>
				<input type="text" name="detaddress" id="detaddress" class="form-control"
					autocomplete="off" required>
	
				<div class="title">전화번호 :</div>
				<div class="check">
					<input type="text" name="phone" id="phone" class="form-control" value="${user.phone}" disabled>
				</div>
			</fieldset>

			<fieldset>
				<legend>요청사항</legend>
				<div class="title">가게 사장님께 :</div>
				<input type="text" name="request_owner" id="request_owner" class="form-control" autocomplete="off">
					
				<div class="title">라이더님께 :</div>
				<input type="text" name="request_rider" id="request_rider" class="form-control" autocomplete="off">
			</fieldset>
			
			<fieldset id="sale">
				<legend>할인&#38;이벤트</legend>
				<c:set var="price_sum" value="0"/>
				<div class="title">쿠폰 :
					<select name="coupon" id="coupon" class="form-control">
					    <option value="0" data-price="0">선택안함</option>
					    <c:forEach items="${couponList}" var="coupon">
					    	<option value="${coupon.seq}" data-price="${coupon.price}">${coupon.name}</option>
					    </c:forEach>
					</select>
					<div id="couponprice"></div>
				</div>
				
				<div class="title">포인트 :
					<input type="number" name="usePoint" id="usePoint" class="form-control" value="0" autocomplete="off" onchange="inputPointChk()">
					<div id="pointprice">보유 포인트: ${user.point}원</div>
				</div>
				
				<c:if test="${revnotice == 1}">
					<div class="title">리뷰이벤트 :
						<select name="reviemenu" class="form-control">
							<option value="0" data-price="0">선택안함</option>
						    <c:forEach items="${revmenulist}" var="menu">
						    	<option value="${menu.seq}">${menu.name}</option>
						    </c:forEach>
					   	</select>
					</div>
				</c:if>
				
			</fieldset>

			<fieldset>
				<legend>결제금액</legend>
				
				<c:set var="price_sum" value="0"/>
				<c:forEach items="${menulist}" var="menu">
					<c:if test="${seq == menu.shop_seq}">
						<c:set var="price_sum" value="${price_sum + menu.menu_price * menu.cnt}"/>
						<c:set var="price_sum" value="${price_sum + menu.menu_option_price * menu.cnt}"/>
					</c:if>
				</c:forEach>
				
				<div class="title">주문금액 :</div>
				<input type="tel" name="orderprice" id="orderprice" class="form-control" value="<fmt:formatNumber value="${price_sum}" pattern="###,###"/>" readonly>

				<div class="title">배달팁 :</div>
				<input type="tel" name="tip" id="tip" class="form-control" value="<fmt:formatNumber value="${shopTip}" pattern="###,###"/>" readonly>
				
				<div class="title">할인 :</div>
				<input type="tel" name="discount" id="discount" class="form-control" value="<fmt:formatNumber value="0" pattern="###,###"/>" readonly>
				
				<c:set var="total_price" value="0"/>
				<c:set var="total_price" value="${price_sum + shopTip}"/>
				<div class="title">총 결제금액 :</div>
				<input type="tel" name="chargeprice" id="chargeprice" class="form-control" value="<fmt:formatNumber value="${total_price}" pattern="###,###"/>" readonly>
			</fieldset>
			
			<div class="btns">
				<button type="button" class="btn btn-default"
					onclick="location.href='/yumyum/cart.do';">목록으로</button>
				<button type="submit" class="btn btn-order">주문하기</button>
			</div>

			<input type="hidden" name="auth" value="${auth}">

		</form>

	</section>

	<%@ include file="/inc/copyright.html"%>
	
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
		
		var f = document.order;
	
		function inputPointChk() {
			var discount = parseInt(document.getElementById('discount').value);
			var point = parseInt(document.getElementById('usePoint').value);
			var priceValue = $("#coupon").find("option:selected").data("price");
			
			discount = 0;
			
			discount += priceValue;
			discount += point;
			
			const cn1 = discount.toLocaleString('ko-KR');
			var num2 = discount - point;
			const cn2 = num2.toLocaleString('ko-KR');
			
			var total = ${price_sum + shopTip} - discount;
			const cn3 = total.toLocaleString('ko-KR');
			var total2 = ${price_sum + shopTip} - priceValue;
			const cn4 = total2.toLocaleString('ko-KR');
			
			if (${user.point} < point) {
				alert('보유 포인트를 초과하였습니다.');
				document.getElementById('discount').value = cn2;
				document.getElementById('usePoint').value = 0;
				document.getElementById('chargeprice').value = cn4;
			} else {
				document.getElementById('discount').value = cn1;
				document.getElementById('chargeprice').value = cn3;
			}
			
			console.log(total);
		}
		
		window.onload = function() {
			document.getElementById("address").addEventListener("click", function(){ //주소 검색 버튼을 클릭하면
				//카카오 지도 발생
				new daum.Postcode({
					oncomplete: function(data) { //선택시 입력값 세팅
						document.getElementById("address").value = data.jibunAddress; // 주소 넣기
						f.detaddress.focus();
					}
				}).open();
			});
			
		}
		
		$(function () {
			$("#coupon").on("change", function () {
				var discount = parseInt(document.getElementById('discount').value);
				var point = parseInt(document.getElementById('usePoint').value);
				var priceValue = $(this).find("option:selected").data("price");
							
				discount = 0;
				
				discount += priceValue;
				discount += point;
				
				const cn1 = discount.toLocaleString('ko-KR');
				var total = ${price_sum + shopTip} - discount;
				const cn3 = total.toLocaleString('ko-KR');
				
				document.getElementById('discount').value = cn1;
				document.getElementById('chargeprice').value = cn3;
				
				$("#couponprice").html(priceValue + "원 사용");
			});
		});

	</script>

</body>
</html>
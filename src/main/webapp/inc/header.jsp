<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
	window.onload = function() {

		var path = location.pathname;
		var menu = document.getElementsByClassName('mainbtn');

		for (var i = 0; i < path.length; i++) {
			if (path == menu[i].id) {
				menu[i].style.color = "#FDD100";
			}
		}
	}
</script>
<header class="main-header">
	<section>
		<span id="main_logo" onclick="location.href='/yumyum/main.do';">
			<img src="/yumyum/images/logo.png" style="cursor: pointer;">
		</span>
		<nav>
			<c:if test="${empty id||auth eq 'C'}">
				<ul>
					<li class="mainbtn"
						onclick="location.href='/yumyum/category_delivery.do';"
						title="delivery" id="/yumyum/category_delivery.do">배달</li>
					<li class="mainbtn"
						onclick="location.href='/yumyum/category_pickup.do';"
						title="pickup" id="/yumyum/category_pickup.do">포장</li>
					<li class="mainbtn"
						onclick="location.href='/yumyum/noticelist.do';"
						title="noticelist" id="/yumyum/noticelist.do">공지사항</li>
					<li class="mainbtn" onclick="location.href='/yumyum/eventlist.do';"
						title="eventlist" id="/yumyum/eventlist.do">이벤트</li>
					<li class="mainbtn" onclick="location.href='/yumyum/premium.do';"
						title="premium" id="/yumyum/premium.do">얌얌혜택</li>

				</ul>
			</c:if>

			<c:if test="${not empty id}">
				<c:if test="${auth eq 'O'}">
					<ul>

						<li class="mainbtn"
							onclick="location.href='/yumyum/owner/shop/shop_add.do';"
							title="shopedit" id="/yumyum/owner/shop/shop_add.do">가게관리</li>
						<li class="mainbtn"
							onclick="location.href='/yumyum/owner/menu/menulist.do';"
							title="menulist" id="/yumyum/owner/menu/menulist.do">메뉴관리</li>
						<li class="mainbtn"
							onclick="location.href='/yumyum/owner/order/own_orderlist.do';"
							title="orderlist" id="/yumyum/owner/order/own_orderlist.do">주문관리</li>
						<li class="mainbtn"
							onclick="location.href='/yumyum/owner/review/reviewevent.do';"
							title="reviewevent" id="/yumyum/owner/review/reviewevent.do">리뷰관리</li>
						<li class="mainbtn"
							onclick="location.href='/yumyum/noticelist.do';"
							title="noticelist" id="/yumyum/noticelist.do">공지사항</li>
					</ul>
				</c:if>

				<c:if test="${auth eq 'A'}">
					<ul>
						<li class="mainbtn"
							onclick="location.href='/yumyum/admin/shop/shopenroll.do';"
							title="shopenroll.do" id="/yumyum/admin/shop/shopenroll.do">가게관리</li>
						<li class="mainbtn"
							onclick="location.href='/yumyum/admin/member/customerlist.do';"
							title="customerlist" id="/yumyum/admin/member/customerlist.do">회원관리</li>
						<li class="mainbtn"
							onclick="location.href='/yumyum/admin/event/eventlist.do';"
							title="eventlist" id="/yumyum/admin/event/eventlist.do">이벤트관리</li>
						<li class="mainbtn"
							onclick="location.href='/yumyum/admin/question/questionlist.do';"
							title="questionlist" id="/yumyum/admin/question/questionlist.do">자주묻는질문관리</li>
						<li class="mainbtn"
							onclick="location.href='/yumyum/admin/notice/noticelist.do';"
							title="noticelist" id="/yumyum/admin/notice/noticelist.do">공지사항관리</li>
					</ul>
				</c:if>
			</c:if>
		</nav>
		<div class="auth">

			<c:if test="${not empty id}">
				<div class="btn-auth"
					onclick="location.href='/yumyum/auth/logout.do';" title="로그아웃">로그아웃</div>
				<c:if test="${auth eq 'C'}">
					<i class="glyphicon glyphicon-shopping-cart" style="cursor: pointer;"
						onclick="location.href='/yumyum/cart.do';"></i>
				</c:if>
				<i class="glyphicon glyphicon-user" style="cursor: pointer;"
					onclick="location.href='/yumyum/customer/mypage/mypage.do';"></i>
			</c:if>
				
			<c:if test="${empty id}">
				<div class="btn-auth"
					onclick="location.href='/yumyum/auth/login.do';" title="로그인">로그인</div>
				<div class="btn-auth"
					onclick="location.href='/yumyum/auth/auth_select.do';" title="회원가입">회원가입</div>
			</c:if>
			
		</div>
	</section>
</header>
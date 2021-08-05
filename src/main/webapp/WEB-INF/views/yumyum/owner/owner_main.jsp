<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>

<%@ include file="/inc/asset.jsp" %>

<style>
#box {
	width: 800px;
	border: 0px solid #333;
	text-align: center;
	margin: 0px auto;
}

#wrap {
	position: relative;
	border: 0px solid red;
	width: 600px; /* wrap에 속한 div의 넓이+margin값을 더한 넓이와 높이값 */
	height: 600px;
	left: 50%;
	transform: translate(-50%); /* 중앙정렬 */
}

#wrap>div {
	width: 150px;
	height: 150px;
	border: 0px solid #333;
	/* border-radius: 1em; */
	float: left;
	margin: 25px 20px;
	box-sizing: border-box; /* border 또한 위치값을 갖기에 div크기가 더 커짐을 방지하기 위한 */
	/* background-color: #FAB700; */
}

#wrap>div>span {
	font-size: 20px;
}

#wrap>div:nth-child(4) {
	clear: both; /* float:left 의 줄바꿈을 위한 */
}

.material-icons {
	font-size: 140px;
	color: #62760C;
}

.material-icons:hover {
	text-shadow: 6px 2px 2px gray;
	
}

</style>
</head>
<body>
	<%@ include file="/inc/header.jsp" %>
	
	<section class="main-section">
		
	</section>
	
	<section class="main_area" id="main">
		<div id="box">
			<div id="wrap">
				<div>
					<a href="/yumyum/owner/shop/shop_add.do">
						<i class="material-icons">store</i>
					</a>
					<span>가게 입점 관리</span>
				</div>
				<div>
					<a href="/yumyum/owner/shop/shop_edit.do">
						<i class="material-icons">settings</i>
					</a>
					<span>가게 정보 관리</span>
				</div>
				<div>
					<a href="/yumyum/owner/menu/menulist.do">
						<i class="material-icons">inventory</i>
					</a>
					<span>메뉴 관리</span>
				</div>
				<div>
					<a href="/yumyum/owner/order/own_orderlist.do">
						<i class="material-icons">ring_volume</i>
					</a>
					<span>주문 관리</span>
				</div>
				<div>
					<a href="/yumyum/owner/review/reviewevent.do">
						<i class="material-icons">rate_review</i>
					</a>
					<span>리뷰이벤트 관리</span>
				</div>
				<div>
					<a href="/yumyum/owner/review/review_comment.do">
						<i class="material-icons">thumb_up</i>
					</a>
					<span>리뷰 답변 관리</span>
				</div>
				<div>
					<a href="/yumyum/owner/shop/delivery_tip.do">
						<i class="material-icons">control_point_duplicate</i>
					</a>
					<span>추가배달팁 관리</span>
				</div>
				<div>
					<a href="/yumyum/owner/shop/sales.do">
						<i class="material-icons">monetization_on</i>
					</a>
					<span>매출조회</span>
				</div>
				<div>
					<a href="/yumyum/noticelist.do">
						<i class="material-icons">error_outline</i>
					</a>
					<span>공지사항</span>
				</div>
			</div>
		
		</div>

	</section>
	
	
	<%@ include file="/inc/copyright.html" %>
	
	<script>
		
	</script>

</body>
</html>
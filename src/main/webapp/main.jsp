<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>yumyum</title>

<%@ include file="/inc/asset.jsp" %>

<style>

#box {
    width:800px;
    height:640px;
    /* border:1px solid #333; */
    text-align: center;
    margin: 0px auto;
}

#wrap {
    position:relative;
    width:620px; /* wrap에 속한 div의 넓이+margin값을 더한 넓이와 높이값 */
    height:500px;
    top:50%;
    left:50%;
    transform: translate(-50%, -60%); /* 중앙정렬 */
}

#wrap > div {
    width:250px;
    height:220px;
    border:0px solid #333;
    /* border-radius: 1em; */
    float:left;
    margin: 25px 30px;
    box-sizing:border-box; /* border 또한 위치값을 갖기에 div크기가 더 커짐을 방지하기 위한 */
    /* background-color: #FAB700; */
}

#wrap > div img {
	width: 100%;
	height: 100%;
	margin-bottom: 10px;
}

#wrap > div > span {
	font-size: 20px;
}

#wrap > div:nth-child(3) {
    clear:both; /* float:left 의 줄바꿈을 위한 */
}

#bottom_menu {
	/* border:1px solid #333; */
	width: 300px;
	height: 100px;
	margin: 40px auto;
}

button {
	width: 300px;
	height: 50px;
	margin-top: 60px;
	background-color: #FAB700;
	border-radius: 1em;
	border: none;
	font-size: 20px;
	color: white;
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
					<a href="/yumyum/category_delivery.do">
						<img src="/yumyum/images/main/main_delivery.jpg">
					</a>
					<span>배달</span>
				</div>
				<div>
					<a href="/yumyum/category_pickup.do">
						<img src="/yumyum/images/main/main_pickup.png">
					</a>
					<span>포장</span>
				</div>
				<div>
					<a href="#!">
						<img src="/yumyum/images/main/main_random.png">
					</a>
					<span>랜덤메뉴</span>
				</div>
				<div>
					<a href="/yumyum/eventlist.do">
						<img src="/yumyum/images/main/main_event.png">
					</a>
					<span>이벤트</span>
				</div>
			</div>
			
			<button id="btn_yum" onclick="location.href='/yumyum/premium.do'">얌얌 혜택</button>
		</div>

	</section>


	<%@ include file="/inc/copyright.html" %>
	
	<script>
		
	</script>
</body>
</html>

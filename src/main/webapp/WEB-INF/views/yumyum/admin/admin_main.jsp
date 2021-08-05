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
	width: 1000px;
	border: 0px solid #333;
	text-align: center;
	margin: 0px auto;
} 

#wrap {
	position: relative;
	border: 0px solid red;
	width: 970px; /* wrap에 속한 div의 넓이+margin값을 더한 넓이와 높이값 */
	height: 300px;
	left: 50%;
	transform: translate(-50%,50px); /* 중앙정렬 */
}

#wrap>div {
	width: 150px;
	height: 150px;
	border: 0px solid #333;
	border-radius: 1em;
	float: left;
	margin: 25px 20px;
	box-sizing: border-box; /* border 또한 위치값을 갖기에 div크기가 더 커짐을 방지하기 위한 */
	/* background-color: #FAB700; */
}

#wrap>div>span {

	font-size: 20px;
}

.material-icons {
	font-size: 140px;
	color:#0E3D5B;
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
	
	
		<div id="box">
			<div id="wrap">
				<div>
					<a href="/yumyum/admin/shop/shoplist.do">
						<i class="material-icons">storefront</i>
					</a>
					<span>가게 관리</span>
				</div>
				<div>
					<a href="/yumyum/admin/member/customerlist.do">
						<i class="material-icons">group</i>
					</a>
					<span>회원관리</span>
				</div>
				<div>
					<a href="/yumyum/admin/event/eventlist.do">
						<i class="material-icons">celebration</i>
					</a>
					<span>이벤트 관리</span>
				</div>
				<div>
					<a href="/yumyum/admin/question/questionlist.do">
						<i class="material-icons">live_help</i>
					</a>
					<span>자주묻는 질문관리</span>
				</div>
				<div>
					<a href="/yumyum/admin/notice/noticelist.do">
						<i class="material-icons">support_agent</i>
					</a>
					<span>공지사항 관리</span>
				</div>
			</div>
		</div>
	
	
	<%@ include file="/inc/copyright.html" %>
	
	<script>
		
	</script>

</body>
</html>
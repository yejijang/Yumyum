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
.main-section #sel {
	display: block;
	width: 320px;
	height: 60px;
	text-align: center;
	vertical-align: middle;
	margin: 0 auto;
	border: 1px solid #333;
	padding-top:17px;
	margin-bottom: 60px;
	font-size: 20px;
}

#box {
    width:800px;
    height:540px;
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
    height:350px;
    border:1px solid #333;
    float:left;
    margin: 30px 30px;
    font-size: 20px;
}

#wrap > div i {
	width: 100%;
	height: 85%;
	font-size: 200px;
}


</style>
</head>
<body>
	<%@ include file="/inc/header.jsp" %>

	<section class="main-section">
		
		<div id="sel">회원가입</div>

		<section class="main_area" id="main">
			<div id="box">
				<div id="wrap">
					<div>
						<a href="/yumyum/auth/signup.do?auth=C">
							<i class="material-icons">person</i>
							<span>소비자로 회원가입</span>
						</a> 
						
					</div>
					<div>
						<a href="/yumyum/auth/signup.do?auth=O"> 
							<i class="material-icons">perm_identity</i>
							<span>점주로 회원가입</span>
						</a> 
					</div>
				</div>

			</div>

		</section>


	</section>
	
	
	<%@ include file="/inc/copyright.html" %>
	
	<script>
		
	</script>

</body>
</html>
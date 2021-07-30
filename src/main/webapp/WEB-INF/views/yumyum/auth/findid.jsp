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

.main-section {
	text-align: center;
	margin: 0 auto;
	font-size: 20px;
}

.main-section h1 {
	border-bottom: none;
	margin-top: 50px;
	margin-bottom: 50px;
}

.form_in {
	display: flex;
	justify-content: center;
	margin-bottom: 25px;
}

.form_in i {
	font-size: 50px;
}

.form-size {
	width: 300px;
	height: auto;
}

#name_pos {
	margin-right: 200px;
}

#phone_pos {
	margin-right: 150px;
}

#btn_find {
	margin-left: 200px;
	margin-bottom: 20px;
}

#btn_find input {
	width: 150px;
	height: 40px;
	background-color: #FDD100;
}

#info {
	font-size: 15px;
	margin-bottom: 100px;
}

button {
	width: 100px;
	height: 40px;
}

</style>
</head>
<body>
	<%@ include file="/inc/header.jsp" %>
	
	
	<section class="main-section">
		
		<h1>아이디 찾기</h1>
		
		<form method="post" action="/yumyum/auth/findidok.do">
			<span id="name_pos">이름</span>
			<div class="form_in">
				<i class="material-icons">person_outline</i>
				<input type="text" name="name" class="form-control form-size" required placeholder="이름을 입력하세요.">
			</div>
			<span id="phone_pos">휴대폰번호</span>
			<div class="form_in">
				<i class="material-icons">phone_iphone</i>
				<input type="text" name="phone" class="form-control form-size" maxlength="11" required numberOnly 
				 placeholder="-을 제외한 휴대폰번호를 입력하세요." pattern="[0-9]+">
			</div>
			<div id="btn_find">
				<input type="submit" value="아이디 찾기" class="btn btn-default">
			</div>
		</form>
		
		<span id="info">회원가입시 입력한 이름, 전화번호를 입력하시면 아이디를 찾을 수 있습니다.</span> <br><br>
		<button tpye="button" class="btn btn-default" onclick="history.back();">취소</button>
	</section>
	
	
	<%@ include file="/inc/copyright.html" %>
	
	<script>
	
		
	</script>

</body>
</html>
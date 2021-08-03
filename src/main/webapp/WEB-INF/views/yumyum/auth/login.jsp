<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>yumyum</title>

<%@ include file="/inc/asset.jsp" %>

<style>
   
</style>

</head>
<body>
   
   <%@ include file="/inc/header.jsp" %>
   
   <section class="main-section">
      
      <h1>login page</h1>
      
   </section>   
   
   <%@ include file="/inc/copyright.html" %>
   
   <script>
      
   </script>
</body>
</html> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>yumyum</title>

<%@ include file="/inc/asset.jsp"%>

<style>
#cont {
	padding-top: 40px;
	margin: 0px auto;
	width: 800px;
	text-align: center;
}

table {
	width: 400px;
	text-align: center;
	margin: 0px auto;
}

.box {
	border: 0px;
	width: 500px;
	height: 40px;
	font-size: 1.5em;
	background-color: #FAB700;
	margin-bottom: 40px;
	margin: 0px auto;
}

td {
	border: 0px;
	margin-bottom: 20px;
}

h1 {
	padding-top: 8px;
	text-align: center;
	font-weight: bold;
}

input {
	height: 40px;
	width: 320px;
	font-size: 1.5em;
}

.btn {
	border: 0px;
	height: 30px;
	margin-left: -4px;
}

.btn:hover, #btnlogin:hover {
	background-color: #8DC5AA;
}

#btnbox {
	text-align: right;
	margin-right: 150px;
	margin-top: 20px;
}

.text {
	margin-left: 20px;
}
</style>

</head>
<body>
	<!-- login.jsp -->
	<%@ include file="/inc/header.jsp"%>

	<section class="main-section">

		<div id="cont">
			<div class="box" style="margin-bottom: 40px;">
				<h1>WELCOME 2 YUM YUM :)</h1>
			</div>
			
			<form method="POST" action="loginok.do">
				<table>
					<tr>
						<td><i class="glyphicon glyphicon-user"
							style="font-size: 3em"></i></td>
						<td><input type="text" class="text" name="id" id="id"
							placeholder="ID"></td>
					</tr>
					<tr>
						<td><i class="material-icons" style="font-size: 3em">vpn_key</i>
						</td>
						<td><input type="password" class="text" name="password" id="password"
							placeholder="PASSWORD"></td>
					</tr>
				</table>

				<input type="submit" class="box" id="btnlogin" value="로그인"
					style="margin-top: 40px;">
			</form>


			<div id="btnbox">
				<input type="button" class="btn" onclick="location.href='/yumyum/auth/findid.do';"
				id="findid" value="아이디 찾기" style="width: 90px;"> 
				<input type="button" class="btn" onclick="location.href='/yumyum/auth/findpw.do';"
				id="findpw" value="비밀번호 찾기" style="width: 100px;"> 
				<input type="button" class="btn" onclick="location.href='/yumyum/auth/signup.do';"
				id="signup" value="회원가입" style="width: 70px;">
			</div>

		</div>


	</section>

	<%@ include file="/inc/copyright.html"%>

	<script>
      
   </script>
</body>
</html>
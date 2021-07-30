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

#shopname, #shopexp {border:1px solid black; }
#shoppic {width:500px;}
#infobox{float:left;}

</style>
</head>
<body>
	<%@ include file="/inc/header.jsp" %>
	
	
	<div class="container">
		<h1 class="page-header"></h1>
		
		
		<div class="pic">
		<img src="/yumyum/images/외관사진.png" id="shoppic">
		</div>
		
		<div class="info">
		<div class="shopname">육회랑 연어랑</div>
		<div class="shopexp">최상급 육회~노르웨이 생연어~ 이제는 걱정없이 편하게 집에서 드세요~</div>
		<div class="score"></div>
		</div>
		
	</div>
	
	
	<%@ include file="/inc/copyright.html" %>
	
	<script>
		
	</script>

</body>
</html>















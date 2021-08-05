<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>yumyum</title>

<%@ include file="/inc/asset.jsp" %>

<style>
.container {
	width: 800px;
}

.nav-size {
	width: 150px;
}

.content-size {
	width: 650px;
	padding-right: 0px;
}

.col-md-8 {
	padding: 0px;
}

.tab-pane {
	margin-bottom: 20px;
	font-size: 17px;
}

label {
	cursor: pointer;
}

.page-header>.btn-group>.btn {
	padding-top: 20px;
	font-size: 20px;
	margin-top: -30px;
	margin-bottom: -10px;
	padding-bottom: -10px;
	padding-top: 10px;
}

.qcategory>.btn-group>.btn {
	display: inline-block;
	font-size: 15px;
	margin-top: -20px;
	padding: -30px;
	margin-left: 25px;
}

.qcategory>.btn-group>label {
	background-color: #F1F3F4;
}

#search {
	display: flex;
	text-align: center;
	margin: 10px;
	margin-left: 25px;
	width: 250px;
}

#search button {
	margin-right: 10px;
	background-color: #337AB7;
	color: white;
}

#qSearch {
	background-color: #fff;
}

#question {
widht:580px;
margin-left:25px;
border:1px solid black;
}

</style>
</head>
<body>
	<%@ include file="/inc/header.jsp" %>


	<section class="main-section">
		<h1 class="page-header">
			<div class="btn-group btn-group-toggle" data-toggle="buttons">
				<label class="btn btn-success-outline"
					onclick="location.href='/yumyum/customer/customerservice/cs_question.do?cs_subject_seq=6';"> 
					<input type="radio" name="jajo_rb" id="jajo_rb"> 자주 묻는 질문
				</label> 
				<label class="btn btn-success-outline active"
					onclick="location.href='/yumyum/customer/customerservice/cs_email_list.do';">
					<input type="radio" name="email_rb" id="email_rb"> 이메일 문의
				</label>
			</div>
		</h1>

		<div class="container">

			<div class="row">
				<div class="tabbable">

					<ul class="nav nav-pills nav-stacked col-md-3 nav-size">
						<li><a href="/yumyum/customer/mypage/mypage.do">My Page</a></li>
						<li><a href="/yumyum/customer/orderlist/cus_orderlist.do">주문내역</a></li>
						<li class="active"><a
							href="/yumyum/customer/customerservice/cs_question.do">고객센터</a></li>
						<li><a href="/yumyum/customer/bookmark.do">즐겨찾는가게</a></li>
					</ul>
					<div class="tab-content col-md-9 content-size">
						<div class="qcategory">
							<div class="btn-group btn-group-toggle" data-toggle="buttons">
								<label class="btn"><input type="radio" name="top10" value="top10">top10</label> 
								<label class="btn"><input type="radio" name="회원가입" value="회원가입">회원가입</label> 
								<label class="btn"><input type="radio" name="바로결제" value="바로결제">바로결제</label> 
								<label class="btn"><input type="radio" name="리뷰관리" value="리뷰관리">리뷰관리</label> 
								<label class="btn"><input type="radio" name="이용문의" value="이용문의">이용문의</label> 
								<label class="btn"><input type="radio" name="불편문의" value="불편문의">불편문의</label>
							</div>
						</div>



						<div id="search">
							<button class="btn btn-info" id="btnSearch">검색</button>
							<input type="text" name="qSearch" id="qSearch"
								class="form-control" required placeholder="제목, 내용을 검색해보세요.">
						</div>

						<div id="question">
						
							이메일문의입니다.
							
						</div>






					</div>
				</div> <!-- tabs -->
			</div>

		</div><!-- container -->


	</section>


	<%@ include file="/inc/copyright.html" %>
	
	<script>
	
/* 	HTML

	<label><input type="radio" name="fruits" value="사과">사과</label>

	<label><input type="radio" name="fruits" value="복숭아">복숭아</label>



	Javascript

	$("input:radio[name='fruits']:radio[value='사과']").prop('checked', true); // 선택하기

	$("input:radio[name='fruits']:radio[value='사과']").prop('checked', false); // 해제하기 */

	
	</script>

</body>
</html>
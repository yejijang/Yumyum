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

#searchWord {
	background-color: #fff;
}

.qcategory {
margin-left:25px;
}
.qcategory button {
	font-size: 15px;
	margin-top: -20px;
	padding:20px;
}
.questionList {
	margin-top: 20px;

}

.questionList li {
	list-style: none;
	border: 0px solid red;
	font-size: 18px;
	padding: 10px 10px;
}

.question a {
	list-style: none;
	cursor: pointer;
}

.question .hide {
	display: none;
}

.question:hover {
	font-weight: bold;
}

.question:hover .answer, .question:hover .content {
	font-weight: normal;
}

.answer {
	width: 594px;
	margin-left: -50px;
	margin-top: 7px;
	background-color: #F1F3F4;
}

.answer p {
	margin-top: 10px;
	padding-left:10px;
}

.question .answer {
	border-top:1px solid gray;
}
.question >i {
	border:1px solid red;
	margin-top: 10px;
	margin-right: 10px;
	font-size="large";
}
</style>
</head>
<body>
	<%@ include file="/inc/header.jsp" %>


	<section class="main-section">
		<h1 class="page-header">
			<div class="btn-group btn-group-toggle" data-toggle="buttons">
				<label class="btn btn-success-outline active"
					onclick="location.href='/yumyum/customer/customerservice/cs_question.do?cs_subject_seq=6';"> 
					<input type="radio" name="jajo_rb" id="jajo_rb"> 자주 묻는 질문
				</label> 
				<label class="btn btn-success-outline"
					onclick="location.href='/yumyum/customer/customerservice/cs_question.do?cs_subject_seq=6';">
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
							href="/yumyum/customer/customerservice/cs_question.do?cs_subject_seq=6">고객센터</a></li>
						<li><a href="/yumyum/customer/bookmark.do">즐겨찾는가게</a></li>
					</ul>
					<div class="tab-content col-md-9 content-size">
						<div class="qcategory">
							<%-- 
								<button type="button" class="btn btn-default" onclick="location.href='/yumyum/customer/customerservice/cs_question.do?cs_subject_seq=6';">top10</button>
								<button type="button" class="btn btn-default" onclick="location.href='/yumyum/customer/customerservice/cs_question.do?cs_subject_seq=1';">회원가입</button>
								<button type="button" class="btn btn-default" onclick="location.href='/yumyum/customer/customerservice/cs_question.do?cs_subject_seq=2';">바로결제</button>
								<button type="button" class="btn btn-default" onclick="location.href='/yumyum/customer/customerservice/cs_question.do?cs_subject_seq=3';">리뷰관리</button>
								<button type="button" class="btn btn-default" onclick="location.href='/yumyum/customer/customerservice/cs_question.do?cs_subject_seq=4';">이용문의</button>
								<button type="button" class="btn btn-default" onclick="location.href='/yumyum/customer/customerservice/cs_question.do?cs_subject_seq=5';">불편문의</button>
							--%> 
								
								<button type="button" class="btn btn-default" id="6" onclick="clickBtn(this.id);">top10</button>
								<button type="button" class="btn btn-default" id="1" onclick="clickBtn(this.id);">회원가입</button>
								<button type="button" class="btn btn-default" id="2" onclick="clickBtn(this.id);">바로결제</button>
								<button type="button" class="btn btn-default" id="3" onclick="clickBtn(this.id);">리뷰관리</button>
								<button type="button" class="btn btn-default" id="4" onclick="clickBtn(this.id);">이용문의</button>
								<button type="button" class="btn btn-default" id="5" onclick="clickBtn(this.id);">불편문의</button>

						</div>
						
						<form method="get" action="/yumyum/customer/customerservice/cs_question.do">
						<div id="search">
							<button class="btn" id="btnSearch">검색</button>
							<input type="text" name="searchWord" id="searchWord"
								   class="form-control" required placeholder="제목, 내용을 검색해보세요.">
						
						</div>
						</form>
						
						
						<div id="questionwrap">
						<c:if test="${list.size() == 0 }">
							<div class="middle-column">
								<br>
								<br>
								<div style="margin-left:30px;">검색 결과가 없습니다.</div>
							</div>
						</c:if>
						
							<ul class="questionList">
								<c:forEach items="${cslist}" var="csdto">
									<c:if test="${csdto.searchflag.equals('2000')}">
										<li class="question"><a><i class="material-icons">contact_support</i>${csdto.title}</a>
											<ul class="hide">
												<li class="answer" style="padding-top: 10px;">
													<p><i class="material-icons">headset_mic</i>${csdto.content}</p>
												</li>
											</ul></li>
									</c:if>
								</c:forEach>

								<c:if test="${map.searchWord!=null}">
									<c:forEach items="${list}" var="dto">
										<li class="question"><a><i class="material-icons">contact_support</i>${dto.title}</a>
											<ul class="hide">
												<li class="answer" style="padding-top: 10px;">
													<p><i class="material-icons">headset_mic</i>${dto.content}</p>
												</li>
											</ul></li>
									</c:forEach>
								</c:if>
							</ul>
						</div>
						
					</div>
				</div> <!-- tabs -->
			</div>

		</div><!-- container -->


	</section>


	<%@ include file="/inc/copyright.html" %>
	
	<script>
	
	$(document).ready(function() {
		$(".question>a").click(function() {
			$(this).next("ul").toggleClass("hide");
		});
	});
	
	function clickBtn(id) {
		//var location = $(document).val()
		//alert(val);
		location.href='/yumyum/customer/customerservice/cs_question.do?cs_subject_seq=' + id;
	}
	
	<c:if test="${map.isSearch.equals('y')}">
	$('#searchWord').val('${map.searchWord}');
	</c:if>
	

	
	
	
	</script>

</body>
</html>
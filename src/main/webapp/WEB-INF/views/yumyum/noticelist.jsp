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
	width: 800px;
	margin: 0px auto;
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
	padding-top: 10px;
}

#search {
	display: flex;
	text-align: center;
	margin: 15px;
	width: 300px;
}

#search button {
	margin-right: 10px;
	background-color: #337AB7;
	color: white;
}

#searchWord {
	background-color: #fff;
	
}

.questionList {
	margin-top: 20px;
	padding: 0px;
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
	width: 800px;
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
.pagebar {
	text-align: center;
}
</style>
</head>
<body>
	<%@ include file="/inc/header.jsp" %>


	<section class="main-section">
		<h1 class="page-header">
			공지사항
		</h1>

		<div class="container">

			<div class="row">
				<div class="tabbable">

					<div class="tab-content content-size">
						
						<form method="get" action="/yumyum/noticelist.do">
							<div id="search">
								<button class="btn" id="btnSearch">검색</button>
								<input type="text" name="searchWord" id="searchWord"
								class="form-control" placeholder="제목, 내용을 검색하세요.">
							
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
								<c:forEach items="${list}" var="dto">
									<li class="question"><a><i class="material-icons">lightbulb</i>${dto.subject}</a>
											<ul class="hide">
												<li class="answer" style="padding-top: 10px;">
													<p><i class="material-icons">headset_mic</i>${dto.content}</p>
												</li>
											</ul></li>
								</c:forEach>

				
							</ul>
						</div>
						<hr>
		
						<div class="pagebar">
							${pagebar}
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
	
	<c:if test="${map.isSearch.equals('y')}">
	$('#searchWord').val('${map.searchWord}');
	</c:if>
	

	
	
	
	</script>

</body>
</html>
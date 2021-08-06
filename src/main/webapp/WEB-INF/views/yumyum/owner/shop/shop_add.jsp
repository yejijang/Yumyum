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

#btn-align {
	text-align: right;
	margin-bottom: 10px;
}

table {
	margin-top: 10px;
	font-size: 15px;
}
table th:nth-child(1) { width: 180px; height: 60px; }

.btn-primary {
	margin-left: 250px;
	margin-bottom: 10px;
}

.auth-txt {
	font-size: 25px;
	display: flex;
	justify-content: center;
	height: 50px;
	margin-top: 50px;
}
.auth-txt i {
	font-size:33px;
	margin-right: 5px;
}

</style>
</head>
<body>
	<%@ include file="/inc/header.jsp" %>
	
	
	<section class="main-section">
	
		<h1>가게관리 > 가게 입점 신청</h1>	
		
	  	<div class="container">
    <div class="row">

        <div class="">
            <!-- tabs left -->
            <div class="tabbable">
                <ul class="nav nav-pills nav-stacked col-md-3 nav-size">
                    <li class="active"><a href="/yumyum/owner/shop/shop_add.do">가게 입점 관리</a></li>
                    <li><a href="/yumyum/owner/shop/shop_edit.do">가게 정보 관리</a></li>
                    <li><a href="/yumyum/owner/shop/delivery_tip.do">추가 배달팁 관리</a></li>
                    <li><a href="/yumyum/owner/shop/sales.do">매출 조회</a></li>
                </ul>
                <div class="tab-content col-md-9 content-size">
                	
                    <hr>
                    
                    <div class="tab-pane active">
                    	<c:if test="${dto.auth == null}">
                    	<form method="post" action="/yumyum/owner/shop/shop_addok.do" enctype="multipart/form-data">
	                    	<table class="table table-bordered">
								<tr>
									<th>상호명</th>
									<td><input type="text" name="name" id="name" class="form-control" required></td>
								</tr>
								<tr>
									<th>카테고리</th>
									<td>
										<select name="category" class="form-control" required>
											<option value="">카테고리 선택</option>
											<c:forEach items="${list}" var="dto">
											<option value="${dto.seq}">${dto.name}</option>
											</c:forEach>
										</select>
									</td>
								</tr>
								<tr>
									<th>전화번호</th>
									<td>
										<input type="tel" name="phone" id="phone" class="form-control" telOnly 
											pattern="01[0-9]{1}-[0-9]{4}-[0-9]{4}" maxlength="13"
											placeholder="ex) 010-xxxx-xxxx" autocomplete="off" required>
									</td>
								</tr>
								<tr>
									<th>사업자 등록 번호<br> (사업자등록증 첨부)</th>
									<td>
										<input type="text" name="registration" class="form-control form-size" maxlength="10" required numberOnly 
					 					placeholder="-을 제외한 사업자등록번호를 입력하세요." pattern="[0-9]+">
					 					<input type="file" class="file" name="file1" style="margin-top: 10px;" required>
									</td>
								</tr>
								<tr>
									<th>통장 사본(첨부)</th>
									<td>
										<input type="file" class="file" name="file2" required>
									</td>
								</tr>
								<tr>
									<th>주소</th>
									<td><input type="text" name="address" id="address" class="form-control" required></td>
								</tr>
								
							</table>
							
							<input type="submit" class="btn btn-primary" value="가게 입점 신청하기"><br>
							<span>- 가게 상세 정보는 입점 승인 완료 후 [가게 정보 관리 > 수정]에서 입력 가능합니다.</span><br>
						</form>
						</c:if>
						
						<c:if test="${dto.auth.equals('R')}">
						<div class="auth-txt">
							<i class="material-icons">sentiment_satisfied_alt</i>
							<span>입점 승인 대기중입니다.</span>
						</div>
						
						</c:if>
						
						<c:if test="${dto.auth.equals('Y')}">
						<div class="auth-txt">
							<i class="material-icons">sentiment_very_satisfied</i>
							<span>입점 승인 완료된 가게입니다.</span>
						</div>
						</c:if>
						
						<c:if test="${dto.auth.equals('N')}">
						<div class="auth-txt">
							<i class="material-icons">sentiment_very_dissatisfied</i>
							<span>입점 승인이 거부되었습니다.</span>
						</div>
						<div style="text-align: center;">고객센터에 문의해주세요.</div>
						
						</c:if>
						
						
                    </div>
                    
					
                </div>
            </div>
            <!-- /tabs -->
        </div>

    </div>
    <!-- /row -->
</div>

		
	</section>
	
	
	<%@ include file="/inc/copyright.html" %>
	
	<script>
	

	</script>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>yumyum</title>

<%@ include file="/inc/asset.jsp" %>
<script src="/yumyum/asset/js/highcharts.js"></script>

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

#top {
	background-color: #DDDDDD;
	padding: 20px;
	display: flex;
}

#top .my-grade {
	margin-left: 50px;
	width: 500px;
}

#top .my-grade a {
	font-size: 15px;
	display:flex;
}

#top .my-grade button {
	font-size: 16px;
	width: 120px;
	height: 40px;
}

#top img {
	margin-left: 10px;
	width: 60px;
	height: 60px;
}

#btn-align {
	text-align: right;
	margin-bottom: 10px;
}

table {
	margin-top: 10px;
	font-size: 15px;
}
table th:nth-child(1) { width: 80px; }
table th:nth-child(2) { width: auto; }
table th:nth-child(3) { width: 150px; }

#premium {
	background-color: #DDDDDD;
	padding: 20px;
	text-align: center;
}

.chart {
	border: 1px solid #999;
	border-radius: 10px;
	width: 600px;
	margin: 40px auto;
}

.modal.modal-center {
  text-align: center;
}

@media screen and (min-width: 768px) { 
  .modal.modal-center:before {
    display: inline-block;
    vertical-align: middle;
    content: " ";
    height: 100%;
  }
}

.modal-dialog.modal-center {
  display: inline-block;
  text-align: left;
  vertical-align: middle;
	margin: 0px auto; 
  width: 400px;
}

</style>
</head>
<body>
	<%@ include file="/inc/header.jsp" %>
	
	
	<section class="main-section">
	
		<h1>마이페이지</h1>	
		
	  	<div class="container">
    <div class="row">

        <div class="">
            <!-- tabs left -->
            <div class="tabbable">
                <ul class="nav nav-pills nav-stacked col-md-3 nav-size">
                    <li class="active"><a href="/yumyum/customer/mypage/mypage.do">My Page</a></li>
                    <li><a href="/yumyum/customer/orderlist/cus_orderlist.do">주문내역</a></li>
                    <li><a href="/yumyum/customer/customerservice/cs_question.do?cs_subject_seq=6">고객센터</a></li>
                    <li><a href="/yumyum/customer/bookmark.do">즐겨찾는가게</a></li>
                </ul>
                <div class="tab-content col-md-9 content-size">
                	<div class="my-grade" id="btn-align">
                   		<button class="btn btn-primary" onclick="location.href='/yumyum/customer/mypage/mypage_edit.do';">개인정보 수정</button>
                   	</div>
                    <div class="tab-pane active" id="top">
                    	<div>
                    		<c:if test="${dto.grade.equals('골드')}">
	                    	<img alt="등급" src="/yumyum/images/mypage/gold.png">
	                    	</c:if>
	                    	<c:if test="${dto.grade.equals('실버')}">
	                    	<img alt="등급" src="/yumyum/images/mypage/silver.png">
	                    	</c:if>
	                    	<c:if test="${dto.grade.equals('브론즈')}">
	                    	<img alt="등급" src="/yumyum/images/mypage/bronze.png">
	                    	</c:if>
	                    	<c:if test="${dto.grade.equals('일반')}">
	                    	<img alt="등급" src="/yumyum/images/mypage/normal.png">
	                    	</c:if>
	                    </div>
	                    <div class="my-grade">
	                    	<span>이번 달 '${dto.nickname}' 님의 등급은 '${dto.grade}' 입니다.</span><br>
	                    	<a href="" data-toggle="modal" data-target="#exampleModal">
	                    		<i class="material-icons" style="font-size:30px; padding-top: 5px;">info</i>
	                    		<span style="padding-top: 10px; margin-left: 5px;">등급별 혜택</span>
	                    	</a>
                    	</div>
                    </div>
                    
                    <hr>
                    
                    <div class="tab-pane active">
                    	<span>- 나의 포인트 : ${dto.point}포인트</span><br>
                    	<span>- 누적 결제금액 : ${dto.sumPrice}원</span>
                    </div>
                    
                    <div class="tab-pane active">
                    	<span>- 사용 가능 쿠폰</span><br>
                    	<table class="table table-bordered">
							<tr>
								<th>No.</th>
								<th>쿠폰명</th>
								<th>쿠폰금액</th>
							</tr>
							<c:if test="${list.size() == 0 }">
							<tr>
								<td colspan="3">사용 가능한 쿠폰이 없습니다.</td>
							</tr>
							</c:if>
							<c:forEach items="${list}" var="dto">
							<tr>
								<td>${dto.rnum}</td>
								<td>${dto.name}</td>
								<td>${dto.price}원</td>
							</tr>
							</c:forEach>
						</table>
                    </div>
                    
                    <c:if test="${not empty dto.premiumEndDate}">
                    <div class="tab-pane active" id="premium">
                    	<span style="font-size: 20px; color: #FF6505;">얌얌 혜택 사용 중</span><br>
                    	<span>사용 종료 예정일 : ${dto.premiumEndDate}</span><br>
                    	<span>잔여 횟수 : ${dto.premiumRemainCnt}회</span>
                    </div>
                    </c:if>
                    
                    <c:if test="${list2.size() != 0 }">
                    <div class="tab-pane active">
                    	<span>- 내가 가장 많이 주문한 카테고리는?</span><br>
                    	<div class="chart" id="chart1"></div>
                    </div>
                    </c:if>
                    
					<!-- Modal -->
					<div class="modal fade modal-center" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
						<div class="modal-dialog modal-center" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<h4 class="modal-title" id="exampleModalLabel">등급별 혜택</h4>
								</div>
								<div class="modal-body">
									<h4 style="color: #D5A11E;">1. 골드 등급</h5>
									<p>- 조건 : 직전 월 주문 횟수가 20회 이상인 회원</p>
									<p>- 혜택 : 결제시 결제 금액의 10%를 포인트로 적립</p>

									<h4 style="color: #A3A3A3;">2. 실버 등급</h4>
									<p>- 조건 : 직전 월 주문 횟수가 10회 이상인 회원</p>
									<p>- 혜택 : 결제시 결제 금액의 7%를 포인트로 적립</p>
									
									<h4 style="color: #CD7F32;">3. 브론즈 등급</h4>
									<p>- 조건 : 직전 월 주문 횟수가 5회 이상인 회원</p>
									<p>- 혜택 : 결제시 결제 금액의 3%를 포인트로 적립</p>
        
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
								</div>
							</div>
						</div>
					</div>
					<!-- Modal End -->  
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
	
		Highcharts.chart('chart1', {
		    chart: {
		        plotBackgroundColor: null,
		        plotBorderWidth: null,
		        plotShadow: false,
		        type: 'pie'
		    },
		    title: {
		        text: '카테고리별 주문 수'
		    },
		    tooltip: {
		        pointFormat: '{series.name}: <b>{point.y}개</b>'
		    },
		    accessibility: {
		        point: {
		            valueSuffix: '개'
		        }
		    },
		    plotOptions: {
		        pie: {
		            allowPointSelect: true,
		            cursor: 'pointer',
		            dataLabels: {
		                enabled: true,
		                format: '<b>{point.name}</b>: {point.y}개'
		            }
		        }
		    },
		    series: [{
		        name: '주문 수',
		        colorByPoint: true,
		        data: [
		        
		        <c:forEach items="${list2}" var="dto2">
		        {
		            name: '${dto2.name}',
		            y: ${dto2.cnt}
		        },
		        </c:forEach>
		        
		        
		        ]
		    }]
		});

	</script>

</body>
</html>
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
	position: relative;
	text-align: center;
	margin: 0px auto;
	width: 800px;
}

a {
   color: black;
   text-decoration: none;
}

a:hover {
   text-decoration: none;
}

.item-list {
   display: flex;
   text-align: center;
   margin: 0px auto;
}

.item-list>.item {
   width: 200px;
   height: 170px;
   margin: 10px;
   margin-left: 5px;
   border: 1px solid #dbdbdb;
}


.item-list>.item05, .item-list>.item09 {
   margin-left: 5px;
}

.item-image {
   height: 100px;
   margin-left: 50px;
}

.item-image img {
	width:100px;
}


.item-maintext {
   text-align: left;
   font-size: 21px;
   margin: 10px;
   margin-bottom: 0px;
}

.item-subtext {
   text-align: left;
   font-size: 15px;
   margin-left: 10px;
   color: #818181;
}

</style>
</head>
<body>
	<%@ include file="/inc/header.jsp" %>

    <%@ include file="/inc/location.jsp" %>
	
	<div class="container">
		<h1 class="page-header"></h1>

		<div class="item-list item-list01">
			<div class="item item01">         
				<a href="/yumyum/shoplist.do?category=1" class="anchor">
					<p class="item-maintext">한식</p>
               		<p class="item-subtext">Korean Food</p>
					<div class="item-image">
						<img src="/yumyum/images/category/category01.png" />
					</div>
				</a>
            </div>
            <div class="item item-set item02">
				<a href="/yumyum/shoplist.do?category=2" class="anchor">
					<p class="item-maintext">분식</p>
					<p class="item-subtext">Snack Menu</p>
					<div class="item-image">
						<img src="/yumyum/images/category/category02.png" />
					</div>
				</a>
            </div>
            <div class="item item-set item03">
				<a href="/yumyum/shoplist.do?category=3" class="anchor">
					<p class="item-maintext">카페/디저트</p>
					<p class="item-subtext">cafe/Dessert</p>
					<div class="item-image">
						<img src="/yumyum/images/category/category03.png" />
					</div>
				</a>
			</div>
            <div class="item item-set item04">
				<a href="/yumyum/shoplist.do?category=4" class="anchor">
					<p class="item-maintext">돈가스/회/일식</p>
					<p class="item-subtext">Pork Cutlets, Lime</p>
					<div class="item-image">
						<img src="/yumyum/images/category/category04.png" />
					</div>
				</a>
            </div>
		</div>
         
		<div class="item-list item-list02">
			<div class="item item-set item05">
				<a href="/yumyum/shoplist.do?category=5" class="anchor">
					<p class="item-maintext">치킨</p>
					<p class="item-subtext">Chciken</p>
					<div class="item-image">
						<img src="/yumyum/images/category/category05.png" />
					</div>
				</a>
			</div>
			<div class="item item-set item06">
				<a href="/yumyum/shoplist.do?category=6" class="anchor">
					<p class="item-maintext">피자</p>
					<p class="item-subtext">Pizza</p>
					<div class="item-image">
						<img src="/yumyum/images/category/category06.png" />
					</div>
				</a>
			</div>
			<div class="item item-set item07">
				<a href="/yumyum/shoplist.do?category=7" class="anchor">
					<p class="item-maintext">아시안/양식</p>
					<p class="item-subtext">Chinesse Food</p>
					<div class="item-image">
						<img src="/yumyum/images/category/category07.png" />
					</div>
				</a>
			</div>
            <div class="item item-set item08">
               <a href="/yumyum/shoplist.do?category=8" class="anchor">
	               <p class="item-maintext">중국집</p>
	               <p class="item-subtext">Chinesse Food</p>
	               <div class="item-image">
	                  <img src="/yumyum/images/category/category08.png" />
	               </div>
               </a>
            </div>
        </div>
		<div class="item-list item-list03">
			<div class="item item-set item09">
				<a href="/yumyum/shoplist.do?category=9" class="anchor">
					<p class="item-maintext">족발/보쌈</p>
					<p class="item-subtext">Pork feet</p>
					<div class="item-image">
						<img src="/yumyum/images/category/category09.png" />
					</div>
				</a>
			</div>
			<div class="item item-set item10">
				<a href="/yumyum/shoplist.do?category=10" class="anchor">
					<p class="item-maintext">야식</p>
					<p class="item-subtext">Dawn Food</p>
					<div class="item-image">
						<img src="/yumyum/images/category/category10.png" />
					</div>
				</a>
			</div>
			<div class="item item-set item11">
				<a href="/yumyum/shoplist.do?category=11" class="anchor">
					<p class="item-maintext">찜/탕</p>
					<p class="item-subtext">Soup</p>
					<div class="item-image">
						<img src="/yumyum/images/category/category11.png" />
					</div>
				</a>
			</div>
			<div class="item item-set item12">
				<a href="/yumyum/shoplist.do?category=12" class="anchor">
					<p class="item-maintext">패스트푸드</p>
					<p class="item-subtext">Fastfood</p>
					<div class="item-image">
						<img src="/yumyum/images/category/category12.png" />
					</div>
				</a>
			</div>
		</div>
		
	</div>
	
	
	
	<%@ include file="/inc/copyright.html" %>
	
	<script>

		$('.anchor').click(function () {
			
			if($('#address').val() == "") {
				alert("주소를 설정해주세요.");
				return false;
			}
			
			let anchors = document.getElementsByClassName('anchor');
			for (let i=0; i<anchors.length; i++) {

				anchors[i].setAttribute('href', anchors[i].getAttribute('href') + '&address=' + $('#address').val());
			}
		});
		
		
	</script>



</body>
</html>
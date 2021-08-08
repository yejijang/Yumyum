<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    


<style>

#gps {
	display: flex;
	text-align: center;
	margin: 0px auto;
	width: 500px;
}

#gps button {
	margin-right: 10px;
}

#gps-icon {
	vertical-align: middle;
	color: #FF6505;
}

#address {
	background-color: #fff;
}

</style>
	
	<section class="main-section">
		
		<div id="gps">
			<button id="btnGps"><i class="material-icons" id="gps-icon">gps_fixed</i></button>
			<button class="btn btn-info" id="btnSearchAdr">주소 검색</button>
			<input type="text" name="address" id="address" class="form-control" 
			required readonly placeholder="현재 위치를 설정하거나 주소 검색을 해주세요.">
		</div>
		
		
	</section>
	
	<script>
	
		var options = {
			enableHighAccuracy : true,
			timeout : 5000,
			maximumAge : 0
		};
		function success(pos) {
			var crd = pos.coords;
			console.log('위도 : ' + crd.latitude);
			console.log('경도: ' + crd.longitude);
			lat = crd.latitude;
			lon = crd.longitude;
			
			//좌표로 주소 얻어 오기
			$.ajax({
				url : 'https://dapi.kakao.com/v2/local/geo/coord2address.json?x=' + lon +'&y=' + lat,
			    type : 'GET',
			    headers : {
					//'Authorization' : 'KakaoAK {REST_API_KEY}'
					'Authorization' : 'KakaoAK 2749ec6aa32a3f47466d064d03c9a8ba'
			    },
			    success : function(data) {
			      console.log(data);
			      //alert(data.documents[0].address.address_name);
			      $('#address').val(data.documents[0].address.address_name);
			    },
			    error : function(e) {
			      console.log(e);
			      alert(e);
			    }
			});
			
			
		};

		function error(err) {
			console.warn('ERROR(' + err.code + '): ' + err.message);
		};

		$("#btnGps").click(function() {
			navigator.geolocation.getCurrentPosition(success, error, options);
		});
		

		document.getElementById("btnSearchAdr").addEventListener("click", function(){ //주소 검색 버튼을 클릭하면
			//카카오 지도 발생
			new daum.Postcode({
				oncomplete: function(data) { //선택시 입력값 세팅
					document.getElementById("address").value = data.jibunAddress; // 주소 넣기
				}
			}).open();
		});
		


	</script>

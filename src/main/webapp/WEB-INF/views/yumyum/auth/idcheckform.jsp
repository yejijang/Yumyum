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
#wrap {
	text-align: center;
}

#userId {
	width: 60%;
	margin-right: 5px;
}

#checkForm {
	display: flex;
	justify-content: center;
}

#title {
	padding: 20px;
	margin-bottom: 30px;
	font-size: 16px;
	border-bottom: 1px solid #DDD;
}

#useBtn {
	background-color: #FD9F32;
	color: white;
}

#useBtn:hover {
	background-color: #FE8C06;
	color: white;
}

#msg {
	margin-top: 10px;
	color: #FF6505;
}
</style>
</head>
<body onload="pValue()">
	<div id="wrap">
		<div id="title">아이디 중복체크</div>
		<div id="chk">
			<form id="checkForm">
				<input type="text" name="idinput" id="userId" class="form-control"
					autocomplete="off" required numeng>
				<button type="button" class="btn" onclick="idCheck()">중복확인</button>
			</form>
			<div id="msg"></div>
			<br>
			<button type="button" id="cancelBtn" class="btn btn-default"
				onclick="window.close()" style="visibility: hidden;">취소</button>
			<br>
			<button type="button" id="useBtn" class="btn"
				onclick="sendCheckValue()" style="visibility: hidden;">사용하기</button>
		</div>
	</div>

	<script>
		var httpRequest = null;
		var userId = document.getElementById("userId");

		// 숫자 & 소문자만 입력 가능
		$(document).on("keyup", "input[numeng]", function() {
			$(this).val($(this).val().replace(/[^a-z0-9]/g, ""));
		});

		// httpRequest 객체 생성
		function getXMLHttpRequest() {
			var httpRequest = null;

			if (window.ActiveXObject) {
				try {
					httpRequest = new ActiveXObject("Msxml2.XMLHTTP");
				} catch (e) {
					try {
						httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
					} catch (e2) {
						httpRequest = null;
					}
				}
			} else if (window.XMLHttpRequest) {
				httpRequest = new window.XMLHttpRequest();
			}
			return httpRequest;
		}

		function pValue() {
			userId.value = opener.document.sign.id.value; // 실행되자마자 부모창에서 id 입력값 받아오기
		}

		// 중복체크하기 위해 ajax 사용
		function idCheck() {
			var id = userId.value;

			if (!id) {
				alert("아이디를 입력하지 않았습니다.");
				return false;
			} else {
				var param = "id=" + id;
				httpRequest = getXMLHttpRequest();
				httpRequest.onreadystatechange = callback;
				httpRequest.open("POST", "memberidcheckaction.do", true);
				httpRequest.setRequestHeader('Content-Type',
						'application/x-www-form-urlencoded');
				httpRequest.send(param);
			}
		}

		// 중복체크 결과
		function callback() {
			if (httpRequest.readyState == 4) {
				if (userId.value.length < 6) {
					userId.focus();
					alert("아이디는 소문자와 숫자 포함 최소6자로 입력하세요.");
					return false;
				} else {
					var resultText = httpRequest.responseText;
					console.log(resultText);
					if (resultText == 0) { // 아이디 중복 O
						alert("사용할 수 없는 아이디입니다.");
						document.getElementById("cancelBtn").style.visibility = 'visible';
						document.getElementById("useBtn").style.visibility = 'hidden';
						document.getElementById("msg").innerHTML = "";
						userId.focus();
					} else if (resultText == 1) { // 아이디 중복 X
						document.getElementById("cancelBtn").style.visibility = 'hidden';
						document.getElementById("useBtn").style.visibility = 'visible';
						document.getElementById("msg").innerHTML = "사용 가능한 아이디입니다.";
					}
				}
			}
		}

		// 중복체크 후 사용하기 클릭 시 부모창으로 값 전달
		function sendCheckValue() {
			// 중복체크 결과값 전달
			opener.document.sign.idDuplication.value = "idCheck";
			// 회원가입 화면(부모창)의 id입력란에 값을 전달
			opener.document.sign.id.value = userId.value;

			if (opener != null) {
				opener.chkForm = null;
				self.close();
			}
		}
	</script>
</body>
</html>
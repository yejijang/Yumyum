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
form {
	width: 420px;
	margin: 0 auto;
}

form .title {
	margin-left: 15px;
	font-size: 18px;
}

form input.form-control {
	margin-left: 10px;
	margin-bottom: 10px;
	width: 320px;
}

.btns {
	text-align: center;
	margin-top: 20px;
}

.check {
	display: flex;
	justify-content: flex-start;
}

.check::after {
	content: '';
	display: block;
	clear: both;
}

.btn-check {
	margin-left: 5px;
	height: 34px;
	background-color: #FD9F32;
	color: white;
}

.btn-check:hover {
	background-color: #FE8C06;
	color: white;
}

form .btns .btn-signup {
	background-color: #9DAB86;
	color: white;
}

form .btns .btn-signup:hover {
	background-color: #62760C;
}
</style>
</head>
<body>

	<%@ include file="/inc/header.jsp"%>

	<section class="main-section">

		<h1>회원가입</h1>

		<form method="post" action="/yumyum/auth/signupok.do" name="sign"
			onsubmit="return check()">

			<div class="title">이름 :</div>
			<input type="text" name="name" id="name" class="form-control"
				autocomplete="off" required>

			<div class="title">아이디 :</div>
			<div class="check">
				<input type="text" name="id" id="id" class="form-control"
					autocomplete="off" placeholder="소문자와 숫자 포함 최소6자"
					onkeydown="inputIdChk()" required numeng>
				<button type="button" class="btn btn-check" onclick="openIdChk()">중복확인</button>
				<input type="hidden" name="idDuplication" value="idUncheck">
			</div>

			<!-- 소비자 회원가입의 경우 -->
			<c:if test="${auth eq 'C'}">
				<div class="title">닉네임 :</div>
				<div class="check">
					<input type="text" name="nickname" id="nickname"
						class="form-control" autocomplete="off" placeholder="한글, 소문자만 가능"
						onkeydown="inputNickChk()" required koreng>
					<button type="button" class="btn btn-check" onclick="openNickChk()">중복확인</button>
					<input type="hidden" name="nickDuplication" value="nickUncheck">
				</div>
			</c:if>

			<div class="title">이메일 :</div>
			<input type="email" name="email" id="email" class="form-control"
				autocomplete="off" required>

			<div class="title">비밀번호 :</div>
			<input type="password" name="pw" id="pw" class="form-control"
				autocomplete="off" placeholder="소문자와 숫자 포함 최소6자" required numeng>

			<div class="title">비밀번호 확인 :</div>
			<input type="password" name="pw2" id="pw2" class="form-control"
				autocomplete="off" placeholder="비밀번호 확인" required numeng>

			<div class="title">전화번호 :</div>
			<input type="tel" name="phone" id="phone" class="form-control"
				pattern="01[0-9]{1}-[0-9]{4}-[0-9]{4}"
				placeholder="ex) 010-xxxx-xxxx" autocomplete="off" required>

			<div class="btns">
				<button type="submit" class="btn btn-signup">가입하기</button>
				<button type="button" class="btn btn-default"
					onclick="location.href='/myapp/board/list.do';">돌아가기</button>
			</div>

			<input type="hidden" name="auth" value="${auth}">

		</form>

	</section>

	<%@ include file="/inc/copyright.html"%>

	<script>
		// 한글 & 소문자만 입력 가능
		$(document).on("keyup", "input[koreng]", function() {
			$(this).val($(this).val().replace(/[^ㄱ-힣a-z]/g, ""));
		});

		// 숫자 & 소문자만 입력 가능
		$(document).on("keyup", "input[numeng]", function() {
			$(this).val($(this).val().replace(/[^a-z0-9]/g, ""));
		});

		// 회원가입 입력폼
		var f = document.sign;

		// 회원가입 유효성 검사
		function check() {

			if (f.id.value.length < 6) {
				f.id.focus();
				alert("아이디는 소문자와 숫자 포함 최소6자로 입력하세요.");
				return false;
			} else if (f.pw.value.length < 6) {
				f.pw.focus();
				alert("비밀번호는 소문자와 숫자 포함 최소6자로 입력하세요.");
				return false;
			} else if (f.pw.value != f.pw2.value) {
				f.pw.focus();
				f.pw2.value = "";
				alert("비밀번호가 일치하지 않습니다.");
				return false;
			} else if (f.idDuplication.value != "idCheck") {
				alert("아이디 중복체크를 해주세요.");
				return false;
			} else if (f.nickDuplication.value != "nickCheck") {
				alert("닉네임 중복체크를 해주세요.");
				return false;
			} else
				return true;
		}

		// 아이디 중복검사 자식창 열기
		function openIdChk() {

			if (f.id.value.length < 6) {
				f.id.focus();
				alert("아이디는 소문자와 숫자 포함 최소6자로 입력하세요.");
				return false;
			} else {
				window.name = "parentForm";
				myExternalWindow = window.open("/yumyum/auth/idcheckform.do",
						"chkForm", "resizable");
				myExternalWindow.resizeTo(500, 370);
			}
		}

		// 아이디 중복체크 이후에 새로운 아이디를 입력한 경우 > 새로 중복검사를 해야한다.
		function inputIdChk() {
			document.sign.idDuplication.value = "idUncheck";
		}

		// 닉네임 중복검사 자식창 열기
		function openNickChk() {

			if (f.nickname.value.length == 0) {
				f.nickname.focus();
				alert("닉네임을 입력하세요.");
				return false;
			} else {
				window.name = "parentForm";
				myExternalWindow = window.open("/yumyum/auth/nickcheckform.do",
						"chkForm", "resizable");
				myExternalWindow.resizeTo(500, 370);
			}
		}
		
		// 닉네임 중복체크 이후에 새로운 닉네임를 입력한 경우 > 새로 중복검사를 해야한다.
		function inputNickChk() {
			document.sign.nickDuplication.value = "nickUncheck";
		}
	</script>

</body>
</html>
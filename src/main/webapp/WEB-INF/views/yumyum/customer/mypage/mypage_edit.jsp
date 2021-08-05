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
	font-size: 18px;
}

form input.form-control {
	margin-bottom: 10px;
	width: 320px;
}

.btns {
	text-align: center;
	margin-top: 20px;
	margin-left: 0px;
	width: 320px;
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

.btns .btn-signup {
	background-color: #9DAB86;
	color: white;
}

.btns .btn-signup:hover {
	background-color: #62760C;
}

.btn-withdrawal {
	background-color: #FF6505;
	color: white;
}

.btn-withdrawal:hover {
	background-color: #FF0000;
	color: white;
}

.container {
	width: 800px;
	padding: 0px;
}

.nav-size {
	width: 150px;
}

.content-size {
	width: 650px;
	padding-right: 0px;
}

.modal {
    text-align: center;
}

@media screen and (min-width: 768px) { 
	.modal:before {
	    display: inline-block;
	    vertical-align: middle;
	    content: " ";
	    height: 100%;
	}
}

.modal-dialog {
	width: 400px;
	display: inline-block;
    text-align: left;
    vertical-align: middle;
}

.modal-body-form {
	margin: 20px;
}

.modal-body-form #delpw {
	margin-top: 10px;
}
</style>
</head>
<body>

	<%@ include file="/inc/header.jsp"%>

	<section class="main-section">

		<h1>개인정보 수정</h1>

		<div class="container">
			<!-- tabs left -->
			<div class="tabbable">
				<ul class="nav nav-pills nav-stacked col-md-3 nav-size">
					<li class="active"><a href="/yumyum/customer/mypage/mypage.do">My
							Page</a></li>
					<li><a href="/yumyum/customer/orderlist/cus_orderlist.do">주문내역</a></li>
					<li><a href="/yumyum/customer/customerservice/cs_question.do?cs_subject_seq=6">고객센터</a></li>
					<li><a href="/yumyum/customer/bookmark.do">즐겨찾는가게</a></li>
				</ul>
				<div class="tab-content col-md-9 content-size">
					<form method="post"
						action="/yumyum/customer/mypage/mypage_editok.do" name="sign"
						onsubmit="return check()">

						<!-- 소비자 개인정보수정 경우 -->
						<c:if test="${auth eq 'C'}">
							<div class="title">닉네임 :</div>
							<div class="check">
								<input type="text" name="nickname" id="nickname"
									class="form-control" autocomplete="off"
									placeholder="한글, 소문자만 가능" onkeydown="inputNickChk()"
									value="${dto.nickname}" required koreng>
								<button type="button" class="btn btn-check"
									onclick="openNickChk()">중복확인</button>
								<input type="hidden" name="nickDuplication" value="nickCheck">
							</div>
						</c:if>

						<div class="title">현재 비밀번호 :</div>
						<input type="text" name="oldpw" id="oldpw" class="form-control"
							value="${dto.password}" disabled>

						<div class="title">새로운 비밀번호 :</div>
						<input type="password" name="newpw" id="newpw"
							class="form-control" autocomplete="off"
							placeholder="소문자와 숫자 포함 최소6자" required numeng>

						<div class="title">새로운 비밀번호 확인 :</div>
						<input type="password" name="newpw2" id="newpw2"
							class="form-control" autocomplete="off" placeholder="비밀번호 확인"
							required numeng>

						<div class="title">전화번호 :</div>
						<input type="tel" name="phone" id="phone" class="form-control"
							pattern="01[0-9]{1}-[0-9]{4}-[0-9]{4}"
							placeholder="ex) 010-xxxx-xxxx" autocomplete="off"
							value="${dto.phone}" required>

						<div class="title">이메일 :</div>
						<input type="email" name="email" id="email" class="form-control"
							autocomplete="off" value="${dto.email}" required>

						<div class="btns">
							<button type="submit" class="btn btn-signup">수정하기</button>
							<button type="button" class="btn btn-withdrawal"
								data-toggle="modal" data-target="#delModal">회원탈퇴</button>
						</div>

						<input type="hidden" name="seq" value="${seq}">

					</form>

					<!-- Modal -->
					<div class="modal fade" id="delModal" tabindex="-1" role="dialog"
						aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="myModalLabel">회원탈퇴</h4>
								</div>
								<div class="modal-body">
									<div class="modal-body-text">
										재사용 및 복구 불가 안내<br>
										- 내 정보 및 이용 기록이 모두 삭제되며,
										<br><span style="color: tomato;">삭제된 데이터는 복구되지 않습니다.</span><br>
										<br>안전한 회원탈퇴를 위해, <span style="color: cornflowerblue;">비밀번호를 확인해주세요.</span><br>
									</div>
									<div class="modal-body-form">
										아이디: ${id}
										<input type="password" name="delpw" id="delpw"
											class="form-control" placeholder="비밀번호를 입력하세요."
											autocomplete="off" required>
									</div>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-withdrawal" onclick="delPwChk()">탈퇴하기</button>
								</div>
							</div>
						</div>
					</div>
					<!-- Modal End -->
				</div>
			</div>
			<!-- /tabs -->
		</div>

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

		// 개인정보수정 입력폼
		var f = document.sign;

		// 개인정보수정 유효성 검사
		function check() {

			if (f.newpw.value.length < 6) {
				f.newpw.focus();
				alert("비밀번호는 소문자와 숫자 포함 최소6자로 입력하세요.");
				return false;
			} else if (f.newpw.value != f.newpw2.value) {
				f.newpw.focus();
				f.newpw2.value = "";
				alert("비밀번호가 일치하지 않습니다.");
				return false;
			} else if (f.nickDuplication.value != "nickCheck") {
				alert("닉네임 중복체크를 해주세요.");
				return false;
			} else
				return true;
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
		
		function delPwChk() {
			var delpw = document.getElementById("delpw");
			
			if (delpw.value != "${dto.password}") {
				alert("비밀번호가 일치하지 않습니다.");
				delpw.focus();
			} else {
				// 탈퇴 진행
				alert("회원탈퇴가 완료되었습니다.");
				location.href='/yumyum/customer/mypage/mypage_withdrawal.do?seq=${seq}';
			}
		}
	</script>

</body>
</html>
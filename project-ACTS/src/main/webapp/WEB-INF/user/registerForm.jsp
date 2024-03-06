<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<style type="text/css">
#title {
	background-color: #C9E4C5;
	color: white;
}

h3, h4 {
	font-weight: bold;
}

#b {
	background-color: #C9E4C5;
	border: 0;
	outline: 0;
}

.d {
	text-align: center;
}
table {
    margin-left:auto; 
    margin-right:auto;
}
</style>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous"/>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous"/>

<script>
	function userCreate() {
		if (form.accountId.value == "") {
			alert("사용자 ID를 입력하십시오.");
			form.accountId.focus();
			return false;
		}
		if (form.password.value == "") {
			alert("비밀번호를 입력하십시오.");
			form.password.focus();
			return false;
		}
		if (form.password.value != form.password2.value) {
			alert("비밀번호가 일치하지 않습니다.");
			form.password2.focus();
			return false;
		}
		if (form.name.value == "") {
			alert("이름을 입력하십시오.");
			form.userName.focus();
			return false;
		}
		var emailExp = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
		if (emailExp.test(form.email.value) == false) {
			alert("이메일 형식이 올바르지 않습니다.");
			form.email.focus();
			return false;
		}
		var phoneExp = /^\d{2,3}-\d{3,4}-\d{4}$/;
		if (phoneExp.test(form.phone.value) == false) {
			alert("전화번호 형식이 올바르지 않습니다.");
			form.phone.focus();
			return false;
		}
		if (form.regNum.value == "") {
			alert("주민등록번호를 입력하십시오.");
			form.regNum.focus();
			return false;
		}
		if (form.nickName.value == "") {
			alert("닉네임을 입력하십시오.");
			form.nickName.focus();
			return false;
		}
		form.submit();
	}

	function userList(targetUri) {
		form.action = targetUri;
		form.submit();
	}
</script>
</head>

<body>
<%@include file="/WEB-INF/navbar.jsp" %>

	<div class="container"> 
    <div class="input-form-backgroud row"> <div class="input-form col-md-12 mx-auto"> 
    <h4 class="mb-3">회원가입</h4> 
    <form name = "form" method="POST" action="<c:url value='/user/register' />"> 
    <div class="row"> 
    <div class="col-md-6 mb-3"> 
    <label for="name">이름</label> 
    <input type="text" class="form-control" name="name" placeholder="" value="" required> 
    <div class="invalid-feedback"> 이름을 입력해주세요. </div> 
    </div> 
    <div class="col-md-6 mb-3"> 
    <label for="nickName">별명</label> 
    <input type="text" class="form-control" name="nickName" placeholder="" value="" required>
     <div class="invalid-feedback"> 별명을 입력해주세요. </div>
     </div> 
    </div> 
    <div class="mb-3"> 
        <label for="accountId">아이디</label>
         <input type="text" class="form-control" name="accountId" placeholder="" required> 
        <div class="invalid-feedback"> 아이디를 입력해주세요. </div> 
    </div>
    <div class="mb-3"> 
        <label for="password">비밀번호</label>
         <input type="password" class="form-control" name="password" placeholder="" required> 
        <div class="invalid-feedback"> 비밀번호를 입력해주세요. </div> 
    </div>
    <div class="mb-3"> 
        <label for="password2">비밀번호 확인</label>
         <input type="password" class="form-control" name="password2" placeholder="" required> 
    </div>
    <div class="mb-3"> 
        <label for="phone">전화번호</label>
         <input type="text" class="form-control" name="phone" placeholder="" required> 
        <div class="invalid-feedback"> 전화번호를 입력해주세요. </div> 
    </div>
    <div class="mb-3"> 
    <label for="email">이메일</label>
     <input type="email" class="form-control" name="email" placeholder="you@example.com" required> 
    <div class="invalid-feedback"> 이메일을 입력해주세요. </div> 
    </div> 
    <div class="mb-3"> 
    <label for="regNum">주민등록번호</label> 
    <input type="text" class="form-control" name="regNum" placeholder="" required> 
    <div class="invalid-feedback"> 주민등록번호를 입력해주세요. </div>
    </div> 
    <hr class="mb-4">
     <div class="custom-control custom-checkbox"> 
    <input type="checkbox" class="custom-control-input" id="aggrement" required> 
    <label class="custom-control-label" for="aggrement">개인정보 수집 및 이용에 동의합니다.</label> 
    </div>
    <div class="mb-4"></div> 
    <button class="btn btn-primary btn-lg btn-block" type="submit" onClick="userCreate()" id="b">가입 완료</button> 
    <button class="btn btn-primary btn-lg btn-block" type="reset" id="b">다시 입력</button> 
    </form> 
    </div>
     </div>
     <footer class="my-3 text-center text-small"> 
    <p class="mb-1">&copy; 2021 ACTSON</p> 
    </footer> 
    </div>
    
</body>
</html>
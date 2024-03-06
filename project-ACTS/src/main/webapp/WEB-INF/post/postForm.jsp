<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="model.*"%>
<% User user = (User)request.getAttribute("user"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<script
	src="https://cdn.ckeditor.com/ckeditor5/30.0.0/classic/ckeditor.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<title>등록</title>

<!-- 넓이 높이 조절 -->
<style>
.ck.ck-editor {
	max-width: 500px;
}

.ck-editor__editable {
	min-height: 300px;
}
body {
  padding-bottom: 30px;

}

</style>
<script>
function postCreate() {
	if (form.title.value == "") {
		alert("제목을 입력하십시오.");
		form.title.focus();
		return false;
	} 
	if (form.price.value == "") {
		alert("가격을 입력하십시오.");
		form.price.focus();
		return false;
	}
	
	if (form.content_textarea.value == "") {
		alert("내용을 입력하십시오.");
		form.content_textarea.focus();
		return false;
	}
	
	form.submit();
}

</script>
</head>
<body>
<%@include file="/WEB-INF/navbar.jsp" %>
<div class="container">
<br><br>

  <!-- Content here -->

<h3>작성하기</h3>

	<!-- title -->
	<form name="form" method="POST" action="<c:url value='/post/upload' />" enctype="multipart/form-data">
		<div class="mb-3">
			<label for="exampleFormControlInput1" class="form-label">Title</label>
			<br> <input name="title" class="form-control  input-sm"
				id="exampleFormControlInput1" placeholder="제목을 입력하세요."
				aria-label=".form-control-lg example">
		</div>

		<!-- seller or buyer -->
		<label for="exampleFormControlInput1" class="form-label">acts 타입</label>
		<div class="form-check">
			<input class="form-check-input" type="radio" name="pType"
				id="flexRadioDefault1" value="s" checked> <label class="form-check-label"
				> seller </label>
		</div>
		<div class="form-check">
			<input class="form-check-input" type="radio" name="pType"
				id="flexRadioDefault2" value="b" > <label
				class="form-check-label"> buyer </label>
		</div>
		
		<!-- 게시글 status -->
		<label for="exampleFormControlInput1" class="form-label">상태</label>
		<br>
		<select name="status" style="width: 240">
					<option value="available">거래 가능</option>
					<option value="ongoing">거래 중</option>
					<option value="completed">거래 완료</option>
		</select>
		
		<!-- 게시글 카테고리 -->
		<br>
		<label for="exampleFormControlInput1" class="form-label">카테고리</label>
		<br>
		<select name="categoryId" style="width: 240">
					<option value="1">디자인</option>
					<option value="2">IT</option>
					<option value="3">문서</option>
					<option value="4">기타</option>
		</select>
		
		<!-- 가격 -->
		<div class="mb-3">
			<label for="exampleFormControlInput1" class="form-label">가격</label>
			<br> <input name="price" class="form-control  input-sm"
				id="exampleFormControlInput1"
				aria-label=".form-control-lg example">원
		</div>	
		
	
		<!-- 게시글 내용 -->
		<div>
			<div>
				<textarea name="description" id="content_textarea" cols="100" rows="20"></textarea>
			</div>
		</div>
		
		<!--  이미지 업로드 -->
	<input type="file" id="image" accept="image/*"
		onchange="setThumbnail(event);" name="image" />
	
	<div id="image_container"></div>
	<script>
		function setThumbnail(event) {
			var reader = new FileReader();
			reader.onload = function(event) {
				var img = document.createElement("img");
				img.setAttribute("src", event.target.result);
				document.querySelector("div#image_container").appendChild(img);
			};
			reader.readAsDataURL(event.target.files[0]);
		}
	</script>
	
	<!-- 등록버튼 -->
	
	<br><br>
	<input type="button" value="등록" onClick="postCreate()"
			class="btn btn-primary">
			</form>
			
</div>		
</body>
</html>


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	Post post = (Post)request.getAttribute("post");	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 작성</title>

<script>
function reviewCreate() {
	if (form.content.value == "") {
		alert("내용을 입력하십시오.");
		form.content.focus();
		return false;
	} 
	if (form.score.value == "") {
		alert("점수를 선택하십시오.");
		form.score.focus();
		return false;
	}

	form.submit();
}

</script>

</head>
<body>
<%@include file="/WEB-INF/navbar.jsp" %>
	<h2>이용 후기 등록</h2>
	<h3>포스트 이름: <%= post.getTitle() %></h3>
	<form name="form" method="POST" action="<c:url value='/review/upload' />" >
		<input type="hidden" name="postId" value="${post.postId}" />
		<!-- 리뷰 스코어 -->
		<br>
		<select name="score" style="width: 240">
					<option value="1">★</option>
					<option value="2">★★</option>
					<option value="3">★★★</option>
					<option value="4">★★★★</option>
					<option value="5">★★★★★</option>
		</select>

		<!-- 리뷰 내용 -->
		<div>
			<div>
				<textarea name="content" id="content_textarea" cols="100" rows="20"></textarea>
			</div>
		</div>

	<!-- 등록버튼 -->
	<br><br>
	<input type="button" value="등록" onClick="reviewCreate()"
			class="btn btn-primary">
	</form>
</body>
</html>
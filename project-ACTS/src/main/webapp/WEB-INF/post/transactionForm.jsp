<%@page contentType="text/html; charset=utf-8"%>
<%@page import="model.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	Post post = (Post)request.getAttribute("post");
	String nickName = (String)request.getAttribute("nickname");

%>
<html>
<head>
<title>문의하기</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/user.css' />"
	type="text/css">
<script>
function transactionCreate() {
	if (form.title.value == "") {
		alert("제목을 입력하십시오.");
		form.title.focus();
		return false;
	} 
	if (form.transContents.value == "") {
		alert("내용을 입력하십시오.");
		form.price.focus();
		return false;
	}
	
	form.submit();
}

</script>
</head>
<body>
<%@include file="/WEB-INF/navbar.jsp" %>
<div class="container">
<div class="m-3"> 주문하기 </div>

  <!-- Content here -->
  <hr class="my-2 m-3">
<form name="form" method="POST" action="<c:url value='/post/transaction' />">
<input type="hidden" name="postId" value="${post.postId}">
<input type="hidden" name="writerId" value="${post.writerId}">
<div class="mb-3 m-3">
  <label for="exampleFormControlInput1" class="form-label">제목</label>
  <input type="text" name="transTitle" class="form-control" id="exampleFormControlInput1" placeholder="제목을 입력하세요.">
</div>
<div class="mb-3 m-3">
  <label for="exampleFormControlTextarea1" class="form-label">요청 내용</label>
  <textarea name="transContents" class="form-control" id="exampleFormControlTextarea1" rows="20" placeholder="문의 내용을 입력하세요."></textarea>
</div>
<br><input type="button" value="주문하기" class="btn btn-secondary" onClick="transactionCreate()"/>
</form>

<c:if test="${trasactionCreateFailed}">
	    	<script>
				alert("내가 작성한 글은 주문할 수 없습니다.");
			</script>
 </c:if>
 </div>
</body>
</html>
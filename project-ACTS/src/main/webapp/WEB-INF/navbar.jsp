
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*, model.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">

<style>
i {
	font-size: 2rem;
	color: white;
}
#headLineText {
	padding-left: 20px;
	padding-top: 5px;
	color: white;
	font-size: 30px;
	font-weight: bold;
	font-family: 'IBM Plex Sans KR', sans-serif;
}

body {
	font-family: 'IBM Plex Sans KR', sans-serif;
}


</style>

</head>

<body>
	<nav class="navbar navbar-light" style="background-color: #b5cda3;">
		<div class="container-fluid">
			<a class="navbar-brand" href="<c:url value='/comm/main'></c:url>" id="headLineText" align="left">
				Acts <img src="<c:url value='/images/sprout2.png' />" border="0"
				width="45" height="30" />
			</a>
			<div align="right" id="font">
				<c:if test="${accountId eq null}">
					<a href="<c:url value='/user/login/form' />" class="btn btn-light"
						role="button">LOGIN</a>
				</c:if>
				<c:if test="${accountId ne null}">
					<a href="<c:url value='/user/logout' />" class="btn btn-light"
						role="button">LOGOUT</a>
				</c:if>
				
				<a href="<c:url value='/user/myPage' />">
				<img src="<c:url value='/images/mypageIcon.GIF' />" /></a>


			</div>
		</div>
	</nav>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*, model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- mobile metas -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="viewport" content="initial-scale=1, maximum-scale=1">

<title>Acts On</title>

<!-- bootstrap css -->
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/bootstrap.min.css' />">
<!-- style css -->
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/style.css' />">
<!-- Responsive-->
<link rel="stylesheet" href="<c:url value='/css/responsive.css' />">
<!-- Scrollbar Custom CSS -->
<link rel="stylesheet"
	href="<c:url value='/css/jquery.mCustomScrollbar.min.css' />">
<!-- Tweaks for older IEs-->
<link rel="stylesheet"
	href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">
<!-- fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Poppins:400,700&display=swap"
	rel="stylesheet">
<!-- font awesome -->
<link rel="stylesheet" type="text/css"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<!-- owl stylesheets -->
<link
	href="https://fonts.googleapis.com/css?family=Great+Vibes|Poppins:400,700&display=swap&subset=latin-ext"
	rel="stylesheet">
<link rel="stylesheet"
	href="<c:url value='/css/owl.carousel.min.css' />">
<link rel="stylesoeet"
	href="<c:url value='/css/owl.theme.default.min.css' />">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css"
	media="screen">


<!-- Javascript files-->
<script src="<c:url value='/js/jquery.min.js' />"></script>
<script src="<c:url value='/js/popper.min.js' />"></script>
<script src="<c:url value='/js/bootstrap.bundle.min.js' />"></script>
<script src="<c:url value='/js/jquery-3.0.0.min.js' />"></script>
<script src="<c:url value='/js/plugin.js' />"></script>
</head>
<body>
	<!-- banner bg main start -->
	<div class="banner_bg_main">
		<!-- header top section start -->
		<!-- logo section start -->
		<div class="logo_section">
			<div class="container">
				<div class="row">
					<div class="col-sm-12">
						<div class="logo">
							<a href="<c:url value='/comm/main' />"> <img
								src="<c:url value='/images/logo.png' />"></a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- logo section end -->
		<!-- header section start -->
		<div class="header_section">
			<div class="container">
				<div class="containt_main">
					<div class="dropdown">
						<button class="btn btn-secondary dropdown-toggle" type="button"
							id="dropdownMenuButton" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false">모든 카테고리</button>
						<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
							<a class="dropdown-item" href="<c:url value='/comm/buyerPage' />">의뢰
								요청</a> <a class="dropdown-item"
								href="<c:url value='/comm/search/list'>
		                <c:param name='categoryName' value='디자인'/></c:url>">디자인</a>
							<a class="dropdown-item"
								href="<c:url value='/comm/search/list'>
		                <c:param name='categoryName' value='IT'/></c:url>">IT</a>
							<a class="dropdown-item"
								href="<c:url value='/comm/search/list'>
		                <c:param name='categoryName' value='문서'/></c:url>">문서</a>
							<a class="dropdown-item"
								href="<c:url value='/comm/search/list'>
		                <c:param name='categoryName' value='ETC'/></c:url>">기타</a>
						</div>
					</div>
					<div class="main">
						<form id="searchKeywordForm" method="POST" action="<c:url value='/post/search' />">
							<!-- Another variation with a button -->
							<div class="input-group">
								<input type="text" name="searchKeyword" class="form-control"
									placeholder="찾고 싶은 게시물을 검색하세요">
								<div class="input-group-append">
									<button class="btn btn-secondary"
										style="background-color: #b5cda3; border-color: #b5cda3">
										<i class="fa fa-search"></i>
									</button>
								</div>
							</div>
						</form>

					</div>
					<div class="header_box">
						<div class="login_menu">
							<ul>
								<li><c:if test="${accountId eq null}">
										<a href="<c:url value='/user/login/form' />">LOGIN</a>
									</c:if> <c:if test="${accountId ne null}">
										<a href="<c:url value='/user/logout' />">LOGOUT</a>
									</c:if></li>
								<li></li>
								<li><a href="<c:url value='/user/myPage' />"> <i
										class="fa fa-user" aria-hidden="true"></i><span
										class="padding_5">Mypage</span></a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- header section end -->
		<!-- banner section start -->
		<div class="banner_section layout_padding">
			<div class="container">
				<div id="my_slider" class="carousel slide" data-ride="carousel">
					<div class="carousel-inner">
						<div class="carousel-item active">
							<div class="row">
								<div class="col-sm-12">
									<h1 class="banner_taital">
										당신만을 위한 <br>외주를 찾아보세요
									</h1>
									<div class="buynow_bt">
										<a href="<c:url value='/comm/search/list'>
		                <c:param name='categoryName' value='디자인'/></c:url>">디자인 외주 보기</a>
									</div>
								</div>
							</div>
						</div>
						<div class="carousel-item">
							<div class="row">
								<div class="col-sm-12">
									<h1 class="banner_taital">
										당신만이 할 수 있는 <br>외주 의뢰를 찾아보세요
									</h1>
									<div class="buynow_bt">
										<a href="<c:url value='/comm/buyerPage' />">외주 요청 글 보기</a>
									</div>
								</div>
							</div>
						</div>
					</div>
					<a class="carousel-control-prev" href="#my_slider" role="button"
						data-slide="prev"> <i class="fa fa-angle-left"></i>
					</a> <a class="carousel-control-next" href="#my_slider" role="button"
						data-slide="next"> <i class="fa fa-angle-right"></i>
					</a>
				</div>
			</div>
		</div>
		<!-- banner section end -->
	</div>
	<!-- banner bg main end -->
	
	<!-- image post list start-->

	<jsp:include page="/WEB-INF/post/postListImg.jsp"></jsp:include>
	
	<!-- image post list end-->
	
	<!-- footer section start -->
	<div class="footer_section layout_padding">
		<div class="container">
			<div class="footer_logo">
				<img src="<c:url value='/images/footer-logo.png' />">
			</div>
			<div class="location_main">
				from. <a href="https://www.dongduk.ac.kr/kor/main.do">동덕여대 데이터베이스 프로그래밍 팀프로젝트</a>
			</div>
		</div>
	</div>
	<!-- footer section end -->
	<!-- copyright section start -->
	<div class="copyright_section">
		<div class="container">
			<p class="copyright_text">
				Copyright © Your Website 2021</a>
			</p>
		</div>
	</div>
	<!-- copyright section end -->
	

	<!-- post create button -->
	<div style="position: fixed; bottom: 5px; right: 5px;" id="i-color">
		<a href="<c:url value='/post/upload/form'></c:url>" class="btn btn-light" role="button" >
		<i class="bi bi-plus-circle-fill">&nbsp; 게시글 올리기</i></a>
	</div>


</body>
</html>
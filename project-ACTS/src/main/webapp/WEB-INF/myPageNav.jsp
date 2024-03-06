
<%@page import="model.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>

<link
   href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
   rel="stylesheet"
   integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
   crossorigin="anonymous">
<link rel="stylesheet"
   href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">

</head>

<body>
  <!-- Content here -->

   <nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
       <li class="nav-item">
          <a class="nav-link" href="<c:url value='/user/myInfo'>
          								<c:param name='user' value='${user}'/>
          							</c:url>">Info</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="<c:url value='/user/update'>
          								<c:param name='user' value='${user}'/>
          							</c:url>">UserUpdate</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="<c:url value='/user/myPost'>
      									<c:param name='userId' value='${user.userId}'/>
      								</c:url>">MyPost</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="<c:url value='/user/myPage/myBuyerTransaction'>
      									<c:param name='userId' value='${user.userId}'/>
      								</c:url>">MyTransaction</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="<c:url value='/user/followingList' />">Favorite</a>
        </li>
      </ul>
    </div>
  </div>
</nav>

</body>
</html>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<tiles:importAttribute scope="request" />
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title><spring:message code="frontend.title" /></title>

<!-- Bootstrap Core CSS -->
<link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- MetisMenu CSS -->
<link
	href="${pageContext.request.contextPath}/resources/metisMenu/css/metisMenu.min.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link href="${pageContext.request.contextPath}/resources/modern-business/modern-business.css" rel="stylesheet">

<!-- Custom CSS -->
<link
	href="${pageContext.request.contextPath}/resources/sb-admin/css/sb-admin-2.css"
	rel="stylesheet">

 <!-- Custom Fonts -->
 <link href="${pageContext.request.contextPath}/resources/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

<!-- Pagination CSS -->
 <link href="${pageContext.request.contextPath}/resources/pagination/css/pagination.css" rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

<!-- jQuery Version 1.11.0 -->
<script
	src="${pageContext.request.contextPath}/resources/jquery/jquery-2.1.0.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
	<script
		src="${pageContext.request.contextPath}/resources/metisMenu/js/metisMenu.min.js"></script>

<!-- Pagination -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/pagination/js/jquery.pajinate.js"></script>

<!-- Custom Theme JavaScript -->
	<script
		src="${pageContext.request.contextPath}/resources/sb-admin/js/sb-admin-2.js"></script>

</head>

<body>

	<!-- Navigation -->
	<tiles:insertAttribute name="menu" />
	
	<!-- Page Content -->
	<div class="container">

		<tiles:insertAttribute name="body" />

	</div>
	<!-- /.container -->

	<div class="container">

		<hr>
		<!-- Footer -->
		<tiles:insertAttribute name="footer" />

	</div>
	<!-- /.container -->

</body>

</html>
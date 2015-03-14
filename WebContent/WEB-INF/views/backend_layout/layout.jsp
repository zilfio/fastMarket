<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<tiles:importAttribute scope="request" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title><spring:message code="backend.title" /></title>

<!-- Bootstrap Core CSS -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css" />

<!-- MetisMenu CSS -->
<link
	href="${pageContext.request.contextPath}/resources/metisMenu/css/metisMenu.min.css"
	rel="stylesheet">

<!-- DataTables CSS -->
<link
	href="${pageContext.request.contextPath}/resources/datatables/css/dataTables.bootstrap.css"
	rel="stylesheet">

<!-- Select2 CSS -->
<link
	href="${pageContext.request.contextPath}/resources/select2/select2.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/select2/select2-bootstrap.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link
	href="${pageContext.request.contextPath}/resources/sb-admin/css/sb-admin-2.css"
	rel="stylesheet">

<!-- Custom Fonts -->
<link
	href="${pageContext.request.contextPath}/resources/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
	
<!-- Datepicker CSS -->
<link
	href="${pageContext.request.contextPath}/resources/datepicker/css/datepicker.css"
	rel="stylesheet" type="text/css">

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

<script	src="${pageContext.request.contextPath}/resources/datatables/my.js"></script>

<!-- DataTables JavaScript -->
<script
	src="${pageContext.request.contextPath}/resources/datatables/js/jquery.dataTables.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/datatables/js/dataTables.bootstrap.js"></script>

<!-- Chosen JavaScript -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/select2/select2.min.js"></script>

<!-- Chosen JavaScript -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/ckeditor/ckeditor.js"></script>

<!-- Custom Theme JavaScript -->
<script
	src="${pageContext.request.contextPath}/resources/sb-admin/js/sb-admin-2.js"></script>

<!-- Datepicker - Bootstrap -->
<script src="${pageContext.request.contextPath}/resources/datepicker/js/bootstrap-datepicker.js"></script>

</head>

<body>

	<div id="wrapper">

		<!-- Navigation -->
		<tiles:insertAttribute name="menu" />

		<div id="page-wrapper">
			<tiles:insertAttribute name="body" />
		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->

</body>

</html>
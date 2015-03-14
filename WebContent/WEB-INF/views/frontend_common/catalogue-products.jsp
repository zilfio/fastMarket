<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" charset="utf-8">
		$(document).ready(function() {
			$('#products-container').pajinate({
    	    	items_per_page : 8,
    	    	nav_label_first : 'Inizio',
    	    	nav_label_prev : 'Precedente',
    	    	nav_label_next : 'Successivo',
    	    	nav_label_last : 'Fine'
    	    });
		});
</script>
<div class="row">
	<c:choose>
		<c:when test="${requestScope.search}">
			<div class="col-lg-12">
				<h1 class="page-header">
					${requestScope.title}
				</h1>
				<ol class="breadcrumb">
					<li><a href="${pageContext.request.contextPath}"><spring:message code="frontend.menu.home" /></a></li>
					<li><a href="${pageContext.request.contextPath}/catalogue-products"><spring:message code="frontend.menu.catalogueProducts" /></a></li>
					<li class="active">${requestScope.title}</li>
				</ol>
			</div>
		</c:when>
		<c:otherwise>
			<div class="col-lg-12">
				<h1 class="page-header">
					<spring:message code="frontend.menu.catalogueProducts" />
				</h1>
				<ol class="breadcrumb">
					<li><a href="${pageContext.request.contextPath}"><spring:message code="frontend.menu.home" /></a></li>
					<li class="active"><spring:message code="frontend.menu.catalogueProducts" /></li>
				</ol>
			</div>
		</c:otherwise>
	</c:choose>
</div>
<!-- /.row -->

<div class="row">

	<div class="col-md-3">

		<!-- Blog Search Well -->
		<div class="well">
			<h4>Cerca</h4>
			<form action="${pageContext.request.contextPath}/catalogue-products/base_search" method="get">
				<div class="input-group">
					<input type="text" class="form-control" name="name"> <span class="input-group-btn">
						<button class="btn btn-default" type="submit">
							<i class="fa fa-search"></i>
						</button>
					</span>
				</div>
			</form>
		</div>

		<!-- Blog Categories Well -->
		<c:if test="${not empty requestScope.categories}">
		<div class="well">
			<h4>Categorie</h4>
			<div class="row">
				<div class="col-lg-12">
					<ul class="list-unstyled">
						<c:forEach items="${requestScope.categories}" var="category">
							<li><a href="${pageContext.request.contextPath}/catalogue-products/search_by_category?category=${category.id}">${category.name}</a></li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<!-- /.row -->
		</div>
		</c:if>
	</div>

	<!-- Blog Post Content Column -->
	<div class="col-lg-9">


		<div class="col-lg-12">
			<div class="well">
				<div class="row">
					<form method="get" action="${pageContext.request.contextPath}/catalogue-products/advanced_search">
					<div class="col-lg-3">
						<div class="form-group">
							<label for="brand"><spring:message code="product.brand" /></label>
							<select class="form-control" name="brand">
								<option value="0">Tutte le marche</option>
								<c:forEach items="${requestScope.brands}" var="brand">
									<option value="${brand.id}">${brand.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label for="brand"><spring:message code="product.category" /></label>
							<select class="form-control" name="category">
								<option value="0">Tutte le categorie</option>
								<c:forEach items="${requestScope.categories}" var="category">
									<option value="${category.id}">${category.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label for="brand"><spring:message code="grocery.intoleranceCategory" /></label>
							<select class="form-control" name="intoleranceCategory">
								<option value="0">Tutte le categorie</option>
								<c:forEach items="${requestScope.intoleranceCategories}" var="intoleranceCategory">
									<option value="${intoleranceCategory.id}">${intoleranceCategory.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group" style="margin-top: 25px;">
							<button type="submit" class="btn btn-default">
								<spring:message code="common.search" />
							</button>
						</div>
					</div>
					</form>
				</div>
			</div>
		</div>

		<c:choose>
		<c:when test="${not empty requestScope.products}">
			<div id="products-container" class="container1">
			<c:forEach items="${requestScope.products}" var="product">
			<div class="content">
			<div class="col-md-3 col-sm-6">
				<div class="panel panel-default text-center">
					<div class="panel-heading">
						<span class="fa-stack fa-5x" style="width: auto; height: auto; line-height: normal;"> 
							<c:choose>
								<c:when test="${not empty product.path}">
									<img src="${pageContext.request.contextPath}/resources/${product.path}" style="max-height: 85px; overflow: hidden;" />
								</c:when>
								<c:otherwise>
									<img src="${pageContext.request.contextPath}/resources/images/frontend/default.jpg" style="max-height: 85px; overflow: hidden;" />
								</c:otherwise>
							</c:choose>
						</span>
					</div>
					<div class="panel-body">
						<h4 class="pull-right">${product.price} &euro;</h4>
					</div>
					<div>
						<h4><a href="${pageContext.request.contextPath}/catalogue-products/detail_product?product=${product.id}">${product.name}</a></h4>
					</div>
				</div>
			</div>
			</div>
			</c:forEach>
				<div style="clear: both;"></div>
				<div class="page_navigation" style="text-align: center; margin-top: 10px; margin-bottom: 10px;"></div>
			</div>
		</c:when>
		<c:otherwise>
			<div class="col-lg-12">
				<div class="alert alert-warning" role="alert"><spring:message code="frontend.menu.catalogueProducts.noResult" />!</div>
			</div>
		</c:otherwise>
		</c:choose>
	</div>

</div>
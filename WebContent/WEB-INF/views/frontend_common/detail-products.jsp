<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">
			<spring:message code="frontend.menu.detailProducts" />
		</h1>
		<ol class="breadcrumb">
			<li><a href="${pageContext.request.contextPath}"><spring:message code="frontend.menu.home" /></a></li>
			<li><a href="${pageContext.request.contextPath}/catalogue-products"><spring:message code="frontend.menu.catalogueProducts" /></a></li>
			<li class="active"><spring:message code="frontend.menu.detailProducts" /></li>
		</ol>
	</div>
</div>

<!-- Portfolio Item Row -->
<div class="row">
	<c:choose>
		<c:when test="${not empty requestScope.product}">
		<div class="col-md-3">
			<span class="fa-stack fa-5x"> 
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
	
		<div class="col-md-9">
				<h2>${product.name}</h2>
				<c:if test="${not empty product.description}">
					<h3>${product.description}</h3>
				</c:if>
				<h3>Prezzo: ${product.price}</h3>
				<h3>Quantità: ${product.stock}</h3>
				<c:choose>
					<c:when test="${requestScope.product.getClass().name == 'it.univaq.mwt.fastmarket.business.model.NonGrocery'}">
						<h3>Tipo di utilizzo: ${product.scopeOfUse}</h3>
					</c:when>
					<c:when test="${requestScope.product.getClass().name == 'it.univaq.mwt.fastmarket.business.model.Grocery'}">
						<h3>Data di scadenza: ${product.expirationDate}</h3>
						<h3>Prezzo al Kg: ${product.pricePerKg}</h3>
						<h3>Prezzo al Lt: ${product.pricePerLt}</h3>
					</c:when>
				</c:choose>
		</div>
		</c:when>
		<c:otherwise>
			<div class="col-md-12">
				<div class="alert alert-danger" role="alert">Nessun prodotto trovato!</div>
			</div>
		</c:otherwise>
	</c:choose>
</div>
<!-- /.row -->
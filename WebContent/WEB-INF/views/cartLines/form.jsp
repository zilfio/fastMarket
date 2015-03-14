<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		$("select").select2();
	});
</script>
<c:if test="${requestScope.delete}">
	<script type="text/javascript" charset="utf-8">
		$(document).ready(function() {
			$("select").select2("enable", false);
			$(":input[type='text'],textarea").each(function() {
				$(this).attr('disabled', 'disabled');
			});
		});
	</script>
</c:if>
<div class="row">
	<div class="col-lg-12">
		<c:choose>
			<c:when test="${requestScope.create}">
				<h1 class="page-header">
					<spring:message code="cartline.heading.create" />
				</h1>
			</c:when>
			<c:when test="${requestScope.update}">
				<h1 class="page-header">
					<spring:message code="cartline.heading.update" />
				</h1>
			</c:when>
			<c:when test="${requestScope.delete}">
				<h1 class="page-header">
					<spring:message code="cartline.heading.delete" />
				</h1>
			</c:when>
		</c:choose>
	</div>
</div>
<form:form modelAttribute="cartLine"
	action="${pageContext.request.contextPath}${requestScope.action}"
	method="POST">
	<form:hidden path="id" />
	<div class="form-group">
		<label for="product"><spring:message code="cartline.product" /></label>
		<span class="glyphicon glyphicon-ok-circle"></span>
		<div>
			<form:select path="product.id" id="product" cssClass="form-control" autofocus="true">
				<form:options items="${products}" itemLabel="name" itemValue="id" />
			</form:select>
			<form:errors path="product" cssClass="alert-danger" />
		</div>
	</div>
	<div class="form-group">
		<label for="quantity"><spring:message code="cartline.quantity" /></label>
		<span class="glyphicon glyphicon-ok-circle"></span>
		<form:input path="quantity" cssClass="form-control" />
		<form:errors path="quantity" cssClass="alert-danger" />
	</div>
	<div class="form-group">
		<label for="cart"><spring:message code="cartline.cart" /></label>
		<span class="glyphicon glyphicon-ok-circle"></span>
		<div>
			<form:select path="cart.id" id="cart" cssClass="form-control">
				<form:options items="${carts}" itemLabel="name" itemValue="id" />
			</form:select>
			<form:errors path="cart" cssClass="alert-danger" />
		</div>
	</div>
	<div class="form-group">
		<c:choose>
			<c:when test="${!requestScope.delete}">
				<button type="submit" class="btn btn-primary">
					<spring:message code="common.submit" />
				</button>
			</c:when>
			<c:otherwise>
				<button type="submit" class="btn btn-danger">
					<spring:message code="common.delete" />
				</button>
			</c:otherwise>
		</c:choose>
	</div>
</form:form>
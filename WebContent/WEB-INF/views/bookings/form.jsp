<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		$("select").select2();
		$(".datepicker").datepicker({format:"dd/mm/yyyy"});
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
					<spring:message code="booking.heading.create" />
				</h1>
			</c:when>
			<c:when test="${requestScope.update}">
				<h1 class="page-header">
					<spring:message code="booking.heading.update" />
				</h1>
			</c:when>
			<c:when test="${requestScope.delete}">
				<h1 class="page-header">
					<spring:message code="booking.heading.delete" />
				</h1>
			</c:when>
		</c:choose>
	</div>
</div>
<form:form modelAttribute="booking"
	action="${pageContext.request.contextPath}${requestScope.action}"
	method="POST">
	<form:hidden path="id" />
	<div class="form-group">
		<label for="creationDate"><spring:message code="booking.creationDate" /></label> 
		<span class="glyphicon glyphicon-ok-circle"></span>
		<form:input path="creationDate" cssClass="form-control datepicker" />
		<form:errors path="creationDate" cssClass="alert-danger" />
	</div>
	<div class="form-group">
		<label for="totalPrice"><spring:message code="booking.totalPrice" /></label>
		<span class="glyphicon glyphicon-ok-circle"></span>
		<form:input path="totalPrice" cssClass="form-control" />
		<form:errors path="totalPrice" cssClass="alert-danger" />
	</div>
	<div class="form-group">
		<label for="cart"><spring:message code="booking.cart" /></label>
		<span class="glyphicon glyphicon-ok-circle"></span>
		<div>
			<form:select path="cart.id" id="cart" cssClass="form-control">
				<form:options items="${carts}" itemLabel="name" itemValue="id" />
			</form:select>
			<form:errors path="cart" cssClass="alert-danger" />
		</div>
	</div>
	<div class="form-group">
		<label for="expirationDate"><spring:message code="booking.expirationDate" /></label>
		<span class="glyphicon glyphicon-ok-circle"></span>
		<form:input path="expirationDate" cssClass="form-control datepicker" />
		<form:errors path="expirationDate" cssClass="alert-danger" />
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
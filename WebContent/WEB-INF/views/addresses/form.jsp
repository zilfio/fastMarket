<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${requestScope.delete}">
	<script type="text/javascript" charset="utf-8">
		$(document).ready(function() {
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
					<spring:message code="address.heading.create" />
				</h1>
			</c:when>
			<c:when test="${requestScope.update}">
				<h1 class="page-header">
					<spring:message code="address.heading.update" />
				</h1>
			</c:when>
			<c:when test="${requestScope.delete}">
				<h1 class="page-header">
					<spring:message code="address.heading.delete" />
				</h1>
			</c:when>
		</c:choose>
	</div>
</div>
<form:form modelAttribute="address"
	action="${pageContext.request.contextPath}${requestScope.action}?id_user=${id_user}"
	cssClass="form-horizontal" method="POST">
	<form:hidden path="id" />
	<div class="form-group">
		<label for="street"><spring:message code="address.street" /></label> 
		<span class="glyphicon glyphicon-ok-circle"></span>
		<form:input path="street" cssClass="form-control" autofocus="true" />
		<form:errors path="street" cssClass="alert-danger" />
	</div>
	<div class="form-group">
		<label for="streetNumber"><spring:message code="address.streetNumber" /></label> 
		<span class="glyphicon glyphicon-ok-circle"></span>
		<form:input path="streetNumber" cssClass="form-control" />
		<form:errors path="streetNumber" cssClass="alert-danger" />
	</div>
	<div class="form-group">
		<label for="country"><spring:message code="address.country" /></label> 
		<span class="glyphicon glyphicon-ok-circle"></span>
		<form:input path="country" cssClass="form-control" />
		<form:errors path="country" cssClass="alert-danger" />
	</div>
	<div class="form-group">
		<label for="district"><spring:message code="address.district" /></label>
		<span class="glyphicon glyphicon-ok-circle"></span>
		<div>
			<form:select path="district.id" id="district" cssClass="form-control">
				<form:options items="${districts}" itemLabel="name" itemValue="id" />
			</form:select>
			<form:errors path="district" cssClass="alert-danger" />
		</div>
	</div>
	<div class="form-group">
		<label for="zipCode"><spring:message code="address.zipCode" /></label> 
		<span class="glyphicon glyphicon-ok-circle"></span>
		<form:input path="zipCode" cssClass="form-control" />
		<form:errors path="zipCode" cssClass="alert-danger" />
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
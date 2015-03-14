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
					<spring:message code="cart.heading.create" />
				</h1>
			</c:when>
			<c:when test="${requestScope.update}">
				<h1 class="page-header">
					<spring:message code="cart.heading.update" />
				</h1>
			</c:when>
			<c:when test="${requestScope.delete}">
				<h1 class="page-header">
					<spring:message code="cart.heading.delete" />
				</h1>
			</c:when>
		</c:choose>
	</div>
</div>
<form:form modelAttribute="cart"
	action="${pageContext.request.contextPath}${requestScope.action}"
	cssClass="form-horizontal" method="POST">
	<form:hidden path="id" />
	<div class="form-group">
		<label for="name"><spring:message code="cart.name" /></label>
		<span class="glyphicon glyphicon-ok-circle"></span>
		<form:input path="name" cssClass="form-control" autofocus="true" />
		<form:errors path="name" cssClass="alert-danger" />
	</div>
	<div class="form-group">
		<label for="user"><spring:message code="cart.user" /></label>
		<span class="glyphicon glyphicon-ok-circle"></span>
		<div>
			<form:select path="user.id" id="user" cssClass="form-control">
				<form:options items="${users}" itemLabel="username" itemValue="id" />
			</form:select>
			<form:errors path="user" cssClass="alert-danger" />
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
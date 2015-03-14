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
					<spring:message code="province.heading.create" />
				</h1>
			</c:when>
			<c:when test="${requestScope.update}">
				<h1 class="page-header">
					<spring:message code="province.heading.update" />
				</h1>
			</c:when>
			<c:when test="${requestScope.delete}">
				<h1 class="page-header">
					<spring:message code="province.heading.delete" />
				</h1>
			</c:when>
		</c:choose>
	</div>
</div>
<form:form modelAttribute="province"
	action="${pageContext.request.contextPath}${requestScope.action}"
	cssClass="form-horizontal" method="POST">
	<form:hidden path="id" />
	<div class="form-group">
		<label for="name"><spring:message code="province.name" /></label> 
		<span class="glyphicon glyphicon-ok-circle"></span>
		<form:input path="name" cssClass="form-control" autofocus="true" />
		<form:errors path="name" cssClass="alert-danger" />
	</div>
	<div class="form-group">
		<label for="abbreviation"><spring:message code="province.abbreviation" /></label> 
		<span class="glyphicon glyphicon-ok-circle"></span>
		<form:input path="abbreviation" cssClass="form-control" />
		<form:errors path="abbreviation" cssClass="alert-danger" />
	</div>
	<div class="form-group">
		<label for="region"><spring:message code="province.region" /></label>
		<div>
			<form:select path="region.id" id="region" cssClass="form-control">
				<form:options items="${regions}" itemLabel="name" itemValue="id" />
			</form:select>
			<form:errors path="region" cssClass="alert-danger" />
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
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		$("select").select2();
		CKEDITOR.replace('ckeditor');
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
					<spring:message code="systeminformation.heading.create" />
				</h1>
			</c:when>
			<c:when test="${requestScope.update}">
				<h1 class="page-header">
					<spring:message code="systeminformation.heading.update" />
				</h1>
			</c:when>
			<c:when test="${requestScope.delete}">
				<h1 class="page-header">
					<spring:message code="systeminformation.heading.delete" />
				</h1>
			</c:when>
		</c:choose>
	</div>
</div>
<form:form modelAttribute="systemInformation"
	action="${pageContext.request.contextPath}${requestScope.action}"
	method="POST">
	<form:hidden path="id" />
	<div class="form-group">
		<label for="name"><spring:message
				code="systeminformation.name" /></label> <span
			class="glyphicon glyphicon-ok-circle"></span>
		<form:input path="name" cssClass="form-control" />
		<form:errors path="name" cssClass="alert-danger" />
	</div>
	<div class="form-group">
		<label for="description"><spring:message
				code="systeminformation.description" /></label>
		<span class="glyphicon glyphicon-ok-circle"></span>
		<form:textarea path="description" cssClass="form-control"
			id="ckeditor" />
		<form:errors path="description" cssClass="alert-danger" />
	</div>
	<div class="form-group">
		<label for="creationDate"><spring:message
				code="systeminformation.creationDate" /></label>
		<span class="glyphicon glyphicon-ok-circle"></span>
		<form:input path="creationDate" cssClass="form-control datepicker" />
		<form:errors path="creationDate" cssClass="alert-danger" />
	</div>
	<div class="form-group">
		<label for="user"><spring:message
				code="systeminformation.user" /></label>
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
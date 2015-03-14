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
					<spring:message code="district.heading.create" />
				</h1>
			</c:when>
			<c:when test="${requestScope.update}">
				<h1 class="page-header">
					<spring:message code="district.heading.update" />
				</h1>
			</c:when>
			<c:when test="${requestScope.delete}">
				<h1 class="page-header">
					<spring:message code="district.heading.delete" />
				</h1>
			</c:when>
		</c:choose>
	</div>
</div>
<form:form modelAttribute="district"
	action="${pageContext.request.contextPath}${requestScope.action}"
	cssClass="form-horizontal" method="POST">
	<form:hidden path="id" />
	<div class="form-group">
		<label for="name"><spring:message code="district.name" /></label> 
		<span class="glyphicon glyphicon-ok-circle"></span>
		<form:input path="name" cssClass="form-control" autofocus="true" />
		<form:errors path="name" cssClass="alert-danger" />
	</div>
	<div class="form-group">
		<label for="province"><spring:message code="district.province" /></label>
		<span class="glyphicon glyphicon-ok-circle"></span>
		<div>
			<form:select path="province.id" id="province" cssClass="form-control">
				<form:options items="${provinces}" itemLabel="name" itemValue="id" />
			</form:select>
			<form:errors path="province" cssClass="alert-danger" />
		</div>
	</div>
	<div class="form-group">
		<label for="enableService"><spring:message code="district.enableService" /></label>
		<div class="checkbox">
	      	<label><form:checkbox path="enableService" />Attiva il servizio</label>
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
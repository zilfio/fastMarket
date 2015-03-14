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
			$(":input[type='text'],input[type='password'],textarea").each(function() {
				$(this).attr('disabled', 'disabled');
			});
		});
	</script>
</c:if>
<c:if test="${requestScope.update}">
	<script type="text/javascript" charset="utf-8">
		$(document).ready(function() {
			$('#username').attr('readonly','readonly');
		});
	</script>
</c:if>
<div class="row">
	<div class="col-lg-12">
		<c:choose>
			<c:when test="${requestScope.create}">
				<h1 class="page-header">
					<spring:message code="user.heading.create" />
				</h1>
			</c:when>
			<c:when test="${requestScope.update}">
				<h1 class="page-header">
					<spring:message code="user.heading.update" />
				</h1>
			</c:when>
			<c:when test="${requestScope.delete}">
				<h1 class="page-header">
					<spring:message code="user.heading.delete" />
				</h1>
			</c:when>
		</c:choose>
	</div>
</div>
<form:form modelAttribute="user"
	action="${pageContext.request.contextPath}${requestScope.action}"
	cssClass="form-horizontal" method="POST">
	<form:hidden path="id" />
	<div class="form-group">
		<label for="username"><spring:message code="user.username" /></label>
		<span class="glyphicon glyphicon-ok-circle"></span>
		<form:input path="username" id="username" cssClass="form-control" autofocus="true" />
		<form:errors path="username" cssClass="alert-danger" />
	</div>
	<div class="form-group">
		<label for="password"><spring:message code="user.password" /></label> <span
			class="glyphicon glyphicon-ok-circle"></span>
		<form:input type="password" path="password" cssClass="form-control"/>
		<form:errors path="password" cssClass="alert-danger" />
	</div>
	<div class="form-group">
		<label for="firstName"><spring:message code="user.firstName" /></label>
		<span class="glyphicon glyphicon-ok-circle"></span>
		<form:input path="firstName" cssClass="form-control" />
		<form:errors path="firstName" cssClass="alert-danger" />
	</div>
	<div class="form-group">
		<label for="middleName"><spring:message code="user.middleName" /></label>
		<form:input path="middleName" cssClass="form-control" />
		<form:errors path="middleName" cssClass="alert-danger" />
	</div>
	<div class="form-group">
		<label for="lastName"><spring:message code="user.lastName" /></label>
		<span class="glyphicon glyphicon-ok-circle"></span>
		<form:input path="lastName" cssClass="form-control" />
		<form:errors path="lastName" cssClass="alert-danger" />
	</div>
	<div class="form-group">
		<label for="gender"><spring:message code="user.gender" /></label>
		<div class="radio">
			<label class="radio-inline"><form:radiobutton path="gender" value="M" />M</label> 
			<label class="radio-inline"><form:radiobutton path="gender" value="F" />F</label>
		</div>
	</div>
	<div class="form-group">
		<label for="mobilePhone"><spring:message code="user.mobilePhone" /></label>
		<form:input path="mobilePhone" cssClass="form-control" />
	</div>
	<div class="form-group">
		<label for="telephone"><spring:message code="user.telephone" /></label>
		<form:input path="telephone" cssClass="form-control" />
	</div>
	<div class="form-group">
		<label for="email"><spring:message code="user.email" /></label>
		<span class="glyphicon glyphicon-ok-circle"></span>
		<form:input path="email" cssClass="form-control" />
		<form:errors path="email" cssClass="alert-danger" />
	</div>
	<div class="form-group">
		<label for="roles"><spring:message code="user.role" /></label> <span
			class="glyphicon glyphicon-ok-circle"></span>
		<c:choose>
			<c:when test="${not requestScope.update}">
				<form:select path="roles" cssClass="form-control" items="${roles}"
					itemLabel="name" itemValue="id" multiple="true">
				</form:select>
			</c:when>
			<c:when test="${requestScope.update}">
				<form:select path="roles" cssClass="form-control" multiple="true">
					<form:options selected="true" items="${rolesSel}" itemLabel="name" itemValue="id" />
					<form:options items="${rolesNotSel}" itemLabel="name" itemValue="id" />
				</form:select>
			</c:when>
		</c:choose>
		<form:errors path="roles" cssClass="alert-danger" />
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
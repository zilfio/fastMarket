<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">
			<spring:message code="userProfile.heading.update" />
		</h1>
	</div>
</div>
<form:form modelAttribute="user" 
action="${pageContext.request.contextPath}${requestScope.action}" 
cssClass="form-horizontal" method="POST">
	<form:hidden path="id" />
	<div class="form-group">
		<label for="username"><spring:message code="user.username" /></label>
		<span class="glyphicon glyphicon-ok-circle"></span>
		<form:input path="username" cssClass="form-control" readonly="true" />
		<form:errors path="username" cssClass="alert-danger" />
	</div>
	<div class="form-group">
		<label for="password"><spring:message code="user.password" /></label>
		<span class="glyphicon glyphicon-ok-circle"></span>
		<form:input type="password" path="password" cssClass="form-control" />
		<form:errors path="password" cssClass="alert-danger" />
	</div>
	<div class="form-group">
		<label for="firstName"><spring:message code="user.firstName" /></label>
		<span class="glyphicon glyphicon-ok-circle"></span>
		<form:input path="firstName" cssClass="form-control" autofocus="true" />
		<form:errors path="firstName" cssClass="alert-danger" />
	</div>
	<div class="form-group">
		<label for="middleName"><spring:message code="user.middleName" /></label>
		<form:input path="middleName" cssClass="form-control" />
		<form:errors path="lastName" cssClass="alert-danger" />
	</div>
	<div class="form-group">
		<label for="lastName"><spring:message code="user.lastName" /></label>
		<span class="glyphicon glyphicon-ok-circle"></span>
		<form:input path="lastName" cssClass="form-control" />
		<form:errors path="lastName" cssClass="alert-danger" />
	</div>
	<div class="form-group">
		<label for="gender"><spring:message code="user.gender" /></label>
		<span class="glyphicon glyphicon-ok-circle"></span>
		<div class="radio">
			<label class="radio-inline"><form:radiobutton path="gender" value="M" />M</label> 
			<label class="radio-inline"><form:radiobutton path="gender" value="F" />F</label>
			<form:errors path="gender" cssClass="alert-danger" />
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
	<div class="form-group" style="display: none;">
		<label for="roles"><spring:message code="user.role" /></label>
			<form:select path="roles" cssClass="form-control" multiple="true">
				<form:options selected="true" items="${roles}" itemLabel="name" itemValue="id" />
			</form:select>
	</div>
	<div class="form-group">
		<button type="submit" class="btn btn-primary">
			<spring:message code="common.submit" />
		</button>
	</div>
</form:form>
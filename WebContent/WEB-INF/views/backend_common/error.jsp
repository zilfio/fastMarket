<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<br />
<div class="alert alert-danger" role="alert">
	<spring:message code="common.error" />
	<hr />
	<c:if test="${not empty errorCause}">
		<p><strong><spring:message code="exceptions.cause" />: </strong>${errorCause}</p>
	</c:if>
	<c:if test="${not empty errorMessage}">
		<p><strong><spring:message code="exceptions.message" />: </strong>${errorMessage}</p>
	</c:if>
</div>
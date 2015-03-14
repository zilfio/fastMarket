<%@taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="container">
	<security:authorize access="!isAuthenticated()">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="login-panel panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title" style="text-align: center;">
							<spring:message code="frontend.menu.login.title" />
						</h3>
					</div>
					<div class="panel-body">
						<form role="form" name="loginform"
							action="${pageContext.request.contextPath}/j_spring_security_check"
							method="post">
							<fieldset>
								<div class="form-group">
									<input class="form-control" placeholder="Username"
										name="j_username" type="text" autofocus>
								</div>
								<div class="form-group">
									<input class="form-control" placeholder="Password"
										name="j_password" type="password" value="">
								</div>
								<button type="submit" class="btn btn-lg btn-success btn-block">
									<spring:message code="common.signin" />
								</button>
							</fieldset>
						</form>
					</div>
				</div>
			</div>
		</div>
	</security:authorize>
	<security:authorize access="isAuthenticated()">
		<br />
		<div class="alert alert-warning" role="alert">
			<spring:message code="frontend.menu.login.userLogged" />
			!
		</div>
	</security:authorize>
</div>

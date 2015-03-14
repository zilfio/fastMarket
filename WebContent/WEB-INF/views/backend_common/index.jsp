<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header"><spring:message code="backend.title" /></h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<div class="alert alert-info alert-dismissable">
	<button type="button" class="close" data-dismiss="alert"
		aria-hidden="true">&times;</button>
	<spring:message code="common.welcome"/> <strong><security:authentication property="name"/></strong>!
</div>    

<security:authorize access="hasRole('SiteAdmin')">
<div class="row">
	<div class="col-lg-3 col-md-6">
		<div class="panel panel-yellow">
			<div class="panel-heading">
				<div class="row">
					<div class="col-xs-3">
						<i class="fa fa-glass fa-5x"></i>
					</div>
					<div class="col-xs-9 text-right">
						<div class="huge">#</div>
						<div><spring:message code="backend.index.nongroceries" /></div>
					</div>
				</div>
			</div>
			<a href="${pageContext.request.contextPath}/non-groceries/views">
				<div class="panel-footer">
					<span class="pull-left"><spring:message code="backend.index.manager" /></span> <span
						class="pull-right"><i class="fa fa-arrow-circle-right"></i> </span>
					<div class="clearfix"></div>
				</div>
			</a>
		</div>
	</div>
	<div class="col-lg-3 col-md-6">
		<div class="panel panel-green">
			<div class="panel-heading">
				<div class="row">
					<div class="col-xs-3">
						<i class="fa fa-cutlery fa-5x"></i>
					</div>
					<div class="col-xs-9 text-right">
						<div class="huge">#</div>
						<div><spring:message code="backend.index.groceries" /></div>
					</div>
				</div>
			</div>
			<a href="${pageContext.request.contextPath}/groceries/views">
				<div class="panel-footer">
					<span class="pull-left"><spring:message code="backend.index.manager" /></span> <span
						class="pull-right"><i class="fa fa-arrow-circle-right"></i> </span>
					<div class="clearfix"></div>
				</div>
			</a>
		</div>
	</div>
	<div class="col-lg-3 col-md-6">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<div class="row">
					<div class="col-xs-3">
						<i class="fa fa-info fa-5x"></i>
					</div>
					<div class="col-xs-9 text-right">
						<div class="huge">#</div>
						<div><spring:message code="backend.index.systemInformation" /></div>
					</div>
				</div>
			</div>
			<a href="${pageContext.request.contextPath}/systemInformations/views">
				<div class="panel-footer">
					<span class="pull-left"><spring:message code="backend.index.manager" /></span> <span
						class="pull-right"><i class="fa fa-arrow-circle-right"></i> </span>
					<div class="clearfix"></div>
				</div>
			</a>
		</div>
	</div>
	<div class="col-lg-3 col-md-6">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<div class="row">
					<div class="col-xs-3">
						<i class="fa fa-user fa-5x"></i>
					</div>
					<div class="col-xs-9 text-right">
						<div class="huge">#</div>
						<div><spring:message code="backend.index.users" /></div>
					</div>
				</div>
			</div>
			<a href="${pageContext.request.contextPath}/users/views">
				<div class="panel-footer">
					<span class="pull-left"><spring:message code="backend.index.manager" /></span> <span
						class="pull-right"><i class="fa fa-arrow-circle-right"></i> </span>
					<div class="clearfix"></div>
				</div>
			</a>
		</div>
	</div>
</div>   
</security:authorize>

<security:authorize access="hasRole('SalesManager')">
<div class="row">
	<div class="col-lg-3 col-md-6">
		<div class="panel panel-yellow">
			<div class="panel-heading">
				<div class="row">
					<div class="col-xs-3">
						<i class="fa fa-glass fa-5x"></i>
					</div>
					<div class="col-xs-9 text-right">
						<div class="huge">#</div>
						<div><spring:message code="backend.index.nongroceries" /></div>
					</div>
				</div>
			</div>
			<a href="${pageContext.request.contextPath}/non-groceries/views">
				<div class="panel-footer">
					<span class="pull-left"><spring:message code="backend.index.manager" /></span> <span
						class="pull-right"><i class="fa fa-arrow-circle-right"></i> </span>
					<div class="clearfix"></div>
				</div>
			</a>
		</div>
	</div>
	<div class="col-lg-3 col-md-6">
		<div class="panel panel-green">
			<div class="panel-heading">
				<div class="row">
					<div class="col-xs-3">
						<i class="fa fa-cutlery fa-5x"></i>
					</div>
					<div class="col-xs-9 text-right">
						<div class="huge">#</div>
						<div><spring:message code="backend.index.groceries" /></div>
					</div>
				</div>
			</div>
			<a href="${pageContext.request.contextPath}/groceries/views">
				<div class="panel-footer">
					<span class="pull-left"><spring:message code="backend.index.manager" /></span> <span
						class="pull-right"><i class="fa fa-arrow-circle-right"></i> </span>
					<div class="clearfix"></div>
				</div>
			</a>
		</div>
	</div>
	<div class="col-lg-3 col-md-6">
		<div class="panel panel-red">
			<div class="panel-heading">
				<div class="row">
					<div class="col-xs-3">
						<i class="fa fa-credit-card fa-5x"></i>
					</div>
					<div class="col-xs-9 text-right">
						<div class="huge">#</div>
						<div><spring:message code="backend.index.deliveries" /></div>
					</div>
				</div>
			</div>
			<a href="${pageContext.request.contextPath}/deliveries/views">
				<div class="panel-footer">
					<span class="pull-left"><spring:message code="backend.index.manager" /></span> <span
						class="pull-right"><i class="fa fa-arrow-circle-right"></i> </span>
					<div class="clearfix"></div>
				</div>
			</a>
		</div>
	</div>
	<div class="col-lg-3 col-md-6">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<div class="row">
					<div class="col-xs-3">
						<i class="fa fa-usd fa-5x"></i>
					</div>
					<div class="col-xs-9 text-right">
						<div class="huge">#</div>
						<div><spring:message code="backend.index.bookings" /></div>
					</div>
				</div>
			</div>
			<a href="${pageContext.request.contextPath}/bookings/views">
				<div class="panel-footer">
					<span class="pull-left"><spring:message code="backend.index.manager" /></span> <span
						class="pull-right"><i class="fa fa-arrow-circle-right"></i> </span>
					<div class="clearfix"></div>
				</div>
			</a>
		</div>
	</div>
</div>   
</security:authorize>

<security:authorize access="hasRole('WebMarketingManager')">
<div class="row">
	<div class="col-lg-12 col-md-6">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<div class="row">
					<div class="col-xs-3">
						<i class="fa fa-info fa-5x"></i>
					</div>
					<div class="col-xs-9 text-right">
						<div class="huge">#</div>
						<div><spring:message code="backend.index.systemInformation" /></div>
					</div>
				</div>
			</div>
			<a href="${pageContext.request.contextPath}/systemInformations/views">
				<div class="panel-footer">
					<span class="pull-left"><spring:message code="backend.index.manager" /></span> <span
						class="pull-right"><i class="fa fa-arrow-circle-right"></i> </span>
					<div class="clearfix"></div>
				</div>
			</a>
		</div>
	</div>
</div>   
</security:authorize>
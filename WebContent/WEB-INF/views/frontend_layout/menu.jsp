<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${pageContext.request.contextPath}"><spring:message code="frontend.menu.home" /></a>
		</div>
        <!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="${pageContext.request.contextPath}/about"><spring:message code="frontend.menu.about" /></a></li>
				<li><a href="${pageContext.request.contextPath}/catalogue-products"><spring:message code="frontend.menu.catalogueProducts" /></a></li>
				<li><a href="#"><spring:message code="frontend.menu.recipes" /></a></li>
				<li class="dropdown">
          			<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><spring:message code="frontend.menu.customerSupport" /> <span class="caret"></span></a>
          			<ul class="dropdown-menu" role="menu">
			            <li><a href="#"><spring:message code="frontend.menu.customerSupport.termsOfSale" /></a></li>
			            <li><a href="#"><spring:message code="frontend.menu.customerSupport.paymentMethods" /></a></li>
			            <li><a href="#"><spring:message code="frontend.menu.customerSupport.deliveryMethods" /></a></li>
			            <li><a href="#"><spring:message code="frontend.menu.customerSupport.moneyBackGuarantee" /></a></li>
			            <li><a href="#"><spring:message code="frontend.menu.customerSupport.faq" /></a></li>
          			</ul>
        		</li>
        		<li class="dropdown">
          			<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><spring:message code="frontend.menu.pointsCard" /> <span class="caret"></span></a>
          			<ul class="dropdown-menu" role="menu">
			            <li><a href="#"><spring:message code="frontend.menu.pointsCard.cardAdvantages" /></a></li>
			            <li><a href="#"><spring:message code="frontend.menu.pointsCard.howWorks" /></a></li>
			            <li><a href="#"><spring:message code="frontend.menu.pointsCard.catalogAwards" /></a></li>
          			</ul>
        		</li>
				<li><a href="${pageContext.request.contextPath}/signin"><spring:message code="frontend.menu.signin" /></a></li>
				<security:authorize access="isAuthenticated()">
				<li><a href="${pageContext.request.contextPath}/dashboard"><spring:message code="frontend.menu.dashboard" /></a></li>
				</security:authorize>
				<security:authorize access="!isAuthenticated()">
				<li><a href="${pageContext.request.contextPath}/login"><spring:message code="frontend.menu.login" /></a></li>
				</security:authorize>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container -->
</nav>
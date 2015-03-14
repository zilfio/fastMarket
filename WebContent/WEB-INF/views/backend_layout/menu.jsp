<%@taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="${pageContext.request.contextPath}/dashboard"><spring:message code="backend.title"/></a>
    </div>
    <!-- /.navbar-header -->

	<security:authorize access="isAuthenticated()">
    <ul class="nav navbar-top-links navbar-right">
        <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
            </a>
            <ul class="dropdown-menu dropdown-user">
                <li><a href="${pageContext.request.contextPath}/userProfile/update"><i class="fa fa-user fa-fw"></i> <spring:message code="backend.menutop.userprofile" /></a>
                </li>
                <li class="divider"></li>
                <li><a href="${pageContext.request.contextPath}/j_spring_security_logout"><i class="fa fa-sign-out fa-fw"></i> <spring:message code="backend.menutop.logout" /></a>
                </li>
            </ul>
            <!-- /.dropdown-user -->
        </li>
        <!-- /.dropdown -->
    </ul>
    <!-- /.navbar-top-links -->

    <div class="navbar-default sidebar" role="navigation">
        <div class="sidebar-nav navbar-collapse">
            <ul class="nav" id="side-menu">
                <li>
                    <a class="active" href="${pageContext.request.contextPath}"><i class="fa fa-dashboard fa-fw"></i> <spring:message code="backend.menu.backtosite" /></a>
                </li>
                <security:authorize access="hasAnyRole('SiteAdmin', 'SalesManager')">
                <li>
                    <a href="#"><i class="fa fa-glass fa-fw"></i> <spring:message code="backend.menu.managenongroceries" /><span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                    	<li><a href="${pageContext.request.contextPath}/non-groceries/views"><spring:message code="backend.menu.managenongroceries" /></a></li>
                        <li><a href="${pageContext.request.contextPath}/brands/views"><spring:message code="backend.menu.managebrands" /></a></li>
						<li><a href="${pageContext.request.contextPath}/categories/views"><spring:message code="backend.menu.managecategories" /></a></li>
                    </ul>
                    <!-- /.nav-second-level -->
                </li>
                </security:authorize>
                <security:authorize access="hasAnyRole('SiteAdmin', 'SalesManager')">
                <li>
                    <a href="#"><i class="fa fa-cutlery fa-fw"></i> <spring:message code="backend.menu.managegroceries" /><span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                    	<li><a href="${pageContext.request.contextPath}/groceries/views"><spring:message code="backend.menu.managegroceries" /></a></li>
                    	<li><a href="${pageContext.request.contextPath}/brands/views"><spring:message code="backend.menu.managebrands" /></a></li>
						<li><a href="${pageContext.request.contextPath}/categories/views"><spring:message code="backend.menu.managecategories" /></a></li>
                        <li><a href="${pageContext.request.contextPath}/intolerance-categories/views"><spring:message code="backend.menu.manageintolerancecategories" /></a></li>
                    </ul>
                    <!-- /.nav-second-level -->
                </li>
                </security:authorize>
                <security:authorize access="hasRole('SiteAdmin')">
                <li>
                    <a href="#"><i class="fa fa-shopping-cart fa-fw"></i> <spring:message code="backend.menu.managecarts" /><span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li><a href="${pageContext.request.contextPath}/carts/views"><spring:message code="backend.menu.managecarts" /></a></li>
                        <li><a href="${pageContext.request.contextPath}/cart-lines/views"><spring:message code="backend.menu.managecartlines" /></a></li>
                    </ul>
                    <!-- /.nav-second-level -->
                </li>
                </security:authorize>
                <security:authorize access="hasAnyRole('SiteAdmin', 'SalesManager')">
                <li>
                    <a href="#"><i class="fa fa-credit-card fa-fw"></i> <spring:message code="backend.menu.manageorders" /><span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li><a href="${pageContext.request.contextPath}/deliveries/views"><spring:message code="backend.menu.managedeliveries" /></a></li>
                        <li><a href="${pageContext.request.contextPath}/bookings/views"><spring:message code="backend.menu.managebookings" /></a></li>
                    </ul>
                    <!-- /.nav-second-level -->
                </li>
                </security:authorize>
                <security:authorize access="hasRole('SiteAdmin')">
                <li>
                    <a href="#"><i class="fa fa-user fa-fw"></i> <spring:message code="backend.menu.manageusers" /><span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li><a href="${pageContext.request.contextPath}/users/views"><spring:message code="backend.menu.manageusers" /></a></li>
                        <li><a href="${pageContext.request.contextPath}/roles/views"><spring:message code="backend.menu.manageroles" /></a></li>
                    </ul>
                    <!-- /.nav-second-level -->
                </li>
                </security:authorize>
                <security:authorize access="hasRole('SiteAdmin')">
                <li>
                    <a href="#"><i class="fa fa-home fa-fw"></i> <spring:message code="backend.menu.managedistricts" /><span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                    	<li><a href="${pageContext.request.contextPath}/districts/views"><spring:message code="backend.menu.managedistricts" /></a></li>
                    	<li><a href="${pageContext.request.contextPath}/provinces/views"><spring:message code="backend.menu.manageprovinces" /></a></li>
                        <li><a href="${pageContext.request.contextPath}/regions/views"><spring:message code="backend.menu.manageregions" /></a></li>
                    </ul>
                    <!-- /.nav-second-level -->
                </li>
                </security:authorize>
                <security:authorize access="hasAnyRole('SiteAdmin', 'WebMarketingManager')">
                <li>
                    <a href="#"><i class="fa fa-info fa-fw"></i> <spring:message code="backend.menu.managesysteminformations" /><span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                    	<li><a href="${pageContext.request.contextPath}/systemInformations/views"><spring:message code="backend.menu.managesysteminformations" /></a></li>
                    </ul>
                    <!-- /.nav-second-level -->
                </li>
                </security:authorize>
            </ul>
        </div>
        <!-- /.sidebar-collapse -->
    </div>
    <!-- /.navbar-static-side -->
    </security:authorize>
</nav>
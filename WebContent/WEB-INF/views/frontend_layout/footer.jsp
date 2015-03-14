<%@page import="java.util.Calendar"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<footer>
	<div class="row">
		<div class="col-lg-12" style="text-align: center;">
			<p>Copyright &copy; <%=Calendar.getInstance().get(Calendar.YEAR)%> -- <spring:message code="common.footer"/></p>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12" style="text-align: center;">
			<ul class="list-unstyled list-inline">
				<li><a href="#"><spring:message code="frontend.menu.sitemap" /></a>
				</li>
				<li><a href="${pageContext.request.contextPath}/contact"><spring:message code="frontend.menu.contact" /></a>
				</li>
			</ul>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12" style="text-align: center;">
			<ul class="list-unstyled list-inline list-social-icons">
				<li><a href="#"><i class="fa fa-facebook-square fa-2x"></i> </a>
				</li>
				<li><a href="#"><i class="fa fa-linkedin-square fa-2x"></i> </a>
				</li>
				<li><a href="#"><i class="fa fa-twitter-square fa-2x"></i> </a>
				</li>
				<li><a href="#"><i class="fa fa-google-plus-square fa-2x"></i> </a>
				</li>
			</ul>
		</div>
	</div>
</footer>
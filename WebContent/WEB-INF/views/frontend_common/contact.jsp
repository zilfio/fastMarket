<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!-- Page Heading/Breadcrumbs -->
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">
			<spring:message code="frontend.menu.home" />
		</h1>
		<ol class="breadcrumb">
			<li><a href="index.php"><spring:message code="frontend.menu.home" /></a>
			</li>
			<li class="active"><spring:message code="frontend.menu.contact" /></li>
		</ol>
	</div>
</div>
<!-- /.row -->

<!-- Content Row -->
<div class="row">
	<!-- Map Column -->
	<div class="col-md-8">
		<!-- Embedded Google Map -->
		<iframe width="100%" height="400px" frameborder="0" scrolling="no"
			marginheight="0" marginwidth="0" src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2947.8138624957!2d13.352947999999987!3d42.367806000000016!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x132fcd66aaaca013%3A0xc28c47acede5a259!2sUniversita&#39;+Dell&#39;Aquila!5e0!3m2!1sit!2sit!4v1417879937767"></iframe>
	</div>
	<!-- Contact Details Column -->
	<div class="col-md-4">
		<h3>Informazioni di contatto</h3>
		<p>
			Via Vetoio, 1<br>L'Aquila, AQ 67100<br>
		</p>
		<p>
			<i class="fa fa-phone"></i> <abbr title="Phone">P</abbr>: (+39) 333-3333333
		</p>
		<p>
			<i class="fa fa-envelope-o"></i> <abbr title="Email">E</abbr>: <a
				href="mailto:lamp@mwt.it">jee@mwt.it</a>
		</p>
		<p>
			<i class="fa fa-clock-o"></i> <abbr title="Hours">H</abbr>: Luned&igrave; - Venerd&igrave;: 9:00 AM - 17:00 PM
		</p>
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
<!-- /.row -->
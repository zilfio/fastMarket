<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">
			<spring:message code="frontend.menu.about" />
		</h1>
		<ol class="breadcrumb">
			<li><a href="${pageContext.request.contextPath}"><spring:message code="frontend.menu.home" /></a>
			</li>
			<li class="active"><spring:message code="frontend.menu.about" /></li>
		</ol>
	</div>
</div>
<!-- /.row -->

<!-- Intro Content -->
<div class="row">
	<div class="col-md-6">
		<img class="img-responsive" src="${pageContext.request.contextPath}/resources/images/frontend/about.jpeg" alt="">
	</div>
	<div class="col-md-6">
		<h2><spring:message code="frontend.title" /></h2>
		<p>L'applicazione web ha lo scopo di permettere a una determinata catena di supermercati di vendere i propri prodotti. </p>
		<p>A tal fine, consentir&agrave; all'utente di acquistare online diverse tipologie di articoli e piatti pronti. Al momento dell'acquisto l'utente potr&agrave; scegliere se:</p>
		<ul>
			<li>ritirare la spesa direttamente in negozio, attraverso uno sportello dedicato (servizio "prenota e ritira");</li>
			<li>ricevere la spesa direttamente a casa (servizio di consegna a domicilio che copre la zona circoscritta de L'Aquila e dintorni, attualmente inesistente nel Web).</li>
		</ul>
		<p>Tramite l'applicazione web &egrave; possibile sfogliare il catalogo di tutti i prodotti in vendita, utilizzando anche diversi filtri (marca, prezzo, categoria ecc.), visualizzare tutte le caratteristiche di un determinato articolo oppure accedere direttamente alle offerte disponibili.</p>
		<p>Il punto di forza dell'applicazione &agrave; costituito dalla sua responsiveness, infatti, il sito web permetter&agrave; all'utente di accedere ai servizi tramite dispositivo mobile, in particolar modo di pagare con estrema facilit&agrave;.</p>
	</div>
</div>
<!-- /.row -->

<!-- Team Members -->
<div class="row">
	<div class="col-lg-12">
		<h2 class="page-header">Il nostro team</h2>
	</div>
	<div class="col-md-6 text-center">
		<div class="thumbnail">
			<img class="img-responsive" src="${pageContext.request.contextPath}/resources/images/frontend/silvio.jpg" alt="">
			<div class="caption">
				<h3>
					Silvio D'Orazio<br> <small>Web Developer</small>
				</h3>
				<ul class="list-inline">
					<li><a href="#"><i class="fa fa-2x fa-facebook-square"></i> </a>
					</li>
					<li><a href="#"><i class="fa fa-2x fa-linkedin-square"></i> </a>
					</li>
					<li><a href="#"><i class="fa fa-2x fa-twitter-square"></i> </a>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<div class="col-md-6 text-center">
		<div class="thumbnail">
			<img class="img-responsive" src="${pageContext.request.contextPath}/resources/images/frontend/ale.jpg" alt="">
			<div class="caption">
				<h3>
					Alessandra Ponis<br> <small>Web Developer</small>
				</h3>
				<ul class="list-inline">
					<li><a href="#"><i class="fa fa-2x fa-facebook-square"></i> </a>
					</li>
					<li><a href="#"><i class="fa fa-2x fa-linkedin-square"></i> </a>
					</li>
					<li><a href="#"><i class="fa fa-2x fa-twitter-square"></i> </a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
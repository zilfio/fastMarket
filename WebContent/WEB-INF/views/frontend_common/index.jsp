<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Marketing Icons Section -->
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Benvenuto su FastMarket</h1>
	</div>
	<div class="col-md-4">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4>
					<i class="fa fa-fw fa-check"></i> Chi siamo
				</h4>
			</div>
			<div class="panel-body">
				<p>FastMarket rispecchia la visione futura del fare la spesa nel
					supermercato di fiducia della zona di residenza. È più di un
					classico e-commerce, in quanto ogni funzionalità è studiata
					appositamente per offrirti un'ottima esperienza di navigazione.</p>
				<a href="${pageContext.request.contextPath}/about"
					class="btn btn-default">Ulteriori informazioni</a>
			</div>
		</div>
	</div>
	<div class="col-md-4">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4>
					<i class="fa fa-fw fa-gift"></i> Cosa offriamo
				</h4>
			</div>
			<div class="panel-body">
				<p>Con soli pochi click puoi comodamente fare la spesa nei
					ritagli di tempo attraverso un pc, un tablet o uno smartphone,
					facendoti recapitare la spesa comodamente a casa in tempi ultra
					brevi o scegliendo di ritirare la spesa o un pasto pronto
					direttamente in negozio.</p>
				<a href="${pageContext.request.contextPath}/about"
					class="btn btn-default">Ulteriori informazioni</a>
			</div>
		</div>
	</div>
	<div class="col-md-4">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4>
					<i class="fa fa-fw fa-compass"></i> Come funziona
				</h4>
			</div>
			<div class="panel-body">
				<p>Come un normale e-commerce, puoi aggiungere al carrello i
					prodotti di tuo gradimento e registrandoti al nostro sito potrai
					scegliere se farti recapitare la spesa comodamente a casa e pagare
					al ritiro oppure online o se prenotare i tuoi prodotti, o il tuo
					pasto pronto, tra quelli disponibili per poi prelevare il tutto in
					negozio.</p>
				<a href="${pageContext.request.contextPath}/about"
					class="btn btn-default">Ulteriori informazioni</a>
			</div>
		</div>
	</div>
</div>
<!-- /col -->
<!-- /.row -->

<!-- Portfolio Section -->
<c:if test="${not empty requestScope.lastProducts}">
	<div class="row">
		<div class="col-lg-12">
			<h2 class="page-header">Novità nel supermercato</h2>
		</div>
		<div class="container">
			<div class="col-md-12">
				<div id="Carousel" data-ride="carousel" class="carousel slide">
					<ol class="carousel-indicators">
						<li data-target="#Carousel" data-slide-to="0" class="active"></li>
						<li data-target="#Carousel" data-slide-to="1"></li>
						<li data-target="#Carousel" data-slide-to="2"></li>
					</ol>
					<!-- Carousel items -->
					<div class="carousel-inner">
						<div class="item active">
							<div class="row">
								<c:forEach items="${requestScope.lastProducts}" var="lastProduct">
								<div class="col-md-4 col-sm-6">
									<a href="${pageContext.request.contextPath}/catalogue-products/detail_product?product=${lastProduct.id}"> 
									<c:choose>
										<c:when test="${not empty lastProduct.path}">
											<img src="${pageContext.request.contextPath}/resources/${lastProduct.path}" style="max-height: 85px; overflow: hidden;" />
										</c:when>
										<c:otherwise>
											<img src="${pageContext.request.contextPath}/resources/images/frontend/default.jpg" style="max-height: 85px; overflow: hidden;" />
										</c:otherwise>
									</c:choose>
									</a>
								</div>
								</c:forEach>
							</div>
							<!--.row-->
						</div>
					</div>
					<!--.carousel-inner-->
				</div>
				<!--.Carousel slide-->
			</div>
			<!--container-->
		</div>
		<!-- /.row -->
	</div>
</c:if>

<hr>

<!-- Call to Action Section -->
<div class="well">
	<div class="row">
		<div class="col-md-8">
			<p>FastMarket vanta una vasta gamma di articoli di qualità e di
				gustosissimi piatti pronti messi a disposizione giornalmente per te
				dai nostri dipendenti del supermercato. Accedi al catalogo prodotti
				e scegli gli articoli di tuo interesse.</p>
		</div>
		<div class="col-md-4">
			<a class="btn btn-lg btn-default btn-block"
				href="${pageContext.request.contextPath}/catalogue-products"><spring:message
					code="frontend.menu.catalogueProducts" /></a>
		</div>
	</div>
</div>

<div class="row">
	<div class="col-md-4">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4>
					<span class="glyphicon glyphicon-cutlery"></span> La ricetta del
					giorno
				</h4>
			</div>
			<div class="panel-body">
				<div class="col-sm-12 text-center">
					<img class="img-circle"
						src="http://blog.giallozafferano.it/valeriaciccotti/wp-content/uploads/2015/01/c.jpg"
						style="width: 100px; height: 100px;"><br />
				</div>
				<br />
				<h4>Ciambelline al forno alla nutella</h4>
				<p>Una ricetta dolce ed ideale per la prima colazione. Soffici,
					golose e ripiene di nutella, possono essere farcite anche con crema
					e marmellata. Scoprite con quanta facilità si preparano le
					Ciambelline al forno alla nutella.</p>
			</div>
			<a class="btn btn-sm btn-default btn-block" role="button">LEGGI E
				VOTA LA RICETTA</a>
		</div>
	</div>
	<div class="col-md-4">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4>
					<i class="fa fa-fw fa-envelope"></i> Email newsletter
				</h4>
			</div>
			<div class="panel-body">
				<p>Iscriviti alla nostra newsletter per scoprire le novità e le
					ultime offerte proposte da FastMarket!</p>
				<form class="form form-signup" role="form">
					<div class="form-group">
						<div class="input-group">
							<span class="input-group-addon"><span
								class="glyphicon glyphicon-user"></span></span> <input type="text"
								class="form-control" placeholder="Il tuo nome" />
						</div>
					</div>
					<div class="form-group">
						<div class="input-group">
							<span class="input-group-addon"><span
								class="glyphicon glyphicon-envelope"></span> </span> <input type="text"
								class="form-control" placeholder="Il tuo indirizzo Email" />
						</div>
					</div>
				</form>
			</div>
			<a class="btn btn-sm btn-primary btn-block" role="button">SUBMIT</a>
		</div>
	</div>

	<div class="col-md-4">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4>
					<i class="fa fa-fw fa-user"></i> Le testimonianze dei nostri
					clienti
				</h4>
			</div>
			<div class="carousel slide" id="testimonials-rotate">
				<div class="carousel-inner">
					<div class="item active">
						<div class="col-md-12 text-center">
							<br /> <img class="img-circle"
								src="http://thumbs.dreamstime.com/x/young-guy-looking-down-5612441.jpg"
								style="width: 100px; height: 100px;">
						</div>
						<div class="testimonials  col-md-12">
							<br />
							<p>Finalmente un supermercato serio che mi permette di
								prenotare il pranzo con pochi click e saltare la fila quando sto
								di fretta.</p>
							<small><i>Fabio Rossi</i></small>
						</div>

						<div class="clearfix"></div>
					</div>
					<div class="item">
						<div class="col-md-12 text-center">
							<br /> <img class="img-circle"
								src="http://www.westmountain.co.nz/images/businessman2.jpg"
								style="width: 100px; height: 100px;">
						</div>
						<div class="testimonials  col-md-12">
							<br />
							<p>Raccomando FastMarket per la serietà e la velocità nel
								recapitare la spesa a casa.</p>
							<small><i>Alfonsino Neri</i></small>
						</div>

						<div class="clearfix"></div>
					</div>
					<div class="item">
						<div class="col-md-12 text-center">
							<br /> <img class="img-circle"
								src="http://d236bkdxj385sg.cloudfront.net/wp-content/uploads/2012/11/confident-businesswoman-pf.jpg"
								style="width: 100px; height: 100px;">
						</div>
						<div class="testimonials  col-md-12">
							<br />
							<p>FastMarket mi ha permesso di risolvere il problema di fare
								la spesa a mia madre, ormai anziana, quando non ho tempo di
								recarmi fisicamente in negozio.</p>
							<small><i>Valeria Bianchi</i></small>
						</div>

						<div class="clearfix"></div>
					</div>
				</div>
			</div>
			<div class="pull-right">
				<a class="left" href="#testimonials-rotate" data-slide="prev"><span
					class="glyphicon glyphicon-chevron-left"></span></a> <a class="right"
					href="#testimonials-rotate" data-slide="next"><span
					class="glyphicon glyphicon-chevron-right"></span></a>
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>

		</div>
		<!-- panel-->
	</div>
	<!-- col-->
</div>
<!-- /.row -->

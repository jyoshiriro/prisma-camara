<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Bem-vindo!</title>
	</head>
	<body>
		<div class="container">
			<div class="row">
				<div class="col-sm-4 centered">
					<sec:ifNotLoggedIn>
						<div class="row">
							<div class="col-md-8 col-md-offset-2">
								<span class="glyphicon glyphicon-arrow-left"></span> 
								Conecte-se com
								<span class="glyphicon glyphicon-arrow-right"></span>
								<br/> 
							</div>
						</div>
						<div class="row">		
							<div class="col-md-8 col-md-offset-2">
								<a href="j_spring_security_facebook_redirect" title="Entrar com Facebook"><img class="redes" alt="" src="${resource(dir:'images', file:'botao-face.png')}"></a>
								<a href="j_spring_twitter_security_check" title="Entrar com Twitter"><img class="redes" alt="" src="${resource(dir:'images', file:'botao-twitter.png')}"></a>
							</div>
						</div>
					</sec:ifNotLoggedIn>
					<sec:ifAllGranted roles="ROLE_USER">
						<p>Use o sistema acessando as opções do menu.</p>
					</sec:ifAllGranted>
				</div>
				<div class="col-sm-6">
					<g:include view="banner-home.gsp"/>
				</div>
			</div> <!-- row -->
		</div> <!-- container -->
	</body>
</html>

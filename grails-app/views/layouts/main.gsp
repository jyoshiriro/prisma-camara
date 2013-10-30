<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title>Olho na Câmara <g:layoutTitle/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<r:require modules="twitterAuth, application"/>
		<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
				
		<g:layoutHead/>
		<r:layoutResources />
	</head>
	<body>
		<div class="navbar navbar-inverse">
	    	<div class="container">
		        <div class="navbar-header">
		          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
		            <span class="icon-bar"></span>
		            <span class="icon-bar"></span>
		            <span class="icon-bar"></span>
		          </button>
		          <a class="navbar-brand" href="${createLink(uri: '/', absolute: true)}"><strong>Olho na Câmara</strong></a>
		        </div>
		        <div class="navbar-collapse collapse">
		        	<ul class="nav navbar-nav">
			            <li><a href="${createLink(uri: '/', absolute: true)}">Início</a></li>
			            <sec:ifAllGranted roles="ROLE_USER">
				        	<li><g:link controller="painel" action="meuPerfil">Meu Perfil</g:link></li>
					        <li><g:link controller="painel" action="index">Meus Acompanhamentos</g:link></li>
				        </sec:ifAllGranted>
				        <li><g:link controller="painel" action="sobre">Sobre</g:link></li>
			    	</ul>
		            <sec:ifAllGranted roles="ROLE_USER">
		            	<ul class="nav navbar-nav navbar-right">
			              <li><g:link controller="painel" action="meuPerfil"><g:nomeUsuario /></g:link></li>
			              <li><g:link controller="logout" action="index">Sair</g:link></li>
			            </ul>
		            </sec:ifAllGranted>
	    		</div><!--/.nav-collapse -->
	    	</div>
	    </div>
		<g:layoutBody/>
		<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
		<script src="${resource(dir: 'js', file: 'application.js')}"></script>
		<r:layoutResources />
	</body>
</html>

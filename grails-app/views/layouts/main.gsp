<%--
 * Copyright 2013 de José Yoshiriro (jyoshiriro@gmail.com) e Raimundo Norberto (raimundonorberto@gmail.com)
 * Este arquivo é parte do programa Olho na Câmara.
 * 
 * O Olho na Câmara é um software livre; você pode redistribuí-lo e/ou modificá-lo dentro
 * dos termos da GNU Affero General Public License como publicada pela Fundação do Software Livre
 * (FSF); na versão 3 da Licença. Este programa é distribuído na esperança que possa ser
 * útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer
 * MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a licença para maiores detalhes. Você deve ter
 * recebido uma cópia da GNU Affero General Public License, sob o título 'LICENCA.txt', junto com
 * este programa, se não, acesse http://www.gnu.org/licenses/
 --%>
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
		<div class="navbar navbar-inverse" role="navigation">
	    	<div class="container">
		        <div class="navbar-header">
		          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
		            <span class="icon-bar"></span>
		            <span class="icon-bar"></span>
		            <span class="icon-bar"></span>
		          </button>
		          <a class="navbar-brand" href="${createLink(uri: '/', absolute: true)}"><strong style="color: white;">OlhoNaCamara</strong><small>.com.br</small></a>
		        </div>
		        <div class="navbar-collapse collapse">
		        	<ul class="nav navbar-nav">
		        		<sec:ifNotLoggedIn>
			            <li><a href="${createLink(uri: '/', absolute: true)}">Início</a></li>
			            </sec:ifNotLoggedIn>
			            <sec:ifAllGranted roles="ROLE_USER">
					        <li>
					        <g:link controller="painel" action="index"
					        class="${['/','index'].contains(params.action)?'ativo':''}">
					        Meus Acompanhamentos
					        </g:link></li>
				        	<li><g:link controller="painel" action="meuPerfil"
				        	class="${params.action=='meuPerfil'?'ativo':''}">Meu Perfil</g:link></li>
				        </sec:ifAllGranted>
				        <li><g:link controller="painel" action="ajuda"
				        class="${params.action=='ajuda'?'ativo':''}">Ajuda</g:link></li>
				        <li><g:link controller="painel" action="sobre"
				        class="${params.action=='sobre'?'ativo':''}">Sobre</g:link></li>
			    	</ul>
		            <sec:ifAllGranted roles="ROLE_USER">
		            	<ul class="nav navbar-nav navbar-right">
		            	  <g:set var="nomeLogo" value="logo-${tipoRedeUsuario()}.png"/>
			              <li><img src='${resource(dir:'images',file:nomeLogo)}' class="logo-rede-topo"></li>
			              <li><g:link controller="painel" action="meuPerfil" class="link-perfil ${tipoRedeUsuario()}"><g:nomeUsuario /></g:link></li>
			              <li><g:link controller="logout" action="index">Sair</g:link></li>
			            </ul>
		            </sec:ifAllGranted>
	    		</div><!--/.nav-collapse -->
	    	</div>
	    </div>
		<g:layoutBody/>
		<div id="spinner" class="spinner" style="display:none;">Carregando...</div>
		
<footer id="footer-main" data-tracking="Footer">
<div class="footer-bottom">
<div class="container">

<p>
<a href="https://twitter.com/olhonacamaradep" title="Seguir @olhonacamaradep no Twitter" target="_blank"
class="botao-rede">
<img src="${resource(dir:'images',file:'botao-seguir-twitter.png')}"></a>
 &nbsp; 
<a href="https://www.facebook.com/olhonacamaradosdeputados" title="Seguir as atualizações públicas de Olho na Câmara dos Deputados" target="_blank"
class="botao-rede">
<img src="${resource(dir:'images',file:'botao-seguir-facebook.png')}">
</a>
</p>


		<p>
		Desenvolvido por <a href="https://www.facebook.com/jyoshiriro" target="_blank">José Yoshiriro</a> 
		e <a href="https://www.facebook.com/raimundo.norbertolameirajunior" target="_blank">Raimundo Lameira</a> 
		para o <b>Hackathon 2013 da Câmara dos Deputados</b>.
		</p>
		<p>
Versão <i>0.3 Beta</i>. Código Licenciado na 
<a href="http://www.gnu.org/licenses/agpl-3.0.html" target="_blank">GNU AFFERO GENERAL PUBLIC LICENSE v3.0</a>.
</p>
</div>
</div>
		</footer>
		
		<r:layoutResources />
	</body>
</html>

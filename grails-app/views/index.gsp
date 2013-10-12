<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Welcome to Grails</title>
	</head>
	<body>
		<a href="#page-body" class="skip"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="page-body" role="main">
			<h1>Welcome to Grails</h1>
			<p>Congratulations, you have successfully started your first Grails application! At the moment
			   this is the default page, feel free to modify it to either redirect to a controller or display whatever
			   content you may choose. Below is a list of controllers that are currently deployed in this application,
			   click on each to execute its default action:</p>

			<div id="controller-list" role="navigation">
				<h2>Available Controllers:</h2>
				<ul>
					<g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
						<li class="controller"><g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link></li>
					</g:each>
				</ul>
			</div>
			<div>
				<sec:ifNotGranted roles="ROLE_USER">
					<facebookAuth:connect />
				</sec:ifNotGranted>
				<br/>
				<br/>
				<sec:ifAllGranted roles="ROLE_USER">
					Bem-vindo <sec:username/>! (<g:link uri="/j_spring_security_logout">Logout</g:link>)
					<br/>
					<g:link class="create" controller="facebookPost" action="timeline">Minha Timeline</g:link>
				</sec:ifAllGranted>
			</div>
		</div>
	</body>
</html>

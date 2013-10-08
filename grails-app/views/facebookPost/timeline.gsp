<html>
<head>
	<meta name='layout' content='main'/>
	<title><g:message code="springSecurity.login.title"/></title>
</head>

<body>
	<div id="page-body" role="main">
		<div id="controller-list" role="navigation">
			<g:each var="post" in="${posts}">
				<li class="controller">
					<strong>Caption:</strong> ${post.caption}<br/>
					<strong>Message:</strong> ${post.message}<br/>
					<strong>Description:</strong> ${post.description}
				</li>
			</g:each>
		</div>
	</div>
</body>
</html>

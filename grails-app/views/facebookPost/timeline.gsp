<html>
<head>
	<title><g:message code="springSecurity.login.title"/></title>
</head>

<body>
	<g:each var="post" in="${posts}">
		<li class="controller">
			<strong>Caption:</strong> ${post.caption}<br/>
			<strong>Message:</strong> ${post.message}<br/>
			<strong>Description:</strong> ${post.description}
		</li>
	</g:each>
</body>
</html>

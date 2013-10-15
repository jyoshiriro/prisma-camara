<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/> 
		<title>Painel</title>
	</head>
	<body>
		<a href="#page-body" class="skip"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="page-body" role="main">
			<h1>Painel</h1>
			<p>Bem-vindo <g:if test="usuario?.nome?.trim()?.isEmpty()">${usuario.username}</g:if><g:else>${usuario.nome}</g:else>!</p>
			<br/>
			<div>
				<h2>Deputados de Interesse</h2>
				<table id="tbDeputados">
				    <thead>
				        <tr>
				            <th width="60%">Deputado</th>
				            <th width="10%">Partido</th>
				            <th width="10%">Estado</th>
				            <th width="10%">Opções</th>
				        </tr>
				    </thead>
				    <tbody>
				    	<g:if test="${usuario.usuarioDeputados.isEmpty()}">
				    		<tr>
						    	<td colspan="5">Nenhum deputado selecionado.</td>
							</tr>
				    	</g:if>
				    	<g:else>
					    	<g:each in="${usuario.usuarioDeputados}" var="usuarioDeputado">
						    	<tr>
						        	<td>${usuarioDeputado.deputado.nomeParlamentar}</td>
						        	<td>${usuarioDeputado.deputado.partido.sigla}</td>
						        	<td>${usuarioDeputado.deputado.uf}</td>
						        	<td><g:link action="removerDeputado" id="${usuarioDeputado.id}">Remover</g:link></td>
						        </tr>
							</g:each>
						</g:else>
				    </tbody>
				</table>
				<g:link action="adicionarDeputados">Adicionar Deputado</g:link>
			</div>
			<br/>
			<div>
				<h2>Proposições de Interesse</h2>
				<table id="tbProposicoes">
				    <thead>
				        <tr>
				        	<th width="10%">Número</th>
				            <th width="10%">Ano</th>
				            <th width="70%">Emenda</th>
				            <th width="10%">Opções</th>
				        </tr>
				    </thead>
				    <tbody>
				        <g:if test="${usuario.usuarioProposicoes.isEmpty()}">
				    		<tr>
						    	<td colspan="5">Nenhuma proposição selecionada.</td>
							</tr>
				    	</g:if>
				    	<g:else>
					    	<g:each var="usuarioProposicao" in="${usuario.usuarioProposicoes}">
						    	<tr>
						        	<td>${usuarioProposicao.proposicao.numero}</td>
						        	<td>${usuarioProposicao.proposicao.ano}</td>
						        	<td>${usuarioProposicao.proposicao.txtEmenta}</td>
						        	<td><g:link action="removerProposicao" id="${usuarioProposicao.id}">Remover</g:link></td>
						        </tr>
							</g:each>
						</g:else>
				    </tbody>
				</table>
				<g:link action="adicionarProposicoes">Adicionar Proposição</g:link>	
			</div>
			<%--<div>
				<g:form name="config" url="[action:'gravarConfiguracoes']">
					<h2>Partidos de Interesse</h2>
					<g:each in="${partidos}" var="partido" status="i">
					    <g:checkBox name="partidosSelecionados" value="${partido.id}" checked="${usuario.usuarioPartidos.find{it.partido.id==partido.id}}" />
					    <label for="partidosSelecionados">${partido.sigla}</label><br/>
					</g:each>
					<br/>
					<br/>
					<h2>Deputados de Interesse</h2>
					<g:each in="${deputados}" var="deputado" status="i">
					    <g:checkBox name="deputadosSelecionados" value="${deputado.id}" checked="${usuario.usuarioDeputados.find{it.deputado.id==deputado.id}}" />
					    <label for="deputadosSelecionados">${deputado.descricao}</label><br/>
					</g:each>
					<br/>
					<g:submitButton name="gravar" value="Gravar"/>
				</g:form>
			</div>--%>
			<br/>
			<g:link action="">Voltar</g:link>			
		</div>
	</body>
</html>
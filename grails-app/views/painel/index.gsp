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
<g:set var="etwitter" value="${tipoRedeUsuario()=='twitter'}"/>
<g:set var="asterisco" value="${etwitter?'*':''}"/>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/> 
		<title>Painel</title>
	</head>
	<body>
	<g:if test="${jaLeuMsgInicial()!='true'}">
	<!-- Modal -->
			<div class="modal fade" style="overflow-y: auto;" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">Instrução inicial</h4>
						</div>
						<div class="modal-body">
							<p>Olá, <b><g:nomeUsuario/></b>. Como você é novo(a) por aqui, gostariamos de avisá-lo(a) que:</p>
							<ol>
							<li>Toda mensagem que receber será PRIVADA, ou seja, só você irá vê-la em sua rede social. Se quiser, pode compartilhá-la para que seus amigos a vejam também</li>
							<li>Talvez amanhã ou em poucas horas você receba um volume um pouco grande de postagens na linha do tempo de sua rede social. Do segundo dia em diante você receberá menos mensagens</li>
							<li>A quantidade de postagens que vai receber dependenderá de quantos deputados/partidos/proposições selecionar</li>
							</ol> 
						</div>
						<div class="modal-footer">
							<g:link class="btn btn-primary" controller="painel" action="confirmarLeituraMsgInicial">Ok, obrigado pelas informações :)</g:link>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<r:script>
				$("#myModal").modal({show:true});
			</r:script>
			<!-- /.modal -->
	</g:if>
			
		<div class="container">
			<h1>Meus Acompanhamentos</h1>
			<br/>
			<nav class="navbar navbar-default">
				<div class="nav navbar-nav ">
					<p class="navbar-text">
					<span class="glyphicon glyphicon-user"></span>
					<g:link controller="deputado">Deputados</g:link> 
					</p>
				</div>
				<div class="nav navbar-nav navbar-right">
					<g:if test="${session.contagemDeputados}">
					<p class="navbar-text">Você acompanha <b>${session.contagemDeputados}</b></p>
					</g:if>
					<g:else>
					<p class="navbar-text">Não acompanha nenhum(a)</p>
					</g:else>
				</div>
				
				<div class="alert alert-warning painel">
				    <p>
				    Pesquise e adicione <b>Deputados</b> à sua conta.
					</p>
					<p>
					De cada Deputado que escolher acompanhar, receberá sem sua rede social, diariamente as suas:
					</p>
					<ol>
						<li><span>Frequência diária</span></li>
						<li><span>Gastos que realizou</span></li>
						<li><span>Discursos que proferiu</span></li>
					</ol>
				</div>
			</nav>
			
			<nav class="navbar navbar-default">
				<div class="nav navbar-nav">
					<p class="navbar-text">
					<span class="glyphicon glyphicon-link"></span>
					<g:link controller="partido">Partidos</g:link>
					</p>
				</div>
				<div class="nav navbar-nav navbar-right">
					<g:if test="${session.contagemPartidos}">
					<p class="navbar-text">Você acompanha <b id="uContPartidos">${session.contagemPartidos}</b></p>
					</g:if>
					<g:else>
					<p class="navbar-text">Não acompanha nenhum</p>
					</g:else>
				</div>
				
				<div class="alert alert-success painel">
				    <p>
				    Pesquise e adicione <b>Partidos</b> à sua conta.
					</p>
					<p>
					De cada Partido que escolher acompanhar, receberá sem sua rede social, diariamente informações de todos os seus Deputados:
					</p>
					<ol>
						<li><span>Frequência diária</span></li>
						<li><span>Gastos que realizou</span></li>
						<li><span>Discursos que proferiu</span></li>
					</ol>
					</p>
				</div>
			</nav>
			
			
			<nav class="navbar navbar-default">
				<div class="nav navbar-nav">
					<p class="navbar-text">
					<span class="glyphicon glyphicon-transfer"></span>
					<g:link controller="proposicao">Proposições</g:link>
					</p>
				</div>
				<div class="nav navbar-nav navbar-right">
					<g:if test="${session.contagemProposicoes}">
					<p class="navbar-text">Você acompanha <b>${session.contagemProposicoes}</b></p>
					</g:if>
					<g:else>
					<p class="navbar-text">Não acompanha nenhuma</p>
					</g:else>
				</div>
				
				<div class="alert alert-info painel">
				    <p>
				    Pesquise e adicione <b>Proposições</b> à sua conta.
					</p>
					<p>
					Sempre que uma Proposição que você acompanha sofrer uma votação, você redeberá os seguintes detalhes em sua rede social 
					</p>
					<ol>
						<li><span>Objeto da Votação</span></li>
						<li><span>Resumo ${asterisco}</span></li>
						<li><span>Quantitativo de cada voto</span></li>
						<li><span>Nomes dos Deputados em cada um dos votos ${asterisco}</span></li>
					</ol>
					</p>
				</div>				
			</nav>
			
			<g:if test="${etwitter}">
			
			<div style="clear: both;"></div>
			
			<div class="alert alert-danger">
				<b>*</b> Alguns detalhes só chegam para contas do <b>Facebook</b>. Contas do <b>Twitter</b> recebem informações bem mais resumidas.
			</div>
			
			</g:if>
			
		</div>

	</body>
</html>
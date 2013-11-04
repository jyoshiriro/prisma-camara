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
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Meu Perfil</title>
	</head>
	<body>
		<div class="container">
			<h1>Meu Perfil</h1>
			<div class="row">
				<div class="col-lg-4">
					<g:form role="form" controller="painel" action="atualizaNome">
						<div class="form-group">
							
							<label >A rede pela qual nos acompanha é</label>
						    <g:set var="nomeLogo" value="logo-${tipoRedeUsuario()}.png"/>
			                <img src='${resource(dir:'images',file:nomeLogo)}' alt="${tipoRedeUsuario().capitalize()}" title="${tipoRedeUsuario().capitalize()}">
			                
						    <label for="nome" style="margin-top: 1em">
						    <g:if test="${session.primeiroAcesso}">
						    Como quer que te chamemos por aqui?
						    </g:if>
						    <g:else>
						    O nome pelo qual o chamamos aqui é:
						    </g:else>
						    </label>
							<input type="text" class="form-control" name="nome" placeholder="Seu nome aqui conosco" value="${nome}">
							
						</div>
						<button type="submit" class="btn btn-default">Gravar</button>
					</g:form>
				</div>
			</div>
			<br/> <br/> <br/>
			<div class="row">
				<div class="col-lg-4 center">
					<button type="button" class="btn btn-danger" data-toggle="modal" data-target="#myModal">
						Quero cancelar meu cadastro!
					</button>
				</div>
			</div>
			<!-- Modal -->
			<div class="modal fade" style="overflow-y: auto;" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">Confirmação</h4>
						</div>
						<div class="modal-body">
							Você tem certeza que deseja cancelar sua conta no Olho na Câmara?<br>
							Não é possível desfazer essa operação.
						</div>
						<div class="modal-footer">
							<g:link class="btn btn-primary" controller="painel" action="cancelaInscricao">Sim</g:link>
							<button type="button" class="btn btn-default" data-dismiss="modal">Não</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->
		</div>
	</body>
</html>
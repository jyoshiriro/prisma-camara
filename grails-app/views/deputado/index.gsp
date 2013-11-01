<%--
 * Copyright 2013 de José Yoshiriro (jyoshiriro@gmail.com) e Raimundo Norberto (raimundonorberto@gmail.com)
 * Este arquivo é parte do programa Olho na Câmara.
 * 
 * O Olho na Câmara é um software livre; você pode redistribuí-lo e/ou modificá-lo dentro
 * dos termos da GNU General Public License como publicada pela Fundação do Software Livre
 * (FSF); na versão 3 da Licença. Este programa é distribuído na esperança que possa ser
 * útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer
 * MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a licença para maiores detalhes. Você deve ter
 * recebido uma cópia da GNU General Public License, sob o título 'LICENCA.txt', junto com
 * este programa, se não, acesse http://www.gnu.org/licenses/
 --%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<meta name="layout" content="main"/>
	<title>:: Acompanhamento de Deputados</title>
</head>
<body>

	<div class="container">
	
		<div class="panel panel-default">
			<div class="panel-body">
				<div class="row">
				<div class="col-sm-6">
		    		<span class="glyphicon glyphicon-user"></span> <b>Seus Deputados</b>
				</div>
				<div class="col-sm-6">
		    		<span class="pull-right" id="pContagem"><g:include controller="painel" action="contagem" id="Deputados"/></span>
				</div>
				</div>
		  	</div>
		</div>

		<g:formRemote url="[action:'list',controller:'deputado']" name="searchableForm" update="resultado">
			<div class="row">
			
				<div class="col-md-4">
			        <div class="input-group">
				    	<g:textField class="form-control" name="q" id="campoQ" value="${params.q}" size="50" placeholder="Nome, partido ou a UF do deputado"/>
				      	<span class="input-group-btn">
				        	<button class="btn btn-default" type="submit">Pesquisar</button>
				      	</span>
				    </div><!-- /input-group -->
				    <br/>
		        </div>
		        
		        <div class="col-md-4">
			        <div class="panel panel-default">
						<div class="panel-body">
							<span id="slink62" class="glyphicon glyphicon-search"></span>
					        Deputados que você ainda <b>Não Acompanha</b>.<br>
					        <span>Clique no ícone para acompanhar</span>.
						</div>
					</div>        
				</div>
	        
	        	<div class="col-md-4">
			        <div class="panel panel-default acompanhando">
						<div class="panel-body">
							<span id="slink62" class="glyphicon glyphicon-check"></span>
					        Deputados que você <b>Já Acompanha</b>.<br>
					        Clique no ícone para deixar de acompanhar.
						</div>
					</div>        
				</div>	        
	        </div>	        
	    </g:formRemote>
	    
	    <div style="clear: both;"></div>
		<div id="resultado">
			<g:include controller="deputado" action="list"/>
		</div>
	</div>
</body>
</html>

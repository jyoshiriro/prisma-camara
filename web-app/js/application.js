/*
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
 */
if (typeof jQuery !== 'undefined') {
	(function($) {
		$('#spinner').ajaxStart(function() {
			$(this).fadeIn();
		}).ajaxStop(function() {
			$(this).fadeOut();
			dicas();
		});
	})(jQuery);
}

function toogleAssociar(elemento) {
	$("#s"+elemento.id).toggleClass('glyphicon-check');
	$("#s"+elemento.id).toggleClass('glyphicon-search');
	$("#s"+elemento.id).parent().parent().toggleClass('nao');
	$("#linkContagem").click();
}

function fecharDivs() {
	$('#divAcompanhaDeputados,#divAcompanhaProposicoes,#divAcompanhaPartidos').html('');
}

function limparPesquisa(){
	$("#campoQ").val('');
	$("#searchableForm").submit();
}

function dicas() {
	$('[data-toggle="tooltip"]').tooltip();
}

$(document).ready(function() {
	dicas();
});
if (typeof jQuery !== 'undefined') {
	(function($) {
		$('#spinner').ajaxStart(function() {
			$(this).fadeIn();
		}).ajaxStop(function() {
			$(this).fadeOut();
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
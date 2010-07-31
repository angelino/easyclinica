//processada assim que a renderiza��o da p�gina � conclu�da
$(document).ready(function(){
		setup(document);
});

function setup(main_selector){
	criar_mascaras_campos(main_selector);
	
	check_all_checkboxes(main_selector);
}

/*
	Para adicionar m�scara para determinado campo, adicionar as seguintes class no mesmo.
	
	mask_telefone: (99) 9999-9999
	mask_cep:			 99999-999
*/
function criar_mascaras_campos(main_selector){
	$(main_selector).find('.mask_telefone').mask('(99) 9999-9999');
	$(main_selector).find('.mask_cep').mask('99999-999');
}

/*
	mecanismo de sele��o multipla de checkbox. Exemplo:
	defina para o checkbox que ativar� a sele��o multipla a classe check_all e na propriedade rel defina
	o rel dos checkboxes que ser�o controlados.
*/
function check_all_checkboxes(main_selector) {
	$(main_selector).find('.check_all').change(function() {
		var pai = $(this);
		var rel = $(pai).attr('rel');
		
		$(main_selector).find("input[rel='" + rel + "']").each(function(index){
				$(this).attr('checked', $(pai).attr('checked'));
		});	
		
	});
}
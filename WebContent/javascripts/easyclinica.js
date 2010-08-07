//processada assim que a renderização da página é concluída
$(document).ready(function(){
		setup(document);
});

function setup(main_selector){
	criar_mascaras_campos(main_selector);
	
	check_all_checkboxes(main_selector);
	
	ativar_opcao_menu_principal();
}

/* 
	Método responsável por ativas a opção selecionada no menu active
*/
function ativar_opcao_menu_principal() {
	//desativar todas as opções do menu
	$('#menu-principal li').each(function(index){
		$(this).removeClass('active');
	});
	
	var url = document.location.href;
	var partes = url.split('/');
	
	for(var i = partes.length - 1; i >= 0; i--)
	{
		var opcao = $('#menu-link-' + partes[i]);
		if($(opcao).length) {
			$(opcao).addClass('active');
			break;
		}
	}	
}

/*
	Para adicionar máscara para determinado campo, adicionar as seguintes class no mesmo.
	
	mask_telefone: (99) 9999-9999
	mask_cep:			 99999-999
*/
function criar_mascaras_campos(main_selector){
	$(main_selector).find('.mask_telefone').mask('(99) 9999-9999');
	$(main_selector).find('.mask_cep').mask('99999-999');
}

/*
	mecanismo de seleção multipla de checkbox. Exemplo:
	defina para o checkbox que ativará a seleção multipla a classe check_all e na propriedade rel defina
	o rel dos checkboxes que serão controlados.
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

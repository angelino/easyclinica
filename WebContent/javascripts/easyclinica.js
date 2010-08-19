//processada assim que a renderização da página é concluída
$(document).ready(function(){
		setup(document);
});

function setup(main_selector){
	criar_mascaras_campos(main_selector);
	
	check_all_checkboxes(main_selector);
	
	ativar_opcao_menu_principal();
	
	gerenciar_abas(main_selector);
	
	deletar_registros(main_selector);
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
  	Método responsável pelo mecanismo de abas
*/
function gerenciar_abas(main_selector) {
	$('.abas li:first-child').addClass('active');
	
	$('.abas li a').each(function(index){
		var div_conteudo = $(this).attr('href');
		if(index == 0) $(div_conteudo).show();
		else $(div_conteudo).hide();
	});
	
	$(main_selector).find('.abas li a').click(function() {
		var a = $(this);
		var li = $(a).parent();
		var abas = $(li).parent(); // ul - li - a		

		// esconde todos os divs e retira active dos li
		$(abas).find('li a').each(function(index){
			var div_conteudo = $(this).attr('href');
			$(div_conteudo).hide();
			
			$(this).parent().removeClass('active');
		});
		
		$(li).addClass('active');
		
		$($(a).attr('href')).show();
	});
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

/*
 * Método ajax para deletar registros.
 * Colocar a class 'delete' e o href deve ser a url a ser chamada. O link deve estar dentro de uma tag span
 */
function deletar_registros(main_selector) {
	$(main_selector).find(".delete").click(function(event) {
		
		var ok = confirm('Você tem certeza que deseja inativar esse médico?');		
		if(!ok) return false;
		
		var a = $(this);
		var url = $(a).attr('href');
		
		var span = $(this).parent();
		$(span).html("<img src='/easyclinica/images/loading.gif' />");		
		
		$.ajax({
			type: 'POST',
			url: url,
			data: '_method=DELETE',
			cache: false,
			dataType: 'json',
			success: function(data) {
				var td = $('#name_' + data.doctor.id);
				var name = $(td).html();
				var new_name_value = "<span class='deactivated-item'>" + name + "</span> (inativo)";
				
				$(td).html(new_name_value);
				
				$(span).hide();
			},
			error: function(xhr, ajaxOptions, thrownError){
                alert(xhr.status);
                alert(thrownError);				
			}
		});
		
		return false;
	});
}

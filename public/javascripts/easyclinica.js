//processada assim que a renderização da página é concluída
$(document).ready(function(){
		setup();
});

function setup(){
	criar_mascaras_campos();
}

/*
	Para adicionar máscara para determinado campo, adicionar as seguintes class no mesmo.
	
	mask_telefone: (99) 9999-9999
	mask_cep:			 99999-999
*/
function criar_mascaras_campos(){
	$('.mask_telefone').mask('(99) 9999-9999');
	$('.mask_cep').mask('99999-999');
}
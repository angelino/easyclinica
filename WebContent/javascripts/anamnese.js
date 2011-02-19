EasyClinica.pages['anamneses'] = function(){
	
	$('.exibir').click(function(e){
		e.preventDefault();
		
		var anamnese_id = $(this).attr('anamnese_id');
		var url = EasyClinica.cfg.services.showAnamneseDetails;
		EasyClinica.lib.openModal(url, 'POST', { id: anamnese_id }, function(){});		
	});
	
};
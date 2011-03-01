EasyClinica.pages['pacientes'] = function(){
	
	showConfiguration();
	
	$('#btnBuscar').click(function(e){
		e.preventDefault();
		
		$(this).after("<img id='loading' src='" + EasyClinica.cfg.images.loading + "' alt='carregando...'/>");
		
		var url = EasyClinica.cfg.services.searchPatients;
		var texto = $('input[name=patient.textobusca]').val();
		
		$.post(url, {text: texto}, function(data){
			$('#box_listagem').html(data);
			EasyClinica.common.generalFunctions();
			showConfiguration();
			
			$('#loading').remove();
		});
	});
};

var showConfiguration = function() {
	$('.exibir').click(function(e){
		e.preventDefault();
		
		var patient_id = $(this).attr('patient_id');
		var url = EasyClinica.cfg.services.showPatientDetails;
		EasyClinica.lib.openModal(url, 'POST', { id: patient_id }, function(){});		
	});
};
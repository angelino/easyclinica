EasyClinica.pages['pacientes'] = function(){
	
	$('.exibir').click(function(e){
		e.preventDefault();
		
		var patient_id = $(this).attr('patient_id');
		var url = EasyClinica.cfg.services.showPatientDetails;
		EasyClinica.lib.openModal(url, 'POST', { id: patient_id }, function(){});		
	});
	
};
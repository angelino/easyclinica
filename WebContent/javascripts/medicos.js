EasyClinica.pages['medicos'] = function(){
	
	$('.exibir').click(function(e){
		e.preventDefault();
		
		var doctor_id = $(this).attr('doctor_id');
		var url = EasyClinica.cfg.services.showDoctorDetails;
		EasyClinica.lib.openModal(url, 'POST', { id: doctor_id }, function(){});		
	});
	
};
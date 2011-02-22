EasyClinica.pages['usuarios'] = function(){
	
	$('.exibir').click(function(e){
		e.preventDefault();
		
		var employee_id = $(this).attr('employee_id');
		var url = EasyClinica.cfg.services.showUserDetails;
		EasyClinica.lib.openModal(url, 'POST', { id: employee_id }, function(){
			EasyClinica.common.generalFunctions();
		});		
	});
	
};
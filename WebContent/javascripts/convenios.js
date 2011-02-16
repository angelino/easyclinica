EasyClinica.pages['convenios'] = function(){
	
	$('.exibir').click(function(e){
		e.preventDefault();
		
		var healthCarePlan_id = $(this).attr('healthCarePlan_id');
		var url = EasyClinica.cfg.services.showHealthCarePlanDetails;
		EasyClinica.lib.openModal(url, 'POST', { id: healthCarePlan_id }, function(){
			EasyClinica.common.generalFunctions();
		});		
	});
	
};
EasyClinica.pages['anamneses'] = function(){
	
	$("#txt_search_cid").autocomplete(EasyClinica.cfg.services.searchCID, {
		autoFill: false
	}).result(function(event, item) {
		$('#anamnese.cid.id').val(item[1]);
	});
	
};
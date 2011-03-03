EasyClinica.pages['pacientes'] = function(){
	
	showConfiguration();
	
	$('#btnBuscar').click(function(e){
		e.preventDefault();
		
		searchPatients(1);
	});
};

var searchPatients = function(page) {
	$(this).after("<img id='loading' src='" + EasyClinica.cfg.images.loading + "' alt='carregando...'/>");
	
	var url = EasyClinica.cfg.services.searchPatients;
	var texto = $('input[name=patient.textobusca]').val();
	
	$.post(url, {text: texto, page: page}, function(data){
		$('#box_listagem').html(data);
		EasyClinica.common.generalFunctions();
		showConfiguration();		
		managerPagination();
		
		$('#loading').remove();
	});
};

var managerPagination = function() {
	$('.boxpagination a[page]').click(function(e){
		e.preventDefault();
		
		var page = $(this).attr("page");
		searchPatients(page);
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
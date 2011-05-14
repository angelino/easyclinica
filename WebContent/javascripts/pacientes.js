EasyClinica.pages['pacientes'] = function(){
	
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


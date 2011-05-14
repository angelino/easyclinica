EasyClinica.pages['pacientes'] = function(){
	
	$('#btnBuscar').click(function(e){
		e.preventDefault();
		
		searchPatients(1);
	});
	
	var searchPatients = function(page) {
		$('#loading').show();
		
		var url = EasyClinica.cfg.services.searchPatients;
		var texto = $('input[name=patient.textobusca]').val();
		
		$.get(url, {text: texto, page: page}, function(data){
			$('#box_listagem').html(data);
			EasyClinica.common.generalFunctions();
			//showConfiguration();		
			managerPagination();
			
			$('#loading').hide();
		});
	};
	$('#loading').hide();
	
	var managerPagination = function() {
		$('.boxpagination a[page]').click(function(e){
			e.preventDefault();
			
			var page = $(this).attr("page");
			searchPatients(page);
		});
	};
	managerPagination();
	
	$('input[name=patient.textobusca]').keydown(function(event){
		if(event.keyCode == 13) {
			searchPatients(1);
	    }
	});
};
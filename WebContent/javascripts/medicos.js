EasyClinica.pages['medicos'] = function(){
	
	$('.exibir').click(function(e){
		e.preventDefault();
		
		var doctor_id = $(this).attr('doctor_id');
		var url = EasyClinica.cfg.services.showDoctorDetails;
		EasyClinica.lib.openModal(url, 'POST', { id: doctor_id }, function(){});		
	});
	
	$('#btnBuscar').click(function(e){
		e.preventDefault();
		
		searchDoctors(1);
	});
	

	var searchDoctors = function(page) {
		$(this).after("<img id='loading' src='" + EasyClinica.cfg.images.loading + "' alt='carregando...'/>");
		
		var url = EasyClinica.cfg.services.searchDoctors;
		var texto = $('input[name=doctor.textobusca]').val();
		
		$.get(url, {text: texto, page: page}, function(data){
			$('#box_listagem').html(data);
			EasyClinica.common.generalFunctions();
			manageDoctorPagination();
			
			$('#loading').remove();
		});
	};

	var manageDoctorPagination = function() {
		$('.boxpagination a[page]').click(function(e){
			e.preventDefault();
			
			var page = $(this).attr("page");
			searchDoctors(page);
		});
	};

	manageDoctorPagination();
};

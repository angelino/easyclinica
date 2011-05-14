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
		$('#loading').show();
		
		var url = EasyClinica.cfg.services.searchDoctors;
		var texto = $('input[name=doctor.textobusca]').val();
		
		$.get(url, {text: texto, page: page}, function(data){
			$('#box_listagem').html(data);
			EasyClinica.common.generalFunctions();
			manageDoctorPagination();
			
			$('#loading').hide();
		});
	};
	$('#loading').hide();
	
	var manageDoctorPagination = function() {
		$('.boxpagination a[page]').click(function(e){
			e.preventDefault();
			
			var page = $(this).attr("page");
			searchDoctors(page);
		});
	};
	manageDoctorPagination();
	
	$('input[name=doctor.textobusca]').keydown(function(event){
		if(event.keyCode == 13) {
			searchDoctors(1);
	    }
	});
};

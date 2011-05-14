EasyClinica.pages['convenios'] = function(){
	
	$('.exibir').click(function(e){
		e.preventDefault();
		
		var healthCarePlan_id = $(this).attr('healthCarePlan_id');
		var url = EasyClinica.cfg.services.showHealthCarePlanDetails;
		EasyClinica.lib.openModal(url, 'POST', { id: healthCarePlan_id }, function(){
			EasyClinica.common.generalFunctions();
		});		
	});
	
	$('#btnBuscar').click(function(e){
		e.preventDefault();
		
		searchPlans(1);
	});

	var searchPlans = function(page) {
		$(this).after("<img id='loading' src='" + EasyClinica.cfg.images.loading + "' alt='carregando...'/>");
		
		var url = EasyClinica.cfg.services.searchPlans;
		var texto = $('input[name=plan.textobusca]').val();
		
		$.get(url, {text: texto, page: page}, function(data){
			$('#box_listagem').html(data);
			EasyClinica.common.generalFunctions();
			managePlanPagination();
			
			$('#loading').remove();
		});
	};

	var managePlanPagination = function() {
		$('.boxpagination a[page]').click(function(e){
			e.preventDefault();
			
			var page = $(this).attr("page");
			searchPlans(page);
		});
	};
	
	managePlanPagination();

};
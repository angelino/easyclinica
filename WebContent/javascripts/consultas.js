EasyClinica.pages['consultas'] = function(){
	
	$('#btn_search_procedure').click(function(e){
		var text = $('#txt_search_procedure').val();
		
		EasyClinica.lib.openModal(EasyClinica.cfg.services.searchProcedure, 'POST', { text: text }, function(){			
			$('.add-procedure').click(function(e){
				e.preventDefault();
				
				var procedureId = $(this).attr('procedure_id');
				var convenioId = $("input[name=appointment.healthCarePlan.id]:checked").val();
				
				$.post(EasyClinica.cfg.services.newProcedureToAppointment, { procedureId: procedureId, convenioId: convenioId }, function(data){
					var index = $('#procedures .procedure').size();
					if(index == "") index = 0;
					data = data.replace(/#index#/g, index);
					
					$('#procedures').append(data);
					$('.modal').remove();
					
					configureAmountManager();
					configureRemoveActions();
					EasyClinica.common.generalFunctions();
					refreshAppointentValue();
				});		
			});			
		});
	});
	
	var configureRemoveActions = function() {
		$('.remove-procedure').click(function(e){
			e.preventDefault();
			
			var procedure = findRecursiveParent($(this),'.procedure');				
			procedure.remove();				
			refreshAppointentValue();
		});
		
		$('.remove-material, .remove-medicine').click(function(e){
			e.preventDefault();
			
			selector = '.' + $(this).attr('class').replace(/remove-/g, '');
			var element = findRecursiveParent($(this),selector);				
			element.remove();
			
			refreshAppointentValue();
		});
	};
	
	var refreshAppointentValue = function() {
		refreshProceduresValue();
		
		var valor_consulta = convertCurrencyToFloat($('#valor-consulta'));
		var valor_procedimentos = convertCurrencyToFloat($('#appointment-procedure-amount'));
		
		$('#appointment-amount').html(valor_consulta + valor_procedimentos).formatCurrency(EasyClinica.cfg.currency);
	};
	
	var refreshProceduresValue = function() {
		var appointment_procedure_amount = 0;
		
		$('.procedure').each(function(index){
			var procedure_total = convertCurrencyToFloat($(this).find('.procedure-total'));
							
			$(this).find('.material, .medicine').each(function(material_index){
				var qty = $(this).find('.qty').val();
				var amount = convertCurrencyToFloat($(this).find('.amount'));					
				var total = EasyClinica.lib.calculateAmount(qty,amount);
				
				$(this).find('.total').html(total).formatCurrency(EasyClinica.cfg.currency);
				
				procedure_total += total;
			});
			
			$(this).find('.procedure-amount').html(procedure_total).formatCurrency(EasyClinica.cfg.currency);
			
			appointment_procedure_amount += procedure_total;
		});
		
		$('#appointment-procedure-amount').html(appointment_procedure_amount).formatCurrency(EasyClinica.cfg.currency);
	};
	
	var configureAmountManager = function() {
		$('.qty, .amount').change(function(){
			refreshProceduresValue();
		});
	};

};
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
					refreshAppointentValue();
					EasyClinica.common.generalFunctions();
					EasyClinica.common.formValidation();
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
		
		var valor_consulta = $('#valor-consulta').html().convertToFloat();
		var valor_procedimentos = $('#appointment-procedure-amount').html().convertToFloat();
		var appointment_amount = valor_consulta + valor_procedimentos;
		
		$('#appointment-amount').html(appointment_amount.toString().formatCurrency(true));
	};
	
	var refreshProceduresValue = function() {
		var appointment_procedure_amount = 0;
		
		$('.procedure').each(function(index){
			var procedure_total = $(this).find('.procedure-total').html().convertToFloat();
							
			$(this).find('.material, .medicine').each(function(material_index){
				var qty = $(this).find('.qty').val();
				var amount = $(this).find('.amount').val();					
				var total = EasyClinica.lib.calculateAmount(qty,amount);
				
				$(this).find('.total').html(total.toString().formatCurrency(true));
				
				procedure_total += total.toString().convertToFloat();
			});
			
			$(this).find('.procedure-amount').html(procedure_total.toString().formatCurrency(true));
			
			appointment_procedure_amount += procedure_total.toString().convertToFloat();
		});
		
		$('#appointment-procedure-amount').html(appointment_procedure_amount.toString().formatCurrency(true));
	};
	
	var configureAmountManager = function() {
		$('.qty, .amount').change(function(){
			refreshAppointentValue();
		});
	};

	$('select[name=appointment.specialty.id]').change(function(){
		var specialty_id = $(this).val();
		
		if(specialty_id == 0) return;
		
		refreshMedicalAppointmentAmount(specialty_id);
		isReturn();
	});
	
	$('select[name=appointment.doctor.id]').change(function(){
		var doctorId = $(this).val();
		
		if(doctorId == 0) return;
		
		var url = EasyClinica.cfg.services.getDoctorSpecialty.format(doctorId);		
		$.get(url, function(data) {
			var specialty_id = data.specialty.id;
			$('select[name=appointment.specialty.id]').val(specialty_id);
			refreshMedicalAppointmentAmount(specialty_id);
			isReturn();
		});
	});
	
	var refreshMedicalAppointmentAmount = function(specialty_id) {
		var convenioId = $('input[name=appointment.healthCarePlan.id]:checked').val();
		
		var url = EasyClinica.cfg.services.getSpecialtyPrice.format(specialty_id, convenioId);		
		$.get(url, function(data) {
			var amount = data.precifiedSpecialty.amount;
			$('#valor-consulta').html(amount.toString().formatCurrency(true));
		});
	};
	
	var isReturn = function() {
		var specialty_id = $('select[name=appointment.specialty.id]').val();
		var patient_id = $('input[name=appointment.patient.id]').val();
		var convenio_id = $('input[name=appointment.healthCarePlan.id]:checked').val();
		
		var url = EasyClinica.cfg.services.verifyIfAppointmentIsReturn.format(patient_id, specialty_id, convenio_id);		
		$.get(url, function(data) {
			if(data.boolean) $('#aviso-retorno').show();
			else $('#aviso-retorno').hide();
		});
	};
};
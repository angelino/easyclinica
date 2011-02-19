EasyClinica.pages['consultas'] = function(){
	
	$('#informe-procedimento-message').hide();
		
	$("#txt_search_procedure").autocomplete(EasyClinica.cfg.services.searchProcedure, {
		autoFill: false
	}).result(function(event, item) {
		$('#selected_procedure_id').val(item[1]);
	});
	
	$('#btn_search_procedure').click(function(e){		
		e.preventDefault();
		
		var procedureId = $('#selected_procedure_id').val();
		
		if(procedureId == 0) {
			$('#informe-procedimento-message').show('slow');
			return;
		}
		
		var healthCarePlanId = $("input[name=appointment.healthCarePlan.id]:checked").val();
		addNewProcedure(procedureId, healthCarePlanId);
		
	});
	
	$('input[name=appointment.healthCarePlan.id]').change(function(){
		var healthCarePlanId = $(this).val();
		var procedures = new Array();
		var indice = 0;
		
		$('.procedure-id').each(function(index){
			var procedureId = $(this).val();
			procedures[indice] = procedureId;
			removeProcedure(procedureId);
			
			indice += 1;
		});
		
		for (var i = 0; i <procedures.length; i++){
			addNewProcedure(procedures[i], healthCarePlanId);
		}
		
		var specialtyId = $('select[name=appointment.specialty.id]').val();
		if(specialtyId == 0) return;
		refreshMedicalAppointmentAmount(specialtyId);
	});
	
	var addNewProcedure = function(procedureId, healthCarePlanId) {
		$.post(EasyClinica.cfg.services.newProcedureToAppointment, { procedureId: procedureId, healthCarePlanId: healthCarePlanId }, function(data){
			var index = $('.procedure-id').size();
			if(index == "") index = 0;
			data = data.replace(/#index#/g, index);
			
			$('.boxtotal').first().before(data);
			$('.modal').remove();
			
			configureAmountManager();
			configureRemoveActions();
			refreshAppointentValue();
			EasyClinica.common.generalFunctions();
			EasyClinica.common.formValidation();
			
			$("#txt_search_procedure").val("");
			$('#selected_procedure_id').val(0);
			$('#informe-procedimento-message').hide();
		});
	};
	
	var removeProcedure = function(procedureId) {
		$('tr[procedure_id=' + procedureId + ']').remove();
		refreshAppointentValue();
	};
	
	var configureRemoveActions = function() {
		$('.remove-procedure').click(function(e){
			e.preventDefault();
			
			if(!confirm('Deseja realmente deletar esse procedimento?')) return;
			
			var procedure_id = $(this).attr('procedure_id');				
			removeProcedure(procedure_id);
		});
		
		$('.remove-material, .remove-medicine').click(function(e){
			e.preventDefault();			
			var isMaterial = $(this).hasClass('remove-material');
			
			if(!confirm('Deseja realmente deletar esse ' + (isMaterial ? 'material' : 'medicamento') + '?')) return;
			
			var procedure_id = $(this).attr('procedure_id');
			
			selector = (isMaterial ? '.material' : '.medicine') + '-' + procedure_id;
			var element = findRecursiveParent($(this),selector);				
			element.remove();
			
			var table_space = $('#table-space-' + procedure_id);
			var rowspan = table_space.attr('rowspan');
			rowspan -= 1;
			table_space.attr('rowspan',rowspan);
			
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
		
		$('.procedure-id').each(function(index){
			var procedure_id = $(this).val();
			
			var procedure_total = $('.procedure-total-' + procedure_id).html().convertToFloat();
			
			$('.material-' + procedure_id + ', .medicine-' + procedure_id).each(function(material_index){
				var qty = $(this).find('.qty').val();
				var amount = $(this).find('.amount').val();					
				var total = EasyClinica.lib.calculateAmount(qty,amount);
				
				$(this).find('.total').html(total.toString().formatCurrency(true));
				
				procedure_total += total.toString().convertToFloat();
			});
			
			$('.procedure-amount-' + procedure_id).html(procedure_total.toString().formatCurrency(true));
			
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
		
		if(specialty_id == 0) {
			$('#valor-consulta').html('0.00'.formatCurrency(true));
			return;
		}
		
		refreshMedicalAppointmentAmount(specialty_id);
		isReturn();
	});
	
	$('input[name=appointment.appointmentDate]').change(function(){
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
			$('input[name=appointment.appointmentAmount]').val(amount);
		});
	};
	
	var isReturn = function() {
		var specialtyId = $('select[name=appointment.specialty.id]').val();
		var patientId = $('input[name=appointment.patient.id]').val();
		var healthCarePlanId = $('input[name=appointment.healthCarePlan.id]:checked').val();
		var appointmentDate = $('input[name=appointment.appointmentDate]').val();
		
		if(specialtyId == 0) return;
		
		var dados = {
				specialtyId: specialtyId, 
				patientId: patientId, 
				healthCarePlanId: healthCarePlanId,
				appointmentDate: appointmentDate
		};
		
		$.post(EasyClinica.cfg.services.verifyIfAppointmentIsReturn, dados, function(data){
			if(data.boolean) $('#aviso-retorno').show();
			else $('#aviso-retorno').hide();
		});
	};
};
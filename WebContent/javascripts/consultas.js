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
		refreshRoomRateAmount();
		
		var specialtyId = $('select[name=appointment.specialty.id]').val();
		if(specialtyId == 0) return;
		refreshMedicalAppointmentAmount(specialtyId);
				
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
			managerProcedureElements();
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
		var taxa_sala = $('input[name=appointment.roomRateAmount]').val().convertToFloat();
		var appointment_amount = valor_consulta + valor_procedimentos + taxa_sala;
		
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
	
	var refreshRoomRateAmount = function() {
		var convenioId = $('input[name=appointment.healthCarePlan.id]:checked').val();
		
		var url = EasyClinica.cfg.services.getHealthCarePlan.format(convenioId);		
		$.get(url, function(data) {
			var amount = 0;
			if(data.healthCarePlan.payForRoomRate) amount = data.healthCarePlan.roomRateDefaultAmount;
			
			$('input[name=appointment.roomRateAmount]').val(amount.toString().formatCurrency());
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
	
	var managerProcedureElements = function(){		
		hideFormToAddNewProcedureElements();
		
		$('.procedure-elements li a').click(function(e){
			e.preventDefault();
			
			hideFormToAddNewProcedureElements();
			$(this).next().show();
		});
		
		$('.new-assistant .btnclose').click(function(e){
			var boxNewAssistant = findRecursiveParent($(this), '.new-assistant');
			boxNewAssistant.hide();
		});
		
		$('.new-assistant .btnsave').click(function(e){
			e.preventDefault();
			
			var validator = $('#frm-new-assistant').data('validator');
	        if (validator && validator.checkValidity()) {
	            validator.destroy();
	            
	            generateAssistantTemplate();
	        } 
		});
	};
	
	var hideFormToAddNewProcedureElements = function(){
		$('.procedure-elements li div:first').hide();
	};
	
	var generateAssistantTemplate = function(){
		var form = $('#frm-new-assistant');
        var index = form.find('input[name=index]').val();
        var procedure_id = form.find('input[name=procedure_id]').val();
        var assistantName = form.find('input[name=assistantName]').val();
        var assistantType = form.find('select[name=assistantType]').val();
        var assistantCH = form.find('input[name=assistantCH]').val();
        var assistantAmount = form.find('input[name=assistantAmount]').val();
        
        var assistant_index = $('.assistant').size();
		if(assistant_index == "") assistant_index = 0;
        
        var template = "<tr class='assistant' procedure_id='#procedure_id#'>";
    			template += "<td>";
    				template += "<input type='hidden' name='appointment.procedures[#index#].assistants[#assistant_index#].name' value='#assistantName#' />";
    				template += "#assistantName#";
    			template += "</td>";
    			template += "<td>";
	    			template += "<input type='hidden' name='appointment.procedures[#index#].assistants[#assistant_index#].type' value='#assistantType#' />";
	    			template += "#assistantType#";
    			template += "</td>";
    			template += "<td>";
    				template += "<input type='text' class='qty currency' pattern='^[0-9]+(\,\d{1,2})?$' name='appointment.procedures[#index#].assistants[#assistant_index#].ch' value='#assistantCH#' />";
    			template += "</td>";
    			template += "<td class='total'>";
    				template += "<input type='text' class='amount currency' pattern='^[0-9]+(\,\d{1,2})?$' name='appointment.procedures[#index#].assistants[#assistant_index#].amount' value='#assistantAmount#' />";
    			template += "</td>";
    			template += "<td>";
    				template += "<a href='#' class='remove-assistant btndelete last' procedure_id='#procedure_id#'>Excluir</a>";
    			template += "</td>";
    		template += "</tr>";
    	
    	template = template.replace(/#index#/g, index);
    	template = template.replace(/#assistant_index#/g, assistant_index);
    	template = template.replace(/#procedure_id#/g, procedure_id);
    	template = template.replace(/#assistantName#/g, assistantName);
    	template = template.replace(/#assistantType#/g, assistantType);
    	template = template.replace(/#assistantCH#/g, assistantCH);
    	template = template.replace(/#assistantAmount#/g, assistantAmount);
    	
    	alert(template);
    	
    	$('.boxsubtotal').each(function(index){
    		var box_procedure_id = $(this).attr('procedure_id');
    		if(procedure_id == box_procedure_id) {
    			$(this).before(template);
    			alert("Opa");
    		}
    	});
	};
};
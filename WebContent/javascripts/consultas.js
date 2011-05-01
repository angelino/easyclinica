EasyClinica.pages['consultas'] = function(){

	$(function() {
		$('#aviso-retorno').hide();
		$('#sugestao-taxadesala').hide();

		$('#informe-procedimento-message').hide();
		
		$("#txt_search_procedure").autocomplete(EasyClinica.cfg.services.searchProcedure, {
			autoFill: false
		}).result(function(event, item) {
			$('#selected_procedure_id').val(item[1]);
		});
		
		$('#lnk-default-roomtax').click(function() {
			var roomTax = $('#lnk-default-roomtax').attr('data-value').toString().formatCurrency();
			$('input[name=appointment.roomRateAmount]').val(roomTax);
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
			checkIfRoomRateAmountIsStillNeeded();
			
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
	
	});
	

	
	var addNewProcedure = function(procedureId, healthCarePlanId) {
		$.post(EasyClinica.cfg.services.newProcedureToAppointment, { procedureId: procedureId, healthCarePlanId: healthCarePlanId }, function(data){
			var index = $('.procedure-id').size();
			if(index == "") index = 0;
			data = data.replace(/#index#/g, index);
			
			$('.boxtotal').first().before(data);
			$('.modal').remove();
			
			suggestRoomRate(index);
			configureAmountManager();
			configureRemoveActions();
			refreshAppointentValue();
			managerProcedureElements();
			EasyClinica.common.generalFunctions('tr[procedure_id='+ procedureId +']');
			EasyClinica.common.formValidation();
			roomRateAmountManager();
			
			$("input[name=materialName]").autocomplete(EasyClinica.cfg.services.searchMaterial, {
				autoFill: false
			}).result(function(event, item) {
				$('input[name=materialId]').val(item[1]);
			});
			
			$("input[name=medicineName]").autocomplete(EasyClinica.cfg.services.searchMedicine, {
				autoFill: false
			}).result(function(event, item) {
				$('input[name=medicineId]').val(item[1]);
			});
			
			$("#txt_search_procedure").val("");
			$('#selected_procedure_id').val(0);
			$('#informe-procedimento-message').hide();
		});
	};
	
	var removeProcedure = function(procedureId) {
		$('tr[procedure_id=' + procedureId + ']').remove();
		checkIfRoomRateAmountIsStillNeeded();
		refreshAppointentValue();
	};
	
	var suggestRoomRate = function(index) {
		var suggestedRoomRate = $('input[name=procedure-'+index+'-roomRate]').val();
		if(parseFloat(suggestedRoomRate) == 0) {
			suggestedRoomRate = $('#default-room-tax').val();
		}
		
		if(parseFloat(suggestedRoomRate) > 0) {
			var formattedRoomRate = suggestedRoomRate.toString().formatCurrency(true);
			$('#sugestao-taxadesala-info').html('A taxa de sala sugerida para essa consulta é de ' + formattedRoomRate
					+ ". Aceita? <a href='javascript:void(0);' id='lnk-accept-roomrate' data-value='"+suggestedRoomRate+"'>Sim</a>");
			
			$('#lnk-accept-roomrate').click('click', function() {
				var suggestedRoomRate = $('#lnk-accept-roomrate').attr('data-value');
				$('input[name=appointment.roomRateAmount]').val(suggestedRoomRate.toString().formatCurrency());
				$('#sugestao-taxadesala').hide();	
				refreshAppointentValue();
			});
			$('#sugestao-taxadesala').show();
		}
		else {
			$('#sugestao-taxadesala').hide();
		}
	};
	
	var configureRemoveActions = function(selector) {
		if (selector === undefined) selector = '*';
		
		$(selector).find('.remove-procedure').click(function(e){
			e.preventDefault();
			
			if(!confirm('Deseja realmente deletar esse procedimento?')) return;
			
			var procedure_id = $(this).attr('procedure_id');				
			removeProcedure(procedure_id);
		});
		
		$(selector).find('.remove-material, .remove-medicine').click(function(e){
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
		
		$(selector).find('.remove-assistant').click(function(e){
			e.preventDefault();
			
			if(!confirm('Deseja realmente deletar esse auxiliar?')) return;
			
			var procedure_id = $(this).attr('procedure_id');
			
			selector = '.assistant-' + procedure_id;
			var element = findRecursiveParent($(this),selector);				
			element.remove();
			
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
			
			var procedure_total = $('#procedure-total-' + procedure_id).val().convertToFloat();
			
			$('.material-' + procedure_id + ', .medicine-' + procedure_id).each(function(material_index){
				var qty = $(this).find('.qty').val();
				var amount = $(this).find('.amount').val();					
				var total = EasyClinica.lib.calculateAmount(qty,amount);
				
				$(this).find('.total').html(total.toString().formatCurrency(true));
				
				procedure_total += total.toString().convertToFloat();
			});
			
			$('.assistant-' + procedure_id).each(function(assistant_index){
				var amount = $(this).find('.amount').val().convertToFloat();
				
				procedure_total += amount;
			});
			
			$('.procedure-amount-' + procedure_id).html(procedure_total.toString().formatCurrency(true));
			
			appointment_procedure_amount += procedure_total.toString().convertToFloat();
		});
		
		$('#appointment-procedure-amount').html(appointment_procedure_amount.toString().formatCurrency(true));
	};
	
	var configureAmountManager = function(selector) {
		if (selector === undefined) selector = '*';
		
		$(selector).find('.qty, .amount').change(function(){
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
	
	var roomRateAmountManager = function() {
		$('input[name=appointment.roomRateAmount]').change(function(){
			refreshAppointentValue();
		});
		
		$('input[name=appointment.roomRateAmount]').keyup(function(key){
			onlyNumbersAndComma($(this), key);
		});
	};
	roomRateAmountManager();
	
	var refreshMedicalAppointmentAmount = function(specialty_id) {
		var convenioId = $('input[name=appointment.healthCarePlan.id]:checked').val();
		
		var url = EasyClinica.cfg.services.getSpecialtyPrice.format(specialty_id, convenioId);		
		$.get(url, function(data) {
			var amount = data.precifiedSpecialty.amount;
			$('#valor-consulta').html(amount.toString().formatCurrency(true));
			$('input[name=appointment.appointmentAmount]').val(amount);
			
			refreshAppointentValue();
		});
	};
	
	var checkIfRoomRateAmountIsStillNeeded = function() {
		var qtyProcedures = $('.procedure-id').size();
		if(qtyProcedures == 0) {
			$('input[name=appointment.roomRateAmount]').val('0'.formatCurrency());
			$('#sugestao-taxadesala').hide();
		}
	};
	
	var getHealthCarePlanData = function(planId, OnCreate) {
		var url = EasyClinica.cfg.services.getHealthCarePlan.format(planId);		
		$.get(url, function(data) {
			OnCreate(data);
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
			removeActiveFromMenuProcedureElements();
			
			$(this).next().show();
			$(this).addClass('active');
			
			if($(this).hasClass('new-assistant')) {
				var chDefault = getAssistantCHDefaultValue();
				$('input[name=assistantCH]').val(chDefault.toString());
				
				generateFormNewAssistantValidation();
			}
			
			if($(this).hasClass('new-material')) {
				generateFormNewMaterialValidation();
			}
			
			if($(this).hasClass('new-medicine')) {
				generateFormNewMedicineValidation();
			}
		});
		
		$('.procedure-elements .btnclose').click(function(e){
			var rel = $(this).attr('rel');			
			var box = $('div.' + rel);
			box.hide();
			
			switch(rel) {
				case 'new-assistant':
					removeFormNewAssistantValidation();
					break;					
				case 'new-material':
					removeFormNewMaterialValidation();
					break;
				case 'new-medicine':
					removeFormNewMedicineValidation();
					break;
			}
			
		});
		
		$('.new-assistant .btnsave').click(function(e){
			e.preventDefault();
			
			var validator = $('#frm-new-assistant').data('validator');
	        if (validator && validator.checkValidity()) {
	            validator.destroy();
	            
	            generateAssistantTemplate();
	            clearNewAssistantForm();
	        } 
		});
		
		$('.new-material .btnsave').click(function(e){
			e.preventDefault();
			
			var validator = $('#frm-new-material').data('validator');
	        if (validator && validator.checkValidity()) {
	            validator.destroy();
	            
	            generateMaterialTemplate();
	            clearNewMaterialForm();
	        } 
		});
		
		$('.new-medicine .btnsave').click(function(e){
			e.preventDefault();
			
			var validator = $('#frm-new-medicine').data('validator');
	        if (validator && validator.checkValidity()) {
	            validator.destroy();
	            
	            generateMedicineTemplate();
	            clearNewMedicineForm();
	        } 
		});
	};
	
	var getAssistantCHDefaultValue = function() {
		var procedure_id = $('.procedure-id').val();
		var ch = $('#procedure-ch-' + procedure_id).val();
		var qtyAssistants = $('.assistant').size();
		
		var chDefault = parseInt(ch * 0.3);
		if(qtyAssistants > 0) chDefault = parseInt(ch * 0.2);
		
		return chDefault;
	};
	
	var clearNewAssistantForm = function(){
		$('input[name=assistantName]').val('');
		
		var chDefault = getAssistantCHDefaultValue();
		$('input[name=assistantCH]').val(chDefault.toString());
	};
	
	var clearNewMaterialForm = function(){
		$('input[name=materialName]').val('');
		$('input[name=materialId]').val('0');
		$('input[name=materialQuantity]').val('0'.formatCurrency());
		$('input[name=materialUnitAmount]').val('0'.formatCurrency());
	};
	
	var clearNewMedicineForm = function(){
		$('input[name=medicineName]').val('');
		$('input[name=medicineId]').val('0');
		$('input[name=medicineQuantity]').val('0'.formatCurrency());
		$('input[name=medicineUnitAmount]').val('0'.formatCurrency());
	};
	
	var hideFormToAddNewProcedureElements = function(){
		$('.procedure-elements li > div').hide();
	};
	
	var removeActiveFromMenuProcedureElements = function() {
		$('.procedure-elements li a').removeClass('active');
	};
	
	var generateFormNewAssistantValidation = function(){
		$('input[name=assistantName]').attr('required','required');
		$('input[name=assistantCH]').attr('pattern',EasyClinica.cfg.validation.number);
		
		EasyClinica.common.generalFunctions();
	};
	
	var generateFormNewMaterialValidation = function(){
		$('input[name=materialName]').attr('required','required');
		$('input[name=materialId]').attr('min','1');
		$('input[name=materialId]').attr('data-message','Escolha um material válido');
		$('input[name=materialQuantity]').attr('pattern',EasyClinica.cfg.validation.currency);
		$('input[name=materialUnitAmount]').attr('pattern',EasyClinica.cfg.validation.currency);
		
		EasyClinica.common.generalFunctions();
	};
	
	var generateFormNewMedicineValidation = function(){
		$('input[name=medicineName]').attr('required','required');
		$('input[name=medicineId]').attr('min','1');
		$('input[name=medicineId]').attr('data-message','Escolha um medicamento válido');
		$('input[name=medicineQuantity]').attr('pattern',EasyClinica.cfg.validation.currency);
		$('input[name=medicineUnitAmount]').attr('pattern',EasyClinica.cfg.validation.currency);
		
		EasyClinica.common.generalFunctions();
	};

	var removeFormNewAssistantValidation = function() {
		$('input[name=assistantName]').removeAttr('required');
		$('input[name=assistantCH]').removeAttr('pattern');
		
		$('#frm-new-assistant').data('validator').destroy();
	};
	
	var removeFormNewMaterialValidation = function() {
		$('input[name=materialName]').removeAttr('required');
		$('input[name=materialId]').removeAttr('min');
		$('input[name=materialQuantity]').removeAttr('pattern');
		$('input[name=materialUnitAmount]').removeAttr('pattern');
		
		$('#frm-new-material').data('validator').destroy();
	};
	
	var removeFormNewMedicineValidation = function() {
		$('input[name=medicineName]').removeAttr('required');
		$('input[name=medicineId]').removeAttr('min');
		$('input[name=medicineQuantity]').removeAttr('pattern');
		$('input[name=medicineUnitAmount]').removeAttr('pattern');
		
		$('#frm-new-medicine').data('validator').destroy();
	};
	
	var generateAssistantTemplate = function(){
		var form = $('#frm-new-assistant');
        var index = $('input[name=index]').val();
        var procedure_id = $('input[name=procedure_id]').val();
        var assistantName = form.find('input[name=assistantName]').val();
        var assistantType = form.find('select[name=assistantType]').val();
        var assistantTypeName = form.find('select[name=assistantType]').text();
        var assistantCH = form.find('input[name=assistantCH]').val();
        
        var assistant_index = $('.assistant').size();
		if(assistant_index == "") assistant_index = 0;
        
        var template = "<tr class='assistant assistant-#procedure_id#' procedure_id='#procedure_id#'>";
        		template += "<td>&nbsp;</td>";
    			template += "<td>";
    				template += "<input type='hidden' name='appointment.procedures[#index#].assistants[#assistant_index#].name' value='#assistantName#' />";
    				template += "#assistantName#";
    				template += "<input type='hidden' name='appointment.procedures[#index#].assistants[#assistant_index#].type' value='#assistantType#' />";
	    			template += "(#assistantTypeName#)";
    			template += "</td>";
    			template += "<td colspan='2'>";
    				template += "<input type='text' class='qty number assistantCH' name='appointment.procedures[#index#].assistants[#assistant_index#].ch' value='#assistantCH#' />";
    			template += "</td>";
    			template += "<td class='total'>";
    				template += "<input type='text' class='amount currency' name='appointment.procedures[#index#].assistants[#assistant_index#].amount' value='' />";
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
    	template = template.replace(/#assistantTypeName#/g, assistantTypeName);
    	template = template.replace(/#assistantCH#/g, assistantCH);
    	
    	$('.header-materials-medicine').each(function(index){
    		var box_procedure_id = $(this).attr('procedure_id');
    		if(procedure_id == box_procedure_id) {
    			$(this).before(template);
    		}
    	});
    	
    	var convenioId = $('input[name=appointment.healthCarePlan.id]:checked').val();
        getHealthCarePlanData(convenioId, function(data){
        	var assistantAmount = assistantCH.convertToFloat() * data.healthCarePlan.ch;
        	
        	$('.assistant').last().find('.amount').val(assistantAmount.toString().formatCurrency());
        	
        	configureAmountManager('.assistant-'+procedure_id);
			configureRemoveActions('.assistant-'+procedure_id);
			refreshAppointentValue();
			assistantCHChange('.assistant-'+procedure_id);
			EasyClinica.common.generalFunctions('.assistant-'+procedure_id);
			EasyClinica.common.formValidation();
        });
	};
	
	var assistantCHChange = function(selector){
		if (selector === undefined) selector = '*';
		
		$(selector).find('.assistantCH').change(function(){
			var assistantCH = $(this);
			var convenioId = $('input[name=appointment.healthCarePlan.id]:checked').val();
	        getHealthCarePlanData(convenioId, function(data){
	        	var assistantAmount = assistantCH.val().convertToFloat() * data.healthCarePlan.ch;
	        	
	        	var amountField = findRecursiveParent(assistantCH, 'tr');
	        	amountField.find('.amount').val(assistantAmount.toString().formatCurrency());
	        	
	        	refreshAppointentValue();
			});
		});
	};
	
	var generateMaterialTemplate = function(){
		var form = $('#frm-new-material');
		var index = $('input[name=index]').val();
        var procedure_id = $('input[name=procedure_id]').val();
        var materialName = form.find('input[name=materialName]').val();
        var materialId = form.find('input[name=materialId]').val();
        var materialQuantity = form.find('input[name=materialQuantity]').val();
        var materialUnitAmount = form.find('input[name=materialUnitAmount]').val();
        var materialAmount = materialQuantity.convertToFloat() * materialUnitAmount.convertToFloat();
        
        var material_index = $('.material-'+procedure_id).size();
		if(material_index == "") material_index = 0;
        
        var template = "<tr class='material-#procedure_id#' procedure_id='#procedure_id#'>";
        		template += "<td><input type='hidden' name='appointment.procedures[#index#].materials[#material_index#].material.id' value='#materialId#' />#materialName#</td>";
    			template += "<td><input type='text' class='qty currency' name='appointment.procedures[#index#].materials[#material_index#].qty' value='#materialQuantity#' /></td>";
    			template += "<td><input type='text' class='amount currency' name='appointment.procedures[#index#].materials[#material_index#].unitAmount' value='#materialUnitAmount#' /></td>";
    			template += "<td class='total currency'>#materialAmount#</td>";
    			template += "<td>";
    				template += "<a href='#' class='remove-material btndelete last' procedure_id='#procedure_id#'>Excluir</a>";
    			template += "</td>";
    		template += "</tr>";
    	
    	template = template.replace(/#index#/g, index);
    	template = template.replace(/#material_index#/g, material_index);
    	template = template.replace(/#procedure_id#/g, procedure_id);
    	template = template.replace(/#materialName#/g, materialName);
    	template = template.replace(/#materialId#/g, materialId);
    	template = template.replace(/#materialQuantity#/g, materialQuantity);
    	template = template.replace(/#materialUnitAmount#/g, materialUnitAmount);
    	template = template.replace(/#materialAmount#/g, materialAmount);
    	
    	$('.material-'+procedure_id).last().after(template);
    	
    	var table_space = $('#table-space-' + procedure_id);
		var rowspan = table_space.attr('rowspan');
		rowspan += 1;
		table_space.attr('rowspan',rowspan);
    	
    	configureAmountManager('.material-'+procedure_id);
		configureRemoveActions('.material-'+procedure_id);
		refreshAppointentValue();
		assistantCHChange('.material-'+procedure_id);
		EasyClinica.common.generalFunctions('.material-'+procedure_id);
		EasyClinica.common.formValidation();
	};
	
	var generateMedicineTemplate = function(){
		var form = $('#frm-new-medicine');
		var index = $('input[name=index]').val();
        var procedure_id = $('input[name=procedure_id]').val();
        var medicineName = form.find('input[name=medicineName]').val();
        var medicineId = form.find('input[name=medicineId]').val();
        var medicineQuantity = form.find('input[name=medicineQuantity]').val();
        var medicineUnitAmount = form.find('input[name=medicineUnitAmount]').val();
        var medicineAmount = medicineQuantity.convertToFloat() * medicineUnitAmount.convertToFloat();
        
        var medicine_index = $('.medicine-'+procedure_id).size();
		if(medicine_index == "") medicine_index = 0;
        
        var template = "<tr class='medicine-#procedure_id#' procedure_id='#procedure_id#'>";
        		template += "<td><input type='hidden' name='appointment.procedures[#index#].medicines[#medicine_index#].medicine.id' value='#medicineId#' />#medicineName#</td>";
    			template += "<td><input type='text' class='qty currency' name='appointment.procedures[#index#].medicines[#medicine_index#].qty' value='#medicineQuantity#' /></td>";
    			template += "<td><input type='text' class='amount currency' name='appointment.procedures[#index#].medicines[#medicine_index#].unitAmount' value='#medicineUnitAmount#' /></td>";
    			template += "<td class='total currency'>#medicineAmount#</td>";
    			template += "<td>";
    				template += "<a href='#' class='remove-medicine btndelete last' procedure_id='#procedure_id#'>Excluir</a>";
    			template += "</td>";
    		template += "</tr>";
    	
    	template = template.replace(/#index#/g, index);
    	template = template.replace(/#medicine_index#/g, medicine_index);
    	template = template.replace(/#procedure_id#/g, procedure_id);
    	template = template.replace(/#medicineName#/g, medicineName);
    	template = template.replace(/#medicineId#/g, medicineId);
    	template = template.replace(/#medicineQuantity#/g, medicineQuantity);
    	template = template.replace(/#medicineUnitAmount#/g, medicineUnitAmount);
    	template = template.replace(/#medicineAmount#/g, medicineAmount);
    	
    	$('.medicine-'+procedure_id).last().after(template);
    	
    	var table_space = $('#table-space-' + procedure_id);
		var rowspan = table_space.attr('rowspan');
		rowspan += 1;
		table_space.attr('rowspan',rowspan);
    	
    	configureAmountManager('.medicine-'+procedure_id);
		configureRemoveActions('.medicine-'+procedure_id);
		refreshAppointentValue();
		assistantCHChange('.medicine-'+procedure_id);
		EasyClinica.common.generalFunctions('.medicine-'+procedure_id);
		EasyClinica.common.formValidation();
	};
};
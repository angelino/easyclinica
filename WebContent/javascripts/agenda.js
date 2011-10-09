EasyClinica.pages['agenda'] = function(){
	$('.loading').hide();
	
	$('#btnCarregarCompromissos').click(function(e){
		e.preventDefault();
		
		if(!validarForm()) return;
	
		carregarListaDeCompromissos();
	});
	
	var validarForm = function() {
		var validator = $('form').data('validator');
		if (validator && validator.checkValidity()) {
			validator.destroy();
			return true;
		}		
		return false;
	};
	
	var carregarListaDeCompromissos = function() {
		$('.loading').show();
		
		var doctorId = $('select[name=schedule.doctor.id]').val();
		var date = $('input[name=schedule.startTime]').val();
		
		dados = "doctorId={0}&date={1}".format(doctorId, date);
		
		$.ajax({
	        type: 'POST',
	        url: EasyClinica.cfg.services.scheduleList,
	        data: dados,
	        success: function(data) {
	        	$('#horarios').html(data);
				
				EasyClinica.common.generalFunctions('#horarios');
				
				configureArrivalTimeChange();
				configureTreatedFlag();
				configureDeleteButton();
				configureAddCompromiseFunctions();
				configurePatientAutoComplete();
				
				$('.loading').hide();
				$('.infocompromisso').hide();
	        }
	    });
	};
	
	var configurePatientAutoComplete = function() {
		$("input[easyautocomplete=true]").autocomplete(EasyClinica.cfg.services.searchPatient, {
			autoFill: false
		}).result(function(event, item) {
			$('input[name=schedule.patient.id]').val(item[1]);
		});
	};
	
	var configureAddCompromiseFunctions = function() {
		
		$('#horarios').find('.btnaddevent').click(function(e){
			e.preventDefault();
			
			var subjectField = $(this).next('input[name=schedule.subject]');			
			subjectField.show(200);
			
			var subjectDescription = subjectField.next('.infocompromisso');
			subjectDescription.show(200);
		});
		
		$('input[name=schedule.subject]').hide();
		$('input[name=schedule.subject]').keyup(function(event){
			if (event.keyCode == '13') {
			     event.preventDefault();
			     
			     var doctorId = $('select[name=schedule.doctor.id]').val();
					
				 var subjectField = $(this);			
				 subjectField.addClass('ac_loading');
				
				 var patientId = subjectField.next('input[type=hidden]').val();
				 
				 var time = $('span#' + subjectField.attr('time_ref')).html(); 
				 var startTime = $('input[name=schedule.startTime]').val() + ' ' + time;
				 var fakeStartTime = $("<input type='text' name='schedule.startTime' value='" + startTime+ "' />");
				
				 
				 var dadosFormat = 'schedule.doctor.id={0}&{1}&{2}';
				 var dados = dadosFormat.format(doctorId, fakeStartTime.serialize(), subjectField.serialize());
				 
				 if(patientId > 0) {
					 dados += '&schedule.patient.id={0}'.format(patientId);
				 }
				
				 $.ajax({
			        type: 'POST',
			        url: EasyClinica.cfg.services.scheduleSave,
			        data: dados,
			        success: function(json) {
			        	if(json.status == '1') {
			        		carregarListaDeCompromissos();
			        	}
			        }
			     });
			}
		});		
	};
	
	var configureArrivalTimeChange = function() {
		$('#horarios').find('a.changeArrivalTime').click(function(e){
			e.preventDefault();
			
			var scheduleId = $(this).attr('schedule_id');
			
			var arrivalTimeField = $('input[name=arrivalTime-'+scheduleId+']');
			arrivalTimeField.addClass('ac_loading');
						
			var arrivalTimeValue = $('input[name=schedule.startTime]').val() + ' ' + arrivalTimeField.val();
			var fakeField = $("<input type='text' name='arrivalTime' value='" + arrivalTimeValue + "' />");
			
			var dados = '_method=PUT&scheduleId=' + scheduleId + '&' + fakeField.serialize();
			
			$.ajax({
		        type: 'POST',
		        url: EasyClinica.cfg.services.scheduleChangeArrivalTime,
		        data: dados,
		        success: function(json) {
		        	if(json.status == '1') {
		        		arrivalTimeField.removeClass('ac_loading');
		        		arrivalTimeField.css('border-color','#00FF00');
		        	}
		        }
		    });
		});
	};
	
	var configureTreatedFlag = function() {
		$('#horarios').find('input[name=treated]').change(function() {
			var scheduleId = $(this).attr('schedule_id');
			
			var dados = '_method=PUT&scheduleId=' + scheduleId;
			
			var url = '';
			if($(this).is(':checked')) {
				url = EasyClinica.cfg.services.scheduleSetAsTreated;
			} else {
				url = EasyClinica.cfg.services.scheduleSetAsNotTreated;
			}
			
			$.ajax({
		        type: 'POST',
		        url: url,
		        data: dados,
		        success: function(json) { }
		    });			
		});
	};
	
	var configureDeleteButton = function() {
		$('#horarios').find('.btndelete[schedule_id]').click(function(e){
			e.preventDefault();
			
			if(!confirm('Deseja realmente deletar esse compromisso?')) return;
			
			var scheduleId = $(this).attr('schedule_id');
			
			var dados = '_method=DELETE&scheduleId=' + scheduleId;
			
			$.ajax({
		        type: 'POST',
		        url: EasyClinica.cfg.services.scheduleDelete,
		        data: dados,
		        success: function(json) { 
		        	if(json.status == 1) {
		        		var trCompromisso = $('tr[schedule_id=' + scheduleId + ']');
		        		var table = trCompromisso.parent();
		        		trCompromisso.remove();
		        		
		        		if(table.find('tr').size() == 0) table.remove();
		        	}
		        }
		    });
		});		
	};
};
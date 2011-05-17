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
		
		$.get(EasyClinica.cfg.services.scheduleList, {doctorId: doctorId, date: date}, function(data){
			$('#horarios').html(data);
			
			EasyClinica.common.generalFunctions('#horarios');
			
			configurarArrivalTimeChange();
			configurarFlagAtendido();
			configurarBotaoExcluir();
			
			$('.loading').hide();
		});
	};
	
	var configurarArrivalTimeChange = function() {
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
	
	var configurarFlagAtendido = function() {
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
	
	var configurarBotaoExcluir = function() {
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
		        		
		        		if(table.find('tr').size() == 1) table.remove();
		        	}
		        }
		    });
		});		
	};
};
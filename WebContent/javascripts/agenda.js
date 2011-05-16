EasyClinica.pages['agenda'] = function(){
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
		var doctorId = $('select[name=schedule.doctor.id]').val();
		var date = $('input[name=schedule.startTime]').val();
		
		$.get(EasyClinica.cfg.services.scheduleList, {doctorId: doctorId, date: date}, function(data){
			$('#horarios').html(data);
		});
	};
};
EasyClinica.pages['agenda'] = function(){
	alert('Opa');
	
	$('input[name=schedule.startTime]').change(function(){
		if(!validarForm()) return;
	
		carregarListaDeCompromissos();
	});
	
	$('select[name=schedule.doctor.id]').change(function(){
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
		var doctor = $('select[name=schedule.doctor.id]').val();
		var date = $('input[name=schedule.startTime]').val();
		
		var url = EasyClinica.cfg.services.scheduleList.format(doctor, date);
		
		$.get(url, {}, function(data){
			
		});
	};
};
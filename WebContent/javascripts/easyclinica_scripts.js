$(function(){
	EasyClinica.runCommon();
	EasyClinica.activeMenuOption();
});

var EasyClinica = { 
	common: {}, pages: {}, page: {}, cfg: {}, lib: {}, registry: {},
	
	getMenuOption: function() {
		var url = document.location.href;
		var partes = url.split('/');
		
		for(var i = partes.length - 1; i >= 0; i--)
		{
			var opcao = $('#menu-link-' + partes[i]);
			if($(opcao).length) return $(opcao);
		}
		return null;
	},
	activeMenuOption: function() {
		$('#menu-principal li').each(function(index){
			$(this).removeClass('active');
		});
		
		var opcaoMenu = EasyClinica.getMenuOption();
		opcaoMenu.addClass('active');
	},
	runCommon: function() {
		for (var c in EasyClinica.common) {
			EasyClinica.common[c]();
		}
	}	
};

/* SERVIÇOS AJAX */
EasyClinica.cfg.services = {
		procedure: '/easyclinica/appointments/_newProcedureToAppointment'
};

EasyClinica.common.appointment = function(){
	
	$('#btn_search_procedure').click(function(e){
		var value = $('#txt_search_procedure').val();
		$.post(EasyClinica.cfg.services.procedure, { value: value }, function(data){
			var index = $('#procedures .procedure').size();
			if(index == "") index = 0;
			data = data.replace(/#index#/i, index);
			
			$('#procedures').append(data);
		});
	});

};


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
		if(opcaoMenu != null) opcaoMenu.addClass('active');
	},
	runCommon: function() {
		for (var c in EasyClinica.common) {
			EasyClinica.common[c]();
		}
	}	
};

/* SERVIÇOS AJAX */
EasyClinica.cfg.services = {
		searchProcedure: '/easyclinica/procedures/_searchProcedure',
		newProcedureToAppointment: '/easyclinica/appointments/_newProcedureToAppointment'
};

/* COMMON */
EasyClinica.common.appointment = function(){
	
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
					data = data.replace(/#index#/i, index);
					
					$('#procedures').append(data);
					$('.modal').remove();
				});		
			});			
		});
	});

};

/* LIB */

EasyClinica.lib.openModal = function (contentUrl, type, parameters, onCreate) {
	var today = new Date();
	var id_modal = today.getDate() + today.getDay() + today.getHours() + today.getMinutes() + today.getSeconds() + today.getMilliseconds();

	var conteudo_modal = "<div id='" + id_modal + "' class='modal'>";
		conteudo_modal += "<div class='modal-background'></div>";
			conteudo_modal += "<div class='modal-popup'>";
			conteudo_modal += "<a class='modal-close' rel='" + id_modal + "' href=''>X</a>";
			conteudo_modal += "<div id='conteudo-modal'>";	
			conteudo_modal += "</div>";
		conteudo_modal += "</div>";
	conteudo_modal += "</div>";
	
	$('body').append(conteudo_modal);
	
	if(type == 'POST') {
		$.post(contentUrl, parameters, function(html){
			$('#conteudo-modal').html(html);
			onCreate();
		});
	}
	else {
		$.get(contentUrl, function(html) {
			$('#conteudo-modal').html(html);
			onCreate();
		});
	}	
	
	$('.modal-close').click(function(e){
		e.preventDefault();
		var rel = $(this).attr('rel');
		$('#' + rel).remove();
	});
};

/* Other functions */
String.prototype.format = function(){
    var pattern = /\{\d+\}/g;
    var args = arguments;
    return this.replace(pattern, function(capture){ return args[capture.match(/\d+/)]; });
}
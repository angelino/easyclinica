$(function(){
	EasyClinica.runCommon();
});

var EasyClinica = { 
	common: {}, pages: {}, page: {}, cfg: {}, lib: {}, registry: {},

	runCommon: function() {
		for (var c in EasyClinica.common) {
			EasyClinica.common[c]();
		}
	}	
};

/* SERVIÇOS AJAX */
EasyClinica.cfg.services = {
		procedure: '/easyclinica/procedures/_search'
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
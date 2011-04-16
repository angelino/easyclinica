EasyClinica.pages['reports'] = function(){
	
	var formUrl;
	
	$(function() {
		$( "#accordion" ).accordion();
		formUrl = $('#form').attr('action');
		
		// Datepicker
		$('.datepicker2').datepicker(EasyClinica.cfg.datepicker_with_dashes);
		
	});
	
	$('#generateFinancialReport').click(function() {
		
		 var form = $('#form');
		 
		 var start = $('#start').val();
		 var end = $('#end').val();
		 var plan = $('#planId').val();
		 var doctor = $('#doctorId').val();
		 
		 var newUrl = formUrl + "/" + start + "/" + end + "/" + plan + "/" + doctor;
		 form.attr('action', newUrl);
		 
	});
	
};
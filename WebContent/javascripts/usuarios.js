EasyClinica.pages['usuarios'] = function(){
	
	$('.exibir').click(function(e){
		e.preventDefault();
		
		var employee_id = $(this).attr('employee_id');
		var url = EasyClinica.cfg.services.showUserDetails;
		EasyClinica.lib.openModal(url, 'POST', { id: employee_id }, function(){
			EasyClinica.common.generalFunctions();
		});		
	});
	
	setDoctorAsTheSelectedOne = function() {
		$('#doctorId').val($('#selectedDoctor').val());		
	};
	
	setDoctorAsNull = function() {
		$('#doctorId').val('');
	};
	
	$('#position').change(function() {
		var position = $('#position').val();
		if(position == 'DOCTOR') {
			$('#doctors').show();
			setDoctorAsTheSelectedOne();
		}
		else {
			$('#doctors').hide();
			setDoctorAsNull();
		}
		
	});
	
	$(function() {
		var position = $('#position').val();
		if(position != 'DOCTOR') {
			$('#doctors').hide();
		}
	});
	
};
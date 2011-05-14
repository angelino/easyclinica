EasyClinica.pages['recibos'] = function(){
	
	$('.exibir').click(function(e){
		e.preventDefault();
		
		var recibo_id = $(this).attr('recibo_id');
		var url = EasyClinica.cfg.services.showReceiptDetails;
		EasyClinica.lib.openModal(url, 'POST', { id: recibo_id }, function(){});		
	});
	
	$('select[name=receipt.kinship]').change(function() {
		var kinship = $(this).val();
		
		if(kinship == 'ME') {
			$('input[name=receipt.birthDate]').val($('input[name=birthDate]').val());
		}
		else {
			$('input[name=receipt.birthDate]').val('');
		}
	});
	
};
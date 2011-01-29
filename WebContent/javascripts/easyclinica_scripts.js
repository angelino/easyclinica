$(function(){
	EasyClinica.activeMenuOption();
	
	EasyClinica.runPage();
	EasyClinica.runCommon();	
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
	},
	getPageName: function() {
		return $('.main').attr('id') || 'home';
	},
	runPage: function() {
		var pageName = EasyClinica.getPageName();
		if (EasyClinica.pages[pageName]) {
			EasyClinica.page = EasyClinica.pages[pageName]();
		}
	}
};

/* CONFIG */
EasyClinica.cfg.services = {
		searchProcedure: '/easyclinica/procedures/_searchProcedure',
		newProcedureToAppointment: '/easyclinica/appointments/_newProcedureToAppointment'
};

EasyClinica.cfg.currency = { 
	colorize:true, 
	region: 'pt-BR',
	decimalSymbol: ',',
	digitGroupSymbol: '.',
	groupDigits: true,
	symbol: 'R$',
	roundToDecimalPlace: 2
};

/* COMMON */
EasyClinica.common.generalFunctions = function(){
	
	//Currency: formatar campo como moeda, basta colocar class="currency"
	$('.currency').formatCurrency(EasyClinica.cfg.currency);	
	
	$('.currency').blur(function() {
		var parameters = clone(EasyClinica.cfg.currency);
		parameters.roundToDecimalPlace = -1;
		$(this).formatCurrency(parameters);
	}).keyup(function(e) {
		var e = window.event || e;
		var keyUnicode = e.charCode || e.keyCode;
		if (e !== undefined) {
			switch (keyUnicode) {
				case 16: break; // Shift
				case 17: break; // Ctrl
				case 18: break; // Alt
				case 27: this.value = ''; break; // Esc: clear entry
				case 35: break; // End
				case 36: break; // Home
				case 37: break; // cursor left
				case 38: break; // cursor up
				case 39: break; // cursor right
				case 40: break; // cursor down
				case 78: break; // N (Opera 9.63+ maps the "." from the number key section to the "N" key too!) (See: http://unixpapa.com/js/key.html search for ". Del")
				case 110: break; // . number block (Opera 9.63+ maps the "." from the number block to the "N" key (78) !!!)
				case 190: break; // .

				default: 
					var parameters = clone(EasyClinica.cfg.currency);
					parameters.roundToDecimalPlace = -1;
					$(this).formatCurrency(parameters);
					break;
			}
		}
	});
	
};

/* PAGES */
EasyClinica.pages['consultas'] = function(){
	
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
					
					configureAmountManager();
					configureRemoveActions();
					EasyClinica.common.generalFunctions();
					refreshProceduresValue();
				});		
			});			
		});
		
		var configureRemoveActions = function() {
			$('.remove-procedure').click(function(e){
				e.preventDefault();
				
				var procedure = findRecursiveParent($(this),'.procedure');				
				procedure.remove();				
				refreshProceduresValue();
			});
			
			$('.remove-material, .remove-medicine').click(function(e){
				e.preventDefault();
				
				$(this).parent().remove();				
				refreshProceduresValue();
			});
		};
		
		var refreshProceduresValue = function() {
			
			$('.procedure').each(function(index){
				var procedure_total = convertCurrencyToFloat($(this).find('.procedure-total'));
								
				$(this).find('.material li, .medicine li').each(function(material_index){
					var qty = $(this).find('.qty').val();
					var amount = convertCurrencyToFloat($(this).find('.amount'));					
					var total = EasyClinica.lib.calculateAmount(qty,amount);
					
					$(this).find('.total').html(total).formatCurrency(EasyClinica.cfg.currency);
					
					procedure_total += total;
				});
				
				$(this).find('.procedure-amount').html(procedure_total).formatCurrency(EasyClinica.cfg.currency);
			});
			
		};
		
		var configureAmountManager = function() {
			$('.qty, .amount').change(function(){
				refreshProceduresValue();
			});
		};
	
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

EasyClinica.lib.calculateAmount = function(qty, amount) {
	qty = parseFloat(qty);
	amount = parseFloat(amount);
	
	return qty * amount;
};

/* Other functions */
findRecursiveParent = function(element, selector) {
	var parent = element.parent();
	if(parent.is(selector)) return parent;
	
	return findRecursiveParent(parent, selector);
};

convertCurrencyToFloat = function(element) {
	return element.asNumber({
		region: 'pt-BR' 
	})/100;
};

String.prototype.format = function(){
    var pattern = /\{\d+\}/g;
    var args = arguments;
    return this.replace(pattern, function(capture){ return args[capture.match(/\d+/)]; });
};

clone = function(o) {
	return eval(uneval(o));
};
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
		return $('#main').attr('tela') || 'home';
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
		newProcedureToAppointment: '/easyclinica/appointments/_newProcedureToAppointment',
		getSpecialtyPrice: '/easyclinica/especialidades/{0}/{1}',
		getDoctorSpecialty: '/easyclinica/medicos/{0}/especialidade',
		verifyIfAppointmentIsReturn: '/easyclinica/pacientes/{0}/{1}/{2}/isReturn'
};

/* COMMON */
EasyClinica.common.generalFunctions = function(){
	
	// Currency
	$('.currency').each(function(index){
		var element = $(this);
		var isInput = element.is('input');
		
		var valor = (isInput ? element.val() : element.html());
		valor = valor.convertToFloat();
		
		if(isInput) element.val(valor.toString().formatCurrency());
		else element.html(valor.toString().formatCurrency(true));
	});
	$('input.currency').keyup(function(key){
		if(key.keyCode == '13') key.preventDefault();
		
		var texto = $(this).val();
		var floatingPointregExp = new RegExp('^[-+]?[0-9]*\,?[0-9]*$');
		if(!floatingPointregExp.test(texto)) {
			texto = texto.substring(0,texto.length -1);
			$(this).val(texto);
		}
	});
	
	// Máscaras
	$('.mask_telefone').mask('(99) 9999-9999');
	$('.mask_cep').mask('99999-999');
	
	// Datepicker
	$('.datepicker').datepicker({
		dateFormat: 'dd/mm/yy',
		showOtherMonths: true,
		selectOtherMonths: true,
		showAnim: 'drop'
	});
};

EasyClinica.common.formValidation = function () {	
	$('form').validator({
		lang: 'pt',
		position: 'center right'
	});	
	
    $.tools.validator.localize('pt', {
        '[required]': 'Ítem obrigatório'
    });
    
    $('input[type=submit], .submit').click(function (e) {
        e.preventDefault();
        
        var validator = $('form').data('validator');
        if (validator && validator.checkValidity()) {
            validator.destroy();
        }

        $('form').submit();
    });
    
    //messages
    $('input.currency').attr('data-message','valor inválido');
};

EasyClinica.common.disableSubmitButtonAfterSubmit = function() {
	$('form').submit(function(){
	    $('input[type=submit]', this).attr('disabled', 'disabled');
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
	qty = qty.convertToFloat();
	amount = amount.convertToFloat();
	
	return (qty * amount).toString().convertToFloat();
};

/* Other functions */
findRecursiveParent = function(element, selector) {
	var parent = element.parent();
	if(parent.is(selector)) return parent;
	
	return findRecursiveParent(parent, selector);
};

String.prototype.format = function(){
    var pattern = /\{\d+\}/g;
    var args = arguments;
    return this.replace(pattern, function(capture){ return args[capture.match(/\d+/)]; });
};

String.prototype.convertToFloat = function(){
	var valor = this;
	
	if(!isFloat(valor)) {
		valor = "0";	
		var regexCurrency = new RegExp(/(\d|,)/g);
		var matched = this.match(regexCurrency);
		if(matched != null) {
			valor = "";
			for (i = 0; i < matched.length; i++) {
				valor += (matched[i] == '' ? ',' : matched[i]);
			}
		}	
		valor = valor.replace(/,/g, '.');
	}
	
	return parseFloat(valor);
};

String.prototype.formatCurrency = function(putSymbol, decimalPlaces){
	if (putSymbol === undefined) putSymbol = false;
	if (decimalPlaces === undefined) decimalPlaces = 2;
	
	var valor = this.convertToFloat();
	valor = valor.toFixed(decimalPlaces).toString().replace(/[.]/g, ',');
	
	return (putSymbol ? 'R$ ' : '') + valor;
};

clone = function(o) {
	return eval(uneval(o));
};

isFloat = function(s)
{
	return s.length>0 && !(/[^0-9.]/).test(s) && (/\.\d/).test(s);
};

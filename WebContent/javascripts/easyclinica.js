$(function(){
	EasyClinica.setup();
	EasyClinica.activeMenuOption();
	
	EasyClinica.runPage();
	EasyClinica.runCommon();	
});

var EasyClinica = { 
	common: {}, pages: {}, page: {}, cfg: {}, lib: {}, registry: {},
	
	getMenuOption: function() {
		var opcao = $('#menu-link-' + EasyClinica.getPageName());
		if($(opcao).length) return $(opcao);
		
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
		return $('.box').attr('id') || 'dashboard';
	},
	runPage: function() {
		var pageName = EasyClinica.getPageName();
		if (EasyClinica.pages[pageName]) {
			EasyClinica.page = EasyClinica.pages[pageName]();
		}
	},
	setup: function() {
		// alinhamento do box lateral direito
		if($('.boxmenu').length > 0) {
			$('.boxright').css('margin-top', '55px');
		}
		
		$(window).keydown(function(event){
		    if(event.keyCode == 13) {
		      event.preventDefault();
		      return false;
		    }
		});
	}
};

/* CONFIG */
EasyClinica.cfg.services = {
		newProcedureToAppointment: '/easyclinica/appointments/_newProcedureToAppointment',
		getSpecialtyPrice: '/easyclinica/especialidades/{0}/{1}',
		getDoctorSpecialty: '/easyclinica/medicos/{0}/especialidade',
		verifyIfAppointmentIsReturn: '/easyclinica/appointments/isReturn',
		searchProcedure: '/easyclinica/procedures/_search',
		
		showDoctorDetails: '/easyclinica/doctor/_show',
		showHealthCarePlanDetails: '/easyclinica/healthCarePlan/_show',
		showPatientDetails: '/easyclinica/patient/_show',
		showAnamneseDetails: '/easyclinica/anamnese/_show',
		
		scheduleList: '/easyclinica/medicos/{0}/agenda/_list',
		scheduleQuickAdd: '/easyclinica/medicos/{0}/agenda/_quickAdd',
		scheduleUpdate: '/easyclinica/medicos/{0}/agenda/_quickUpdate',
		scheduleRemove: '/easyclinica/medicos/{0}/agenda/_delete',
		scheduleNew: '/easyclinica/medicos/{0}/agenda/_new',
		scheduleEdit: '/easyclinica/medicos/{0}/agenda/{1}/_edit',
		
		postReply: '/easyclinica/mensagens/{0}/respostas/_nova',
		postMessage: '/easyclinica/mensagens/_nova',
		recentMessages: '/easyclinica/mensagens/_recentes',
			
		showUserDetails: '/easyclinica/usuarios/_show'
};

EasyClinica.cfg.images = {
	loading: '/easyclinica/images/loading.gif'
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
	
	// Mascaras
	$('.mask_telefone').mask('(99) 9999-9999');
	$('.mask_cep').mask('99999-999');
	$('.mask_cnpj').mask('99.999.999/9999-99');
	
	// Datepicker
	$('.datepicker').datepicker({
		dateFormat: 'dd/mm/yy',
		showOtherMonths: true,
		selectOtherMonths: true,
		showAnim: 'drop'
	});
	
	// Datetime Picker
	$('.datetimepicker').datetimepicker({
		timeFormat: 'hh:mm',
		dateFormat: 'dd/mm/yy',
		timeOnlyTitle: 'Escolha um Horário',
		timeText: 'Harário',
		hourText: 'Hora',
		minuteText: 'Minutos',
		secondText: 'Segundos',
		currentText: 'Agora',
		closeText: 'Ok'
	});
	
	// botão voltar
	$('.btnback').click(function(e){
		e.preventDefault();
		var redirect_to = $(this).attr('redirect_to');
		document.location.href = redirect_to;
	});
	
	// botão cancelar
	$('.btncancel').click(function(e) {
		e.preventDefault();
		var redirect_to = $(this).attr('redirect_to');
		document.location.href = redirect_to;
	});
};

EasyClinica.common.formValidation = function () {	
	$('form').validator({
		lang: 'pt',
		position: 'center right'		
	});	
	
    $.tools.validator.localize('pt', {
        '[required]': 'campo obrigatório'
    });
    
    $('input[type=submit], .submit').click(function (e) {
        if($(this).is('a')) e.preventDefault();
        
        var validator = $('form').data('validator');
        if (validator && validator.checkValidity()) {
            validator.destroy();
        } 

        $('form').submit();
    });
    
    //messages
    $('input.currency').attr('data-message','valor inválido');
};

/* LIB */
EasyClinica.lib.openModal = function (contentUrl, type, parameters, onCreate) {
	var today = new Date();
	var id_modal = today.getDate() + today.getDay() + today.getHours() + today.getMinutes() + today.getSeconds() + today.getMilliseconds();

	var conteudo_modal = "<div id='" + id_modal + "' class='modal'>";
		conteudo_modal += "<div class='modal-background'></div>";
		conteudo_modal += "<div class='modal-popup'>";
			conteudo_modal += "<div id='conteudo-modal'>";
				conteudo_modal += "<h2>Carregando...</h2>";
				conteudo_modal += "<img class='loading' alt='carregando...' src='" + EasyClinica.cfg.images.loading + "' />";
			conteudo_modal += "</div>";
		conteudo_modal += "</div>";
	conteudo_modal += "</div>";
	
	$('body').append(conteudo_modal);
	
	if(type == 'POST') {
		$.post(contentUrl, parameters, function(html){
			$('#conteudo-modal').html(html);
			onCreate();
			configureCloseModal();
		});
	}
	else {
		$.get(contentUrl, function(html) {
			$('#conteudo-modal').html(html);
			onCreate();
			configureCloseModal();
		});
	}	
	
	var configureCloseModal = function() {
		$('.modal-close').click(function(e){
			e.preventDefault();
			var modal = findRecursiveParent($(this), '.modal');
			modal.remove();
			
			$('.error').remove();
		});
	};
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

findRecursiveChild = function(element, selector) {
	var child = element.child();
	if(child.is(selector)) return child;
	
	return findRecursiveChild(child, selector);
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
		var regexCurrency = new RegExp(/(\d|,|\.)/g);
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

disable = function(obj) {
	obj.attr('disabled', true);
};

enable = function(obj) {
	obj.attr('disabled', false);
};

isEmpty = function(text) {
	return text == '';
};

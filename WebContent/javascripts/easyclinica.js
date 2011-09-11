$(function(){
	EasyClinica.setup();
	EasyClinica.activeMenuOption();
	
	EasyClinica.runPage();
	EasyClinica.runCommon();	
});

var EasyClinica = { 
	common: {}, pages: {}, page: {}, cfg: {}, lib: {}, registry: {},
	
	getMenuOption: function() {
		var opcao = $('#menu-link-' + EasyClinica.getSelectedHeader());
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
	getSelectedHeader: function() {
		return $('.box').attr('main-page') || $('.box').attr('id') || 'dashboard'; 
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
			if($(event.target).attr("allowEnter")===undefined && event.keyCode == 13) {
		      event.preventDefault();
		      return false;
		    }
		});
		
		// Ajax Configurations
		$('body').ajaxSend(function() {
			$('button, submit, a').attr("disabled", "disabled");
		});
		
		$('body').ajaxStop(function() {
			$('button, submit, a').removeAttr("disabled");
		});
	}
};

/* CONFIG */
EasyClinica.cfg.services = {
		newProcedureToAppointment: BASE_URI + 'appointments/_newProcedureToAppointment',
		getSpecialtyPrice: BASE_URI + 'especialidades/{0}/{1}',
		getDoctorSpecialty: BASE_URI + 'medicos/{0}/especialidade',
		verifyIfAppointmentIsReturn: BASE_URI + 'appointments/isReturn',
		searchProcedure: BASE_URI + 'procedures/_search',
		searchMaterial: BASE_URI + 'materials/_search',
		searchMedicine: BASE_URI + 'medicines/_search',
		searchCID: BASE_URI + 'cid/_search',
		
		showDoctorDetails: BASE_URI + 'doctor/_show',
		showHealthCarePlanDetails: BASE_URI + 'healthCarePlan/_show',
		showPatientDetails: BASE_URI + 'patient/_show',
		showAnamneseDetails: BASE_URI + 'anamnese/_show',
		showUserDetails: BASE_URI + 'usuarios/_show',
		showReceiptDetails: BASE_URI + 'receipt/_show',
		
		doctorScheduleList: BASE_URI + 'medicos/minha-agenda/_list',
		doctorScheduleQuickAdd: BASE_URI + 'medicos/minha-agenda/_quickAdd',
		doctorScheduleQuickUpdate: BASE_URI + 'medicos/minha-agenda/_quickUpdate',
		doctorScheduleRemove: BASE_URI + 'medicos/minha-agenda/_delete',
		doctorScheduleNew: BASE_URI + 'medicos/minha-agenda/_new',
		doctorScheduleEdit: BASE_URI + 'medicos/minha-agenda/{0}/_edit',
		
		searchDoctors: BASE_URI + 'medicos/_busca',
		searchPlans: BASE_URI + 'convenios/_busca',
		searchPatient: BASE_URI + 'patient/_search',
		
		scheduleList: BASE_URI + 'schedule/_loadAppointments',
		scheduleChangeArrivalTime: BASE_URI + 'schedule/_changeArrivalTime',
		scheduleSetAsTreated: BASE_URI + 'schedule/_setAsTreated',
		scheduleSetAsNotTreated: BASE_URI + 'schedule/_setAsNotTreated',
		scheduleDelete: BASE_URI + 'schedule/_delete',
		scheduleSave: BASE_URI + 'schedule/_save',
		
		postReply: BASE_URI + 'mensagens/{0}/respostas/_nova',
		postMessage: BASE_URI + 'mensagens/_nova',
		recentMessages: BASE_URI + 'mensagens/_recentes',
			
		searchPatients: BASE_URI + 'patient/_list',
		getHealthCarePlan: BASE_URI + 'convenios/{0}/resgatar',
		
		newChatMessage: BASE_URI + 'chat',
		getChatMessages: BASE_URI + 'chat/ultimas',
		getOnlineChatUsers: BASE_URI + 'chat/online',
		getChatsInLast3Minutes: BASE_URI + 'chat/3minutos',

		modal: {
			confirmacaoConsulta: BASE_URI + 'modal/_confirmacao-consulta.html'
		}
};

EasyClinica.cfg.images = {
	loading: BASE_URI + 'images/loading.gif'
};

EasyClinica.cfg.datepicker = {
	dateFormat: 'dd/mm/yy',
	showOtherMonths: true,
	selectOtherMonths: true,
	showAnim: 'drop',
	changeMonth: true,
	changeYear: true,
	dayNames: ['Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta', 'Sábado'],
	dayNamesShort: ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sab'],
	dayNamesMin: ['Do', 'Se', 'Te', 'Qa', 'Qi', 'Se', 'Sa'],
	monthNames: ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'],
	monthNamesShort: ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez']
};

EasyClinica.cfg.timepicker = {
	hourText: 'Hora',
	minuteText: 'Minuto',
	defaultTime: '00:00'
};

EasyClinica.cfg.validation = {
	currency: '^[0-9]+(\\,\\d{1,2})?$',
	number: '^[-+]?[0-9]+$'
};

/* COMMON */
EasyClinica.common.boxActionButtons = function() {
	
	$('span.boxtools span.buttons').each(function(index) {
		var box = $(this);
		var numberOfChildren = box.children().size();
		var width = "{0}px".format((numberOfChildren - 2) * 39 + 32);
		 
		box.css("width", width);
		box.parent().css("width", width);
		box.children().last().addClass("last");
	});
	
};

EasyClinica.common.generalFunctions = function(selector){
	if (selector === undefined) selector = '*';
	
	// Currency
	$(selector).find('.currency').each(function(index){
		var element = $(this);
		var isInput = element.is('input');
		
		var valor = (isInput ? element.val() : element.html());
		valor = valor.convertToFloat();
		
		if(isInput) element.val(valor.toString().formatCurrency());
		else element.html(valor.toString().formatCurrency(true));
		
		if(!$(this).hasClass('skip-validation') && isInput) {
			$(this).attr('pattern',EasyClinica.cfg.validation.currency);
		}
	});
	
	$(selector).find('.currency').keyup(function(key){
		onlyNumbersAndComma($(this), key);
	});
	
	// Number
	$(selector).find('input.number').each(function(index){
		if(!$(this).hasClass('skip-validation')) {
			$(this).attr('pattern',EasyClinica.cfg.validation.number);
		}
			
		var valor = $(this).val().convertToInt();
		$(this).val(valor.toString());
		
		$(this).keyup(function(key){
			if(key.keyCode == '13') key.preventDefault();
			
			var texto = $(this).val();
			var decimalRegExp = new RegExp(EasyClinica.cfg.validation.number);
			if(!decimalRegExp.test(texto)) {
				texto = texto.substring(0,texto.length -1);
				$(this).val(texto);
			}
		});
	});
	
	// Mascaras
	$(selector).find('.mask_telefone').mask('(99) 9999-9999');
	$(selector).find('.mask_cep').mask('99999-999');
	$(selector).find('.mask_cpf').mask('999.999.999-99');
	$(selector).find('.mask_cnpj').mask('99.999.999/9999-99');
	$(selector).find('.mask_date').mask('99/99/9999');	
	
	// Datepicker
	$(selector).find('.datepicker').datepicker(EasyClinica.cfg.datepicker);
	
	// Time Picker
	$(selector).find('input.time').timepicker(EasyClinica.cfg.timepicker);
	
	// bot�o voltar
	$(selector).find('.btnback').click(function(e){
		e.preventDefault();
		var redirect_to = $(this).attr('redirect_to');
		document.location.href = redirect_to;
	});
	
	// bot�o cancelar
	$(selector).find('.btncancel').click(function(e) {
		e.preventDefault();
		var redirect_to = $(this).attr('redirect_to');
		document.location.href = redirect_to;
	});
};

EasyClinica.common.formValidation = function (selector){
	if (selector === undefined) selector = '*';
	
	$(selector).find('form').validator({
		lang: 'pt',
		position: 'center right'		
	});	
	
    $.tools.validator.localize('pt', {
        '[required]': 'campo obrigatório'
    });
    
    $(selector).find('input[type=submit], .submit').click(function (e) {
        if($(this).is('a')) e.preventDefault();
        
        $('.error').hide();
        
        var form = findRecursiveParent($(this), 'form');
        var validator = form.data('validator');
        validator.checkValidity();
        if (validator && validator.checkValidity()) {
            validator.destroy();
            
            form.submit();
        }        
    });
    
    //messages
    $(selector).find('input.currency').attr('data-message','valor inválido (valor decimal)');
    $(selector).find('input.number').attr('data-message','valor inválido (apenas inteiros).');
};

EasyClinica.common.easyabas = function() {
	
	var hideAllTabsContentBoxes = function(showFirst) {
		if(showFirst === undefined) showFirst = false;
		
		$('#easyabas li a').each(function(index){
			var contentBox = $(this).attr('href');		
			
			if(showFirst && index == 0) $(contentBox).show();
			else $(contentBox).hide();
			
			$(contentBox).addClass("easyabas-content");
		});		
	};
	hideAllTabsContentBoxes(true);
	
	var inactiveAllTabs = function(activeFirst) {
		if(activeFirst === undefined) activeFirst = false;
		
		$('#easyabas li').removeClass('active');
		
		if(activeFirst) $('#easyabas li').first().addClass('active');
	};
	inactiveAllTabs(true);
	
	$('#easyabas li a').click(function(e){
		e.preventDefault();
		
		hideAllTabsContentBoxes(false);
		inactiveAllTabs(false);
		
		// show this tab content
		$(this).parent().addClass('active');
		
		var contentBox = $(this).attr('href');
		$(contentBox).show();
	});
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
		$.get(contentUrl, parameters, function(html) {
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

EasyClinica.lib.getHourAndMinuteDiffBetweenTwoDates = function(start, end) {
        var tempoRestante = end.getTime() - start.getTime();
        var horas = Math.floor(tempoRestante/3600000);
        var minutos = Math.floor((tempoRestante%3600000)/60000);
        
        horas = String(horas % 24);
        if(horas.length < 2){
                horas = "0" + horas;
        }
        
        minutos = String(minutos % 60);
        
        //O sinal de negativo de aparecer somente na hora
        if (minutos < 0) minutos = minutos * -1;
        
        return horas + ":" + minutos;
};

EasyClinica.lib.generateHTML = function(tag, html, attrs) {
	if (typeof(html) != 'string') {
		attrs = html;
    	html = null;
  	}
  
  	var h = '<' + tag;
  	for (attr in attrs) {
	  	if(attrs[attr] === false) continue;
    	h += ' ' + attr + '="' + attrs[attr] + '"';
  	}
  
  	return h += html ? ">" + html + "</" + tag + ">" : "/>";
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
		var regexCurrency = new RegExp(/(\d|,|\.)/g);
		var matched = this.match(regexCurrency);
		if(matched != null) {
			valor = "";
			for (var i = 0; i < matched.length; i++) {
				valor += (matched[i] == '' ? ',' : matched[i]);
			}
		}	
		valor = valor.replace(/,/g, '.');
	}
	
	return parseFloat(valor);
};

String.prototype.convertToInt = function(){
	var valor = this;
	
	var regexCurrency = new RegExp(/[0-9]/g);
	var matched = this.match(regexCurrency);
	if(matched != null) {
		valor = "";
		for (var i = 0; i < matched.length; i++) {
			valor += matched[i];
		}
	}	
	
	if(valor == '') valor = "0";
	
	return parseInt(valor);
};

String.prototype.formatCurrency = function(putSymbol, decimalPlaces){
	if (putSymbol === undefined) putSymbol = false;
	if (decimalPlaces === undefined) decimalPlaces = 2;
	
	var valor = this.convertToFloat();
	valor = valor.toFixed(decimalPlaces).toString().replace(/[.]/g, ',');
	
	return (putSymbol ? 'R$ ' : '') + valor;
};

clone = function(o) {
	//return eval(uneval(o));
	
	return $.extend({}, o);
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

onlyNumbersAndComma = function(element, key) {
	if(key.keyCode == '37' || key.keyCode == '39') return true;
	
	if(key.keyCode == '13') key.preventDefault();
	
	var texto = element.val();
	valor = "";	
	var regexCurrency = new RegExp(/(\d|,|\.)/g);
	var matched = texto.match(regexCurrency);
	if(matched != null) {
		valor = "";
		for (var i = 0; i < matched.length; i++) {
			valor += (matched[i] == '' ? ',' : matched[i]);
		}
	}
	element.val(valor);	
};

Date.prototype.format = function (formatString) {
	var formatDate = this;
	if(formatDate instanceof Date) {
		var months = new Array("Jan","Fev","Mar","Abr","Mai","Jun","Jul","Ago","Set","Out","Nov","Dez");
		var yyyy = formatDate.getFullYear();
		var yy = yyyy.toString().substring(2);
		var M = formatDate.getMonth() + 1;
		var MM = M < 10 ? "0" + M : M;
		var MMM = months[M];
		var d = formatDate.getDate();
		var dd = d < 10 ? "0" + d : d;
		
		var h = formatDate.getHours();
		var hh = h < 10 ? "0" + h : h;
		var m = formatDate.getMinutes();
		var mm = m < 10 ? "0" + m : m;
		var s = formatDate.getSeconds();
		var ss = s < 10 ? "0" + s : s;
		
		formatString = formatString.replace(/yyyy/g, yyyy);
		formatString = formatString.replace(/yy/g, yy);
		formatString = formatString.replace(/MMM/g, MMM);
		formatString = formatString.replace(/MM/g, MM);
		formatString = formatString.replace(/M/g, M);
		formatString = formatString.replace(/dd/g, dd);
		formatString = formatString.replace(/d/g, d);
		formatString = formatString.replace(/hh/g, hh);
		formatString = formatString.replace(/h/g, h);
		formatString = formatString.replace(/mm/g, mm);
		formatString = formatString.replace(/m/g, m);
		formatString = formatString.replace(/ss/g, ss);
		formatString = formatString.replace(/s/g, s);

		return formatString;
	} else {
		return "";
	}
};

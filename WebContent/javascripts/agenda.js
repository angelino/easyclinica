EasyClinica.pages['schedule'] = function(){
	
	var doctorId = $('input[name=schedule.doctor.id]').val();
	
	var view="week";          
    
    var op = {
        view: view,
        theme:3,
        showday: new Date(),
        EditCmdhandler:Edit,
        onWeekOrMonthToDay:wtd,
        onBeforeRequestData: cal_beforerequest,
        onAfterRequestData: cal_afterrequest,
        onRequestDataError: cal_onerror, 
        autoload:true,
        url: EasyClinica.cfg.services.scheduleList.format(doctorId),  
        quickAddUrl: EasyClinica.cfg.services.scheduleQuickAdd.format(doctorId), 
        quickUpdateUrl: EasyClinica.cfg.services.scheduleQuickUpdate.format(doctorId),
        quickDeleteUrl: EasyClinica.cfg.services.scheduleRemove.format(doctorId)        
    };
    var $dv = $("#calhead");
    var _MH = document.documentElement.clientHeight;
    var dvH = $dv.height() + 2;
    op.height = _MH - dvH;
    op.eventItems =[];

    var p = $("#gridcontainer").bcalendar(op).BcalGetOp();
    if (p && p.datestrshow) {
        $("#txtdatetimeshow").text(p.datestrshow);
    } 
    $("#caltoolbar").noSelect();
    
    $("#hdtxtshow").datepicker({
    	altField: "#txtdatetimeshow",
    	dateFormat: 'dd/mm/yy',
		onSelect:function(dateText, inst){
			var dateParts = dateText.split('/');
			var selectedDate = new Date(dateParts[2], dateParts[1]-1, dateParts[0]);
			
			var p = $("#gridcontainer").gotoDate(selectedDate).BcalGetOp();
            if (p && p.datestrshow) {
                $("#txtdatetimeshow").text(p.datestrshow);
            }
        } 
    });
    
    $('.fshowdatep').click(function(e){
    	$("#hdtxtshow").focus();
    });
    
    function cal_beforerequest(type)
    {
        var t="Carregando...";
        switch(type)
        {
            case 1:
                t="Carregando...";
                break;
            case 2:                      
            case 3:  
            case 4:    
                t="Aguarde...";                                   
                break;
        }
        $("#errorpannel").hide();
        $("#loadingpannel").html(t).show();    
    }
    
    function cal_afterrequest(type)
    {
        switch(type)
        {
            case 1:
                $("#loadingpannel").hide();
                break;
            case 2:
            case 3:
            case 4:
                $("#loadingpannel").html("Success!");
                window.setTimeout(function(){ $("#loadingpannel").hide();},2000);
            break;
        }   
    }
    
    function cal_onerror(type,data)
    {
        $("#errorpannel").show();
    }
    
    function Edit(data)
    {
    	if(data)
        {
        	var url = EasyClinica.cfg.services.scheduleEdit.format(doctorId, data[0]);
        	EasyClinica.lib.openModal(url, 'GET', {}, function(){
            	EasyClinica.common.generalFunctions();
    			EasyClinica.common.formValidation();
    			managerColor();
    			schedulePeriodManager();
            });
        }
    }    
        
    function wtd(p)
    {
       if (p && p.datestrshow) {
            $("#txtdatetimeshow").text(p.datestrshow);
        }
        $("#caltoolbar div.fcurrent").each(function() {
            $(this).removeClass("fcurrent");
        });
        $("#showdaybtn").addClass("fcurrent");
    }
    
    //to show day view
    $("#showdaybtn").click(function(e) {
        $("#caltoolbar div.fcurrent").each(function() {
            $(this).removeClass("fcurrent");
        });
        $(this).addClass("fcurrent");
        var p = $("#gridcontainer").swtichView("day").BcalGetOp();
        if (p && p.datestrshow) {
            $("#txtdatetimeshow").text(p.datestrshow);
        }
    });
    
    //to show week view
    $("#showweekbtn").click(function(e) {
        //document.location.href="#week";
        $("#caltoolbar div.fcurrent").each(function() {
            $(this).removeClass("fcurrent");
        });
        $(this).addClass("fcurrent");
        var p = $("#gridcontainer").swtichView("week").BcalGetOp();
        if (p && p.datestrshow) {
            $("#txtdatetimeshow").text(p.datestrshow);
        }
    });
    
    //to show month view
    $("#showmonthbtn").click(function(e) {
        //document.location.href="#month";
        $("#caltoolbar div.fcurrent").each(function() {
            $(this).removeClass("fcurrent");
        });
        $(this).addClass("fcurrent");
        var p = $("#gridcontainer").swtichView("month").BcalGetOp();
        if (p && p.datestrshow) {
            $("#txtdatetimeshow").text(p.datestrshow);
        }
    });
    
    // refresh
    $("#showreflashbtn").click(function(e){
        $("#gridcontainer").reload();
    });
    
    //Add a new event
    $("#faddbtn").click(function(e) {
        var url = EasyClinica.cfg.services.scheduleNew.format(doctorId);
        
        EasyClinica.lib.openModal(url, 'GET', {}, function(){
        	EasyClinica.common.generalFunctions();
			EasyClinica.common.formValidation();
			managerColor();
			schedulePeriodManager();
        });
    });
    
    //go to today
    $("#showtodaybtn").click(function(e) {
        var p = $("#gridcontainer").gotoDate().BcalGetOp();
        if (p && p.datestrshow) {
            $("#txtdatetimeshow").text(p.datestrshow);
        }
    });
    
    //previous date range
    $("#sfprevbtn").click(function(e) {
        var p = $("#gridcontainer").previousRange().BcalGetOp();
        if (p && p.datestrshow) {
            $("#txtdatetimeshow").text(p.datestrshow);
        }

    });
    
    //next date range
    $("#sfnextbtn").click(function(e) {
        var p = $("#gridcontainer").nextRange().BcalGetOp();
        if (p && p.datestrshow) {
            $("#txtdatetimeshow").text(p.datestrshow);
        }
    });
    
    var managerColor = function(){
    	$('.paleta-cores p').click(function(e){
        	var cor = $(this);
        	
        	$('input[name=schedule.color]').val(cor.attr('value'));
        	$('.cor-selecionada').css('background-color', cor.css('background-color'));
        });
    };
    
    /* Funções Data */
    var schedulePeriodManager = function() {
    	
    	// Start Time
	    var startDateCfg = clone(EasyClinica.cfg.datepicker);
	    startDateCfg.onSelect = function(dateText, inst){
	    	scheduleStartTimeManager();
	    };
	    $("input[name=startDate]").datepicker(startDateCfg);
	    
	    var startTimeCfg = clone(EasyClinica.cfg.timepicker);
	    startTimeCfg.onSelect = function(time, inst) {
	    	scheduleStartTimeManager();
	    };
	    $("input[name=startTime]").timepicker(startTimeCfg);
	    
	    // End Time
	    var endTimeCfg = clone(EasyClinica.cfg.timepicker);
	    endTimeCfg.onSelect = function(time, inst) {
	    	scheduleEndTimeManager();
	    };
	    $("input[name=schedule.duration]").timepicker(endTimeCfg);
    };
    
    var scheduleStartTimeManager = function() {
    	var date = $("input[name=startDate]").val();
    	var time = $("input[name=startTime]").val();
    	
    	$("input[name=schedule.startTime]").val(date + ' ' + time);
    	scheduleEndTimeManager();
    };
    
    var scheduleEndTimeManager = function() {
    	var startDateParts = $("input[name=startDate]").val().split('/');
    	var startTimeParts = $("input[name=startTime]").val().split(':');
    	var howMuchTimeParts = $("input[name=schedule.duration]").val().split(':');
    	
    	if(startDateParts.length < 3 || startTimeParts.length < 2 || howMuchTimeParts.length < 2) return;
    	
    	var endTime = new Date(startDateParts[2], startDateParts[1]-1, startDateParts[0],0,0,0,0);
    	var hour = parseInt(startTimeParts[0], 10) + parseInt(howMuchTimeParts[0], 10);
    	var minutes = parseInt(startTimeParts[1]) + parseInt(howMuchTimeParts[1]);
    	
    	//alert(startTimeParts[0] + ' + ' + howMuchTimeParts[0] + ' = ' + hour);
    	//alert(startTimeParts[1] + ' + ' + howMuchTimeParts[1] + ' = ' + minutes);
    	
    	endTime.setHours(hour, minutes, 0, 0);
    	
    	$("input[name=schedule.endTime]").val(endTime.format('dd/MM/yyyy hh:mm'));
    };
     
};
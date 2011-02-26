EasyClinica.pages['schedule'] = function(){
	
	var doctorId = $('input[name=schedule.doctor.id]').val();
	
	var view="week";          
    
    var op = {
        view: view,
        theme:3,
        showday: new Date(),
        EditCmdhandler:Edit,
        ViewCmdhandler:View,    
        onWeekOrMonthToDay:wtd,
        onBeforeRequestData: cal_beforerequest,
        onAfterRequestData: cal_afterrequest,
        onRequestDataError: cal_onerror, 
        autoload:true,
        url: EasyClinica.cfg.services.scheduleList.format(doctorId),  
        quickAddUrl: EasyClinica.cfg.services.scheduleQuickAdd.format(doctorId), 
        quickUpdateUrl: EasyClinica.cfg.services.scheduleUpdate.format(doctorId),
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
			var p = $("#gridcontainer").gotoDate(dateText).BcalGetOp();
            if (p && p.datestrshow) {
                $("#txtdatetimeshow").text(p.datestrshow);
            }
        } 
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
            });
        }
    }    
    function View(data)
    {
        var str = "";
        $.each(data, function(i, item){
            str += "[" + i + "]: " + item + "\n";
        });
        alert(str);               
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
};
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>    
    	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">    
    	<title>Novo Compromisso</title>    
    
	    <helper:include fileName="plugins/wdCalendar/main.css" type="css" />
	    <helper:include fileName="plugins/wdCalendar/dp.css" type="css" />
	    <helper:include fileName="plugins/wdCalendar/dropdown.css" type="css" />
	    <helper:include fileName="plugins/wdCalendar/colorselect.css" type="css" />
	    
	    <helper:include fileName="plugins/jquery-1.4.4.min.js" type="js"/> 
	    <helper:include fileName="plugins/wdCalendar/Common.js" type="js"/> 
	    <helper:include fileName="plugins/wdCalendar/jquery.form.js" type="js"/> 
	    <helper:include fileName="plugins/wdCalendar/jquery.validate.js" type="js"/> 
	    <helper:include fileName="plugins/wdCalendar/datepicker_lang_PT.js" type="js"/> 
	    <helper:include fileName="plugins/wdCalendar/jquery.datepicker.js" type="js"/>
	    <helper:include fileName="plugins/wdCalendar/jquery.dropdown.js" type="js"/>
	    <helper:include fileName="plugins/wdCalendar/jquery.colorselect.js" type="js"/>
	    <helper:include fileName="agenda_form.js" type="js"/>
	</head>
  	<body>  
	
		<div class="toolBotton">           
	        <a id="Savebtn" class="imgbtn" href="javascript:void(0);">                
	          <span class="Save"  title="Save the calendar">Save(<u>S</u>)</span>          
	        </a>                           
        	<a id="Closebtn" class="imgbtn" href="javascript:void(0);">                
          		<span class="Close" title="Close the window" >Close</span>
          	</a>            
      	</div>                  
      
      	<div style="clear: both"></div>        
      
      	<div class="infocontainer">            
        	
        	<form action="<c:url value="/medicos/${doctor.id}/agenda/_add"/>" class="fform" id="fmEdit" method="POST">                 
          		
          		<input type="hidden" name="schedule.doctor.id" value="${doctor.id}"/>
          		
          		<label>                    
            		<span>Título*:</span>                    
            		<input MaxLength="200" class="required safe" id="Subject" name="schedule.subject" style="width:85%;" type="text" value="" />                     
            	</label>                 
          		
          		<label>                    
            		<span>*Horário:</span>                    
		            <div>  
						<input MaxLength="10" class="required datepicker" id="stpartdate" style="padding-left:2px;width:90px;" type="text" />                       
						<input MaxLength="5" class="required time" id="stparttime" style="width:40px;" type="text" /> Até                       
						<input MaxLength="10" class="required date" id="etpartdate" style="padding-left:2px;width:90px;" type="text" />                       
						<input MaxLength="50" class="required time" id="etparttime" style="width:40px;" type="text" />                                            
		            
		            	<input type="hidden" name="schedule.startTime"/>
		            	<input type="hidden" name="schedule.endTime"/>
		            </div>                
          		</label>                 
          
          		<label class="checkp"> 
                	<input id="IsAllDayEvent" name="schedule.moreThanOneDay" type="checkbox" value="1" /> O compromisso se repetirá por mais dias?                      
              	</label> 
          
          		<label>
          			<span>Cor*</span>
          			<div id="calendarcolor"></div>
          			<input id="colorvalue" name="schedule.color" type="hidden" value="-1" /> 
          		</label>
          
          		<label>                    
            		<span>Descrição:</span>                    
					<textarea cols="20" id="Description" name="schedule.description" rows="2" style="width:95%; height:70px"></textarea>                
          		</label>
          		                
          		<input id="timezone" name="timezone" type="hidden" value="" />           
        	</form>         
      	</div> 
    
  	</body>
</html>
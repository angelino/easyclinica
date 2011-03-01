<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="br.com.easyclinica.view.Link"%>
<%@page import="java.util.LinkedList"%>

<html>
	<head>
		<title>.: EasyClinica - Agenda Médica :.</title>
		
		<helper:include fileName="plugins/wdCalendar/calendar.css" type="css" />
		<helper:include fileName="plugins/wdCalendar/main.css" type="css" />
	
	    <helper:include fileName="plugins/wdCalendar/wdCalendar_lang_PT.js" type="js"/>
	    <helper:include fileName="plugins/wdCalendar/jquery.calendar.js" type="js"/>
	</head>
	<body>
		
		<div class="box" id="allSchedules">		
			<div class="boxcontent">
				<input type="hidden" name="schedule.doctor.id" value="${doctor.id}"/>
			
				<div id="calhead" style="padding-left:1px;padding-right:1px;">          
            		<div class="cHead"><div class="ftitle">Agenda</div>
            		<div id="loadingpannel" class="ptogtitle loadicon" style="display: none;">Carregando...</div>
            		<div id="errorpannel" class="ptogtitle loaderror" style="display: none;">Desculpe, não conseguimos carregar os seus dados, tente novamente mais tarde</div>
            	</div>          
            
            	<div id="caltoolbar" class="ctoolbar">
              		<div id="faddbtn" class="fbutton">
	                	<div>
	                		<span title='Clique para criar novo compromisso' class="addcal">Novo Compromisso</span>
	                	</div>
	            	</div>
	            	<div class="btnseparator"></div>
	             	<div id="showtodaybtn" class="fbutton">
	                	<div>
	                		<span title='Clique para voltar ver agenda de hoje' class="showtoday">Hoje</span>
	                	</div>
	            	</div>
	              	<div class="btnseparator"></div>
		            <div id="showdaybtn" class="fbutton">
		                <div><span title='Diário' class="showdayview">Diário</span></div>
		            </div>
	              	<div  id="showweekbtn" class="fbutton fcurrent">
	                	<div><span title='Semanal' class="showweekview">Semanal</span></div>
	            	</div>
	              	<div  id="showmonthbtn" class="fbutton">
	                	<div><span title='Mensal' class="showmonthview">Mensal</span></div>
	            	</div>
	            	<div class="btnseparator"></div>
	              	<div  id="showreflashbtn" class="fbutton">
	                	<div><span title='Atualizar Compromissos' class="showdayflash">Atualizar</span></div>
	                </div>
	             	<div class="btnseparator"></div>
		            <div id="sfprevbtn" title="Anterior"  class="fbutton">
		              <span class="fprev"></span>
		            </div>
		            <div id="sfnextbtn" title="Próximo" class="fbutton">
		                <span class="fnext"></span>
		            </div>
	            
	            	<div class="fshowdatep fbutton">
	                    <div>
	                        <input type="hidden" name="txtshow" id="hdtxtshow" />
	                        <span id="txtdatetimeshow">Escolha uma data</span>
	                    </div>
	            	</div>
	                <div class="clear"></div>
	            </div>
      		</div>
      
      		<div style="padding:1px;">
		        <div class="t1 chromeColor">&nbsp;</div>
		        <div class="t2 chromeColor">&nbsp;</div>
		        <div id="dvCalMain" class="calmain printborder">
		            <div id="gridcontainer" style="overflow-y: visible;"></div>
		        </div>
        		<div class="t2 chromeColor">&nbsp;</div>
		        <div class="t1 chromeColor">&nbsp;</div>   
        	</div>
     	</div>
	</div>
		
		
		<div class="boxright">			
			<% 
				java.util.List<Link> links = new LinkedList<Link>();  
				links.add(new Link("/medicos","Listagem de médicos"));
				pageContext.setAttribute("links",links);
			%>
			<helper:navigation links="${links}"></helper:navigation>   
	    </div>
		
	</body>
</html>
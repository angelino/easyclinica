<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@page import="br.com.easyclinica.view.Link"%>
<%@page import="java.util.LinkedList"%>

<html>
<head>
	<title>.: EasyClinica - Agenda :.</title>
	
	<helper:include fileName="jquery.simplemodal.1.4.min.js" type="js" />
	<script type="text/javascript" language="javascript">
		$(document).ready(function(){
			$("#calendario").datepicker({ 
				altField: '#data',
				altFormat: 'dd/mm/yy',
				 onSelect: function(dateText, inst) { 
					var date = new Date(Date.parse($(this).datepicker('getDate')));
					if(date > new Date()) change_highlight_date(date);
				 }
			});

			$("#data").datepicker({ 
				altFormat: 'dd/mm/yy', 
				minDate: new Date()
			});

			change_highlight_date(new Date());
		});

		function open_modal() {
			$("#agendamento").modal();
		}

		function change_highlight_date(date) {
			$('.dia').html(date.getDate());

			var months_array = new Array('JAN', 'FEV', 'MAR', 'ABR', 'MAI', 'JUN', 'JUL', 'AGO', 'SET', 'OUT', 'NOV', 'DEZ');
			var month = months_array[date.getMonth()] + "/" + date.getFullYear();
			$('.mes').html(month);
		}
	</script>
</head>
<body>

	<!-- MODAL AGENDAMENTO -->
	<div id="agendamento" class="modal agendamento">
		<a href="#" class="simplemodal-close btn_fechar">X</a>	
		
		<form id="frm_agendamento" method="POST">
			<h3>Médico: ${doctor.name} </h3>
			<em>CRM: ${doctor.crm}</em>		
					
			<fieldset class="cadastro">
				<input type="hidden" name="schedule.doctor.id" value="${doctor.id}"/>
				
				<div class="paciente">
					<label>Paciente*:</label>
					<input type="text" name="schedule.patient.id" />			
					<button class="button" type="submit">
						<c:url value="/images/icons/schedule/search.png" var="img_buscar"/>
						<img src="${img_buscar}" alt="Buscar" />
					</button>
				</div>
				
				<div class="agrupar_campos">
					<label>Data*:</label>
					<input type="text" id="data" name="schedule.day.day"/>
				</div>
				
				<div class="agrupar_campos">
					<label>Motivo*:</label>
					<select name="schedule.reason.reason">
						<option value="1">Consulta</option>
						<option value="2">Retorno</option>
					</select>	
				</div>
							
				<div class="agrupar_campos" style="clear:both;">
					<label>Início*:</label>
					<select name="schedule.start.hour">
						<option>09:00</option>
					</select>	
				</div>
				
				<div class="agrupar_campos">
					<label>Término*:</label>
					<select name="schedule.end.hour">
						<option>10:00</option>
					</select>	
				</div>
						
				<label>Telefone:</label>
				<input type="text" class="mask_telefone" name="schedule.telephone.telephone"/>		
							
				<label>Descrição*:</label>
				<textarea name="schedule.description.description"></textarea>
				
				<div class="botoes">
					<c:url value="/agenda" var="save_url"></c:url>
				
					<button class="button" type="submit" id="salvar_compromisso" url="${save_url}">
						<c:url value="/images/icons/tick.png" var="img_salvar"/>
						<img src="${img_salvar}" alt="Salvar" />Salvar
					</button>
					
					<a class="button simplemodal-close" href="#">
						<c:url value="/images/icons/cross.png" var="img_cancelar"/>
						<img src="${img_cancelar}" alt="Cancelar" />Cancelar
					</a>
				</div>
			</fieldset>
		</form>
	</div>
	<!-- FIM MODAL AGENDAMENTO -->

	<!-- INÍCIO MAIN -->
   	<div id="main">
   		<div id="block-tables" class="block">
   			
   			<div class="content">
   				<h2 class="title">Agenda - ${doctor.name}</h2>
   				
   				<helper:message successKey="${successKey}" errorKey="${errorKey}" />
   				
   				<div class="inner agenda">
   					
   					<div class="topo">
   						<div class="hoje">
   							<div class="mes">AGO/2010</div>
   							<div class="dia">23</div>
   						</div>
   						
   						<div class="filtros">
   							<fieldset class="cadastro busca">
   								<label>Escolha um médico:</label>
   								<select name="medico" id="medico">
   									<c:forEach items="${doctors}" var="d">
   										<c:choose>
   											<c:when test="${d.id == doctor.id}">
   												<option value="${d.id}" selected>${d.name}</option>
   											</c:when>
   											<c:otherwise>
   												<option value="${d.id}">${d.name}</option>
   											</c:otherwise>
   										</c:choose>   										
   									</c:forEach>
   								</select>
   								
   								<button class="button" type="submit">
									<c:url value="/images/icons/schedule/search.png" var="img_buscar"/>
									<img src="${img_buscar}" alt="Buscar" />Buscar
								</button>
   							</fieldset>
   						</div>
   					</div>
   					
   					<div class="compromissos">
   						<ul class="acoes">
   							<li>
   								<a class="button" href="javascript:open_modal()">
									<c:url value="/images/icons/schedule/add.png" var="img_add"/>
									<img src="${img_add}" alt="Novo" />Novo
								</a>
   							</li>
   						</ul>
   						
   						<div id="medical-appointments">
   							<jsp:include page="_medicalAppointments.jsp"/>
   						</div>
   						
   					</div>
   					
   					<div class="calendario">
   						<div id="calendario"></div>
   					</div>
   					
   				</div>
   			</div>
   			
   		</div>   	
   	</div>
   	<!-- FIM MAIN -->
   	
   	<!-- INÍCIO SIDEBAR -->
	<div id="sidebar">
	</div>
	<!-- FIM SIDEBAR -->
	
</body>
</html>
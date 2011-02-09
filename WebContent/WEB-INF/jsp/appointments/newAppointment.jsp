<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>

<%@page import="br.com.easyclinica.view.Link"%>
<%@page import="java.util.LinkedList"%>
<%@page import="br.com.easyclinica.domain.entities.Patient"%>

<html>
<head>
	<title>.: EasyClinica - Nova Consulta :.</title>
	
	<script type="text/javascript" language="javascript">
		$(function(){
			$('#aviso-retorno').hide();
		});
	</script>
</head>
<body>
	<div class="box" id="consultas">
		<helper:patientMenu patient="${patient}" selected="Consultas" />
		
		<form action="<c:url value="/pacientes/${patient.id}/consultas/novo"/>" method="post">
			<input type="hidden" value="${patient.id}" name="appointment.patient.id"/>
		
			<div class="boxcontent">
				
				<h2>Cadastro de consulta - ${patient.name}</h2>
				
				<helper:errors errors="${errors}" />
			
				<fieldset>			
	            	<div class="date">
	                	<label class="title">Data:<span>*</span></label>
	                    <input type="text" name="appointment.appointmentDate" required="required" class="datepicker" />
	                    <a href="javascript:void(0);">icondate</a>
	                </div>
	               	<div class="healthcare">
	                   	<label class="title">Convênio:<span>*</span></label>
	                   	<label>${patient.healthCarePlan.name}:</label>
	                       <input type="radio" class="radio" name="appointment.healthCarePlan.id" value="${patient.healthCarePlan.id}" checked="checked" />
						<label>Particular:</label>
	                       <input type="radio" class="radio" name="appointment.healthCarePlan.id" value="0" />
	                   </div>
	               	<div class="returning">
	                   	<label class="title">Retorno:<span>*</span></label>
	                       <label>Sim:</label>
	                       <input type="radio" class="radio" name="appointment.return" value="true">
	                       <label>Não:</label>
	                       <input type="radio" class="radio" name="appointment.return" value="false" checked="checked"">
	                   </div>
	               	<div class="medical">
	                   	<label class="title">Médico:<span>*</span></label>
	                       <select name="appointment.doctor.id" min="1" data-message="Selecione um médico.">
							<option value="0">Selecione um médico</option>
							<c:forEach var="doctor" items="${doctors}" varStatus="status">
								<option value="${doctor.id}">${doctor.name}</option>
							</c:forEach>
						</select>
	                   </div>
	               	<div class="specialty">
	                   	<label class="title">Especialidade:<span>*</span></label>
						<select name="appointment.specialty.id" min="1" data-message="Selecione uma especialidade.">
							<option value="0">Selecione uma Especialidade</option>
							<c:forEach var="specialty" items="${specialties}" varStatus="status">
								<option value="${specialty.id}">${specialty.name}</option>
							</c:forEach>
						</select>
	                </div>                    
				</fieldset>
	               
	            <fieldset>
	            	<div class="remarks">
	                	<label class="title">Observações:</label>
	                    <textarea name="appointment.observations"></textarea>
	                </div>
	            </fieldset>
	        
	        </div>
			
			<div class="boxcontent">
	        	<h2>Procedimentos</h2>
	            <fieldset class="procedures">
	            	<div class="procedures">
	                    <input type="text"  id="txt_search_procedure" />
	                    <a href="#" id="btn_search_procedure">Adicionar</a>
	                    <p>Digite o código "AMB" ou "TUSS" ou o nome do procedimento.</p>
	                </div>
	            </fieldset>
	        </div>	
			
			<div class="boxcontent">
				<table class="tableprocedures" border="0">
				
					<tr class="boxtotal">
                        <td colspan="1" rowspan="3" class="tablenostyle">&nbsp;</td>
                        <th colspan="2" rowspan="3">&nbsp;</th>
                        <td colspan="1">Procedimentos</td>
                        <td class="valor currency" id="appointment-procedure-amount"></td>
                        <td rowspan="3">&nbsp;</td>
                    </tr>
                    <tr class="boxtotal">
                        <td colspan="1">Consulta:</td>
                        <td class="valor currency" id="valor-consulta"></td>
                    </tr>
                    <tr class="boxtotal">
                        <td colspan="1">Total:</td>
                        <td class="valor currency" id="appointment-amount"></td>
                    </tr>
				</table>
			</div>
		
			<div class="boxcontent">
				<div class="boxactions">
	  				<input type="submit" class="btnsave" value="Salvar" />
	                <input type="button" class="btncancel" value="Cancelar" redirect_to="<c:url value="/pacientes/${patient.id}/consultas"/>"/>
	            </div>
			</div>			
		</form>					
	</div>				
							
	<div class="boxright">	
		<% 
			java.util.List<Link> links = new LinkedList<Link>();  
			links.add(new Link("/pacientes/" + ((Patient)request.getAttribute("patient")).getId() + "/consultas","Voltar para listagem"));
			pageContext.setAttribute("links",links);
		%>
		<helper:navigation links="${links}"></helper:navigation>
		
		<helper:notice id="aviso-retorno" notice="Essa consulta pode ser um retorno." title="Retorno" />
	</div>					
	
</body>
</html>
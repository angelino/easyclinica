<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>.: EasyClinica - Nova Consulta :.</title>
</head>
<body>

	<c:url value="/pacientes/123/consultas/novo" var="action" />
	<form action="${action}" method="post">
		
		<!-- INÍCIO CADASTRO CONSULTA -->
		<fieldset class="appointment">
			<label>Data:</label>
			<input type="text" name="appointment.appointmentDate"/>
			
			<label>Paciente:</label>
			<select name="appointment.patient.id">
			</select>
			
			<label>Convênio</label>
			<input type="hidden" name="appointment.healthCarePlan.id"/>
			
			<label>Médico</label>
			<select name="appointment.doctor.id">
			</select>
			
			<label>Especialidade</label>
			<select name="appointment.specialty.id">
			</select>
			
			<label>Retorno?</label>
			<input type="radio" name="appointment.return" value="1"> Sim<br>
			<input type="radio" name="appointment.return" value="0" checked> Não
			
			<label>Observações:</label>
			<textarea name="appointment.observations"></textarea>
			
		</fieldset>
		<!-- FIM CADASTRO CONSULTA -->
	
		<!-- INÍCIO PROCEDIMENTOS -->
		<fieldset class="add-procedure">
			<label>Procedimento:</label>
			<input type="text"  id="txt_search_procedure"/> <input type="button" value="+" id="btn_search_procedure"/>
		</fieldset>
		
		<div id="procedures"></div>
		<!-- FIM PROCEDIMENTOS -->	
	
	</form>
	
</body>
</html>
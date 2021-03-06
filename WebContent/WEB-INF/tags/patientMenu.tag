<%@ tag language="java" pageEncoding="utf-8"%>
<%@tag display-name="patientMenu"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ attribute name="patient" type="br.com.easyclinica.domain.entities.Patient" required="true" rtexprvalue="true" %>
<%@ attribute name="selected" required="true" %>
<ul class="boxmenu">
	<li ${selected=='Paciente' ? 'class="active first"' : ''}>
		<a href="<c:url value="/pacientes/${patient.id}"/>">Paciente</a>
	</li>
	
	<c:if test="${loggedUser.employee.position != 'DOCTOR'}">
	<li ${selected=='Edicao' ? 'class="active first"' : ''}>
		<a href="<c:url value="/pacientes/${patient.id}/editar"/>">Edição</a>
	</li>
	</c:if>
	
	<li ${selected=='Anamnese' ? 'class="active first"' : ''}><a href="<c:url value="/pacientes/${patient.id}/anamneses"/>">Anamnese</a></li>
	<li ${selected=='Observacoes' ? 'class="active first"' : ''}><a href="<c:url value="/pacientes/${patient.id}/observacoes"/>">Observações</a></li>
	
	<c:if test="${loggedUser.employee.position != 'DOCTOR'}"> 
	<li ${selected=='Consultas' ? 'class="active first"' : ''}>
		<a href="<c:url value="/pacientes/${patient.id}/consultas"/>">Consultas</a>
	</li>
	</c:if>

	<c:if test="${loggedUser.employee.position == 'DOCTOR' || loggedUser.employee.position == 'OWNER'}"> 
	<li ${selected=='prescricoes' ? 'class="active first"' : ''}>
		<a href="<c:url value="/pacientes/${patient.id}/prescricoes"/>">Prescrições</a>
	</li>
	</c:if>
		
	<c:if test="${loggedUser.employee.position != 'DOCTOR'}">
	<li ${selected=='Recibos' ? 'class="active first"' : ''}>
		<a href="<c:url value="/pacientes/${patient.id}/recibos"/>">Recibos</a>
	</li>
	</c:if>
	<li ${selected=='Impressos' ? 'class="active first"' : ''}><a href="<c:url value="/pacientes/${patient.id}/impressos"/>">Impressos</a></li>
</ul>
<%@ tag language="java" pageEncoding="utf-8"%>
<%@tag display-name="patientMenu"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="patient" type="br.com.easyclinica.domain.entities.Patient" required="true" rtexprvalue="true" %>
<%@ attribute name="selected" required="true" %>
<ul class="boxmenu">
	<li ${selected=='Paciente' ? 'class="active first"' : ''}>
		<a href="<c:url value="/pacientes/${patient.id}/editar"/>">Paciente</a>
	</li>
	<li ${selected=='Anamnese' ? 'class="active first"' : ''}><a href="<c:url value="/pacientes/${patient.id}/anamneses"/>">Anamnese</a></li>
	<li ${selected=='Consultas' ? 'class="active first"' : ''}>
		<a href="<c:url value="/pacientes/${patient.id}/consultas"/>">Consultas</a>
	</li>
	<li ${selected=='Impressos' ? 'class="active first"' : ''}><a href="#">Impressos</a></li>
</ul>
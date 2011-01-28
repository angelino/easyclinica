<%@ tag language="java" pageEncoding="utf-8"%>
<%@tag display-name="patientMenu"%>
<%@ attribute name="patient" type="br.com.easyclinica.domain.entities.Patient" required="true" rtexprvalue="true" %>
<%@ attribute name="selected" required="true" %>
<div class="secondary-navigation">
<ul class="wat-cf">
	<li ${selected=='Paciente' ? 'class="active first"' : ''}><a href="#">Paciente</a></li>
	<li ${selected=='Anamnese' ? 'class="active first"' : ''}><a href="#">Anamnese</a></li>
	<li ${selected=='Consultas' ? 'class="active first"' : ''}><a href="#">Consultas</a></li>
	<li ${selected=='Impressos' ? 'class="active first"' : ''}><a href="#">Impressos</a></li>
</ul>
</div>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>
<html>
	<head>
		<title>.: EasyClinica :.</title>
	</head>
	<body>
		<div class="box" id="pacientes">		
		<helper:patientMenu patient="${patient}" selected="Impressos" />
		
			<div class="boxcontent">
				
				<h2>Impressos</h2>
				
				<ul class="printings">
					<li>
						<a href="<c:url value="/pacientes/${patient.id}/impressos/anamnese" />" class="btn">Anamnese</a>
					</li>
					<li>
						<a href="<c:url value="/pacientes/${patient.id}/impressos/atestado-de-saude" />" class="btn">Atestado de Saúde</a>
					</li>
					<li>
						<a href="<c:url value="/pacientes/${patient.id}/impressos/atestado-de-saude-ocupacional" />" class="btn">Atestado de Saúde Ocupacional</a>
					</li>
				</ul>
				
			</div>
		</div>
		
		<div class="boxright">
			<helper:patientDetails patient="${patient}" />
		</div>
	</body>
</html>
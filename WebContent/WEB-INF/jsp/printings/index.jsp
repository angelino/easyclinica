<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>
<html>
	<head>
		<title>.: EasyClinica :.</title>
	</head>
	<body>
		<div class="box">		
		<helper:patientMenu patient="${patient}" selected="Impressos" />
		
			<div class="boxcontent">

				<a href="<c:url value="/pacientes/${patient.id}/impressos/anamnese" />">Anamnese</a>
				
				<a href="<c:url value="/pacientes/${patient.id}/impressos/atestado-de-saude" />">Atestado de Saúde</a>

				<a href="<c:url value="/pacientes/${patient.id}/impressos/atestado-de-saude-ocupacional" />">Atestado de Saúde Ocupacional</a>

			</div>
		</div>
		
		<div class="boxright">
			<helper:patientDetails patient="${patient}" />
		</div>
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>

<html>
	<head>
		<title>.: EasyClinica - Visualizar Médico :.</title>
	</head>
	<body>

		<!-- INÍCIO MAIN -->
		<div id="main">

			<div class="block" id="block">
				
				<helper:message successKey="${successKey}" errorKey="${errorKey}" />
				
				<div class="content">
			   		<h2 class="title">${doctor.name}</h2>
					<div class="inner">
				
					<div class="agrupar_campos">
						<label class="label">CRM:</label>${doctor.crm}
					</div>
					
					<div class="agrupar_campos">
						<label class="label">Espeialidade:</label>${doctor.specialty}
					</div>
			
					<div class="agrupar_campos">
						<label class="label">Telefone*:</label>${doctor.telephone}
					</div>
					
					<div class="agrupar_campos">
						<label class="label">E-mail:</label>${doctor.email}
					</div>
					
					<label class="label">Observações</label>${doctor.observations}
			
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

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="block" id="block">
	
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
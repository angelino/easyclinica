<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>

<div class="modal-medicos">
  	<h2>Médico: ${doctor.name}</h2>
	
	<div class="modal-content">
		<div>
			<p>CRM:</p>
			<span>${doctor.crm}</span>
		</div>
		
		<div>
			<p>Espeialidade:</p>
			<span>${doctor.specialty}</span>
		</div>
		
		<div>
			<p>Telefone:</p>
			<span>${doctor.telephone}</span>
		</div>
		
		<div>
			<p>E-mail:</p>
			<span>${doctor.email}</span>
		</div>
		
		<div class="observations">
			<p>Observações:</p>
			<span>${doctor.observations}</span>
		</div>
		
		<div class="boxactions">
			<a class="btnedit" href="<c:url value="/medicos/${doctor.id}/editar"/>">Editar</a>
			<a class="btnclose modal-close">Fechar</a>
	    </div>
   	</div>
</div>		
	
		

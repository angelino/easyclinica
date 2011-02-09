<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>

<div class="modal-pacientes">
	<h2>Paciente: ${patient.name}</h2>
	
	<div class="modal-content">		
		
		<div>
			<p>Telefone:</p>
			<span>${patient.telephone}</span>
		</div>
				
		<div>
			<p>Celular:</p> 
			<span>${patient.cellphone}</span>
		</div>

		<div>
			<p>Convênio:</p> 
			<span>${patient.healthCarePlan.name}</span>
		</div>
						
		<div>
			<p>Número da carteirinha:</p> 
			<span>${patient.healthCarePlanCode}</span>
		</div>
						
		<div>
			<p>E-mail:</p> 
			<span>${patient.email}</span>
		</div>
					
		<div class="observations">
			<p>Observações</p> 
			<span>${patient.observations}</span>
		</div>
		
		<div>
			<p>Endereço:</p>
			<span>
				<c:if test="${!empty patient.address.street}">
					${patient.address.street}<br/>
					${patient.address.postalCode} - ${patient.address.neighborhood}<br/>
					${patient.address.city} - ${patient.address.state}
				</c:if>
			</span>
		</div>
		
		<div class="observations">
			<p>Observações:</p>
			<span>${patient.observations}</span>
		</div>
		
		<div class="boxactions">
			<a class="btnedit" href="<c:url value="/pacientes/${patient.id}/editar"/>">Editar</a>
			<a class="btnclose modal-close">Fechar</a>
	    </div>
   	</div>
</div>
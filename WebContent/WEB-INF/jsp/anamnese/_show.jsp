<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>

<div class="modal-anamneses">
  	<h2>${anamnese.patient.name}</h2>
	
	<div class="modal-content">
		<div>
			<p>Data:</p>
			<span><fmt:formatDate value="${anamnese.date.time}" pattern="dd/MM/yyyy" /></span>
		</div>		
		
		<div>
			<p>Médico:</p>
			<span>${anamnese.doctor.crm}</span>
		</div>
		
		<div class="anamnese">
			<p>Anamnese:</p>
			<span>${anamnese.text}</span>
		</div>
		
		<div class="boxactions">
			<a class="btnedit" href="<c:url value="/pacientes/${anamnese.patient.id}/anamneses/${anamnese.id}/editar"/>">Editar</a>
			<a class="btnclose modal-close">Fechar</a>
	    </div>
   	</div>
</div>		
	
		

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>

<div class="modal-recibos">
  	<h2>${receipt.patient.name}</h2>
	
	<div class="modal-content">
		<div>
			<p>Data:</p>
			<span><fmt:formatDate value="${receipt.date.time}" pattern="dd/MM/yyyy" /></span>
		</div>		
		
		<div>
			<p>Em nome de:</p>
			<span>${receipt.inNameOf} (<fmt:message key="${receipt.kinship.localeKey}"></fmt:message>)</span>
		</div>

		<div>
			<p>Data de Nascimento:</p>
			<span><fmt:formatDate pattern="dd/MM/yyyy" value="${receipt.birthDate.time}" /></span>
		</div>
				
		<div>
			<p>CPF:</p>
			<span>${receipt.cpf}</span>
		</div>
		
		<div>
			<p>Valor:</p>
			<span class="currency">${receipt.amount}</span>
		</div>
		
		<div class="observations">
			<p>Observações:</p>
			<span>${receipt.observations}</span>
		</div>
		
		<div class="boxactions">
			<a class="btnedit" href="<c:url value="/pacientes/${receipt.patient.id}/recibos/${receipt.id}/editar"/>">Editar</a>
			<a class="btnclose modal-close">Fechar</a>
			<a class="btnpdf" href="<c:url value="/pacientes/${receipt.patient.id}/recibos/${receipt.id}"/>">Gerar Recibo</a>
	    </div>
   	</div>
</div>		
	
		

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>

<div class="modal-convenios">
  	<h2>Convênio: ${healthCarePlan.name}</h2>
	
	<div class="modal-content">
		<div>
			<p>Valor em R$ da CH:</p>
			<span class="currency">${healthCarePlan.ch}</span>
		</div>
	
		<div>
			<p>Período para retorno (em dias):</p>
			<span>${healthCarePlan.periodToReturn}</span>
		</div>
	
		<div>
			<p>Nome do Responsável:</p>
			<span>${healthCarePlan.contact}</span>
		</div>
		
		<div>
			<p>Telefone:</p>
			<span>${healthCarePlan.telephone}</span>
		</div>
		
		<div>
			<p>E-mail:</p>
			<span>${healthCarePlan.email}</span>
		</div>
		
		<div>
			<p>Website:</p>
			<span>${healthCarePlan.website}</span>
		</div>
		
		<div>
			<p>Endereço:</p>
			<span>
				<c:if test="${!empty healthCarePlan.address.street}">
					${healthCarePlan.address.street}<br/>
					${healthCarePlan.address.postalCode} - ${healthCarePlan.address.neighborhood}<br/>
					${healthCarePlan.address.city} - ${healthCarePlan.address.state}
				</c:if>
			</span>
		</div>
		
		<div class="observations">
			<p>Observações:</p>
			<span>${healthCarePlan.observations}</span>
		</div>
		
		<div class="boxactions">
			<a class="btnedit" href="<c:url value="/convenios/${healthCarePlan.id}/editar"/>">Editar</a>
			<a class="btnclose modal-close">Fechar</a>
	    </div>
   	</div>
</div>
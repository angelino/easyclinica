<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="block" id="block">
	
	<div class="content">
   		<h2 class="title">${healthCarePlan.name}</h2>
		<div class="inner">
	
		<label class="label">Endereço:</label>
    	${healthCarePlan.address.street}
		
		<div class="agrupar_campos">
			<label class="label">Bairro:</label>
	    ${healthCarePlan.address.neighborhood}
		</div>
		
		<div class="agrupar_campos">
			<label class="label">CEP:</label>
	    ${healthCarePlan.address.postalCode}
		</div>

		<div class="agrupar_campos">
			<label class="label">Cidade:</label>
	    ${healthCarePlan.address.city}
		</div>
		
		<div class="agrupar_campos">
			<label class="label">Estado:</label>
	    ${healthCarePlan.address.state}
		</div>
			
		<label class="label">Nome do Responsável:</label>
    	${healthCarePlan.contact}	
		<div class="agrupar_campos">
			<label class="label">Telefone*:</label>
    	${healthCarePlan.telephone}
		</div>
		
		<div class="agrupar_campos">
			<label class="label">E-mail:</label>
    	${healthCarePlan.email}
		</div>
		
		<label class="label">Website:</label>
    ${healthCarePlan.website}
	
	<div class="agrupar_campos">
		<label class="label">Tabela de Serviços*:</label>
		${healthCarePlan.table.name}
	</div>
	
	<div class="agrupar_campos">
		<label class="label">Valor em R$ da CH:</label>
		${healthCarePlan.ch.money}
	</div>
	
		<label class="label">Observações</label>
    ${healthCarePlan.observations}

		</div>
	</div>
</div>
<%@tag description="health care plan form" pageEncoding="UTF-8"%>
<%@tag display-name="include"%>
<%@ attribute name="action" type="java.lang.String" required="true" rtexprvalue="true" %>
<%@ attribute name="errors" type="java.util.List" required="false" rtexprvalue="true" %>
<%@ attribute name="model" type="br.com.easyclinica.domain.entities.HealthCarePlan" required="false" rtexprvalue="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<form action="${action}" method="post">
	<input type="hidden" name="healthCarePlan.id" value="${healthCarePlan.id}" />
	<!--  erros -->
	<c:forEach var="error" items="${errors}">
		erros:
	    <fmt:message key="${error.message}"/><br />
	</c:forEach>

	<fieldset class="cadastro">	
		<label class="label">Nome*:</label>
		<input type="text" name="healthCarePlan.name.name" class="text_field" value="${healthCarePlan.name}" />
    	<span class="description">Ex: 'Amil', 'Blue Life'</span>
	
		<label class="label">Endereço:</label>
    	<input type="text" name="healthCarePlan.address.street.street" class="text_field" value="${healthCarePlan.address.street}" />
    	<span class="description">Ex: 'Av. Paulista, 123'</span>
		
		<div class="agrupar_campos">
			<label class="label">Bairro:</label>
	    <input type="text" name="healthCarePlan.address.neighborhood.neighborhood" class="text_field" value="${healthCarePlan.address.neighborhood}" />
	    <span class="description">Ex: 'Jardins'</span>
		</div>
		
		<div class="agrupar_campos">
			<label class="label">CEP:</label>
	    <input type="text" name="healthCarePlan.address.postalCode.postalCode" class="text_field" value="${healthCarePlan.address.postalCode}" />
	    <span class="description">Ex: '12345-789'</span>		
		</div>

		<div class="agrupar_campos">
			<label class="label">Cidade:</label>
	    <input type="text" name="healthCarePlan.address.city.city" class="text_field" value="${healthCarePlan.address.city}" />
	    <span class="description">Ex: 'São Paulo'</span>
		</div>
		
		<div class="agrupar_campos">
			<label class="label">Estado:</label>
	    <input type="text" name="healthCarePlan.address.state.state" class="text_field" value="${healthCarePlan.address.state}" />
	    <span class="description">Ex: 'SP'</span>		
		</div>
			
		<label class="label">Nome do Responsável:</label>
    <input type="text" name="healthCarePlan.contact.name" class="text_field" value="${healthCarePlan.contact}" />
    <span class="description">Ex: 'José da Silva'</span>
	
		<div class="agrupar_campos">
			<label class="label">Telefone*:</label>
    	<input type="text" name="healthCarePlan.telephone.telephone" class="text_field" value="${healthCarePlan.telephone}" />
    	<span class="description">Ex: '(11) 1111-1111'</span>
		</div>
		
		<div class="agrupar_campos">
			<label class="label">E-mail:</label>
    	<input type="text" name="healthCarePlan.email.email" class="text_field" value="${healthCarePlan.email}" />
    	<span class="description">Ex: 'pessoa@convenio.com.br'</span>
		</div>
		
		<label class="label">Website:</label>
    <input type="text" name="healthCarePlan.website.website" class="text_field" value="${healthCarePlan.website}" />
    <span class="description">Ex: 'http://www.convenio.com.br'</span>
	
	<div class="agrupar_campos">
		<label class="label">Tabela de Serviços*:</label>
	
	<span class="description">Ex: 'AMB99'</span>
	</div>
	
	<div class="agrupar_campos">
		<label class="label">Valor em R$ da CH:</label>
	
	<span class="description">Ex: 'R$ 0.15'</span>
	</div>
	
		<label class="label">Observações</label>
    <input type="text" name="healthCarePlan.observations.observations" class="text_field" value="${healthCarePlan.observations}" />
    <span class="description">Ex: 'só atende ortopedia'</span>
	
		<div class="botoes">
			<button class="button" type="submit"><img src="images/tick.png" alt="Salver" />Salvar</button>
			<img src="images/cross.png" alt="Cancelar" /> Cancelar
		</div>
	
	</fieldset>
  
</form>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>

<form action="${param.formAction}" method="post">
	<c:if test="${param.put == true}">
		<input type="hidden" name="_method" value="PUT" />
	</c:if>
	<input type="hidden" name="healthCarePlan.id" value="${healthCarePlan.id}" />
	
	<helper:errors errors="${errors}" />

	<fieldset class="cadastro">	
		<label class="label">Nome*:</label>
		<input type="text" name="healthCarePlan.name.name" maxlength="50" class="text_field" value="${healthCarePlan.name}" />
    	<span class="description">Ex: 'Amil', 'Blue Life'</span>
	
		<label class="label">Endereço:</label>
    	<input type="text" name="healthCarePlan.address.street.street" maxlength="150" class="text_field" value="${healthCarePlan.address.street}" />
    	<span class="description">Ex: 'Av. Paulista, 123'</span>
		
		<div class="agrupar_campos">
			<label class="label">Bairro:</label>
	    <input type="text" name="healthCarePlan.address.neighborhood.neighborhood" maxlength="100" class="text_field" value="${healthCarePlan.address.neighborhood}" />
	    <span class="description">Ex: 'Jardins'</span>
		</div>
		
		<div class="agrupar_campos">
			<label class="label">CEP:</label>
	    <input type="text" name="healthCarePlan.address.postalCode.postalCode" class="text_field" maxlength="12" value="${healthCarePlan.address.postalCode}" />
	    <span class="description">Ex: '12345-789'</span>		
		</div>

		<div class="agrupar_campos">
			<label class="label">Cidade:</label>
	    <input type="text" name="healthCarePlan.address.city.city" class="text_field" maxlength="50" value="${healthCarePlan.address.city}" />
	    <span class="description">Ex: 'São Paulo'</span>
		</div>
		
		<div class="agrupar_campos">
			<label class="label">Estado:</label>
	    <input type="text" name="healthCarePlan.address.state.state" class="text_field" maxlength="2" value="${healthCarePlan.address.state}" />
	    <span class="description">Ex: 'SP'</span>		
		</div>
			
		<label class="label">Nome do Responsável:</label>
    <input type="text" name="healthCarePlan.contact.name" class="text_field" maxlength="100" value="${healthCarePlan.contact}" />
    <span class="description">Ex: 'José da Silva'</span>
	
		<div class="agrupar_campos">
			<label class="label">Telefone*:</label>
    	<input type="text" name="healthCarePlan.telephone.telephone" class="text_field" maxlength="50" value="${healthCarePlan.telephone}" />
    	<span class="description">Ex: '(11) 1111-1111'</span>
		</div>
		
		<div class="agrupar_campos">
			<label class="label">E-mail:</label>
    	<input type="text" name="healthCarePlan.email.email" class="text_field" maxlength="100" value="${healthCarePlan.email}" />
    	<span class="description">Ex: 'pessoa@convenio.com.br'</span>
		</div>
		
		<label class="label">Website:</label>
    <input type="text" name="healthCarePlan.website.website" class="text_field" maxlength="100" value="${healthCarePlan.website}" />
    <span class="description">Ex: 'http://www.convenio.com.br'</span>
	
	<div class="agrupar_campos">
		<label class="label">Tabela de Serviços*:</label>
		<c:choose>
			<c:when test="${param.edit == true}">
				${healthCarePlan.table.name}
			</c:when>
			<c:otherwise>
				<select name="healthCarePlan.table.id">
					<c:forEach var="table" items="${tables}">
					<option value="${table.id}" <c:if test="${table.id == healthCarePlan.table.id}">selected</c:if>>${table.name}</option>
					</c:forEach>
				</select>
			<span class="description">Ex: 'AMB99'</span>
			</c:otherwise>
		</c:choose>
	</div>
	
	<div class="agrupar_campos">
		<label class="label">Valor em R$ da CH:</label>
		<input type="text" name="healthCarePlan.ch.money" class="text_field" value="${healthCarePlan.ch}" />
	<span class="description">Ex: 'R$ 0.15'</span>
	</div>
	
		<label class="label">Observações</label>
		<textarea rows="5" cols="60" name="healthCarePlan.observations.observations" class="textfield">${healthCarePlan.observations}</textarea>
    <span class="description">Ex: 'só atende ortopedia'</span>
	
		<div class="botoes">
			<button class="button" type="submit">
				<c:url value="/images/icons/tick.png" var="img_salvar"/>
				<img src="${img_salvar}" alt="Salvar" />Salvar
			</button>
			
			<c:url value="/convenios" var="cancelar"/>
			<a class="button" href="${cancelar}">
				<c:url value="/images/icons/cross.png" var="img_cancelar"/>
				<img src="${img_cancelar}" alt="Cancelar" />Cancelar
			</a>
		</div>
	
	</fieldset>
  
</form>
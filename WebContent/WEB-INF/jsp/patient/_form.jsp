<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>

<form action="${param.formAction}" method="post">
	<c:if test="${param.put == true}">
		<input type="hidden" name="_method" value="PUT" />
	</c:if>
	<input type="hidden" name="patient.id" value="${patient.id}" />
	
	<helper:errors errors="${errors}" />

	<fieldset class="cadastro">	
		<label class="label">Nome*:</label>
		<input type="text" name="patient.name.name" maxlength="50" class="text_field" value="${patient.name}" />
    	<span class="description">Ex: 'José da Silva'</span>
	
		<label class="label">Endereço:</label>
    	<input type="text" name="patient.address.street.street" maxlength="150" class="text_field" value="${patient.address.street}" />
    	<span class="description">Ex: 'Av. Paulista, 123'</span>
		
		<div class="agrupar_campos">
			<label class="label">Bairro:</label>
	    <input type="text" name="patient.address.neighborhood.neighborhood" maxlength="100" class="text_field" value="${patient.address.neighborhood}" />
	    <span class="description">Ex: 'Jardins'</span>
		</div>
		
		<div class="agrupar_campos">
			<label class="label">CEP:</label>
	    <input type="text" name="patient.address.postalCode.postalCode" class="text_field mask_cep" maxlength="12" value="${patient.address.postalCode}" />
	    <span class="description">Ex: '12345-789'</span>		
		</div>

		<div class="agrupar_campos">
			<label class="label">Cidade:</label>
	    <input type="text" name="patient.address.city.city" class="text_field" maxlength="50" value="${patient.address.city}" />
	    <span class="description">Ex: 'São Paulo'</span>
		</div>
		
		<div class="agrupar_campos">
			<label class="label">Estado:</label>
	    <input type="text" name="patient.address.state.state" class="text_field" maxlength="2" value="${patient.address.state}" />
	    <span class="description">Ex: 'SP'</span>		
		</div>
	
		<div class="agrupar_campos">
			<label class="label">Telefone*:</label>
    	<input type="text" name="patient.telephone.telephone" class="text_field mask_telefone" maxlength="50" value="${patient.telephone}" />
    	<span class="description">Ex: '(11) 1111-1111'</span>
		</div>
		
		<div class="agrupar_campos">
			<label class="label">Celular:</label>
    	<input type="text" name="patient.cellphone.telephone" class="text_field mask_telefone" maxlength="50" value="${patient.cellphone}" />
    	<span class="description">Ex: '(11) 1111-1111'</span>
		</div>
		
		<label class="label">E-mail:</label>
    <input type="text" name="patient.email.email" class="text_field" maxlength="100" value="${patient.email}" />
    <span class="description">Ex: 'joao@dasilva.com.br'</span>
	
	<div class="agrupar_campos">
		<label class="label">Convênio*:</label>
				<select name="patient.healthCarePlan.id">
					<c:forEach var="plan" items="${healthCarePlans}">
					<option value="${plan.id}" <c:if test="${plan.id == patient.healthCarePlan.id}">selected</c:if>>${plan.name}</option>
					</c:forEach>
				</select>
	</div>
	
		<div class="agrupar_campos">
			<label class="label">Número da carteirinha:</label>
    	<input type="text" name="patient.healthCareId.healthCareId" class="text_field" maxlength="100" value="${patient.healthCareId.healthCareId}" />
    	<span class="description">Ex: '1234-ABC'</span>
		</div>
	
		<label class="label">Observações</label>
		<textarea rows="5" cols="60" name="patient.observations.observations" class="textfield">${patient.observations}</textarea>
    <span class="description">Ex: 'alérgico a buscopan'</span>
	
		<div class="botoes">
			<button class="button" type="submit">
				<c:url value="/images/icons/tick.png" var="img_salvar"/>
				<img src="${img_salvar}" alt="Salvar" />Salvar
			</button>
			
			<c:url value="/pacientes" var="cancelar"/>
			<a class="button" href="${cancelar}">
				<c:url value="/images/icons/cross.png" var="img_cancelar"/>
				<img src="${img_cancelar}" alt="Cancelar" />Cancelar
			</a>
		</div>
	
	</fieldset>
  
</form>
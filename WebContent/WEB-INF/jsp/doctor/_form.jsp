<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>

<form action="<%= request.getParameter("formAction") %>" method="post">
	<% if(request.getParameter("put") != null && request.getParameter("put").equals("true")) { %>
		<input type="hidden" name="_method" value="PUT" />
	<% } %>
	<input type="hidden" name="doctor.id" value="${doctor.id}" />
	
	<helper:errors errors="${errors}" />

	<fieldset class="cadastro">	
		<label class="label">Nome*:</label>
		<input type="text" name="doctor.name" maxlength="50" class="text_field" value="${doctor.name}" />
    	<span class="description">Ex: 'Amil', 'Blue Life'</span>
	
		<div class="agrupar_campos">
			<label class="label">CRM:</label>
		    <input type="text" name="doctor.crm" class="text_field" maxlength="50" value="${doctor.crm}" />
		    <span class="description">Ex: '55.555'</span>
		</div>
		
		<div class="agrupar_campos">
			<label class="label">Especialidade:</label>
		    <select name="doctor.specialty.id">
		    	<option value="0">Selecione uma especialidade</option>
		    	<c:forEach items="${specialties}" var="specialty">
		    		<c:choose> 
		    			<c:when test="${specialty.id == doctor.specialty.id}">
		    				<option value="${specialty.id}" selected="selected">${specialty.name}</option>
		    			</c:when>
		    			<c:otherwise>
		    				<option value="${specialty.id}">${specialty.name}</option>
		    			</c:otherwise>
		    		</c:choose>
		    	</c:forEach>
		    </select>
		    <span class="description">Ex: 'Pediatra', 'Psicólogo'</span>		
		</div>

		<div class="agrupar_campos">
			<label class="label">Telefone*:</label>
	    	<input type="text" name="doctor.telephone" class="text_field mask_telefone" maxlength="50" value="${doctor.telephone}" />
	    	<span class="description">Ex: '(11) 1111-1111'</span>
		</div>
		
		<div class="agrupar_campos">
			<label class="label">E-mail:</label>
	    	<input type="text" name="doctor.email" class="text_field" value="${doctor.email}" maxlength="100" />
	    	<span class="description">Ex: 'pessoa@convenio.com.br'</span>
		</div>
		
		<label class="label">Observações</label>
		<textarea rows="5" cols="60" name="doctor.observations" class="text_field">${doctor.observations}</textarea>
	    <span class="description">Ex: 'só atende ortopedia'</span>
	
		<label class="label">Ativo?</label>
		<c:choose>
			<c:when test="${doctor.active || doctor.id == 0}">
				<input type="checkbox" name="doctor.active" value="1" checked="checked"/>
			</c:when>
			<c:otherwise>
				<input type="checkbox" name="doctor.active" value="1"/>
			</c:otherwise>
		</c:choose>
		
	
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

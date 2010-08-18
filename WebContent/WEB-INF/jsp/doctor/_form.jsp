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
		<input type="text" name="doctor.name.name" maxlength="50" class="text_field" value="${doctor.name}" />
    	<span class="description">Ex: 'Amil', 'Blue Life'</span>
	
		<div class="agrupar_campos">
			<label class="label">CRM:</label>
		    <input type="text" name="doctor.crm.crm" class="text_field" maxlength="50" value="${doctor.crm}" />
		    <span class="description">Ex: '55.555'</span>
		</div>
		
		<div class="agrupar_campos">
			<label class="label">Especialidade:</label>
		    <input type="text" name="doctor.specialty.specialty" class="text_field" maxlength="150" value="${doctor.specialty}" />
		    <span class="description">Ex: 'Pediatra', 'Psicólogo'</span>		
		</div>

		<div class="agrupar_campos">
			<label class="label">Telefone*:</label>
	    	<input type="text" name="doctor.telephone.telephone" class="text_field mask_telefone" maxlength="50" value="${doctor.telephone}" />
	    	<span class="description">Ex: '(11) 1111-1111'</span>
		</div>
		
		<div class="agrupar_campos">
			<label class="label">E-mail:</label>
	    	<input type="text" name="doctor.email.email" class="text_field" value="${doctor.email}" maxlength="100" />
	    	<span class="description">Ex: 'pessoa@convenio.com.br'</span>
		</div>
		
		<label class="label">Observações</label>
		<textarea rows="5" cols="60" name="doctor.observations.observations" class="text_field">
			${doctor.observations}
		</textarea>
	    <span class="description">Ex: 'só atende ortopedia'</span>
	
		<div class="botoes">
			<button class="button" type="submit">
				<c:url value="/images/tick.png" var="img_salvar"/>
				<img src="${img_salvar}" alt="Salvar" />Salvar
			</button>
		</div>
	
	</fieldset>
  
</form>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>

<form action="${param.formAction}" method="post">
	<input type="hidden" name="employee.id" value="${employee.id}" />
	<input type="hidden" name="employee.active" value="${employee.active}" />
	<input type="hidden" name="employee.position" value="${employee.position}" />
	<input type="hidden" name="employee.login" value="${employee.login}" />
	
	<c:if test="${not empty employee.doctor.id}">
		<input type="hidden" name="employee.doctor.id" value="${employee.doctor.id}" />
	</c:if>
	
	<helper:errors errors="${errors}" />

	<p class="required"><span>*</span> campos obrigatórios</p>

	<fieldset>
		<div>	
			<label class="title">Nome:<span>*</span></label>
			<input type="text" name="employee.name" maxlength="50" required="required" value="${employee.name}" />
    	</div>

    	
		<div>
			<label class="title">Senha:</label>
			<input type="password" name="employee.password" maxlength="100" />
			<em>Se não quiser alterar a senha, deixe este campo em branco.</em>
		</div>

		<div class="telephone">
			<label class="title">Celular:</label>
    		<input type="text" name="employee.cellphone" class="mask_telefone" maxlength="50" value="${employee.cellphone}" />
    	</div>
		
		<div>
			<label class="title">E-mail:</label>
    		<input type="text" name="employee.email" maxlength="100" value="${employee.email}" />
    	</div>
		
		<div>
			<label class="title">Observações:</label>
			<textarea name="employee.observations">${employee.observations}</textarea>
    	</div>
	
	
	</fieldset>
    
    <div class="boxactions">
		<input type="submit" class="btnsave" value="Salvar" />
        <input type="button" class="btncancel" value="Cancelar" redirect_to="<c:url value="/"/>"/>
    </div>
  
</form>
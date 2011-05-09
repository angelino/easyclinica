<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>

<form action="${param.formAction}" method="post">
	<c:if test="${param.put == true}">
		<input type="hidden" name="_method" value="PUT" />
	</c:if>
	<input type="hidden" name="employee.id" value="${employee.id}" />
	<input type="hidden" name="employee.active" value="${employee.active}" />
	
	<helper:errors errors="${errors}" />

	<p class="required"><span>*</span> campos obrigatórios</p>

	<fieldset>
		<div>	
			<label class="title">Nome:<span>*</span></label>
			<input type="text" name="employee.name" maxlength="50" required="required" value="${employee.name}" />
    	</div>

		<div>	
			<label class="title">Cargo:<span>*</span></label>
			
			<select name="employee.position" id="position">
				<c:forEach var="position" items="${positions}">
					<option value="${position}" ${position == employee.position ? 'selected' : ''}><fmt:message key="${position.localeKey}"></fmt:message></option>
				</c:forEach>
			</select>
    	</div>
    	
		<div id="doctors">
			<label class="title">Médico associado:<span>*</span></label>
			<input type="hidden" name="employee.doctor.id" id="doctorId" value="${employee.doctor.id}" />
			
			<select name="selectedDoctor" id="selectedDoctor">
				<c:forEach var="doctor" items="${doctors}">
					<option value="${doctor.id}" ${doctor.id == employee.doctor.id ? 'selected' : ''} >${doctor.name}</option>
				</c:forEach>
			</select>
		</div>
    	
		<c:if test="${param.put != true}">
			<div>
				<label class="title">Login:<span>*</span></label>
				<input type="text" name="employee.login" maxlength="100" value="${employee.login}" required="required" />
			</div>
			
			<div>
				<label class="title">Senha:<span>*</span></label>
				<input type="password" name="employee.password" maxlength="100" value="${employee.password}" required="required" />
			</div>

		</c:if>
		
		<c:if test="${param.put == true}">
			<div>
				<label class="title">Login:<span>*</span></label>
				<input type="hidden" name="employee.login" value="${employee.login}" />${employee.login}
			</div>
			<div>
				<label class="title">Senha:</label>
				<input type="password" name="employee.password" maxlength="100" />
					<span>Se não quiser alterar a senha, deixe este campo em branco.</span>
			</div>
		</c:if>
					
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
        <input type="button" class="btncancel" value="Cancelar" redirect_to="<c:url value="/usuarios"/>"/>
    </div>
  
</form>
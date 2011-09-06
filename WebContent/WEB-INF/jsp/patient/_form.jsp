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

	<fieldset class="full">
		<div>
			<label class="title">Nome:<span>*</span></label>
			<input type="text" name="patient.name" maxlength="50" tabindex="1" required="required" value="${patient.name}" />
		</div>
		
		<div class="title">
			<label class="title">Sexo:</label>
			<select name="patient.gender" tabindex="2" style="width:100px">
				<c:forEach var="gender" items="${genders}">
					<option value="${gender}" <c:if test="${gender == patient.gender}">selected</c:if>>${gender.formattedName}</option>
				</c:forEach>
			</select>
    	</div>
	</fieldset>

	<fieldset>
		<div class="title">
			<label class="title">CPF:<span>*</span></label>
    		<input type="text" name="patient.cpf" maxlength="50" tabindex="2" value="${patient.cpf}" class="mask_cpf" />
    	</div>
		<div class="title">
			<label class="title">Data de Nascimento:<span>*</span></label>
    		<input type="text" name="patient.birthDate"maxlength="50" tabindex="4" required="required" class="mask_date" value="<fmt:formatDate value="${patient.birthDate.time}" pattern="dd/MM/yyyy" />" />
    	</div>
    	<div class="telephone">
			<label class="title">Celular:</label>
    		<input type="text" name="patient.cellphone" tabindex="6" class="mask_telefone" maxlength="50" value="${patient.cellphone}" />
    	</div>
    	<div class="title">
			<label class="title">Profissão:</label>
    		<input type="text" name="patient.profession" tabindex="8" maxlength="50" value="${patient.profession}" />
    	</div>
	</fieldset>

	<fieldset>
		<div class="title">
			<label class="title">RG:</label>
    		<input type="text" name="patient.rg" tabindex="3" maxlength="50" value="${patient.rg}" />
    	</div>
		<div class="title">
			<label class="title">Telefone:<span>*</span></label>
    		<input type="text" name="patient.telephone" tabindex="5" class="mask_telefone" required="required" maxlength="50" value="${patient.telephone}" />
    	</div>
    	<div class="title">
			<label class="title">Comercial:</label>
    		<input type="text" name="patient.commercialPhone" tabindex="7" class="mask_telefone" maxlength="50" value="${patient.commercialPhone}" />
    	</div>
    	<div class="title">
			<label class="title">Estado Civil:</label>
			<select name="patient.maritalStatus" tabindex="9">
				<c:forEach var="status" items="${statuses}">
					<option value="${status}" <c:if test="${status == patient.maritalStatus}">selected</c:if>>${status.formattedName}</option>
				</c:forEach>
			</select>
    	</div>
    </fieldset>
    
    <fieldset class="full">  	    	
		<div>
			<label class="title">E-mail:</label>
			<input type="text" name="patient.email" tabindex="10" maxlength="100" value="${patient.email}" />
		</div>
	</fieldset>
	
	<fieldset>	
		<div>
			<label class="title">Convênio:<span>*</span></label>
			<select name="patient.healthCarePlan.id" tabindex="11" min="1" data-message="Selecione um convênio.">
				<c:forEach var="plan" items="${healthCarePlans}">
					<c:choose>
						<c:when test="${plan.id == patient.healthCarePlan.id || (patient.id == 0 && plan.id == loggedUser.clinic.privatePlan.id)}">
							<option value="${plan.id}" selected="selected">${plan.name}</option>
						</c:when>
						<c:otherwise>
							<option value="${plan.id}">${plan.name}</option>
						</c:otherwise>
					</c:choose>					
				</c:forEach>
			</select>
		</div>
	</fieldset>
	
	<fieldset>
		<div>
			<label class="title">Número da carteirinha:</label>
    		<input type="text" name="patient.healthCarePlanCode" tabindex="12" maxlength="100" value="${patient.healthCarePlanCode}" />
    	</div>
	</fieldset>
	
	<fieldset class="full">
		<div class="cep">
			<label class="title">CEP:</label>
			<input type="text" name="patient.address.postalCode" tabindex="13" class="mask_cep" maxlength="12" value="${patient.address.postalCode}" />
		</div>
	</fieldset>
	
	<fieldset>
		<div>
			<label class="title">Endereço:</label>
	    	<input type="text" name="patient.address.street" tabindex="14" maxlength="150" value="${patient.address.street}" />
	    </div>
	    <div>
			<label class="title">Cidade:</label>
			<input type="text" name="patient.address.city" tabindex="16" maxlength="50" value="${patient.address.city}" />
		</div>
	</fieldset>
	
	<fieldset>	
		<div>
			<label class="title">Bairro:</label>
			<input type="text" name="patient.address.neighborhood" tabindex="15" maxlength="100" value="${patient.address.neighborhood}" />
		</div>
		<div>
			<label class="title">Estado:</label>
	    	<input type="text" name="patient.address.state" tabindex="17" maxlength="2" value="${patient.address.state}" />
	    </div>
	</fieldset>
    
    <fieldset class="full">
    	<div class="remarks">
       		<label class="title">Observações:</label>
        	<textarea name="patient.observations" tabindex="18">${patient.observations}</textarea>
      	</div>
    </fieldset>
    
    <div class="boxactions">
		<input type="submit" class="btnsave" value="Salvar" id="btnSalvar" />
        <input type="button" class="btncancel" value="Cancelar" redirect_to="<c:url value="/pacientes"/>"/>
    </div>
  
</form>
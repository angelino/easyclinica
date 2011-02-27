<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>

<form action="${param.formAction}" method="post">
	<c:if test="${param.put == true}">
		<input type="hidden" name="_method" value="PUT" />
	</c:if>
	<input type="hidden" name="patient.id" value="${patient.id}" />
	<input type="hidden" name="patient.active" value="${patient.active}" />
	
	<helper:errors errors="${errors}" />

	<p class="required"><span>*</span> campos obrigatórios</p>

	<fieldset>
		<div>
			<label class="title">Nome:<span>*</span></label>
			<input type="text" name="patient.name" maxlength="50" required="required" value="${patient.name}" />
		</div>
		
		<div class="telephone">
			<label class="title">Telefone:<span>*</span></label>
    		<input type="text" name="patient.telephone" class="mask_telefone" required="required" maxlength="50" value="${patient.telephone}" />
    	</div>
		
		<div class="telephone">
			<label class="title">Celular:</label>
    		<input type="text" name="patient.cellphone" class="mask_telefone" maxlength="50" value="${patient.cellphone}" />
    	</div>

		<div class="telephone">
			<label class="title">Comercial:</label>
    		<input type="text" name="patient.commercialPhone" class="mask_telefone" maxlength="50" value="${patient.commercialPhone}" />
    	</div>

		<div class="title">
			<label class="title">Birth Date:</label>
    		<input type="text" name="patient.birthDate"maxlength="50" class="datepicker" value="<fmt:formatDate value="${patient.birthDate.time}" pattern="dd/MM/yyyy" />" />
    	</div>

		<div class="title">
			<label class="title">RG:</label>
    		<input type="text" name="patient.rg" maxlength="50" value="${patient.rg}" />
    	</div>
    	
    	<div class="title">
			<label class="title">CPF:</label>
    		<input type="text" name="patient.cpf" maxlength="50" value="${patient.cpf}" />
    	</div>

    	<div class="title">
			<label class="title">Profissão:</label>
    		<input type="text" name="patient.profession" maxlength="50" value="${patient.profession}" />
    	</div>

    	<div class="title">
			<label class="title">Estado Civil:</label>
			<select name="patient.maritalStatus">
				<c:forEach var="status" items="${statuses}">
					<option value="${status}" <c:if test="${status == patient.maritalStatus}">selected</c:if>>${status.formattedName}</option>
				</c:forEach>
			</select>
    	</div>
    	    	    	
		<div>
			<label class="title">E-mail:</label>
			<input type="text" name="patient.email" maxlength="100" value="${patient.email}" />
		</div>
		
		<div>
			<label class="title">Convênio:<span>*</span></label>
			<select name="patient.healthCarePlan.id" min="1" data-message="Selecione um convênio.">
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
	
		<div>
			<label class="title">Número da carteirinha:</label>
    		<input type="text" name="patient.healthCarePlanCode" maxlength="100" value="${patient.healthCarePlanCode}" />
    	</div>
	
	</fieldset>
	
	<fieldset>	
		<div class="cep">
			<label class="title">CEP:</label>
			<input type="text" name="patient.address.postalCode" class="mask_cep" maxlength="12" value="${patient.address.postalCode}" />
		</div>
		
		<div>
			<label class="title">Endereço:</label>
	    	<input type="text" name="patient.address.street" maxlength="150" value="${patient.address.street}" />
	    </div>
		
		<div>
			<label class="title">Bairro:</label>
			<input type="text" name="patient.address.neighborhood" maxlength="100" value="${patient.address.neighborhood}" />
		</div>
		
		<div>
			<label class="title">Cidade:</label>
			<input type="text" name="patient.address.city" maxlength="50" value="${patient.address.city}" />
		</div>
		
		<div>
			<label class="title">Estado:</label>
	    	<input type="text" name="patient.address.state" maxlength="2" value="${patient.address.state}" />
	    </div>
	
   		<div class="remarks">
       		<label class="title">Observações:</label>
        	<textarea name="patient.observations">${patient.observations}</textarea>
      	</div>
    </fieldset>
    
    <div class="boxactions">
		<input type="submit" class="btnsave" value="Salvar" />
        <input type="button" class="btncancel" value="Cancelar" redirect_to="<c:url value="/pacientes"/>"/>
    </div>
  
</form>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>

<form action="${param.formAction}" method="post">
	<c:if test="${param.put == true}">
		<input type="hidden" name="_method" value="PUT" />
	</c:if>
	<input type="hidden" name="healthCarePlan.id" value="${healthCarePlan.id}" />
	<input type="hidden" name="healthCarePlan.active" value="${healthCarePlan.active}" />
	
	<helper:errors errors="${errors}" />

	<p class="required"><span>*</span> campos obrigatórios</p>

	<fieldset>
		<div>	
			<label class="title">Nome:<span>*</span></label>
			<input type="text" name="healthCarePlan.name" maxlength="50" required="required" value="${healthCarePlan.name}" />
    	</div>
	
		<div>
			<label class="title">Nome do Responsável:</label>
			<input type="text" name="healthCarePlan.contact" maxlength="100" value="${healthCarePlan.contact}" />
		</div>
	
		<div class="telephone">
			<label class="title">Telefone:<span>*</span></label>
    		<input type="text" name="healthCarePlan.telephone" class="mask_telefone" required="required" maxlength="50" value="${healthCarePlan.telephone}" />
    	</div>
		
		<div>
			<label class="title">E-mail:</label>
    		<input type="text" name="healthCarePlan.email" maxlength="100" value="${healthCarePlan.email}" />
    	</div>
		
		<div>
			<label class="title">Website:</label>
			<input type="text" name="healthCarePlan.website" maxlength="100" value="${healthCarePlan.website}" />
    	</div>
		
		<div>
			<label class="title">Valor em R$ da CH:<span>*</span></label>
			<input type="text" name="healthCarePlan.ch" class="currency" required="required" value="${healthCarePlan.ch}" />
		</div>
	
		<div>
			<label class="title">Período para retorno (em dias):<span>*</span></label>
			<input type="text" name="healthCarePlan.periodToReturn" min="0" required="required" data-message="campo obrigatório" value="${healthCarePlan.periodToReturn}" />
		</div>
	
	</fieldset>
	
	<fieldset>	
		<div class="cep">
			<label class="title">CEP:</label>
			<input type="text" name="healthCarePlan.address.postalCode" class="mask_cep" maxlength="12" value="${healthCarePlan.address.postalCode}" />
		</div>
		
		<div>
			<label class="title">Endereço:</label>
	    	<input type="text" name="healthCarePlan.address.street" maxlength="150" value="${healthCarePlan.address.street}" />
	    </div>
		
		<div>
			<label class="title">Bairro:</label>
			<input type="text" name="healthCarePlan.address.neighborhood" maxlength="100" value="${healthCarePlan.address.neighborhood}" />
		</div>
		
		<div>
			<label class="title">Cidade:</label>
			<input type="text" name="healthCarePlan.address.city" maxlength="50" value="${healthCarePlan.address.city}" />
		</div>
		
		<div>
			<label class="title">Estado:</label>
	    	<input type="text" name="healthCarePlan.address.state" maxlength="2" value="${healthCarePlan.address.state}" />
	    </div>
	
   		<div class="remarks">
       		<label class="title">Observações:</label>
        	<textarea name="healthCarePlan.observations">${healthCarePlan.observations}</textarea>
      	</div>
    </fieldset>
    
    <div class="boxactions">
		<input type="submit" class="btnsave" value="Salvar" />
        <input type="button" class="btncancel" value="Cancelar" redirect_to="<c:url value="/convenios"/>"/>
    </div>
  
</form>
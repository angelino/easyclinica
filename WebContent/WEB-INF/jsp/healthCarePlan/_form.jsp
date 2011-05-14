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
			<input type="text" name="healthCarePlan.name" tabindex="1" maxlength="50" required="required" value="${healthCarePlan.name}" />
    	</div>
    	<div class="telephone">
			<label class="title">Telefone:<span>*</span></label>
    		<input type="text" name="healthCarePlan.telephone" tabindex="3" class="mask_telefone" required="required" maxlength="50" value="${healthCarePlan.telephone}" />
    	</div>
    </fieldset>

	<fieldset>
		<div>
			<label class="title">Nome do Responsável:</label>
			<input type="text" name="healthCarePlan.contact" tabindex="2" maxlength="100" value="${healthCarePlan.contact}" />
		</div>
		<div>
			<label class="title">E-mail:</label>
    		<input type="text" name="healthCarePlan.email" tabindex="4" maxlength="100" value="${healthCarePlan.email}" />
    	</div>
	</fieldset>
	
	<fieldset class="full">
		<div>
			<label class="title">Website:</label>
			<input type="text" name="healthCarePlan.website" tabindex="5" maxlength="100" value="${healthCarePlan.website}" />
    	</div>
	</fieldset>
	
	<fieldset class="full">
		<div class="small">
			<label class="title">CEP:</label>
			<input type="text" name="healthCarePlan.address.postalCode" tabindex="6" class="mask_cep" maxlength="12" value="${healthCarePlan.address.postalCode}" />
		</div>
	</fieldset>
	
	<fieldset>	
		<div>
			<label class="title">Endereço:</label>
	    	<input type="text" name="healthCarePlan.address.street" tabindex="7" maxlength="150" value="${healthCarePlan.address.street}" />
	    </div>
			
		<div>
			<label class="title">Estado:</label>
	    	<input type="text" name="healthCarePlan.address.state" tabindex="9" maxlength="2" value="${healthCarePlan.address.state}" />
	    </div>
	</fieldset>
	
	<fieldset>
		<div>
			<label class="title">Bairro:</label>
			<input type="text" name="healthCarePlan.address.neighborhood" tabindex="8" maxlength="100" value="${healthCarePlan.address.neighborhood}" />
		</div>	
		
		<div>
			<label class="title">Cidade:</label>
			<input type="text" name="healthCarePlan.address.city" tabindex="10" maxlength="50" value="${healthCarePlan.address.city}" />
		</div>
	</fieldset>
	
	<fieldset>
		<c:if test="${empty param.put}">
			<div>
				<label class="title">Copiar parte financeira do convênio:</label>
				<select name="example.id" tabindex="11">
					<option value="0" selected="selected">Não copiar</option>
					<c:forEach var="plan" items="${healthCarePlans}">
						<option value="${plan.id}">${plan.name}</option>
					</c:forEach>
				</select>
			</div>	
		</c:if>
		
		<div class="pay-for-room-rate">
			<label class="title">Paga por Taxa de Sala?</label>
			<label>SIM</label>
			<input type="radio" class="radio" name="healthCarePlan.payForRoomRate" tabindex="13" value="true" <c:if test="${healthCarePlan.payForRoomRate}">checked</c:if> />
			<label>NÃO</label>
			<input type="radio" class="radio" name="healthCarePlan.payForRoomRate" tabindex="14" value="false" <c:if test="${not healthCarePlan.payForRoomRate}">checked</c:if> />
		</div>
	</fieldset>
	
	<fieldset>
		<div>
			<label class="title">Valor em R$ da CH:<span>*</span></label>
			<input type="text" name="healthCarePlan.ch" class="currency" tabindex="12" required="required" value="${healthCarePlan.ch}" />
		</div>
		<div>
			<label class="title">Valor padrão para Taxa de Sala:<span>*</span></label>
			<input type="text" name="healthCarePlan.roomRateDefaultAmount" tabindex="15" class="currency" required="required" value="${healthCarePlan.roomRateDefaultAmount}" />
		</div>
	</fieldset>
	
	<fieldset class="full">
		<div class="small">
			<label class="title">Período para retorno (em dias):<span>*</span></label>
			<input type="text" name="healthCarePlan.periodToReturn" tabindex="16" min="0" required="required" data-message="campo obrigatório" value="${healthCarePlan.periodToReturn}" />
		</div>
	</fieldset>
	
    <fieldset class="full">
    	<div class="remarks">
       		<label class="title">Observações:</label>
        	<textarea name="healthCarePlan.observations" tabindex="17">${healthCarePlan.observations}</textarea>
      	</div>
    </fieldset>
    
    <div class="boxactions">
		<input type="submit" class="btnsave" value="Salvar" />
        <input type="button" class="btncancel" value="Cancelar" redirect_to="<c:url value="/convenios"/>"/>
    </div>
  
</form>
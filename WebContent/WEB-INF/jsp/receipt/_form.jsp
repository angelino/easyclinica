<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>

<form action="<%= request.getParameter("formAction") %>" method="post">
	<% if(request.getParameter("put") != null && request.getParameter("put").equals("true")) { %>
		<input type="hidden" name="_method" value="PUT" />
	<% } %>
	<input type="hidden" name="receipt.id" value="${receipt.id}" />
	<input type="hidden" name="receipt.patient.id" value="${receipt.patient.id}" />
	<input type="hidden" name="receipt.employee.id" value="${loggedUser.employee.id}" />
	
	<input type="hidden" name="birthDate" value='<fmt:formatDate pattern="dd/MM/yyyy" value="${receipt.patient.birthDate.time}" />' />
	
	<helper:errors errors="${errors}" />

	<fieldset>
      	<div class="date">
			<label class="title">Data:<span>*</span></label>
			<input type="text" name="receipt.date" maxlength="10" class="datepicker" required="required" value="<fmt:formatDate value="${receipt.date.time}" pattern="dd/MM/yyyy" />" />
		</div>
      	<div>
          	<label class="title">Em nome de:<span>*</span></label>
          	<input type="text" name="receipt.inNameOf" maxlength="100" required="required" value="${(receipt.id == 0 ? receipt.patient.name : receipt.inNameOf)}" />
        </div>
        <div>
          	<label class="title">Grau de Parentesco:<span>*</span></label>
          	<select name="receipt.kinship">
          		<c:forEach var="kinship" items="${kinships}">
					<option value="${kinship}" ${kinship == receipt.kinship ? 'selected' : ''}>
					<fmt:message key="${kinship.localeKey}"></fmt:message>
					</option>
				</c:forEach>
          	</select>
        </div>
      	<div>
          	<label class="title">Data de Nascimento:<span>*</span></label>
          	<% if(request.getParameter("put") != null && request.getParameter("put").equals("true")) { %>
	          	<input type="text" name="receipt.birthDate" required="required" value="${receipt.birthDate}" />
	        <% } else { %>
	          	<input type="text" name="receipt.birthDate" required="required" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${receipt.patient.birthDate.time}" />" />
	        <% } %>          	
        </div>
        <div>
          	<label class="title">CPF:<span>*</span></label>
          	<input type="text" name="receipt.cpf" class="mask_cpf" maxlength="100" required="required" value="${receipt.id == 0 ? receipt.patient.cpf : receipt.cpf}" />
        </div>
        <div>
          	<label class="title">Valor:<span>*</span></label>
          	<input type="text" name="receipt.amount" class="currency" value="${receipt.amount}" />
        </div>
	</fieldset>
	      	
      <fieldset>
      	<div class="remarks">
          	<label class="title">Observações:</label>
            <textarea name="receipt.observations" allowEnter="true">${receipt.observations}</textarea>
        </div>
      </fieldset>
      
      <div class="boxactions">
			<input type="submit" class="btnsave" value="Gerar Recibo" />
      </div>
  
</form>

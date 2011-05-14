<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>

<form action="<%= request.getParameter("formAction") %>" method="post">
	<% if(request.getParameter("put") != null && request.getParameter("put").equals("true")) { %>
		<input type="hidden" name="_method" value="PUT" />
	<% } %>
	<input type="hidden" name="observation.id" value="${observation.id}" />
	<input type="hidden" name="observation.patient.id" value="${patient.id}" />
	
	<helper:errors errors="${errors}" />

	<p class="required"><span>*</span> campos obrigatórios</p>

	<fieldset>
      	<div class="date">
			<label class="title">Data:<span>*</span></label>
			<input type="text" name="observation.date" maxlength="50" class="datepicker" required="required" value="<fmt:formatDate value="${observation.date.time}" pattern="dd/MM/yyyy" />" />
		</div>
    
     	<div class="remarks">
         	<label class="title">Observações:<span>*</span></label>
           <textarea name="observation.text" required="required" allowEnter="true">${observation.text}</textarea>
       </div>
     </fieldset>
      
      <div class="boxactions">
			<input type="submit" class="btnsave" value="Salvar" />
      </div>
  
</form>

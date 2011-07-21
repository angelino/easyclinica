<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>

<form action="<%= request.getParameter("formAction") %>" method="post">
	<% if(request.getParameter("put") != null && request.getParameter("put").equals("true")) { %>
		<input type="hidden" name="_method" value="PUT" />
	<% } %>
	<input type="hidden" name="prescription.id" value="${prescription.id}" />
	<input type="hidden" name="prescription.patient.id" value="${patient.id}" />
	
	<helper:errors errors="${errors}" />

	<fieldset>
      	<div class="date">
			<label class="title">Data:<span>*</span></label>
			<input type="text" name="prescription.date" maxlength="50" class="datepicker" required="required" value="<fmt:formatDate value="${prescription.date.time}" pattern="dd/MM/yyyy" />" />
		</div>

      	<div>
			<label class="title">Médico:<span>*</span></label>
				<select name="prescription.doctor.id" min="1" data-message="Selecione um médico.">
					<option value="0">Selecione um médico</option>
					<c:forEach var="doctor" items="${doctors}" varStatus="status">
						<option value="${doctor.id}" ${doctor.id == prescription.doctor.id ? 'selected' : ''}>${doctor.name}</option>
					</c:forEach>
				</select>
		</div>
		    
     	<div class="remarks">
         	<label class="title">Prescrição:<span>*</span></label>
           <textarea name="prescription.text" required="required" allowEnter="true">${prescription.text}</textarea>
       </div>
     </fieldset>
      
      <div class="boxactions">
			<input type="submit" class="btnsave" value="Salvar" />
      </div>
  
</form>

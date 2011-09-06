<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>

<form action="<%= request.getParameter("formAction") %>" method="post">
	<% if(request.getParameter("put") != null && request.getParameter("put").equals("true")) { %>
		<input type="hidden" name="_method" value="PUT" />
	<% } %>
	<input type="hidden" name="doctor.id" value="${doctor.id}" />
	<input type="hidden" name="doctor.active" value="${doctor.active}" />
	
	<helper:errors errors="${errors}" />

	<fieldset>
      	<div>
			<label class="title">Nome:<span>*</span></label>
			<input type="text" name="doctor.name" maxlength="50" required="required" value="${doctor.name}" />
		</div>
      	<div class="crm">
          	<label class="title">CRM:<span>*</span></label>
            <input type="text" name="doctor.crm" maxlength="50" required="required" value="${doctor.crm}" />
        </div>
      	
      	<div class="specialty">
          	<label class="title">Especialidade:<span>*</span></label>
            <select name="doctor.specialty.id" min="1" data-message="Selecione uma especialidade">
		    	<option value="0">Selecione uma especialidade</option>
		    	<c:forEach items="${specialties}" var="specialty">
		    		<c:choose> 
		    			<c:when test="${specialty.id == doctor.specialty.id}">
		    				<option value="${specialty.id}" selected="selected">${specialty.name}</option>
		    			</c:when>
		    			<c:otherwise>
		    				<option value="${specialty.id}">${specialty.name}</option>
		    			</c:otherwise>
		    		</c:choose>
		    	</c:forEach>
		    </select>
         </div>
         
         <div class="telephone">
         	<label class="title">Telefone:</label>
         	<input type="text" name="doctor.telephone" class="mask_telefone" maxlength="50" value="${doctor.telephone}" />
         </div>
         
         <div>
         	<label class="title">E-mail:</label>
         	<input type="text" name="doctor.email" value="${doctor.email}" maxlength="100" />
         </div>
         
         <div>
         	<label class="title">Intervalo entre consultas (em minutos):</label>
         	<input type="text" name="doctor.intervalBetweenAppointments" value="${doctor.intervalBetweenAppointments}" maxlength="2" />
         </div>
      </fieldset>
      
      <fieldset>
      	<div class="remarks">
          	<label class="title">Observações:</label>
            <textarea name="doctor.observations" allowEnter="true">${doctor.observations}</textarea>
        </div>
      </fieldset>
      
      <div class="boxactions">
			<input type="submit" class="btnsave" value="Salvar" />
          	<input type="button" class="btncancel" value="Cancelar" redirect_to="<c:url value="/medicos"/>"/>
      </div>
  
</form>

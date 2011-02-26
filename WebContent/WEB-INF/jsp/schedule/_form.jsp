<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>

<form action="<%= request.getParameter("formAction") %>" method="post">
	<% if(request.getParameter("put") != null && request.getParameter("put").equals("true")) { %>
		<input type="hidden" name="_method" value="PUT" />
	<% } %>
	
	<fieldset>
		<input type="hidden" name="schedule.id" value="${schedule.id}"/>
		<input type="hidden" name="schedule.doctor.id" value="${schedule.doctor.id}"/>
    		
    	<div>
     		<label>Título:*</label>                    
       		<input type="text" maxlength="200" required="required" name="schedule.subject" value="${schedule.subject}"/>                     
       	</div>
    		
		<div>
			<label>Início:*</label>
			<input type="text" name="schedule.startTime" required="required" class="datetimepicker" value="<fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${schedule.startTime.time}" />"/>
		</div>
 			
		<div>
			<label>Término:*</label>
			<input type="text" name="schedule.endTime" required="required" class="datetimepicker" value="<fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${schedule.endTime.time}" />"/>
		</div>
		
		<div>
			<label>Cor:*</label>
			<div class="colorpicker" rel="schedule.color"></div>
			<input id="schedule.color" name="schedule.color" type="hidden" value="-1" />
		</div>
    		
    	<div>                    
       		<label>Descrição:</label>                    
			<textarea name="schedule.description">${schedule.description}</textarea>                
     	</div>
    </fieldset>
    		
    <div class="boxactions">
		<input type="submit" class="btnsave" value="Salvar" />
      	<a class="btnclose modal-close">Fechar</a>
	</div>
		
</form>
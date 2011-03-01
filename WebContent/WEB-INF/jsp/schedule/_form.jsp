<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>

<form action="<%= request.getParameter("formAction") %>" method="post">
	<% if(request.getParameter("put") != null && request.getParameter("put").equals("true")) { %>
		<input type="hidden" name="_method" value="PUT" />
	<% } %>
	
	<fieldset>
		<p class="required"><span>*</span> campos obrigatórios</p>
		
		<input type="hidden" name="schedule.id" value="${schedule.id}"/>
		
    	<div>
    		<label class="title">Médico:<span>*</span></label>
    		<select name="schedule.doctor.id">
    			<c:forEach items="${medicos}" var="medico">
    				<option value="${medico.id}" <c:if test="${medico.id == schedule.doctor.id}">selected="selected"</c:if> >${medico.name}</option>
    			</c:forEach>
    		</select>
    	</div>
    		
    	<div>
     		<label class="title">Paciente:<span>*</span></label>                    
       		<input type="text" maxlength="200" required="required" name="schedule.subject" value="${schedule.subject}"/>                     
       	</div>
    		
		<div class="horario">
			<label class="title">Início:<span>*</span></label>
			<input type="text" name="startDate" required="required" class="data" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${schedule.startTime.time}" />" />
			<input type="text" name="startTime" class="hora" value="<fmt:formatDate pattern="HH:mm" value="${schedule.startTime.time}" />" />
			<input type="hidden" name="schedule.startTime" value="<fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${schedule.startTime.time}" />"/>
		</div>
 			
		<div>
			<label class="title">Duração:<span>*</span></label>
			<input type="text" name="schedule.duration" required="required" value="${schedule.duration}"/>
			<input type="hidden" name="schedule.endTime" required="required" value="<fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${schedule.endTime.time}" />"/>
		</div>
		
		<div>
			<input name="schedule.color" type="hidden" value="-1" />
			<label class="title">Cor:</label>
			<div class="paleta-cores">
				<p style="background-color:#cc3333;" value="1"/>
				<p style="background-color:#dd4477;" value="2"/>
				<p style="background-color:#994499;" value="3"/>
				<p style="background-color:#3366cc;" value="6"/>
				<p style="background-color:#109618;" value="9"/>
				<p style="background-color:#d6ae00;" value="12"/>
				<p style="background-color:#ee8800;" value="13"/>
			</div>
			<div class="cor-selecionada" title="Cor selecionada"></div>
		</div>
    		
    	<div>                    
       		<label class="title">Observações:</label>                    
			<textarea name="schedule.description">${schedule.description}</textarea>                
     	</div>
    </fieldset>
    		
    <div class="boxactions">
		<input type="submit" class="btnsave" value="Salvar" />
      	<a class="btnclose modal-close">Fechar</a>
	</div>
		
</form>
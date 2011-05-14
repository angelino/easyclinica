<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>

<form action="<%= request.getParameter("formAction") %>" method="post">
	<% if(request.getParameter("put") != null && request.getParameter("put").equals("true")) { %>
		<input type="hidden" name="_method" value="PUT" />
	<% } %>
	<input type="hidden" name="anamnese.id" value="${anamnese.id}" />
	<input type="hidden" name="anamnese.patient.id" value="${patient.id}" />
	
	<helper:errors errors="${errors}" />

	<p class="required"><span>*</span> campos obrigatórios</p>

	<fieldset class="full">
		<div class="date">
			<label class="title">Data:<span>*</span></label>
			<input type="text" name="anamnese.date" maxlength="50" class="datepicker" required="required" value="<fmt:formatDate value="${anamnese.date.time}" pattern="dd/MM/yyyy" />" />
		</div>
	</fieldset>

	<fieldset>
      	<div>
          	<label class="title">Médico:<span>*</span></label>
          	<select name="anamnese.doctor.id" min="1" data-message="Selecione um médico.">
          		<option value="0">Selecione um médico</option>
          		<c:forEach var="doctor" items="${doctors}">
          			<option value="${doctor.id}" <c:if test="${doctor.id == anamnese.doctor.id}">selected</c:if>>${doctor.name}</option>
          		</c:forEach>
          	</select>
        </div>
		
		<div class="remarks">
          	<label class="title">Queixa e duração:<span>*</span></label>
            <textarea name="anamnese.complaintAndDuration" required="required" allowEnter="true">${anamnese.complaintAndDuration}</textarea>
        </div>
        
        <div class="remarks">
          	<label class="title">HSDA:</label>
            <textarea name="anamnese.hsda" allowEnter="true">${anamnese.hsda}</textarea>
        </div>
        
        <div class="remarks">
          	<label class="title">Exame Clínico:</label>
            <textarea name="anamnese.clinicExam" allowEnter="true">${anamnese.clinicExam}</textarea>
        </div>
        
        <div class="remarks">
          	<label class="title">Hipótese Diagnóstica:</label>
            <textarea name="anamnese.hypothesis" allowEnter="true">${anamnese.hypothesis}</textarea>
        </div>
    </fieldset>
	
	<fieldset>
		<div>
          	<label class="title">CID:</label>
          	<input type="text" id="txt_search_cid" value="${anamnese.cid.name}"/>
          	<input type="hidden" id="anamnese.cid.id" value="${anamnese.cid.id}" />
        </div>
        
        <div class="remarks">
          	<label class="title">HPMA:</label>
            <textarea name="anamnese.hpma" allowEnter="true">${anamnese.hpma}</textarea>
        </div>
        
        <div class="remarks">
          	<label class="title">HF:</label>
            <textarea name="anamnese.hf" allowEnter="true">${anamnese.hf}</textarea>
        </div>
        
        <div class="remarks">
          	<label class="title">Exame Suplementar:</label>
            <textarea name="anamnese.supplementaryExam" allowEnter="true">${anamnese.supplementaryExam}</textarea>
        </div>
        
        <div class="remarks">
          	<label class="title">Conduta:</label>
            <textarea name="anamnese.conduct" allowEnter="true">${anamnese.conduct}</textarea>
        </div>
    </fieldset>
	      
    <div class="boxactions">
		<input type="submit" class="btnsave" value="Salvar" />
    </div>
  
</form>

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="modal-schedule">           
    <h2>Novo Compromisso</h2>
    
    <div class="modal-content">    	
		
		<c:url value="/medicos/${schedule.doctor.id}/agenda/add" var="formAction" />
		<jsp:include page="_form.jsp">	
		   <jsp:param name="formAction" value="${formAction}" />			  	
		</jsp:include>
			         
	</div>
</div> 

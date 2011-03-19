<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="modal-schedule">           
    <h2>Editar Compromisso</h2>
    
    <div class="modal-content">    	
		
		<c:choose>
			<c:when test="${loggedUser.doctor}">
				<c:url value="/medicos/minha-agenda/update" var="formAction" />
			</c:when>
			<c:otherwise>
				<c:url value="/agenda/update" var="formAction" />
			</c:otherwise>
		</c:choose>
		
		<jsp:include page="_form.jsp">	
		   	<jsp:param name="formAction" value="${formAction}" />
		   	<jsp:param name="put" value="true"/>	
		   	<jsp:param name="edit" value="true"/>			  	
		</jsp:include>
			         
	</div>
</div> 
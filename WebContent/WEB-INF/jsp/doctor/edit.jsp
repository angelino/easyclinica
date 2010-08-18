<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>

<!-- INÍCIO MAIN -->
<div id="main">

	<div class="block" id="block">
		
		<div class="content">
	   		<h2 class="title">Editar Médico</h2>
			<div class="inner">
	
				<c:url value="/medicos/${doctor.id}" var="formAction" />
			
				<jsp:include page="_form.jsp">	
				   <jsp:param name="formAction" value="${formAction}" />
				   <jsp:param name="put" value="true"/>			  	
				</jsp:include>
				
			</div>
		</div>
	</div>
</div>
<!-- FIM MAIN -->
	
<!-- INÍCIO SIDEBAR -->
<div id="sidebar">

</div>
<!-- FIM SIDEBAR -->
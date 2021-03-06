<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>

<%@page import="br.com.easyclinica.view.Link"%>
<%@page import="br.com.easyclinica.domain.entities.Receipt"%>
<%@page import="java.util.LinkedList"%>

<html>
	<head>
		<title>.: EasyClinica - Editar Recibo :.</title>
	</head>
	<body>

		<div class="box" id="recibos">
			<div class="boxcontent">
		   		<h2>Editar Recibo</h2>				
		
				<c:url value="/pacientes/${receipt.patient.id}/recibos/${receipt.id}" var="formAction" />			
				<jsp:include page="_form.jsp">	
				   <jsp:param name="formAction" value="${formAction}" />
				   <jsp:param name="put" value="true"/>			  	
				</jsp:include>
			</div>			
		</div>
			
		<div class="boxright">
			<% 
				java.util.List<Link> links = new LinkedList<Link>();
				links.add(new Link("/pacientes/" + ((Receipt)request.getAttribute("receipt")).getPatient().getId() + "/anamneses/novo","Criar nova anamnese"));
				links.add(new Link("/pacientes/" + ((Receipt)request.getAttribute("receipt")).getPatient().getId() + "/anamneses","Voltar para anamneses"));
				pageContext.setAttribute("links",links);
			%>
			<helper:navigation links="${links}"></helper:navigation>
			<helper:patientDetails patient="${receipt.patient}" />
		</div>
	
	</body>
</html>
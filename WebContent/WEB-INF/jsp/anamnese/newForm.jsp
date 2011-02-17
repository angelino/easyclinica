<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>

<%@page import="br.com.easyclinica.view.Link"%>
<%@page import="br.com.easyclinica.domain.entities.Patient"%>
<%@page import="java.util.LinkedList"%>

<html>
	<head>
		<title>.: EasyClinica - Nova Anamnese :.</title>
	</head>
	<body>

		<div class="box" id="anamneses">
			<div class="boxcontent">				
				<h2>Nova Anamnese</h2>
				
			   	<c:url value="/pacientes/${patient.id}/anamneses" var="formAction" />
				<jsp:include page="_form.jsp">	
				   <jsp:param name="formAction" value="${formAction}" />			  	
				</jsp:include>
			</div>
			
		</div>
		
		<div class="boxright">
			<% 
				java.util.List<Link> links = new LinkedList<Link>();  
				links.add(new Link("/pacientes/" + ((Patient)request.getAttribute("patient")).getId() + "/anamneses","Voltar para anamneses"));
				pageContext.setAttribute("links",links);
			%>
			<helper:navigation links="${links}"></helper:navigation>
		</div>
	</body>
</html>
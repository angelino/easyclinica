<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>
<%@page import="br.com.easyclinica.view.Link"%>
<%@page import="br.com.easyclinica.domain.entities.HealthCarePlan"%>
<%@page import="java.util.LinkedList"%>
<html>
	<head>
		<title>.: EasyClinica - Editar Convênio :.</title>
	</head>
	<body>

		<div id="main">

			<div class="block" id="block">
				
				<div class="content">
			   		<h2 class="title">Editar Convênio</h2>
					<div class="inner">
			
						<c:url value="/convenios/${healthCarePlan.id}" var="formAction" />
						
						<jsp:include page="_form.jsp">	
						   <jsp:param name="formAction" value="${formAction}" />
						   <jsp:param name="put" value="true"/>	
						   <jsp:param name="edit" value="true"/>			  	
						</jsp:include>
					</div>
				</div>
			</div>
			
		</div>
			
		<div id="sidebar">
			<% 
				java.util.List<Link> links = new LinkedList<Link>();  
				links.add(new Link("/convenios","Voltar para listagem"));
				links.add(new Link("/convenios/"+ ((HealthCarePlan)request.getAttribute("healthCarePlan")).getId(),"Exibir o convênio"));
				links.add(new Link("/convenios/novo","Adicionar novo convênio"));
				pageContext.setAttribute("links",links);
			%>
			<helper:navigation links="${links}"></helper:navigation>
			
			<helper:notice title="Atenção!" notice="Não é possível alterar a tabela de serviços vinculada ao convênio. Caso isso seja necessário, crie um outro convênio e inative esse."></helper:notice>
		</div>
	</body>
</html>
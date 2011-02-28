<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>
<%@page import="br.com.easyclinica.view.Link"%>
<%@page import="java.util.LinkedList"%>
<html>
	<head>
		<title>.: EasyClinica - Novo Convênio :.</title>
	</head>
	<body>

		<div class="box" id="convenios">
			<div class="boxcontent">
			   	<h2>Novo Convênio</h2>
								
				<c:url value="/convenios" var="formAction" />
				<jsp:include page="_form.jsp">	
				   <jsp:param name="formAction" value="${formAction}" />
				</jsp:include>
			</div>
		</div>
			
		<div class="boxright">
		
			<% 
				java.util.List<Link> links = new LinkedList<Link>();  
				links.add(new Link("/convenios","Voltar para listagem"));
				pageContext.setAttribute("links",links);
			%>
			<helper:navigation links="${links}"></helper:navigation>		
			<helper:notice notice="Você deve escolher um convênio de base para a cópia dos preços de materiais, remédios, especialidades e procedimentos. Esses valores podem ser alterados posteriormente através da opção Financeiro, na tela de dados do convênio." title="Gerando o Financeiro"></helper:notice>
		</div>
	</body>
</html>
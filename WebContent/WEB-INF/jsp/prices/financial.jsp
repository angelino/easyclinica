<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>
<%@page import="br.com.easyclinica.view.Link"%>
<%@page import="java.util.LinkedList"%>
<html>
	<head>
		<title>.: EasyClinica :.</title>
	</head>
	<body>
		<div class="box">		
			<helper:healthCarePlanMenu plan="${healthCarePlan}" selected="Financeiro" />
			<div class="boxcontent">
				<h2>Financeiro</h2>


					<p class="messengernotice">
						Clique no link abaixo para gerar um arquivo Excel com os preços. Você poderá utilizar esse
						arquivo para importar novos preços.
						<br/>
						Mas lembre-se de não modificar a estrutura do arquivo; apenas altere os valores.
					</p>

					<input type="button" value="Baixar os preços" onclick="window.location.href='<c:url value="/convenios/${healthCarePlan.id}/precos" />';"/>

					<input type="button" value="Importar os preços" onclick="window.location.href='<c:url value="/convenios/${healthCarePlan.id}/precos/importar" />';"/>
													
			</div>
		</div>
		
		<div class="boxright">
			<% 
				java.util.List<Link> links = new LinkedList<Link>();  
				links.add(new Link("/convenios","Voltar para listagem"));
				links.add(new Link("/convenios/novo","Adicionar novo convênio"));
				pageContext.setAttribute("links",links);
			%>
			<helper:navigation links="${links}"></helper:navigation>
			<helper:planDetails plan="${healthCarePlan}" />
		</div>
		
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>
<%@page import="br.com.easyclinica.view.Link"%>
<%@page import="br.com.easyclinica.domain.entities.HealthCarePlan"%>
<%@page import="java.util.LinkedList"%>
<html>
	<head>
		<title>.: EasyClinica :.</title>
	</head>
	<body>
		<div class="box" main-page="convenios">		
			<helper:healthCarePlanMenu plan="${healthCarePlan}" selected="Financeiro" />
			<div class="boxcontent">
				<h2>Financeiro</h2>

				<p class="messengernotice">
					Clique no link abaixo para gerar um arquivo Excel com os preços. 
					Você poderá utilizar esse arquivo para importar novos preços. 
					<br />
					Lembre-se de não modificar a estrutura do arquivo; apenas altere os valores.
					<br/>
					Para entender mais sobre o sistema de precificação, você pode ver 
					um vídeo <a href="http://www.easyclinica.com.br/blog/entendendo-a-precificacao-da-consulta" target="_blank">aqui</a>. 
				</p>
				<div class="financial-buttons">				
						<form action='<c:url value="/convenios/${healthCarePlan.id}/precos" />' id="frmDownloadPrices">
							<input type="radio" value="material" name="filter" />Materiais
							<input type="radio" value="medicine" name="filter" />Medicamentos
							<input type="radio" value="procedure" name="filter" />Procedimentos
							<input type="radio" value="specialty" name="filter" />Especialidades
							
							<a href="javascript:void(0);" id="btnDownloadPrices" class="btn submit">Baixar os preços</a>
						</form>
				</div>
				<div class="financial-buttons">
						<a href='<c:url value="/convenios/${healthCarePlan.id}/precos/importar" />' class="btn">Importar preços</a>
				</div>	
				
				<br />																	
			</div>
		</div>
		
		<div class="boxright">
			<% 
				java.util.List<Link> links = new LinkedList<Link>();  
				links.add(new Link("/convenios","Voltar para listagem"));
				links.add(new Link("/convenios/novo","Adicionar novo convênio"));
				links.add(new Link("/convenios/" + ((HealthCarePlan)request.getAttribute("healthCarePlan")).getId() + "/editar","Alterar este convênio"));
				pageContext.setAttribute("links",links);
			%>
			<helper:navigation links="${links}"></helper:navigation>
			<helper:planDetails plan="${healthCarePlan}" />
		</div>
		
	</body>
</html>
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
				<h2>Importar Preços</h2>

					<p class="messengernotice">
						Faça o upload do arquivo com os nossos preços para esse convênio. Lembre-se que o arquivo deverá ter
						o <b>mesmo formato</b> do arquivo exportado; você deve alterar apenas os valores. 
						<br/>
						Qualquer alteração no formato do arquivo poderá causar erros na importação.
					</p>

					<form action="<c:url value="/convenios/${id}/precos/confirmar" />" method="post" enctype="multipart/form-data">
						<input type="hidden" name="id" value="${id}" />
						Selecione o arquivo: <input type="file" name="file" />
						
						<input type="submit" value="Ler o arquivo"/>
					</form>				
								
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
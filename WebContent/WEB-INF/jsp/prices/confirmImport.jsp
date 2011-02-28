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
		<div class="box" id="prices">
			<helper:healthCarePlanMenu plan="${healthCarePlan}" selected="Financeiro" />
					
			<div class="boxcontent">
				<h2>Importar Preços</h2>
				
				<p class="messengernotice">Por favor, confirme os valores que serão importados. Essa operação não poderá ser revertida!</p>
				
			<form action="<c:url value="/convenios/${id}/precos" />" method="post">
				<input type="submit" value="Finalizar importação "/>
				
				<div id="tabs">
					<ul>
						<li><a href="#procedimentos">Procedimentos</a></li>
						<li><a href="#materiais">Materiais</a></li>
						<li><a href="#remedios">Remédios</a></li>
						<li><a href="#especialidades">Especialidades</a></li>
					</ul>
					<div id="procedimentos">
					
						<ul>
							<c:forEach var="procedure" items="${import.importedProcedures}" varStatus="st">
								<li>${procedure.name} - ${procedure.value}
								<input type="hidden" name="procedures[${st.index}].id" value="${procedure.id}" />
								<input type="hidden" name="procedures[${st.index}].value" value="${procedure.value}" />
							</c:forEach>
						</ul>
						
					</div>
					<div id="materiais">
						<ul>
						<c:forEach var="material" items="${import.importedMaterials}" varStatus="st">
							<li>${material.name} - ${material.value}</li>
							<input type="hidden" name="materials[${st.index}].id" value="${material.id}" />
							<input type="hidden" name="materials[${st.index}].value" value="${material.value}" />
						</c:forEach>
						</ul>
					</div>
					<div id="remedios">
						<ul>
						<c:forEach var="medicine" items="${import.importedMedicines}" varStatus="st">
							<li>${medicine.name} - ${medicine.value}</li>
							<input type="hidden" name="medicines[${st.index}].id" value="${medicine.id}" />
							<input type="hidden" name="medicines[${st.index}].value" value="${medicine.value}" />
						</c:forEach>
						</ul>
					</div>
					<div id="especialidades">
						<ul>
						<c:forEach var="specialty" items="${import.importedSpecialties}" varStatus="st">
							<li>${specialty.name} - ${specialty.value}</li>
							<input type="hidden" name="specialties[${st.index}].id" value="${specialty.id}" />
							<input type="hidden" name="specialties[${st.index}].value" value="${specialty.value}" />
						</c:forEach>
						</ul>
					</div>
				</div>
						
						<input type="submit" value="Finalizar importação "/>
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
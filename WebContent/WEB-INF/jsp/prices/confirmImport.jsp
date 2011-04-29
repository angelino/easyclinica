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
		<div class="box" id="prices" main-page="convenios">
			<helper:healthCarePlanMenu plan="${healthCarePlan}" selected="Financeiro" />
					
			<div class="boxcontent">
				<h2>Importar Preços</h2>
				
				<p class="messengernotice">Por favor, confirme os valores que serão importados. Essa operação não poderá ser revertida!</p>
				
				<form action="<c:url value="/convenios/${id}/precos" />" method="post">
						
					<ul class="boxmenu" id="easyabas">
						<li><a href="#procedimentos">Procedimentos</a></li>
						<li><a href="#materiais">Materiais</a></li>
						<li><a href="#remedios">Remédios</a></li>
						<li><a href="#especialidades">Especialidades</a></li>
					</ul>
					<div id="procedimentos">						
						<c:choose>
							<c:when test="${empty import.importedProcedures}">
								<p class="messengernotice">Não há procedimentos a serem importados!</p>
							</c:when>
							<c:otherwise>
								<table border="0" class="easy">
									<tr class="tableheader">
										<th>Procedimento:</th>
										<th>Valor:</th>
										<th>CHs:</th>
										<th>Taxa de Sala:</th>
									</tr>
									<c:forEach var="procedure" items="${import.importedProcedures}" varStatus="st">
										<tr>
											<td>
												${procedure.name}
												<input type="hidden" name="procedures[${st.index}].id" value="${procedure.id}" />
												<input type="hidden" name="procedures[${st.index}].ch" value="${procedure.ch}" />
												<input type="hidden" name="procedures[${st.index}].roomTax" value="${procedure.roomTax}" />
												<input type="hidden" name="procedures[${st.index}].value" value="${procedure.value}" />
											</td>
											<td class="currency">${procedure.value}</td>
											<td>${procedure.ch}</td>
											<td>${procedure.roomTax}</td>
										</tr>
									</c:forEach>
								</table>
							</c:otherwise>
						</c:choose>							
					</div>
					
					<div id="materiais">						
						<c:choose>
							<c:when test="${empty import.importedMaterials}">
								<p class="messengernotice">Não há materiais a serem importados!</p>
							</c:when>
							<c:otherwise>
								<table border="0" class="easy">
									<tr class="tableheader">
										<th>Material:</th>
										<th>Valor:</th>
									</tr>
									<c:forEach var="material" items="${import.importedMaterials}" varStatus="st">
										<tr>
											<td>
												${material.name}
												<input type="hidden" name="materials[${st.index}].id" value="${material.id}" />
												<input type="hidden" name="materials[${st.index}].value" value="${material.value}" />
											</td>
											<td>
												${material.value}
											</td>
										</tr>
									</c:forEach>
								</table>
							</c:otherwise>
						</c:choose>
					</div>
					
					<div id="remedios">						
						<c:choose>
							<c:when test="${empty import.importedMedicines}">
								<p class="messengernotice">Não há medicamentos a serem importados!</p>
							</c:when>
							<c:otherwise>
								<table border="0" class="easy">
									<tr class="tableheader">
										<th>Medicamento:</th>
										<th>Valor:</th>
									</tr>
									<c:forEach var="medicine" items="${import.importedMedicines}" varStatus="st">
										<tr>
											<td>
												${medicine.name}
												<input type="hidden" name="medicines[${st.index}].id" value="${medicine.id}" />
												<input type="hidden" name="medicines[${st.index}].value" value="${medicine.value}" />
											</td>
											<td>
												${medicine.value}
											</td>
										</tr>
									</c:forEach>
								</table>
							</c:otherwise>
						</c:choose>
					</div>
					<div id="especialidades">
					
						<c:choose>
							<c:when test="${empty import.importedSpecialties}">
								<p class="messengernotice">Não há especialidades a serem importadas!</p>
							</c:when>
							<c:otherwise>
							
								<table border="0" class="easy">
									<tr class="tableheader">
										<th>Medicamento:</th>
										<th>Valor:</th>
									</tr>
									<c:forEach var="specialty" items="${import.importedSpecialties}" varStatus="st">
										<tr>
											<td>
												${specialty.name}
												<input type="hidden" name="specialties[${st.index}].id" value="${specialty.id}" />
												<input type="hidden" name="specialties[${st.index}].value" value="${specialty.value}" />
											</td>
											<td>
												${specialty.value}
											</td>
										</tr>
									</c:forEach>
								</table>
							</c:otherwise>
						</c:choose>
					</div>
									
					<div class="boxactions">
						<input type="submit" class="btnsave" value="Finalizar importação "/>
					</div>
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
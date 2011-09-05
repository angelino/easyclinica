<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>
<%@page import="br.com.easyclinica.domain.entities.HealthCarePlan"%>
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
						
						<c:forEach var="procedure" items="${import.importedProcedures}" varStatus="st">
							<input type="hidden" name="procedures[${st.index}].id" value="${procedure.id}" />
							<input type="hidden" name="procedures[${st.index}].ch" value="${procedure.ch}" />
							<input type="hidden" name="procedures[${st.index}].roomTax" value="${procedure.roomTax}" />
							<input type="hidden" name="procedures[${st.index}].value" value="${procedure.value}" />
						</c:forEach>
									
					<c:forEach var="material" items="${import.importedMaterials}" varStatus="st">
						<input type="hidden" name="materials[${st.index}].id" value="${material.id}" />
						<input type="hidden" name="materials[${st.index}].value" value="${material.value}" />
					</c:forEach>
					
					<c:forEach var="medicine" items="${import.importedMedicines}" varStatus="st">
							<input type="hidden" name="medicines[${st.index}].id" value="${medicine.id}" />
							<input type="hidden" name="medicines[${st.index}].value" value="${medicine.value}" />
					</c:forEach>
										
					<c:forEach var="specialty" items="${import.importedSpecialties}" varStatus="st">
						<input type="hidden" name="specialties[${st.index}].id" value="${specialty.id}" />
						<input type="hidden" name="specialties[${st.index}].value" value="${specialty.value}" />
					</c:forEach>
	
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
							<c:when test="${fn:length(import.importedProcedures) > 50}">
								<p class="messengernotice">Existem muitos ítens a serem importados, o que nos impede
								de exibir todos! Mas fique tranquilo, confirme a importação e todos eles serão
								importados.
								<br/>
								Na próxima vez, apague as linhas que você não modificou
								do arquivo Excel, e importe somente as que você alterou! Isso facilitará a aumentará
								a velocidade da importação.</p>
							</c:when>
							<c:otherwise>
								<table border="0" class="easy">
									<tr class="tableheader">
										<th>Procedimento:</th>
										<th>Valor</th>
										<th>Taxa de Sala:</th>
									</tr>
									<c:forEach var="procedure" items="${import.importedProcedures}" varStatus="st">
										<tr>
											<td>
												${procedure.name}
											</td>
											<c:choose>
												<c:when test="${procedure.value > 0}">
													<td class="currency">${procedure.value}</td>
												</c:when>
												<c:otherwise>
													<td>${procedure.ch} CHs</td>
												</c:otherwise>
											</c:choose>
											<td class="currency">${procedure.roomTax}</td>
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
							<c:when test="${fn:length(import.importedMaterials) > 50}">
								<p class="messengernotice">Existem muitos ítens a serem importados, o que nos impede
								de exibir todos! Mas fique tranquilo, confirme a importação e todos eles serão
								importados.
								<br/>
								Na próxima vez, apague as linhas que você não modificou
								do arquivo Excel, e importe somente as que você alterou! Isso facilitará a aumentará
								a velocidade da importação.</p>
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
											</td>
											<td class="currency">
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
							<c:when test="${fn:length(import.importedMedicines) > 50}">
								<p class="messengernotice">Existem muitos ítens a serem importados, o que nos impede
								de exibir todos! Mas fique tranquilo, confirme a importação e todos eles serão
								importados.
								<br/>
								Na próxima vez, apague as linhas que você não modificou
								do arquivo Excel, e importe somente as que você alterou! Isso facilitará a aumentará
								a velocidade da importação.</p>
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
											</td>
											<td class="currency">
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
							<c:when test="${fn:length(import.importedSpecialties) > 50}">
								<p class="messengernotice">Existem muitos ítens a serem importados, o que nos impede
								de exibir todos! Mas fique tranquilo, confirme a importação e todos eles serão
								importados.
								<br/>
								Na próxima vez, apague as linhas que você não modificou
								do arquivo Excel, e importe somente as que você alterou! Isso facilitará a aumentará
								a velocidade da importação.</p>
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
											</td>
											<td class="currency">
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
				links.add(new Link("/convenios/" + ((HealthCarePlan)request.getAttribute("healthCarePlan")).getId() + "/editar","Alterar este convênio"));
				pageContext.setAttribute("links",links);
			%>
			<helper:navigation links="${links}"></helper:navigation>
			
			<helper:planDetails plan="${healthCarePlan}" />
		</div>
		
	</body>
</html>
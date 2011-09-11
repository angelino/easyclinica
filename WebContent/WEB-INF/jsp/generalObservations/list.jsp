<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>

<%@page import="br.com.easyclinica.view.Link"%>
<%@page import="java.util.LinkedList"%>
<%@page import="br.com.easyclinica.domain.entities.Patient"%>
<%@page import="br.com.easyclinica.domain.entities.Position"%>
<%@page import="br.com.easyclinica.infra.multitenancy.LoggedUser"%>

<html>
	<head>
		<title>.: EasyClinica - Observações Gerais :.</title>
	</head>
	<body>

		<div class="box" id="observacoes" main-page="pacientes">
			<helper:patientMenu patient="${patient}" selected="Observacoes" />
			
			<div class="boxcontent">			
				<h2>Observações Gerais</h2>
				
				<helper:message successKey="${successKey}" errorKey="${errorKey}" />
				
				<c:choose>
		    		<c:when test="${fn:length(patient.generalObservations) == 0}">
		    			<p class="messengernotice">
		    				Não há observações registradas para esse paciente! <a href='<c:url value="/pacientes/${patient.id}/observacoes/novo" />'>Clique aqui</a> para cadastrar uma observação!
		    			</p>
		    		</c:when>
		    		<c:otherwise>				
						<table border="0" class="easy">
							<tr class="tableheader">
			                    <th>Data</th> 
			                    <th>Observação</th> 
			                    <th width="42px">&nbsp;</th>
			                </tr> 
                  
							<c:forEach items="${patient.generalObservations}" var="observation" varStatus="st">
								<tr class="${st.count%2==0?'odd':'even'}"> 
									<td>
										<fmt:formatDate value="${observation.date.time}" pattern="dd/MM/yyyy" />
									</td> 
									<td>${observation.text}</td>
									
									<td class="buttons">
										<span class="btntools">Ferramentas
	               							<span class="boxtools">
	               								<span class="buttons">
                									<span class="arrow">&nbsp;</span>
										
													<a class="btnpeopleshow" title="Exibir" href="<c:url value="/pacientes/${patient.id}/observacoes/${observation.id}"/>">&nbsp;</a>
													<a class="btnpeopleedit" title="Editar" href="<c:url value="/pacientes/${patient.id}/observacoes/${observation.id}/editar"/>">&nbsp;</a>
			                                                                            	
			                                    	<form action="<c:url value="/pacientes/${patient.id}/observacoes/${observation.id}"/>" method="post">
												        <input type="hidden" name="_method" value="delete"/>
												        <input type="submit" class="btndelete" title="Excluir Observação" value="" onclick="return confirm('Deseja realmente deletar essa observação?');"/>
												    </form>
												</span>
											</span>
										</span>
											
                                    </td>
								</tr>
							</c:forEach>
				  		</table>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
			
		<div class="boxright">	
			<% 
				java.util.List<Link> links = new LinkedList<Link>();  
				
				LoggedUser loggedUser = (LoggedUser)request.getSession().getAttribute("loggedUser");
				if(loggedUser.getEmployee().getPosition() != Position.DOCTOR){
					links.add(new Link("/pacientes/" + ((Patient)request.getAttribute("patient")).getId() + "/editar","Editar paciente"));	
				}
				
				links.add(new Link("/pacientes/" + ((Patient)request.getAttribute("patient")).getId() + "/observacoes/novo","Nova observação"));
				pageContext.setAttribute("links",links);
			%>
			<helper:navigation links="${links}"></helper:navigation>
			<helper:patientDetails patient="${patient}" />
		</div>
	</body>
</html>

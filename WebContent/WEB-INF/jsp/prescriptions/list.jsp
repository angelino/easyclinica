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
		<title>.: EasyClinica - Prescrições :.</title>
	</head>
	<body>

		<div class="box" id="prescricoes" main-page="prescricoes">
			<helper:patientMenu patient="${patient}" selected="prescricoes" />
			
			<div class="boxcontent">			
				<h2>Prescrições</h2>
				
				<helper:message successKey="${successKey}" errorKey="${errorKey}" />
				
				<c:choose>
		    		<c:when test="${fn:length(patient.prescriptions) == 0}">
		    			<p class="messengernotice">
		    				Não há prescrições registradas para esse paciente! <a href='<c:url value="/pacientes/${patient.id}/prescricoes/novo" />'>Clique aqui</a> para cadastrar uma prescrição!
		    			</p>
		    		</c:when>
		    		<c:otherwise>				
						<table border="0" class="easy">
							<tr class="tableheader">
			                    <th>Data</th> 
			                    <th>Médico</th> 
			                    <th>Observação</th> 
			                    <th width="205px">&nbsp;</th>
			                </tr> 
                  
							<c:forEach items="${patient.prescriptions}" var="prescription" varStatus="st">
								<tr class="${st.count%2==0?'odd':'even'}"> 
									<td>
										<fmt:formatDate value="${prescription.date.time}" pattern="dd/MM/yyyy" />
									</td>
									<td>${prescription.doctor.name}</td> 
									<td>${prescription.text}</td>
									
									<td class="buttons">
										<a class="btnpdf" title="Gerar prescrição em formato .pdf" href="<c:url value="/pacientes/${patient.id}/prescricoes/${prescription.id}/imprimir"/>">&nbsp;</a>
										<a class="btnpeopleshow" title="Exibir" href="<c:url value="/pacientes/${patient.id}/prescricoes/${prescription.id}"/>">&nbsp;</a>
										<a class="btnpeopleedit" title="Editar" href="<c:url value="/pacientes/${patient.id}/prescricoes/${prescription.id}/editar"/>">&nbsp;</a>
                                                                            	
                                    	<form action="<c:url value="/pacientes/${patient.id}/prescricoes/${prescription.id}"/>" method="post">
									        <input type="hidden" name="_method" value="delete"/>
									        <input type="submit" class="btndelete" title="Excluir Prescrição" value="" onclick="return confirm('Deseja realmente deletar essa prescrição?');"/>
									    </form>
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
				if(loggedUser.getEmployee().getPosition() == Position.DOCTOR){
					links.add(new Link("/pacientes/" + ((Patient)request.getAttribute("patient")).getId() + "/prescricoes/novo","Nova prescrição"));	
				}
				
				pageContext.setAttribute("links",links);
			%>
			<helper:navigation links="${links}"></helper:navigation>
			<helper:patientDetails patient="${patient}" />
		</div>
	</body>
</html>

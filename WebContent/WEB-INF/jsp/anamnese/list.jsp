<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>

<%@page import="br.com.easyclinica.view.Link"%>
<%@page import="java.util.LinkedList"%>
<%@page import="br.com.easyclinica.domain.entities.Patient"%>

<html>
	<head>
		<title>.: EasyClinica - Anamneses :.</title>
	</head>
	<body>

		<div class="box" id="consultas">
			<helper:patientMenu patient="${patient}" selected="Anamnese" />
			
			<div class="boxcontent">			
				<h2>Anamneses - ${patient.name}</h2>
				
				<helper:message successKey="${successKey}" errorKey="${errorKey}" />
				
				<c:choose>
		    		<c:when test="${fn:length(patient.anamneses) == 0}">
		    			<p class="messengernotice">
		    				Não há anamneses registradas para esse paciente! <a href='<c:url value="/pacientes/${patient.id}/anamneses/novo" />'>Clique aqui</a> para cadastrar uma anamnese!
		    			</p>
		    		</c:when>
		    		<c:otherwise>				
						<table border="0" class="easy">
							<tr class="tableheader">
			                    <th>Data</th> 
			                    <th>Médico</th>
			                    <th>Anamnese</th> 
			                    <th width="145px">&nbsp;</th>
			                </tr> 
                  
							<c:forEach items="${patient.anamneses}" var="anamnese" varStatus="st">
								<tr class="${st.count%2==0?'odd':'even'}"> 
									<td>
										<fmt:formatDate value="${anamnese.date.time}" pattern="dd/MM/yyyy" />
									</td> 
									<td>${anamnese.doctor.name}</td>
									<td>${anamnese.text}</td>
									
									<td class="buttons">
										<a class="btnpeopleshow" title="Exibir" href="<c:url value="/pacientes/${patient.id}/anamneses/${anamnese.id}/editar"/>">&nbsp;</a>
                                    	
                                    	<form action="<c:url value="/pacientes/${patient.id}/anamneses/${anamnese.id}"/>" method="post">
									        <input type="hidden" name="_method" value="delete"/>
									        <input type="submit" class="btndelete" title="Excluir Anamnese" value="" onclick="return confirm('Deseja realmente deletar essa anamnese?');"/>
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
				links.add(new Link("/pacientes/" + ((Patient)request.getAttribute("patient")).getId() + "/editar","Perfil do paciente"));
				links.add(new Link("/pacientes/" + ((Patient)request.getAttribute("patient")).getId() + "/anamneses/novo","Nova anamnese"));
				pageContext.setAttribute("links",links);
			%>
			<helper:navigation links="${links}"></helper:navigation>
		</div>
	</body>
</html>
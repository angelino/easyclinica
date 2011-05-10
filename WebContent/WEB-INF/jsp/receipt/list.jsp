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
		<title>.: EasyClinica - Recibos :.</title>
	</head>
	<body>

		<div class="box" id="recibos">
			<helper:patientMenu patient="${patient}" selected="Recibos" />
			
			<div class="boxcontent">			
				<h2>Recibos</h2>
				
				<helper:message successKey="${successKey}" errorKey="${errorKey}" />
				
				<c:choose>
		    		<c:when test="${fn:length(patient.receipts) == 0}">
		    			<p class="messengernotice">
		    				Não há recibos registrados para esse paciente! <a href='<c:url value="/pacientes/${patient.id}/recibos/novo" />'>Clique aqui</a> para adicionar um novo recibo!
		    			</p>
		    		</c:when>
		    		<c:otherwise>				
						<table border="0" class="easy">
							<tr class="tableheader">
			                    <th>Data</th> 
			                    <th>Em nome de</th>
			                    <th>CPF</th>
			                    <th>Valor</th> 
			                    <th width="202px">&nbsp;</th>
			                </tr> 
                  
							<c:forEach items="${patient.receipts}" var="receipt" varStatus="st">
								<tr class="${st.count%2==0?'odd':'even'}"> 
									<td>
										<fmt:formatDate value="${receipt.date.time}" pattern="dd/MM/yyyy" />
									</td> 
									<td>${receipt.inNameOf} (<fmt:message key="${receipt.kinship.localeKey}"></fmt:message>)</td>
									<td>${receipt.cpf}</td>
									<td class="currency">${receipt.amount}</td>
									
									<td class="buttons">
										<a class="btnpdf" title="Gerar recibo em formato .pdf" href="<c:url value="/pacientes/${receipt.patient.id}/recibos/${receipt.id}"/>">&nbsp;</a>
										<a class="btnpeopleedit" title="Editar" href="<c:url value="/pacientes/${receipt.patient.id}/recibos/${receipt.id}/editar"/>">&nbsp;</a>
                                    	<a class="btnpeopleshow exibir" title="Exibir" recibo_id="${receipt.id}">&nbsp;</a>
                                        
										<form action="<c:url value="/pacientes/${receipt.patient.id}/recibos/${receipt.id}"/>" method="post">
									        <input type="hidden" name="_method" value="delete"/>
									        <input type="submit" class="btndelete" title="Excluir Recibo" value="" onclick="return confirm('Deseja realmente deletar esse recibo?');"/>
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
				links.add(new Link("/pacientes/" + ((Patient)request.getAttribute("patient")).getId() + "/editar","Editar paciente"));
				links.add(new Link("/pacientes/" + ((Patient)request.getAttribute("patient")).getId() + "/recibos/novo","Novo Recibo"));
				pageContext.setAttribute("links",links);
			%>
			<helper:navigation links="${links}"></helper:navigation>
			<helper:patientDetails patient="${receipt.patient}" />
		</div>
	</body>
</html>

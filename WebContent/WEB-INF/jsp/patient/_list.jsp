<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>

   <c:choose>
   		<c:when test="${fn:length(patients.result) == 0}">
   			<p class="messengernotice">
   				Nenhum paciente foi encontrado! <a href='<c:url value="/pacientes/novo" />'>Clique aqui</a> para adicionar um paciente.
   			</p>
   		</c:when>
   		<c:otherwise>
    		<table border="0" class="easy">
				<tr class="tableheader">
					<th width="65px">Status:</th>
					<th>Nome</th>
					<th>Convênio</th>
					<th>Número no Convênio</th>
					<th>Telefone</th>
					<th>Celular</th>
					<th width="191px">&nbsp;</th>
				</tr>
				
				<c:forEach var="patient" items="${patients.result}" varStatus="status">
					<tr class="${status.count % 2 == 0 ? 'odd' : 'even' }">
						<c:choose>
							<c:when test="${patient.active}">
								<td class="statusenable">Ativo</td>
                                 		<td>${patient.name}</td>
							</c:when>
							<c:otherwise>
								<td class="statusdisable">Inativo</td>
                                 		<td class="namedisable">${patient.name}</td>
							</c:otherwise>
						</c:choose>
						
						<td>${patient.healthCarePlan.name}</td>
						<td>${patient.healthCarePlanCode}</td>
						<td>${patient.telephone}</td>
						<td>${patient.cellphone}</td>
						
						<td class="buttons">
							<a class="btnpeopleedit" title="Editar" href="<c:url value="/pacientes/${patient.id}/editar" />">&nbsp;</a>
                                     <a class="btnpeopleshow exibir" title="Exibir" patient_id="${patient.id}">&nbsp;</a>
                                     <a class="btnappointments" title="Consultas" href="<c:url value="/pacientes/${patient.id}/consultas" />">&nbsp;</a>
                                     
                                     <c:choose>
								<c:when test="${patient.active}">
									<a class="btnpeopledisable last" title="Desativar" href="<c:url value="/pacientes/${patient.id}/deactivate" />">&nbsp;</a>
								</c:when>
								<c:otherwise>
									<a class="btnpeopleenable last" title="Ativar" href="<c:url value="/pacientes/${patient.id}/activate" />">&nbsp;</a>
								</c:otherwise>
							</c:choose>
                         </td>
					</tr>
				</c:forEach>
			</table>
			
			<helper:pagging total="${patients.totalPages}" current="${patients.currentPage}" />
					
   		</c:otherwise>
   	</c:choose>
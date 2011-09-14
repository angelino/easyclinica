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
					<th width="20px">Id:</th>
					<th>Nome</th>
					<th>Sexo</th>
					<th>Convênio</th>
					<th>Número no Convênio</th>
					<th>Telefone</th>
					<th>Celular</th>
					<th width="42px">&nbsp;</th>
				</tr>
				
				<c:forEach var="patient" items="${patients.result}" varStatus="status">
					<tr class="${status.count % 2 == 0 ? 'odd' : 'even' }">
						<td>${patient.id}</td>
                        <td>${patient.name}</td>
                        <td>${patient.gender.formattedName}</td>
						<td>${patient.healthCarePlan.name}</td>
						<td>${patient.healthCarePlanCode}</td>
						<td>${patient.telephone}</td>
						<td>${patient.cellphone}</td>
						
						<td class="buttons">
							<span class="btntools">Ferramentas
      							<span class="boxtools">
      								<span class="buttons">
      									<span class="arrow">&nbsp;</span>
      									
										<a class="btnpeopleshow" href="<c:url value="/pacientes/${patient.id}" />">&nbsp;</a>
										<a class="btnpeopleedit" title="Editar" href="<c:url value="/pacientes/${patient.id}/editar" />">&nbsp;</a>
			                            <a class="btnappointments" title="Consultas" href="<c:url value="/pacientes/${patient.id}/consultas" />">&nbsp;</a>
                        			</span>
                        		</span>
                        	</span>
                        </td>
					</tr>
				</c:forEach>
			</table>
			
			<helper:pagging total="${patients.totalPages}" current="${patients.currentPage}" />
					
   		</c:otherwise>
   	</c:choose>
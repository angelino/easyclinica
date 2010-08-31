<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<table class="table">
	<tr>
		<th class="first"></th>
		<th>Horário</th>
		<th>Paciente</th>
		<th>Telefone</th>
		<th class="last">&nbsp;</th>
	</tr>
	
	<c:forEach items="${appointments}" var="appointment">
	
		<c:choose>
			<c:when test="${appointment.available}">
				<c:set var="appointment_class" value="disponivel" scope="page" />
			</c:when>
			<c:otherwise>
				<c:set var="appointment_class" value="indisponivel" scope="page" />
			</c:otherwise>
		</c:choose>
	
		<tr class="${appointment_class}">
			<td></td>
			<td> 
				${appointment.start.hour} - ${appointment.end.hour}
			</td>
			<td>
				<c:if test="${appointment.id != 0}">
					${appointment.patient.name}
				</c:if>
			</td>
			<td>${appointment.telephone}</td>
			<td>
				<c:if test="${appointment.id != 0}">
					<a href="javascript:open_modal()" title="Editar">
						<c:url value="/images/icons/schedule/alterar.png" var="img_alterar"/>
						<img src="${img_alterar}" alt="Editar" width="20" height="20" />
					</a>
					&nbsp;
					<a href="#" title="Excluir">
						<c:url value="/images/icons/schedule/deletar.png" var="img_deletar"/>
						<img src="${img_deletar}" alt="Excluir" width="20" height="20" />
					</a>
				</c:if>
			</td>
		</tr>
	</c:forEach>
	
</table>

<ul class="legenda">
	<li><span class="vermelho"></span>Horário indisponível</li>
	<li><span class="azul"></span>Horário atual</li>
	<li><span class="verde"></span>Horário disponível</li>
</ul>
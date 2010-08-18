<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>
<div class="block" id="block">
	
	<helper:message successKey="${successKey}" errorKey="${errorKey}" />
	
	<div class="content">
   		<h2 class="title">${healthCarePlan.name}</h2>
		<div class="inner">
	
		<label class="label">Endereço:</label>
    	${healthCarePlan.address.street}
		
		<div class="agrupar_campos">
			<label class="label">Bairro:</label>
	    ${healthCarePlan.address.neighborhood}
		</div>
		
		<div class="agrupar_campos">
			<label class="label">CEP:</label>
	    ${healthCarePlan.address.postalCode}
		</div>

		<div class="agrupar_campos">
			<label class="label">Cidade:</label>
	    ${healthCarePlan.address.city}
		</div>
		
		<div class="agrupar_campos">
			<label class="label">Estado:</label>
	    ${healthCarePlan.address.state}
		</div>
			
		<label class="label">Nome do Responsável:</label>
    	${healthCarePlan.contact}	
		<div class="agrupar_campos">
			<label class="label">Telefone*:</label>
    	${healthCarePlan.telephone}
		</div>
		
		<div class="agrupar_campos">
			<label class="label">E-mail:</label>
    	${healthCarePlan.email}
		</div>
		
		<label class="label">Website:</label>
    ${healthCarePlan.website}
	
	<div class="agrupar_campos">
		<label class="label">Tabela de Serviços*:</label>
		${healthCarePlan.table.name}
	</div>
	
	<div class="agrupar_campos">
		<label class="label">Valor em R$ da CH:</label>
		${healthCarePlan.ch.money}
	</div>
	
		<label class="label">Observações</label>
    ${healthCarePlan.observations}

		</div>
	</div>
</div>

<div>
	<form action="<c:url value="/convenios/${healthCarePlan.id}/service-rules" />" method="post" id="service_form">
		<table class="table">
			<tr>
				<th class="first"><input type="checkbox" class="checkbox toggle check_all" rel="chk_convenios"/></th>
				<th>Serviço</th>
				<th>CH?</th>
				<th>Valor?</th>
				<th class="last">&nbsp;</th>
			</tr>
			<c:forEach var="rule" items="${healthCarePlan.serviceRules}">
			<tr>
				<td></td>
				<td>${rule.service.name}</td>
				<td>
					<c:choose>
						<c:when test="${rule.rulingCh}">
						${rule.ch}
						</c:when>
						<c:otherwise>
						-
						</c:otherwise>
					</c:choose>
				</td>
				<td>
					<c:choose>
						<c:when test="${rule.rulingValue}">
						R$ ${rule.value}
						</c:when>
						<c:otherwise>
						-
						</c:otherwise>
					</c:choose>
				</td>							
				<td> 
						<a href="<c:url value="/convenios/${healthCarePlan.id}/service-rules/${rule.id}" />">deletar</a>
				</td>
			</tr>
			</c:forEach>
			<tr>
				<td></td>
				<td>
					<select name="service.id">
						<c:forEach var="service" items="${healthCarePlan.servicesWithNoRules}">
						<option value="${service.id}">${service.name}</option>
						</c:forEach>
					</select>
				</td>
				<td><input type="text" name="ch.ch" /></td>
				<td><input type="text" name="value.money" /></td>
				<td><input type="button" name="" value="Salvar" onclick="$('#service_form').submit();"/></td>
			</tr>
		</table>
	</form>
</div>

<div>
	<form action="<c:url value="/convenios/${healthCarePlan.id}/material-rules" />" method="post" id="material_form">
		<table class="table">
			<tr>
				<th class="first"><input type="checkbox" class="checkbox toggle check_all" rel="chk_convenios"/></th>
				<th>Material</th>
				<th>CH?</th>
				<th>Valor?</th>
				<th class="last">&nbsp;</th>
			</tr>
			<c:forEach var="rule" items="${healthCarePlan.materialRules}">
			<tr>
				<td></td>
				<td>${rule.material.name}</td>
				<td>
					<c:choose>
						<c:when test="${rule.rulingCh}">
						${rule.ch}
						</c:when>
						<c:otherwise>
						-
						</c:otherwise>
					</c:choose>
				</td>
				<td>
					<c:choose>
						<c:when test="${rule.rulingValue}">
						R$ ${rule.value}
						</c:when>
						<c:otherwise>
						-
						</c:otherwise>
					</c:choose>
				</td>							
				<td>
					<a href="<c:url value="/convenios/${healthCarePlan.id}/material-rules/${rule.id}" />">deletar</a>
				</td>
			</tr>
			</c:forEach>
			<tr>
				<td></td>
				<td>
					<select name="material.id">
						<c:forEach var="material" items="${healthCarePlan.materialsWithNoRules}">
						<option value="${material.id}">${material.name}</option>
						</c:forEach>
					</select>
				</td>
				<td><input type="text" name="ch.ch" /></td>
				<td><input type="text" name="value.money" /></td>
				<td><input type="button" name="" value="Salvar" onclick="$('#material_form').submit();"/></td>
			</tr>
		</table>
	</form>
</div>
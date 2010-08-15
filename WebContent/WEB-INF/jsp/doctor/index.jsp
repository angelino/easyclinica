<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>

<html>
	<head>
		<title>.: EasyClinica - Listagem de Médicos :.</title>
	</head>
	<body>	
		<div class="block" id="block-tables">
			<!--  div class="secondary-navigation">
			  <ul class="wat-cf">
			    <li class="first"><a href="javascript:void(0);">Ativos</a></li>
			    <li class="active"><a href="javascript:void(0);">Inativos</a></li>
			  </ul>
			</div -->
		    <div class="content">
		    	<h2 class="title">Listagem de Médicos</h2>
		    	
		    	<helper:message messageKey="${message}" />
		    	
		        <div class="inner">
					<table class="table">
						<tr>
							<th class="first"><input type="checkbox" class="checkbox toggle check_all" rel="chk_medicos"/></th>
							<th>Nome</th>
							<th>CRM</th>
							<th>Telefone</th>
							<th>E-mail</th>
							<th class="last">&nbsp;</th>
						</tr>
						
						<c:forEach var="doctor" items="${doctors.result}" varStatus="status">
							<tr class="${status.count % 2 == 0 ? 'odd' : 'even' }">
								<td><input type="checkbox" class="checkbox" rel="chk_medicos" name="id" value="${doctor.id}" /></td>
								<td>${doctor.name}</td>
								<td>${doctor.crm}</td>
								<td>${doctor.telephone}</td>
								<td>${doctor.email}</td>
								<td></td>
							</tr>
						</c:forEach>
					</table>
					
		            <helper:pagging total="${doctors.totalPages}" current="${doctors.currentPage}" />
				</div>
			</div>
		</div>
	</body>
</html>
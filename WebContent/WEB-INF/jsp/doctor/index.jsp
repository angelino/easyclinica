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
						
						<c:forEach var="doctor" items="${doctors}" varStatus="status">
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
					
		            <div class="actions-bar wat-cf">
			             <div class="actions">
			               <button class="button" type="submit">
			                 <img src="images/icons/cross.png" alt="Delete" /> Delete
			               </button>
			             </div>
		                 <div class="pagination">
		                    <span class="disabled prev_page">« Previous</span><span class="current">1</span><a rel="next" href="#">2</a><a href="#">3</a><a href="#">4</a><a href="#">5</a><a href="#">6</a><a href="#">7</a><a href="#">8</a><a href="#">9</a><a href="#">10</a><a href="#">11</a><a rel="next" class="next_page" href="#">Next »</a>
		                 </div>
		            </div>
				</div>
			</div>
		</div>
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>
<html>
	<head>
		<title>.: EasyClinica - Listagem de Convênios :.</title>
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
		    	<h2 class="title">Listagem de Convênios</h2>
		    	
		    	<helper:message messageKey="${message}" />
		    	
		        <div class="inner">
					<table class="table">
						<tr>
							<th class="first"><input type="checkbox" class="checkbox toggle check_all" rel="chk_convenios"/></th>
							<th>Nome</th>
							<th>Contato</th>
							<th>Telefone</th>
							<th class="last">&nbsp;</th>
						</tr>
						
						<c:forEach var="healthCare" items="${healthcares}" varStatus="status">
							<tr class="${status.count % 2 == 0 ? 'odd' : 'even' }">
								<td><input type="checkbox" class="checkbox" rel="chk_convenios" name="id" value="${healthCare.id}" /></td>
								<td>${healthCare.name}</td>
								<td>${healthCare.contact}</td>
								<td>${healthCare.telephone}</td>
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
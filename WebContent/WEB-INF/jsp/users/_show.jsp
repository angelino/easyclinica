<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>

<div class="modal-usuarios">
  	<h2>Usuário: ${employee.name}</h2>
	
	<div class="modal-content">
		<div>
			<p>Login:</p>
			<span>${employee.login}</span>
		</div>
	
		<div>
			<p>Celular:</p>
			<span>${employee.cellphone}</span>
		</div>

		<div>
			<p>Cargo:</p>
			<span>${employee.position.formattedName}</span>
		</div>
				
		<div class="observations">
			<p>Observações:</p>
			<span>${employee.observations}</span>
		</div>
		
		<div class="boxactions">
			<a class="btnedit" href="<c:url value="/usuarios/${employee.id}/editar"/>">Editar</a>
			<a class="btnclose modal-close">Fechar</a>
	    </div>
   	</div>
</div>
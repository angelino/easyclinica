<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="procedure">

	<div class="procedure-title">
		<p>
			<c:if test="${procedure.ambCode != ''}">
				${procedure.ambCode}(AMB)
			</c:if>
			<c:if test="${procedure.tussCode != ''}">
				${procedure.tussCode}(TUSS)
			</c:if>
		</p>
		<p>
			${procedure.name}
			<input type="hidden" name="appointment.procedures[#index#].procedure.id" value="${procedure.id}"/>
		</p>
		<p>${procedure.ch} CH</p>
		<p>
			<!-- Pensar forma melhor de pegar o preço -->
			<c:choose>
				<c:when test="${precifiedProcedure != null }">
					<fmt:formatNumber value="${precifiedProcedure.fixedAmount}" type="currency"/> 
				</c:when>
				<c:otherwise>
					<fmt:formatNumber value="${procedure.ch * healthCarePlan.ch}" type="currency"/>
				</c:otherwise>
			</c:choose>			
		</p>
		<a href="#">Novo Material</a>		
	</div>
	
	<ul class="material">	
		<li>
			<input type="hidden" name="appointment.procedures[#index#].materials[0].material.id" value="" />
			<input type="text" class="qty" name="appointment.procedures[#index#].materials[0].qty" value="1" />
			<p class="name">Material 1</p>
			<input type="text" class="amount" name="appointment.procedures[#index#].materials[0].unitAmount" value="R$ 100,00" />
			<p>R$ 100,00</p>
			<a href="#">excluir</a>			
		</li>
		
		<li>
			<input type="hidden" name="appointment.procedures[#index#].materials[1].material.id" value="" />
			<input type="text" class="qty" name="appointment.procedures[#index#].materials[1].qty" value="1" />
			<p class="name">Material 2</p>
			<input type="text" class="amount" name="appointment.procedures[#index#].materials[1].unitAmount" value="R$ 100,00" />
			<p>R$ 100,00</p>
			<a href="#">excluir</a>			
		</li>
	</ul>
	
	<ul class="medicine">	
		<li>
			<input type="hidden" name="appointment.procedures[#index#].medicines[0].medicine.id" value="" />
			<input type="text" class="qty" name="appointment.procedures[#index#].medicines[0].qty" value="1" />
			<p class="name">Medicamento 1</p>
			<input type="text" class="amount" name="appointment.procedures[#index#].medicines[0].unitAmount" value="R$ 100,00" />
			<p>R$ 100,00</p>
			<a href="#">excluir</a>			
		</li>
	</ul>
	
	<p>TOTAL DO PROCEDIMENTO: R$ 300,00</p>
	
</div>




	
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
		<p class="procedure-total currency">
			<c:choose>
				<c:when test="${precifiedProcedure != null }">
					${precifiedProcedure.fixedAmount} 
				</c:when>
				<c:otherwise>
					${procedure.ch * healthCarePlan.ch}
				</c:otherwise>
			</c:choose>			
		</p>
		<a href="#">Novo Material</a>
		<a href="#" class="remove-procedure">excluir</a>		
	</div>
	
	<ul class="material">
	
		<c:forEach items="${materials}" var="precifiedMaterial" varStatus="status">			
			<li>
				<input type="hidden" name="appointment.procedures[#index#].materials[${status.count-1}].material.id" value="${precifiedMaterial.material.id}" />
				<input type="text" class="qty" name="appointment.procedures[#index#].materials[${status.count-1}].qty" value="1" />
				<p class="name">${precifiedMaterial.material.name}</p>
				<input type="text" class="amount currency" name="appointment.procedures[#index#].materials[${status.count-1}].unitAmount" value="${precifiedMaterial.amount}" />
				<p class="total currency">${precifiedMaterial.amount}</p>
				<a href="#" class="remove-material">excluir</a>			
			</li> 
		</c:forEach>
		
	</ul>
	
	<ul class="medicine">
		<c:forEach items="${medicine}" var="precifiedMedicine" varStatus="status">	
			<li>
				<input type="hidden" name="appointment.procedures[#index#].medicines[${status.count-1}].medicine.id" value="${precifiedMedicine.medicine.id}" />
				<input type="text" class="qty" name="appointment.procedures[#index#].medicines[${status.count-1}].qty" value="1" />
				<p class="name">${precifiedMedicine.medicine.name}</p>
				<input type="text" class="amount currency" name="appointment.procedures[#index#].medicines[${status.count-1}].unitAmount" value="${precifiedMedicine.amount}" />
				<p class="total currency">${precifiedMedicine.amount}</p>
				<a href="#" class="remove-medicine">excluir</a>			
			</li>
		</c:forEach>
	</ul>
	
	<p>TOTAL DO PROCEDIMENTO: <span class="procedure-amount currency"></span></p>
	
</div>




	
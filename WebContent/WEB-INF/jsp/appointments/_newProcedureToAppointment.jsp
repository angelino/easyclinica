<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="procedure">

	<table class="table">
	
		<!-- INICIO PROCEDURE -->
		<tr class="procedimento-title">
			<td>
				<c:if test="${procedure.ambCode != ''}">
					${procedure.ambCode}(AMB)
				</c:if>
				<c:if test="${procedure.tussCode != ''}">
					${procedure.tussCode}(TUSS)
				</c:if>
			</td>
			<td>
				${procedure.name}
				<input type="hidden" name="appointment.procedures[#index#].procedure.id" value="${procedure.id}"/>
			</td>
			<td>${procedure.ch} CH</td>
			<td></td>
			<td class="procedure-total">
				<c:choose>
					<c:when test="${precifiedProcedure != null }">
						<input type="hidden" name="appointment.procedures[#index#].amount" value="${precifiedProcedure.fixedAmount}"/>
						${precifiedProcedure.fixedAmount} 
					</c:when>
					<c:otherwise>
						<input type="hidden" name="appointment.procedures[#index#].amount" value="${procedure.ch * healthCarePlan.ch}"/>
						${procedure.ch * healthCarePlan.ch}
					</c:otherwise>
				</c:choose>
			</td>
			<td>
				<a href="#">novo material</a> |
				<a href="#" class="remove-procedure">excluir</a>
			</td>
		</tr>
		<!-- FIM PROCEDURE -->
	
		<tr>
			<th></th>
			<th>Material / Medicamento</th>
			<th>Quantidade</th>
			<th>Valor Unitário</th>
			<th>Total</th>
			<th class="last">&nbsp;</th>
		</tr>
		
		<!-- MATERIAIS -->	
		<c:forEach items="${materials}" var="material" varStatus="status">	
			<tr class="${status.count % 2 == 0 ? 'odd' : 'even' } material">
				<td>
					<input type="hidden" name="appointment.procedures[#index#].materials[${status.count-1}].material.id" value="${material.materialId}" />
				</td>
				<td>${material.materialName}</td>
				<td>
					<input type="text" class="qty" name="appointment.procedures[#index#].materials[${status.count-1}].qty" value="${material.qty}" />
				</td>
				<td>
					<input type="text" class="amount currency" name="appointment.procedures[#index#].materials[${status.count-1}].unitAmount" value="${material.amount}" />
				</td>
				<td class="total currency">${material.qty * material.amount}</td>
				<td>
					<a href="#" class="remove-material">excluir</a>
				</td>
			</tr>
		</c:forEach>
		
		<!-- MEDICAMENTOS -->
		<c:forEach items="${medicines}" var="medicine" varStatus="status">
			<tr class="${status.count % 2 == 0 ? 'odd' : 'even' } medicine">
				<td>
					<input type="hidden" name="appointment.procedures[#index#].medicines[${status.count-1}].medicine.id" value="${medicine.medicineId}" />
				</td>
				<td>${medicine.medicineName}</td>
				<td>
					<input type="text" class="qty" name="appointment.procedures[#index#].medicines[${status.count-1}].qty" value="${medicine.qty}" />
				</td>
				<td>
					<input type="text" class="amount currency" name="appointment.procedures[#index#].medicines[${status.count-1}].unitAmount" value="${medicine.amount}" />
				</td>
				<td class="total currency">${medicine.qty * medicine.amount}</td>
				<td>
					<a href="#" class="remove-medicine">excluir</a>
				</td>
			</tr>
		</c:forEach>
		
		<tr class="total">
			<td></td>
			<td>TOTAL DO PROCEDIMENTO</td>
			<td colspan="2"></td>
			<td class="procedure-amount currency"></td>
			<td></td>
		</tr>
		
	</table>
	
</div>




	
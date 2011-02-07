<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="procedure">

	<table class="table">
	
		<!-- INICIO PROCEDURE -->
		<tr class="procedimento-title procedure-table">
			<th>
				<c:if test="${procedure.ambCode != ''}">
					${procedure.ambCode}(AMB)
				</c:if>
				<c:if test="${procedure.tussCode != ''}">
					${procedure.tussCode}(TUSS)
				</c:if>
			</th>
			<th>
				${procedure.name}
				<input type="hidden" name="appointment.procedures[#index#].procedure.id" value="${procedure.id}"/>
			</th>
			<th>${procedure.ch} CH</th>
			<th></th>
			<th>
				<c:choose>
					<c:when test="${precifiedProcedure != null }">
						<input type="hidden" name="appointment.procedures[#index#].amount" value="${precifiedProcedure.fixedAmount}"/>
						<span class="procedure-total">${precifiedProcedure.fixedAmount}</span> 
					</c:when>
					<c:otherwise>
						<input type="hidden" name="appointment.procedures[#index#].amount" value="${procedure.ch * healthCarePlan.ch}"/>
						<span class="procedure-total">${procedure.ch * healthCarePlan.ch}</span>
					</c:otherwise>
				</c:choose>
			</th>
			<th>
				<a href="#" class="remove-procedure">excluir</a>
			</th>
		</tr>
		<!-- FIM PROCEDURE -->
	
		<tr class="sub-table">
			<th class="nostyle"></th>
			<th>Material / Medicamento</th>
			<th>Quantidade</th>
			<th>Valor Unitário</th>
			<th>Total</th>
			<th class="last">&nbsp;</th>
		</tr>
		
		<!-- MATERIAIS -->	
		<c:forEach items="${materials}" var="material" varStatus="status">	
			<tr class="${status.count % 2 == 0 ? 'odd' : 'even' } material">
				<td class="nostyle">
					<input type="hidden" name="appointment.procedures[#index#].materials[${status.count-1}].material.id" value="${material.materialId}" />
				</td>
				<td>${material.materialName}</td>
				<td>
					<input type="text" class="qty currency" pattern="^[0-9]+(\,\d{1,2})?$" name="appointment.procedures[#index#].materials[${status.count-1}].qty" value="${material.qty}" />
				</td>
				<td>
					<input type="text" class="amount currency" pattern="^[0-9]+(\,\d{1,2})?$" name="appointment.procedures[#index#].materials[${status.count-1}].unitAmount" value="${material.amount}" />
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
				<td class="nostyle">
					<input type="hidden" name="appointment.procedures[#index#].medicines[${status.count-1}].medicine.id" value="${medicine.medicineId}" />
				</td>
				<td>${medicine.medicineName}</td>
				<td>
					<input type="text" class="qty currency" pattern="^[0-9]+(\,\d{1,2})?$" name="appointment.procedures[#index#].medicines[${status.count-1}].qty" value="${medicine.qty}" />
				</td>
				<td>
					<input type="text" class="amount currency" pattern="^[0-9]+(\,\d{1,2})?$" name="appointment.procedures[#index#].medicines[${status.count-1}].unitAmount" value="${medicine.amount}" />
				</td>
				<td class="total currency">${medicine.qty * medicine.amount}</td>
				<td>
					<a href="#" class="remove-medicine">excluir</a>
				</td>
			</tr>
		</c:forEach>
		
		<tr class="total">
			<td class="nostyle"></td>
			<td>TOTAL DO PROCEDIMENTO</td>
			<td colspan="2"></td>
			<td class="procedure-amount currency"></td>
			<td></td>
		</tr>
		
	</table>
	
</div>




	
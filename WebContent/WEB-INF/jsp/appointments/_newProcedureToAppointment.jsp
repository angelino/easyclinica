<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<tr class="tableheader" procedure_id="${procedure.id}">
	<th>Código:</th>
    <th>Procedimento:</th>
    <th colspan="2">Valor CH:</th>
    <th>Valor R$</th>
    <th width="95px">&nbsp;</th>
</tr>
<tr procedure_id="${procedure.id}">
     <td>
     	<c:if test="${not empty procedure.ambCode}">
			${procedure.ambCode}(AMB)
		</c:if>
		<c:if test="${not empty procedure.tussCode}">
			${procedure.tussCode}(TUSS)
		</c:if>
     </td>
     <td>
     	${procedure.name}
     	<input type="hidden" class="procedure-id" name="appointment.procedures[#index#].procedure.id" value="${procedure.id}"/>
     </td>
     <td colspan="2" class="center">${procedure.ch} CH</td>
     <td>
     	<c:choose>
			<c:when test="${precifiedProcedure != null }">
				<input type="hidden" name="appointment.procedures[#index#].amount.amount" value="${precifiedProcedure.fixedAmount.amount}"/>
				<span class="procedure-total-${procedure.id} currency">${precifiedProcedure.fixedAmount.amount}</span> 
			</c:when>
			<c:otherwise>
				<input type="hidden" name="appointment.procedures[#index#].amount.amount" value="${procedure.ch * healthCarePlan.ch.amount}"/>
				<span class="procedure-total-${procedure.id} currency">${procedure.ch * healthCarePlan.ch.amount}</span>
			</c:otherwise>
		</c:choose>
     </td>
     <td><a href="#" class="btndelete last remove-procedure" procedure_id="${procedure.id}">Excluir</a></td>
</tr>	
		
<tr class="tableheader" procedure_id="${procedure.id}">
     <td rowspan="${ 3 + fn:length(materials) + fn:length(medicines)}" id="table-space-${procedure.id}" class="tablenostyle">&nbsp;</td>
     <td>Material/Medicamento:</td>
     <td>Quantidade:</td>
     <td>Valor Unitário:</td>
     <td>Total:</td>
     <td>&nbsp;</td>
</tr>
		
<!-- MATERIAIS -->	
<c:forEach items="${materials}" var="material" varStatus="status">	
	<tr class="${status.count % 2 == 0 ? 'odd' : 'even' } material-${procedure.id}" procedure_id="${procedure.id}">
		<td>
			<input type="hidden" name="appointment.procedures[#index#].materials[${status.count-1}].material.id" value="${material.material.id}" />
			${material.material.name}
		</td>
		<td>
			<input type="text" class="qty currency" pattern="^[0-9]+(\,\d{1,2})?$" name="appointment.procedures[#index#].materials[${status.count-1}].qty.qty" value="${material.qty.qty}" />
		</td>
		<td>
			<input type="text" class="amount currency" pattern="^[0-9]+(\,\d{1,2})?$" name="appointment.procedures[#index#].materials[${status.count-1}].unitAmount.amount" value="${material.amount.amount}" />
		</td>
		<td class="total currency">${material.qty.qty * material.amount.amount}</td>
		<td>
			<a href="#" class="remove-material btndelete last" procedure_id="${procedure.id}">Excluir</a>
		</td>
	</tr>
</c:forEach>
		
<!-- MEDICAMENTOS -->
<c:forEach items="${medicines}" var="medicine" varStatus="status">
	<tr class="${status.count % 2 == 0 ? 'odd' : 'even' } medicine-${procedure.id}" procedure_id="${procedure.id}">
		<td>
			<input type="hidden" name="appointment.procedures[#index#].medicines[${status.count-1}].medicine.id" value="${medicine.medicine.id}" />
			${medicine.medicine.name}
		</td>
		<td>
			<input type="text" class="qty currency" pattern="^[0-9]+(\,\d{1,2})?$" name="appointment.procedures[#index#].medicines[${status.count-1}].qty.qty" value="${medicine.qty.qty}" />
		</td>
		<td>
			<input type="text" class="amount currency" pattern="^[0-9]+(\,\d{1,2})?$" name="appointment.procedures[#index#].medicines[${status.count-1}].unitAmount.amount" value="${medicine.amount.amount}" />
		</td>
		<td class="total currency">${medicine.qty.qty * medicine.amount.amount}</td>
		<td>
			<a href="#" class="remove-medicine btndelete last" procedure_id="${procedure.id}">Excluir</a>
		</td>
	</tr>
</c:forEach>

<tr class="boxsubtotal" procedure_id="${procedure.id}">
     <td colspan="3">Total do procedimento:</td>
     <td class="procedure-amount-${procedure.id} currency"></td>
     <td>&nbsp;</td>
</tr>
<tr class="boxdivisortable" procedure_id="${procedure.id}">
     <td colspan="5">&nbsp;</td>
</tr>		





	
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<tr class="tableheader" procedure_id="${procedure.id}">
	<th>Código:</th>
    <th>Procedimento:</th>
    <th colspan="2">Valor CH:</th>
    <th>Valor R$</th>
    <th width="95px">
		<input type="hidden" name="procedure-#index#-roomRate" value="${precifiedProcedure.roomTaxAmount}" />
    	<input type="hidden" name="index" value="#index#"/>
		<input type="hidden" name="procedure_id" value="${procedure.id}"/>
    	
    	<ul class="procedure-elements">
    		<li>
    			<a href="" class="new-assistant" title="Adicionar assistente ao procedimento.">assistente</a>
    			<div class="new-assistant">
    				<form id="frm-new-assistant">
	    				<fieldset>
							<p class="required"><span>*</span> campos obrigatórios</p>
							
							<div>	
								<label class="title">Tipo:<span>*</span></label>
								<select name="assistantType">
									<c:forEach var="type" items="${assistantTypes}">
										<option value="${type}">${type.formattedName}</option>
									</c:forEach>
								</select>
							</div>
							
							<div>	
								<label class="title">Nome:<span>*</span></label>
								<input type="text" name="assistantName" maxlength="50" />
					    	</div>
					    	
					    	<div>
								<label class="title">CH:<span>*</span></label>
								<input type="text" name="assistantCH" class="number" />
							</div>
							
					    </fieldset>
					    		
					    <div class="boxactions">
							<input type="button" class="btnsave" value="Salvar" />
					      	<a class="btnclose" rel="new-assistant">Fechar</a>
						</div>
					</form>
    			</div>
    		</li>
    		
    		<li>
    			<a href="" class="new-material" title="Adicionar material ao procedimento.">material</a>
    			<div class="new-material">
    				<form id="frm-new-material">
	    				<fieldset>
							<p class="required"><span>*</span> campos obrigatórios</p>
							
							<div>	
								<label class="title">Nome:<span>*</span></label>
								<input type="text" name="materialName" maxlength="255" />
								<input type="hidden" name="materialId" value="0" />
					    	</div>
					    	
					    	<div>
								<label class="title">Quantidade:<span>*</span></label>
								<input type="text" name="materialQuantity" class="currency" />
							</div>
							
							<div>
								<label class="title">Valor Unitário:<span>*</span></label>
								<input type="text" name="materialUnitAmount" class="currency" />
							</div>
					    </fieldset>
					    		
					    <div class="boxactions">
							<input type="button" class="btnsave" value="Salvar" />
					      	<a class="btnclose" rel="new-material">Fechar</a>
						</div>
					</form>
    			</div>
    		</li>
    		<li>
    			<a href="" class="new-medicine" title="Adicionar medicamento ao procedimento.">medicamento</a>
    			<div class="new-medicine">
    				<form id="frm-new-medicine">
	    				<fieldset>
							<p class="required"><span>*</span> campos obrigatórios</p>
							
							<div>	
								<label class="title">Nome:<span>*</span></label>
								<input type="text" name="medicineName" maxlength="255" />
								<input type="hidden" name="medicineId" value="0" />
					    	</div>
					    	
					    	<div>
								<label class="title">Quantidade:<span>*</span></label>
								<input type="text" name="medicineQuantity" class="currency" />
							</div>
							
							<div>
								<label class="title">Valor Unitário:<span>*</span></label>
								<input type="text" name="medicineUnitAmount" class="currency" />
							</div>
					    </fieldset>
					    		
					    <div class="boxactions">
							<input type="button" class="btnsave" value="Salvar" />
					      	<a class="btnclose" rel="new-medicine">Fechar</a>
						</div>
					</form>
    			</div>
    		</li>
    	</ul>
    </th>
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
     
     	<c:choose>
			<c:when test="${precifiedProcedure.fixedAmount > 0}">
				<td colspan="2" class="center">
     				-
     				<input type="hidden" id="procedure-ch-${procedure.id}" value="0" />
     			</td>
     
			<td>		
				<input type="text" class="amount currency" id="procedure-total-${procedure.id}" name="appointment.procedures[#index#].amount" value="${precifiedProcedure.fixedAmount}"/>
				<input type="hidden" name="appointment.procedures[#index#].ch" value="0"/>
			</td> 
			</c:when>
			<c:otherwise>
				<td colspan="2" class="center">
     				${precifiedProcedure.ch} CHs
     				<input type="hidden" id="procedure-ch-${procedure.id}" value="${precifiedProcedure.ch}" />
     			</td>
				<td>
					<input type="hidden" name="appointment.procedures[#index#].ch" value="${precifiedProcedure.ch}"/>
					<input type="text" class="amount currency" id="procedure-total-${procedure.id}" name="appointment.procedures[#index#].amount" value="${precifiedProcedure.ch * healthCarePlan.ch}" />
				</td>
			</c:otherwise>
		</c:choose>
     <td><a href="#" class="btndelete last remove-procedure" procedure_id="${procedure.id}" id="lnkDeleteProcedure#index#">Excluir</a></td>
</tr>	
		
<tr class="tableheader header-materials-medicine" procedure_id="${procedure.id}">
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
			<input type="text" class="qty currency" pattern="^[0-9]+(\,\d{1,2})?$" name="appointment.procedures[#index#].materials[${status.count-1}].qty" value="${material.qty}" />
		</td>
		<td>
			<input type="text" class="amount currency" pattern="^[0-9]+(\,\d{1,2})?$" name="appointment.procedures[#index#].materials[${status.count-1}].unitAmount" value="${material.amount}" />
		</td>
		<td class="total currency">${material.qty * material.amount}</td>
		<td>
			<a href="#" class="remove-material btndelete last" procedure_id="${procedure.id}" id="lnkDeleteMaterial${status.count-1}Procedure#index#">Excluir</a>
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
			<input type="text" class="qty currency" pattern="^[0-9]+(\,\d{1,2})?$" name="appointment.procedures[#index#].medicines[${status.count-1}].qty" value="${medicine.qty}" />
		</td>
		<td>
			<input type="text" class="amount currency" pattern="^[0-9]+(\,\d{1,2})?$" name="appointment.procedures[#index#].medicines[${status.count-1}].unitAmount" value="${medicine.amount}" />
		</td>
		<td class="total currency">${medicine.qty * medicine.amount}</td>
		<td>
			<a href="#" class="remove-medicine btndelete last" procedure_id="${procedure.id}" id="lnkDeleteMedicine${status.count-1}Procedure#index#">Excluir</a>
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





	
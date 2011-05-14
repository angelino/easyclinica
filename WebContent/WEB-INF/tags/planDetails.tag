<%@tag display-name="planDetails" pageEncoding="utf-8" %>
<%@ attribute name="plan" type="br.com.easyclinica.domain.entities.HealthCarePlan" required="true" rtexprvalue="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<div class="postit">
  <h4><c:out value="${plan.name}"/></h4>
  
  <p>Telefone: ${plan.telephone}</p>
  <p>Contato: ${plan.contact}</p>
  <p>E-mail: ${plan.email}</p>
  <p>Observações: ${plan.observations}</p>
</div>

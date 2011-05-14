<%@tag display-name="patientDetails" pageEncoding="utf-8" %>
<%@ attribute name="patient" type="br.com.easyclinica.domain.entities.Patient" required="true" rtexprvalue="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 

<div class="postit">
  <h4><c:out value="${patient.name}"/></h4>
  
  <p>Convênio: ${patient.healthCarePlan.name}</p>
  <p>Telefone: ${patient.telephone}</p>
  <p>Celular: ${patient.cellphone}</p>
  <p>RG: ${patient.rg}</p>
  <p>Data de Nascimento: <fmt:formatDate pattern="dd/MM/yyyy" value="${patient.birthDate.time}" /></p>
  <p>Observações do paciente: ${patient.observations}</p>
  <p>Observações do convênio: ${patient.healthCarePlan.observations}</p>
</div>

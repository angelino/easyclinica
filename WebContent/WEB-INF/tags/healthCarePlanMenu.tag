<%@ tag language="java" pageEncoding="utf-8"%>
<%@tag display-name="healthCarePlanMenu"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ attribute name="plan" type="br.com.easyclinica.domain.entities.HealthCarePlan" required="true" rtexprvalue="true" %>
<%@ attribute name="selected" required="true" %>
<ul class="boxmenu">
	<li ${selected=='Convenio' ? 'class="active first"' : 'class="first"'}>
		<a href="<c:url value="/convenios/${plan.id}/editar"/>">Convênios</a>
	</li>
	<li ${selected=='Financeiro' ? 'class="active"' : ''}>
		<a href="<c:url value="/convenios/${plan.id}/financeiro"/>">Financeiro</a>
	</li>
</ul>
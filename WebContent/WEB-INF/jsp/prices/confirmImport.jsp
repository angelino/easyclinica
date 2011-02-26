<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>
<html>
	<head>
		<title>.: EasyClinica :.</title>
	</head>
	<body>
		<div class="box">		
			<div class="boxcontent">
				<h2>Importar Preços</h2>

					<form action="<c:url value="/convenios/${id}/precos" />" method="post">
						<c:forEach var="procedure" items="${import.importedProcedures}" varStatus="st">
							<input type="hidden" name="procedures[${st.index}].id" value="${procedure.id}" />
							<input type="hidden" name="procedures[${st.index}].value" value="${procedure.value}" />
						</c:forEach>

						<c:forEach var="material" items="${import.importedMaterials}" varStatus="st">
							<input type="hidden" name="materials[${st.index}].id" value="${material.id}" />
							<input type="hidden" name="materials[${st.index}].value" value="${material.value}" />
						</c:forEach>
						
						<c:forEach var="medicine" items="${import.importedMedicines}" varStatus="st">
							<input type="hidden" name="medicines[${st.index}].id" value="${medicine.id}" />
							<input type="hidden" name="medicines[${st.index}].value" value="${medicine.value}" />
						</c:forEach>
						
						<c:forEach var="specialty" items="${import.importedSpecialties}" varStatus="st">
							<input type="hidden" name="specialties[${st.index}].id" value="${specialty.id}" />
							<input type="hidden" name="specialties[${st.index}].value" value="${specialty.value}" />
						</c:forEach>
						
						<input type="submit" />
					</form>				
								
			</div>
		</div>
	</body>
</html>
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

					<form action="<c:url value="/convenios/${id}/precos/confirmar" />" method="post" enctype="multipart/form-data">
						<input type="hidden" name="id" value="${id}" />
						<input type="file" name="file" />
						
						<input type="submit" />
					</form>				
								
			</div>
		</div>
	</body>
</html>
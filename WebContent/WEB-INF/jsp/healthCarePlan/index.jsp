<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>
<%@page import="br.com.easyclinica.view.Link"%>
<%@page import="java.util.LinkedList"%>
<html>
	<head>
		<title>.: EasyClinica - Listagem de Convênios :.</title>
	</head>
	<body>

		<div class="box" id="convenios">
			
			<div class="boxcontent">
			   <h2>Listagem de Convênios</h2>
			   <fieldset class="search">
               		<div class="search">
                       <input type="text" name="plan.textobusca" allowEnter="true"/>
                       <a href="#" id="btnBuscar">Buscar</a>
                       <img id="loading" src="<c:url value="images/loading.gif" />" alt="carregando..."/>
                       <p>Digite seu critério de busca.</p>
                   </div>
               </fieldset>
			</div>

			<div class="boxcontent" id="box_listagem">
				<helper:message successKey="${successKey}" errorKey="${errorKey}" />
			 			    	
				<jsp:include page="_list.jsp"/>
			</div>
		</div>
			
		<div class="boxright">
			<% 
				java.util.List<Link> links = new LinkedList<Link>();  
				links.add(new Link("/convenios/novo","Criar novo convênio"));
				pageContext.setAttribute("links",links);
			%>
			<helper:navigation links="${links}"></helper:navigation>

		</div>
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 	prefix="c"%>
<%@ taglib uri="/WEB-INF/sitemesh-decorator.tld" 	prefix="decorator" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" 			prefix="helper" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><decorator:title default="Easy Clinica" /></title>

		<helper:include fileName="style.css" type="css" />
		<helper:include fileName="plugins/jquery-ui/jquery-ui-1.8.2.custom.css" type="css" />
		<helper:include fileName="plugins/jquery.autocomplete/jquery.autocomplete.css" type="css" />
		<script type="text/javascript" language="javascript">
			var BASE_URI = "/";
		</script>
	    <decorator:head />
	</head>

    <body>
    
    	<div class="main">
            
            <div class="header">
       	    	<div class="hcontent">
		       	    <h1><a href="#">${loggedUser.clinic.name}</a></h1>
		
		                <ul class="menulogin">
		                  <c:if test="${loggedUser.employee.position eq 'OWNER'}">
		                  <li><a href="<c:url value="/clinica" />">Dados da Clínica</a></li>
		                  </c:if>
		                  <li><a href="<c:url value="/perfil" />">Meu perfil</a></li>
		                  <li><a href="<c:url value="/logoff" />" class="logout" id="lnkLogout">Logout</a></li>
		                </ul>
		
		                <ul class="menu" id="menu-principal">
		                  <li class="first" id="menu-link-dashboard"><a href="<c:url value="/" />">Mural</a></li>
		                  <li id="menu-link-pacientes"><a href="<c:url value="/pacientes" />">Pacientes</a></li>
		                  <li id="menu-link-convenios"><a href="<c:url value="/convenios" />">Convênios</a></li>
		                  <li id="menu-link-medicos"><a href="<c:url value="/medicos" />">Médicos</a></li>
		                  
		                  <c:if test="${loggedUser.employee.position eq 'OWNER'}">
		                  <li id="menu-link-usuarios"><a href="<c:url value="/usuarios" />">Usuários</a></li>
		                  </c:if>
		                  
		                  
		                  <li id="menu-link-agenda">
								<c:url value="/agenda" var="schedule_link"/>
								<a href="${schedule_link}" title="Ver Agenda">Agenda</a>
						  </li>
								
		
						  <c:if test="${loggedUser.employee.position eq 'OWNER' or loggedUser.employee.position eq 'FINANCIAL'}">
		                  <li id="menu-link-reports"><a href="<c:url value="/relatorios" />">Relatórios</a></li>
		                  </c:if>
		                </ul>
		    	</div>
		    </div>
            <div class="content">
            	<div class="ccontent">
    				<decorator:body />
    			</div> 			
			</div>
            <!-- div class="footer">
            	<div class="fcontent">
            		<p>Copyright &copy; 2011 Easy Clínica.</p>
            	</div>
            </div -->
            
        </div>

<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-23005006-2']);
  _gaq.push(['_setDomainName', '.easyclinica.com.br']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>

	<helper:include fileName="completo.min.js" type="js" />
    </body>
</html>
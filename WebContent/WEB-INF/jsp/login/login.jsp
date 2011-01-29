<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/sitemesh-decorator.tld" prefix="decorator" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Easy Clínica</title>
	    
		<helper:include fileName="reset.css" type="css" />
		<helper:include fileName="base.css" type="css" />
		<helper:include fileName="style.css" type="css" />
		<helper:include fileName="jquery-ui-1.8.2.custom.css" type="css" />
	</head>

	<body>
	<c:url value="/login" var="loginUrl" />		
 <div id="box"> 
      <h1>Easy Cl&iacute;nica</h1>
      <div class="block" id="block-login"> 
        <h2>Login</h2> 
        <div class="content login"> 
          <div class="flash"> 
            <div class="message notice"> 
              <p>Usu&aacute;rio e/ou senha inv&aacute;lidos</p> 
            </div> 
          </div> 
          <form action="${loginUrl}" class="form login" method="post"> 
            <div class="group wat-cf"> 
              <div class="left"> 
                <label class="label right">Login</label> 
              </div> 
              <div class="right"> 
                <input type="text" class="text_field" name="login"/> 
              </div> 
            </div> 
            <div class="group wat-cf"> 
              <div class="left"> 
                <label class="label right">Senha</label> 
              </div> 
              <div class="right"> 
                <input type="password" class="text_field" name="password"/> 
              </div> 
            </div> 
            <div class="group navform wat-cf"> 
              <div class="right"> 
                <button class="button" type="submit"> 
                  <img src="images/icons/key.png" alt="Save" /> Logar!
                </button> 
              </div> 
            </div> 
          </form> 
        </div> 
      </div> 
	</div>
	
	</body>
</html>
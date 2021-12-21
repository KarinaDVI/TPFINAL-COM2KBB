<%-- 
    Document   : Principal
    Created on : 17/12/2021, 22:10:10
    Author     : Karina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
          HttpSession misession = request.getSession();
          String usu = (String) misession.getAttribute("usuario");
          
          if (usu == null){
              response.sendRedirect("login.jsp");
          }
          else{
        %>
        <h1>Logueado correctamente!</h1>
        <% } %>
    </body>
</html>

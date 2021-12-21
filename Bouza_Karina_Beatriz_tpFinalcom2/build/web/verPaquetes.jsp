<%-- 
    Document   : verPaquetes
    Created on : 14/12/2021, 19:05:48
    Author     : Karina
--%>

<%@page import="logica.PaqueteTuristico"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="tablaSrc/css/styles.css">
        <link rel="stylesheet" href="css/styles.css">
	<script src="js/jquery.js"></script>
        <title>Paquetes Ver</title>
</head>
<body>
    <%
          HttpSession misession = request.getSession();
          String usu = (String) misession.getAttribute("usuario");
          
          if (usu==null){
              response.sendRedirect("login.jsp");
          }else
          {  %>
          
<form action="SvPaqueteVer" method="GET">
	<div class="rwd">
		<table class="rwd_auto">
			<thead>
			<tr>
				<th>Codigo de paquete:</th>
				<th>Costo:</th>
			</tr>
			</thead>
			<tbody>
                            <% 
                            List<PaqueteTuristico> listaPaquetes = (List) request.
                                    getSession().getAttribute("listaPaquetes");
                            for (PaqueteTuristico paque : listaPaquetes){                     
                            %>
                            
                                <tr>
                                
                                <%int codigo_paquete= paque.getCodigo_paquete();%>
                                <td><%= codigo_paquete%></td>
                                <%double costo_paquete= paque.getCosto_paquete();%>
				<td><%= costo_paquete %></td>
                                <td> 
                                    <form name="borrar_paquete" action="SvPaqueteBaja" method="post" >
                                        <input type="hidden" name="codigo_paquete" value="<%=codigo_paquete%>">
                                        <button type="submit" data-title="Delete" >Eliminar </button>
                                    </form>
                                </td>
                                <td>
                                    <form name="modificar_paquete" action="SvModificarPaquete" method="post" >
                                        <input type="hidden" name="codigo_paquete" value="<%=codigo_paquete%>">
                                        <button type="submit" data-title="Edit">Modificar</button>
                                    </form>
                                </td>
                                
                               
			</tr>
                        <%}%>
			</tbody>
                        
		</table>
	</div>
        <br>  
        <form action="paquetes.jsp">
       <button type="submit" data-title="volver">Volver a paquetes</button>
        </form>
        
       </form>  
        
        <%}%>
</body>
</html>
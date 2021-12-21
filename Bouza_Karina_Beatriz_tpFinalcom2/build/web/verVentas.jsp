<%-- 
    Document   : verVentas
    Created on : 16/12/2021, 02:52:53
    Author     : Karina
--%>


<%@page import="java.util.ArrayList"%>
<%@page import="logica.Empleado"%>
<%@page import="logica.Cliente"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="logica.Ventas"%>
<%@page import="logica.Controladora"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="tablaSrc/css/styles.css">
        <link rel="stylesheet" href="css/styles.css">
	<script src="js/jquery.js"></script>
        <title>Ventas Ver</title>
</head>
<body>
    <%
          HttpSession misession = request.getSession();
          String usu = (String) misession.getAttribute("usuario");
          
          if (usu==null){
              response.sendRedirect("login.jsp");
          }else
          {  %>

	<div class="rwd">
		<table class="rwd_auto">
			<thead>
			<tr>
				<th>Numero de Venta:</th>
				<th>Fecha de venta:</th>
				<th>Medio de Pago:</th>
				<th>Nº Cliente:</th>
				<th>Empleado:</th>
				<th>Paquete:</th>
				<th>Servicio:</th>
			</tr>
			</thead>
			<tbody>
                            <% Controladora control = new Controladora ();
                            List<Ventas> listaVenta = control.traerVentas();
                            for (Ventas venta : listaVenta){
                            int un_paquete;
                            int un_servicio;
                            %>
                                
                                <tr>
                                <%int num_venta= venta.getNum_venta();%>
                                <td><%= num_venta%></td> 
                                <%String fecha_venta= venta.getFecha_venta();%>
                                <td><%= fecha_venta %></td>
                                <%String medio_pago= venta.getMedio_pago();%>
				<td><%= medio_pago %></td>
                                <%int un_cliente= venta.getUn_cliente().getId_cliente();%>                                
				<td><%= un_cliente %></td>
                                <%int empleado = venta.getUn_empleado().getId_empleado();%>
				<td><%= empleado %></td>
                                <%try{un_paquete=venta.getUn_paquete().getCodigo_paquete();}
                                 catch (Exception e){un_paquete= 0;}%>
				<td><%= un_paquete %></td>
                                <%try{un_servicio= venta.getUn_servicio().getCodigo_servicio();}
                                 catch (Exception e){un_servicio=0;}%>
				<td><%= un_servicio %></td>
                           
			</tr>
                        
                         
                        <%}%>
                        </tbody>
		</table>
	</div>
        <br>  
        <input class="entries" type="button" value="Página anterior" onClick="history.go(-1);">	
    <%}%>
</body>
</html>

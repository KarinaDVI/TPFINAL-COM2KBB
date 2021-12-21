

<%@page import="java.util.Date"%>
<%@page import="logica.Cliente"%>
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
        <title>Clientes Ver</title>
</head>
<body>
    <%
          HttpSession misession = request.getSession();
          String usu = (String) misession.getAttribute("usuario");
          
          if (usu==null){
              response.sendRedirect("login.jsp");
          }else
          {  %>
          
<form action="SvClienteVer" method="GET">
	<div class="rwd">
		<table class="rwd_auto">
			<thead>
			<tr>
				
				<th>Nombres:</th>
                                <th>Apellidos:</th>
				<th>Direccion:</th>
				<th>DNI:</th>
				<th>Fecha de Nacimiento:</th>
				<th>Nacionalidad:</th>
				<th>Celular:</th>
				<th>Email:</th>
                                <th>Eliminar</th>
                                <th>Modificar</th>
			</tr>
			</thead>
			<tbody>
                            <% 
                            
                            List<Cliente> listaClientes = (List) request.getSession().getAttribute("listaClientes");
                            for (Cliente clien : listaClientes){
                                
                            %>
                            
                                <tr>
                                <%String nombre= clien.getNombre();%>
                                <td><%=nombre%></td>    
                                <%String apellido = clien.getApellido();%>
                                <td><%=apellido%></td> 
                                <%String direccion= clien.getDireccion();%>
                                <td><%= direccion %></td>
                                <%String dni= clien.getDni();%>
				<td><%= dni %></td>
                                <%String fecha_nac= clien.getFecha_nac();%>                                
				<td><%= fecha_nac %></td>
                                <%String nacionalidad= clien.getNacionalidad();%>
				<td><%= nacionalidad %></td>
                                <%String celular= clien.getCelular();%>
				<td><%= celular %></td>
                                <%String email= clien.getEmail();%>
				<td><%= email %></td>
                                <%int id_cliente= clien.getId_cliente();%>
                                <td> 
                                    <form name="borrar_cliente" action="SvClienteBaja" method="post" >
                                        <input type="hidden" name="id_clien" value="<%=id_cliente%>">
                                        <button type="submit" data-title="Delete" >Eliminar </button>
                                    </form>
                                </td>
                                <td>
                                    <form name="modificar_cliente" action="SvModificarCliente" method="post" >
                                        <input type="hidden" name="id_clien" value="<%=id_cliente%>">
                                        <button type="submit" data-title="Edit">Modificar</button>
                                    </form>
                                </td>
                                </td>
			</tr>
                        <% } %>
			</tbody>
                        
		</table>
	</div>
        <br>  
        <form action="clientes.jsp">
       <button type="submit" data-title="volver">Volver a clientes</button>
        </form>
</form>                    
    <% } %>                   
</body>
 
</html>

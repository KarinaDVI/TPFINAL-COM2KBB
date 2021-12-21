

<%@page import="java.util.Date"%>
<%@page import="logica.Empleado"%>
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
        <title>Empleados Ver</title>
</head>
<body>
    <%
          HttpSession misession = request.getSession();
          String usu = (String) misession.getAttribute("usuario");
          
          if (usu==null){
              response.sendRedirect("login.jsp");
          }else
          {  %>
    
<form action="SvEmpleadoVer" method="GET">
	<div >
		<table class="table">
			<thead>
			<tr>
				<th>Nombres:</th>
                                <th>Apellidos:</th>
				<th>Direccion:</th>
				<th>DNI:</th>
				<th>Fecha de Nacimiento:</th>
				<th>Cargo:</th>
				<th>Sueldo:</th>
				<th>Nacionalidad:</th>
				<th>Celular:</th>
				<th>Email:</th>
				<th>Usuario: </th>
				<th>Contraseña:</th>
                                <th>Estado:</th>
                                <th>Eliminar</th>
                                <th>Modificar</th>
			</tr>
			</thead>
			<tbody>
                            
                           <%   
                            List<Empleado> listaEmpleados = (List) request.getSession().getAttribute("listaEmpleados");
                            for (Empleado emple : listaEmpleados){                     
                            
                            %>
                                <tr>
                                <%String nombre= emple.getNombre();%>
                                <td><%=nombre%></td>    
                                <%String apellido = emple.getApellido();%>
                                <td><%=apellido%></td> 
                                <%String direccion= emple.getDireccion();%>
                                <td><%= direccion %></td>
                                <%String dni= emple.getDni();%>
				<td><%= dni %></td>
                                <%String fecha_nac= emple.getFecha_nac();%>
				<td><%= fecha_nac %></td>
                                <%String cargo= emple.getCargo();%>
				<td><%= cargo %></td>
                                <%double sueldo= emple.getSueldo();%>
				<td><%= sueldo %></td>
                                <%String nacionalidad= emple.getNacionalidad();%>
				<td><%= nacionalidad %></td>
                                <%String celular= emple.getCelular();%>
				<td><%= celular %></td>
                                <%String email= emple.getEmail();%>
				<td><%= email %></td>
                                <%String usuario= emple.getUn_usuario().getNombre_usuario();%>
				<td><%= usuario %></td>
                                <%String contrasenia= emple.getUn_usuario().getContrasenia();%>
				<td><%= contrasenia %></td>
                                <%String estado= emple.getEstado();%>
				<td><%= estado %></td>
                                <%int id_empleado=emple.getId_empleado();%>
                                <td> 
                                    <form name="borrar_empleado" action="SvEmpleadoBaja" method="post" >
                                        <input type="hidden" name="id_emple" value="<%=id_empleado%>">
                                        <button type="submit" data-title="Delete" >Eliminar </button>
                                    </form>
                                </td>
                                <td>
                                    <form name="modificar_empleado" action="SvModificarEmpleado" method="post" >
                                        <input type="hidden" name="id_emple" value="<%=id_empleado%>">
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
        <form action="empleados.jsp">
       <button type="submit" data-title="volver">Volver a empleados</button>
        </form>
       
</form>
    <% } %>                   
</body>
 
</html>
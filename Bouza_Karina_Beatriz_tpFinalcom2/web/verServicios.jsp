
<%@page import="java.util.Date"%>
<%@page import="logica.ServicioTuristico"%>
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
        <title>Servicios Ver</title>
    </head>
    <body>
        <%
            HttpSession misession = request.getSession();
            String usu = (String) misession.getAttribute("usuario");

            if (usu == null) {
                response.sendRedirect("login.jsp");
        } else {  %>

        <form action="SvServicioVer" method="GET">
            <div class="rwd">
                <table class="rwd_auto">
                    <thead>
                        <tr>

                            <th>Nombre de servicio:</th>
                            <th>Descripción: </th>
                            <th>Destino del servicio: </th>
                            <th>Fecha del Servicio:</th>
                            <th>Costo del Servicio:</th>
                            <th>Disponibilidad?:</th>
                            <th>Codigo:</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            List<ServicioTuristico> listaServicios = (List) request.getSession()
                                    .getAttribute("listaServicios");
                            for (ServicioTuristico servi : listaServicios) {

                        %>

                        <tr>
                            <%String nombre = servi.getNombre();%>
                            <td><%= nombre%></td> 
                            <%String descripcion_breve = servi.getDescripcion_breve();%>
                            <td><%= descripcion_breve%></td>
                            <%String destino_servicio = servi.getDestino_servicio();%>
                            <td><%= destino_servicio%></td>
                            <%Date fecha_servicio = servi.getFecha_servicio();%>                       
                            <td><%= fecha_servicio%></td>
                            <%double costo_servicio = servi.getCosto_servicio();%>
                            <td><%= costo_servicio%></td>
                            <%String disponibilidad = servi.getDisponibilidad();%>
                            <td><%= disponibilidad%></td>
                            <%int codigo_servicio = servi.getCodigo_servicio();%>
                            <td>
                                <form name="borrar_servicio" action="SvServicioBaja" method="post" >
                                    <input type="hidden" name="codigo_servicio" value="<%=codigo_servicio%>">
                                    <button type="submit" data-title="Delete" >Eliminar </button>
                                </form>
                            </td>
                            <td>
                                <form name="modificar_servicio" action="SvModificarServicio" method="post" >
                                    <input type="hidden" name="codigo_servicio" value="<%=codigo_servicio%>">
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
            <form action="servicios.jsp">
                <button type="submit" data-title="volver">Volver a servicios</button>
            </form>
        </form>   

        <% }%>                   
    </body>

</html>
<%-- 
    Document   : venta
    Created on : 12/12/2021, 16:49:56
    Author     : Karina
--%>

<%@page import="logica.Cliente"%>
<%@page import="logica.Empleado"%>
<%@page import="logica.PaqueteTuristico"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="logica.Controladora"%>
<%@page import="logica.ServicioTuristico"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" href="css/styles.css">
        <script src="js/jquery.js"></script>

    </script>  <title>Ventas Alta module</title>

</head>
<body>
    <%
        HttpSession misession = request.getSession();
        String usu = (String) misession.getAttribute("usuario");

        if (usu == null) {
            response.sendRedirect("login.jsp");
            } else {  %>
    <h1>Alta de Ventas</h1>

    <div class="">
        <h3 class="">Agencia de Turismo</h3>
        <nav>
            <ul class="">
                <li><a href="index.jsp">Home</a></li>
                <li><a href="verVentas.jsp">Mostrar Ventas</a></li>
            </ul>
        </nav>
    </div>
    <br>
    <% Controladora control = new Controladora();
        List<ServicioTuristico> lista_servicios_incluidos = control.traerServicioTuristico();
        List<PaqueteTuristico> listaPaquete = control.traerPaqueteTuristico();
        List<Empleado> lista_empleado = control.traerEmpleado();
        List<Cliente> lista_cliente = control.traerClientes();
    %>
    <form class="form" action="SvVentaAlta" method="POST">
        <div>
            <p>Seleccione venta de Servicio o Paquete </p>

            <input type="radio" name="Servicio/Paquete" value="servicio" required>
            <label for="servi">Servicio</label>
            <input type="radio" name="Servicio/Paquete" value="paquete" required>
            <label for="paque">Paquete</label>    
        </div>
        <div>


            <div id="tS">
                <table class="table">
                    <thead>
                        <tr>
                            <th>Nombre del servicio:</th>
                            <th>Descripción breve:</th>
                            <th>Destino :</th>
                            <th>Fecha del Servicio:</th>
                            <th>Costo del servicio:</th>
                            <th>Disponibilidad:</th>
                            <th>Código del servicio:</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%for (ServicioTuristico servi : lista_servicios_incluidos) {%>
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
                            <td><%= codigo_servicio%></td>
                            <td>
                                <input type="checkbox" name="seleccionar_servicio" value="<%=codigo_servicio%>">
                            </td> 
                    <br>
                    </tr>
                    <%}%>
                    </tbody>
                    </div>
                    <br>
                    <br>
                    <! –– Tabla de paquete --!>
                    <div id="tP">
                        <table>
                            <thead>
                                <tr>
                                    <th>Codigo de Paquete:</th>
                                    <th>Costo del paquete:</th>
                                </tr>
                            <thead>
                            <tbody>

                                <%for (PaqueteTuristico paque : listaPaquete) {%>
                                <tr>
                                    <%int codigo_paquete = paque.getCodigo_paquete();%>
                                    <td><%= codigo_paquete%></td>
                                    <%double costo_paquete = paque.getCosto_paquete();%>
                                    <td><%= costo_paquete%></td>
                                    <td>
                                        <input type="checkbox" name="seleccionar_paquete" value="<%=codigo_paquete%>">
                                    </td> 

                            <br>
                            </tr> 
                            <%}%>

                            </tbody>
                        </table>
                    </div>
                    <br>
                    <div>
                        <table>
                            <thead>
                                <tr>
                                    <th>Codigo de cliente:</th>
                                    <th>DNI:</th>
                                </tr>
                            <thead>
                            <tbody>

                                <%for (Cliente clien : lista_cliente) {%>
                                <tr>
                                    <%int id_cliente = clien.getId_cliente();%>
                                    <td><%= id_cliente%></td>
                                    <%String dni = clien.getDni();%>
                                    <td><%= dni%></td>
                                    <%String nombre_completo = clien.getNombre()
                                                + " " + clien.getApellido();%>
                                    <td><%= nombre_completo%></td> 
                                    <td>
                                        <input type="checkbox" name="seleccionar_cliente" value="<%=id_cliente%>" >
                                    </td> 
                            <br>
                            </tr> 
                            <%}%>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div>

                        <table>
                            <thead>
                                <tr>
                                    <th>Codigo de empleado:</th>
                                    <th>Nombre:</th>
                                </tr>
                            <thead>
                            <tbody>

                                <%for (Empleado emple : lista_empleado) {%>
                                <tr>
                                    <%int id_empleado = emple.getId_empleado();%>
                                    <td><%= id_empleado%></td>
                                    <%String nombre = emple.getNombre();%>
                                    <td><%= nombre%></td>
                                    <td>
                                        <input type="checkbox" name="seleccionar_empleado" value="<%=id_empleado%>">
                                    </td> 
                            <br>
                            </tr> 
                            <%}%>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div>
                        <p><label>Fecha de venta: </label><input class="entries" type="date" name="fecha_venta" required></p>
                    </div>
                    <div> 
                        Seleccione medio de Pago

                        <br>

                        <select name="medio_pago">
                            <option selected value="-"> Elige una opción </option>
                            <option value="efectivo">Efectivo</option>
                            <option value="tarjeta_debito">Tarjeta de débito</option> 
                            <option value="monedero_virtual">Monedero Virtual</option>
                            <option value="transferencia">Transferencia</option> 
                        </select>
                        <button class="entries" type="submit">Enviar</button
                        


                        </form>

                        <br> 
                        <br> 
                        <input class="entries" type="button" value="Página anterior" onClick="history.go(-1);">
                        <button class="entries" type="reset">Limpiar Datos</button>
                    </div>
                    <%}%>
                    </body>

                    </html>

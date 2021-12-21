<%-- 
    Document   : modificarPaquetesTuristicos
    Created on : 19/12/2021, 21:04:08
    Author     : Karina
--%>

<%@page import="logica.PaqueteTuristico"%>
<%@page import="java.util.Date"%>
<%@page import="logica.ServicioTuristico"%>
<%@page import="java.util.List"%>
<%@page import="logica.Controladora"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es"><head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="https://getbootstrap.com/docs/3.4/favicon.ico">
        <link rel="canonical" href="https://getbootstrap.com/docs/3.4/examples/cover/">
        <link rel="stylesheet" href="css/styles.css">
        <script src="js/jquery.js"></script>
        <title>Paquetes Turisticos Modify module</title>

        <!-- Bootstrap core CSS -->
        <link href="Boot_files/bootstrap.css" rel="stylesheet">

        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
        <link href="Boot_files/ie10-viewport-bug-workaround.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="Boot_files/cover.css" rel="stylesheet">


    </head>

    <body>
        <%
            HttpSession misession = request.getSession();
            String usu = (String) misession.getAttribute("usuario");

            if (usu == null) {
                response.sendRedirect("login.jsp");
            } else {  %>
        
        <div class="site-wrapper">

            <div class="site-wrapper-inner">

                <div class="cover-container">

                    <div class="masthead clearfix">
                        <div class="inner">
                            <h3 class="masthead-brand">Agencia de Turismo</h3>
                            <nav>
                                <ul class="nav masthead-nav">
                                    <li><a href="index.jsp">Home</a></li>
                                    <li><a href="SvPaqueteVer">Mostrar Paquetes</a></li>
                                </ul>
                            </nav>
                        </div>
                    </div>

                    <div class="inner cover">
                        <h1 class="cover-heading">Modificar paquete.</h1>

                        <p>Seleccione los servicios</p>

                        <br>
                         <form action="SvModificarPaquete" method="POST">
                        <% 
                            List<ServicioTuristico> lista_servicios_incluidos = 
                                    (List) misession.getAttribute("listaServicios");
                             List<PaqueteTuristico> paqueteT = (List) misession.getAttribute("paquete");
                        %>
                            <body>
                                <div class="rwd">    

                        <table class="rwd_auto">
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
                                <%  for (PaqueteTuristico paquete : paqueteT  ){ 
                                    for (ServicioTuristico servi : lista_servicios_incluidos) {%>    

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
                                        <input type="checkbox" name="seleccionar_servicio" 
                                               value="<%=codigo_servicio%>">
                                    </td>
                            <br>
                            </tr>
                                    <input type="hidden" name="codigo_paquete" 
                                           value="<%=paqueteT.getCodigoPaquete()%>">
                            <%}<input type="hidden" name="codigo_paquete" 
                                           value="<%=paqueteT.getCodigoPaquete()%>">
                                }%>
                                <button class="entries" type="submit">Modificar</button>        
                                <input class="entries" type="button" value="Página anterior" 
                                       onClick="history.go(-1);">
                                <button class="entries" type="reset">Limpiar Datos</button>
                                       
                                </div>
                              </form>           
                                <div class="mastfoot">
                                    <div class="inner">

                                        <p>Cover template for <a href="https://getbootstrap.com/">Bootstrap</a>, by <a href="https://twitter.com/mdo">@mdo</a>.</p>
                                    </div>
                                </div>

                                </div>

                                </div>

                                </div>

                                <!-- Bootstrap core JavaScript
                                ================================================== -->
                                <!-- Placed at the end of the document so the pages load faster -->
                                <script src="Boot_files/jquery-1.js" integrity="sha384-nvAa0+6Qg9clwYCGGPpDQLVpLNn0fRaROjHqs13t4Ggj3Ez50XnGQqc/r8MhnRDZ" crossorigin="anonymous"></script>
                                <script>window.jQuery || document.write'(' < script src = "../../assets/js/vendor/jquery.min.js" > <\/script>')'</script>
                                <script src="Boot_files/bootstrap.js"></script>
                                <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
                                <script src="Boot_files/ie10-viewport-bug-workaround.js"></script>

                     
                      
                        <body/>
                        <% }%>
                        </html>

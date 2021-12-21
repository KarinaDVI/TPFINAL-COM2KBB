

<%@page import="logica.ServicioTuristico"%>
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
        <title>Servicios Modify module</title>

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
        <form action="SvModificarServicio" method="post"> </form>
        <div class="site-wrapper">

            <div class="site-wrapper-inner">

                <div class="cover-container">

                    <div class="masthead clearfix">
                        <div class="inner">
                            <h3 class="masthead-brand">Agencia de Turismo</h3>
                            <nav>
                                <ul class="nav masthead-nav">
                                    <li><a href="index.jsp">Home</a></li>
                                    <li><a href="servicios.jsp">Altas</a></li>
                                    <li><a href="SvServicioVer" >Ver Servicios</a></li>

                                </ul>
                            </nav>
                        </div>
                    </div>

                    <div class="inner cover">
                        <h1 class="cover-heading">Modificar servicios.</h1>
                            <p>Ingrese los datos del Servicio</p>
                            <form action="SvModificarServicio" method="get">
                                <%
                                    ServicioTuristico servi = (ServicioTuristico) misession.getAttribute("servicio");

                                    {%> 
                                <p><label>Nombre de servicio: </label><input class="entries" type="text" name="nombre" value="<%=servi.getNombre()%>"></p>
                                <p><label>Descripción : </label><input class="entries" type="text" name="descripcion_breve" value="<%=servi.getDescripcion_breve()%>"></p>
                                <p><label>Destino del servicio: </label><input class="entries" type="text" name="destino_servicio" value="<%=servi.getDestino_servicio()%>"></p>
                                <p><label>Fecha del Servicio: </label><input class="entries" type="date" name="fecha_servicio" value="<%=servi.getFecha_servicio()%>"></p>
                                <p><label>Costo del Servicio: </label><input class="entries" type="text" name="costo_servicio" value="<%=servi.getCosto_servicio()%>"></p>
                                <p><label>Disponibilidad?: </label><input class="entries" type="text" name="disponibilidad" value="<%=servi.getDisponibilidad()%>"></p>

                                <input type="hidden" name="codigo_servicio" value="<%=servi.getCodigo_servicio()%>">
                                <button class="entries" type="submit">Modificar</button>        
                                <input class="entries" type="button" value="Página anterior" onClick="history.go(-1);">
                                <button class="entries" type="reset">Limpiar Datos</button>
                                <br>
                                <% } %>
                            </form>
                    </div>

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
        <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>');</script>
        <script src="Boot_files/bootstrap.js"></script>
        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
        <script src="Boot_files/ie10-viewport-bug-workaround.js"></script>
    </form>
</body>

<% } %>
</html>

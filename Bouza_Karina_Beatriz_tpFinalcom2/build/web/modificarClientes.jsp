

<%@page import="logica.Cliente"%>
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
        <title>Clientes Modify module</title>

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
        <form action="SvModificarCliente" method="post"> </form>
        <div class="site-wrapper">

            <div class="site-wrapper-inner">

                <div class="cover-container">

                    <div class="masthead clearfix">
                        <div class="inner">
                            <h3 class="masthead-brand">Agencia de Turismo</h3>
                            <nav>
                                <ul class="nav masthead-nav">
                                    <li><a href="index.jsp">Home</a></li>
                                    <li><a href="clientes.jsp">Altas</a></li>
                                    <li><a href="verClientes.jsp">Mostrar</a></li>



                                </ul>
                            </nav>
                        </div>
                    </div>

                    <div class="inner cover">
                        <h1 class="cover-heading">Modificar Cliente.</h1>
                        <p>Ingrese los datos del cliente</p>
                        <form action="SvModificarCliente" method="get">
                            <%
                        Cliente clien = (Cliente) misession.getAttribute("cliente");

                    {%> 

                            <p><label>Nombre: </label><input class="entries" type="text" name="nombre" value="<%=clien.getNombre()%>" required></p>
                            <p><label>Apellido: </label><input class="entries" type="text" name="apellido" value="<%=clien.getApellido()%>" required></p>
                            <p><label>Dirección: </label><input class="entries" type="text" name="direccion" value="<%=clien.getDireccion()%>" required></p>
                            <p><label>DNI: </label><input class="entries" type="text" name="dni" value="<%=clien.getDni()%>" required></p>
                            <p><label>Fecha de Nacimiento: </label><input class="entries" type="date" name="fecha_nac" value="<%=clien.getFecha_nac()%>" required></p>
                            <p><label>Nacionalidad: </label><input class="entries" type="text" name="nacionalidad" value="<%=clien.getNacionalidad()%>" required></p>
                            <p><label>Celular: </label><input class="entries" type="text" name="celular" value="<%=clien.getCelular()%>" required></p> 
                            <p><label>Email: </label><input class="entries" type="text" name="email" value="<%=clien.getEmail()%>" required></p>

                            <input type="hidden" name="id_cliente" value="<%=clien.getId_cliente()%>">
                            <button class="entries" type="submit">Modificar</button>        
                            <input class="entries" type="button" value="Página anterior" onClick="history.go(-1);">
                            <br>
                            <%}%>
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
        <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
        <script src="Boot_files/bootstrap.js"></script>
        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
        <script src="Boot_files/ie10-viewport-bug-workaround.js"></script>

    </form>
</body>
<% } %>
</html>


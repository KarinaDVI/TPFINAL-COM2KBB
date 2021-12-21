
package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Controladora;
import logica.ServicioTuristico;


@WebServlet(name = "SvModificarServicio", urlPatterns = {"/SvModificarServicio"})
public class SvModificarServicio extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

       
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener Datos del JSP
        
        SimpleDateFormat textFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        int codigo_servicio = Integer.parseInt(request.getParameter("codigo_servicio"));
        String conx = request.getParameter("fecha_servicio");
        Date fecha_servicio = null;
        String nombre = request.getParameter("nombre");
        String descripcion_breve = request.getParameter("descripcion_breve");
        String destino_servicio = request.getParameter("destino_servicio");
        //Setear la fecha
        try {
            fecha_servicio = textFormat.parse(conx);
        } catch (ParseException ex) {
            Logger.getLogger(SvModificarServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        double costo_servicio = Double.parseDouble(request.getParameter("costo_servicio"));
        String disponibilidad = request.getParameter("disponibilidad");
        
        //A la controladora a buscar el servicio
        
        Controladora control = new Controladora();
        ServicioTuristico servi = control.buscarServicioTuristico(codigo_servicio);
        
        servi.setNombre(nombre);
        servi.setDescripcion_breve(descripcion_breve);
        servi.setDestino_servicio(destino_servicio);
        servi.setFecha_servicio(fecha_servicio);
        servi.setCosto_servicio(costo_servicio);
        servi.setDisponibilidad(disponibilidad);
        
        control.modificarServicioTuristico(servi);
        
        request.getSession().setAttribute("listaServicios", control.traerServicioTuristico());
        response.sendRedirect("verServicios.jsp");
        
        
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int codigo_servicio = Integer.parseInt(request.getParameter("codigo_servicio"));
        Controladora control = new Controladora();
        ServicioTuristico servi = control.buscarServicioTuristico(codigo_servicio);
        
        HttpSession misession = request.getSession();
        misession.setAttribute("servicio", servi);
        response.sendRedirect("modificarServicioTuristico.jsp");



    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

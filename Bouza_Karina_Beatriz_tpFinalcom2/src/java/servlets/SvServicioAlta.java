
package servlets;

import java.io.IOException;
import static java.lang.Double.parseDouble;
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
import logica.Controladora;


@WebServlet(name = "SvServicioAlta", urlPatterns = {"/SvServicioAlta"})
public class SvServicioAlta extends HttpServlet {
Controladora control = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Obtener Datos del JSP
        SimpleDateFormat textFormat = new SimpleDateFormat("yyyy-MM-dd");
        String nombre = request.getParameter("nombre");
        String descripcion_breve = request.getParameter("descripcion_breve");
        String conv = request.getParameter("fecha_servicio");
        Date fecha_servicio = null;
        
    try {
        fecha_servicio = textFormat.parse(conv);
    } catch (ParseException ex) {
        Logger.getLogger(SvServicioAlta.class.getName()).log(Level.SEVERE, null, ex);
    }
        
        String destino_servicio = request.getParameter("destino_servicio");
        double costo_servicio = parseDouble(request.getParameter("costo_servicio"));
        String disponibilidad = request.getParameter("disponibilidad");
        
        request.getSession().setAttribute("nombre", nombre);
        request.getSession().setAttribute("descripcion_breve", descripcion_breve);
        request.getSession().setAttribute("fecha_servicio", fecha_servicio);
        request.getSession().setAttribute("destino_servicio", destino_servicio);
        request.getSession().setAttribute("costo_servicio", costo_servicio);
        request.getSession().setAttribute("disponibilidad", disponibilidad);

        
        //Llamado a la controladora para crear el servicio
        control.crearServicio(nombre,descripcion_breve,fecha_servicio,
                                destino_servicio,costo_servicio, disponibilidad);
        //Vuelta a la pagina de servicios
        response.sendRedirect("servicios.jsp");  
        
    }

 
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}


package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Controladora;
import logica.PaqueteTuristico;
import logica.ServicioTuristico;


@WebServlet(name = "SvModificarPaquete", urlPatterns = {"/SvModificarPaquete"})
public class SvModificarPaquete extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener Datos del JSP
       
        int codigo_paquete = Integer.parseInt(request.getParameter("codigo_paquete"));
        int codigo_servicio = Integer.parseInt(request.getParameter("codigo_servicio"));
        
        Double costo_paquete = Double.parseDouble(request.getParameter("costo_paquete"));
        Controladora control = new Controladora();
        PaqueteTuristico paque = control.buscarPaqueteTuristico(codigo_paquete);
        ServicioTuristico servi = control.buscarServicioTuristico(codigo_servicio);
        
        paque.setCosto_paquete(costo_paquete);
        servi.setCodigo_servicio(codigo_servicio);
        control.modificarPaquete(paque);
        request.getSession().setAttribute("paquete",paque);
        request.getSession().setAttribute("codigo_servicio",codigo_servicio);
        response.sendRedirect("verPaquetes.jsp");

        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Controladora control = new Controladora();
        Integer codigo_paquete = Integer.parseInt(request.getParameter("codigo_paquete"));
        PaqueteTuristico paque = control.buscarPaqueteTuristico(codigo_paquete);
        
        HttpSession misession = request.getSession();
        misession.setAttribute("paquete", paque);
        response.sendRedirect("modificarPaqueteTuristico.jsp");
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}


package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Controladora;


@WebServlet(name = "SvServicioBaja", urlPatterns = {"/SvServicioBaja"})
public class SvServicioBaja extends HttpServlet {

 
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
        
        int codigo_servicio = Integer.parseInt(request.getParameter("codigo_servicio"));
        Controladora control = new Controladora();
        control.eliminarServicio(codigo_servicio);
        
        request.getSession().setAttribute("listaServicios", control.traerServicioTuristico());
        response.sendRedirect("verServicios.jsp");
        
    }

  
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

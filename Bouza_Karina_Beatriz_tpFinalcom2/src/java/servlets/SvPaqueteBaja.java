
package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Controladora;


@WebServlet(name = "SvPaqueteBaja", urlPatterns = {"/SvPaqueteBaja"})
public class SvPaqueteBaja extends HttpServlet {

   
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
        
        int codigo_paquete = Integer.parseInt(request.getParameter("codigo_paquete"));
        Controladora control = new Controladora();
        control.eliminarPaquete(codigo_paquete);
        
        request.getSession().setAttribute("listaPaquetes", control.traerPaqueteTuristico());
        response.sendRedirect("verPaquetes.jsp");  
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

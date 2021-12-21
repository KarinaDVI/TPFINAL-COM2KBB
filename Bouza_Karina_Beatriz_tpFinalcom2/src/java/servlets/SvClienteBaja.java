
package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Controladora;


@WebServlet(name = "SvClienteBaja", urlPatterns = {"/SvClienteBaja"})
public class SvClienteBaja extends HttpServlet {


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
        
        int id_cliente=Integer.parseInt(request.getParameter("id_clien"));
        Controladora control = new Controladora();
        control.eliminarCliente(id_cliente);
        
        request.getSession().setAttribute("listaClientes", control.traerClientes());
        response.sendRedirect("verClientes.jsp");
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}


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
import logica.Controladora;


@WebServlet(name = "SvClienteAlta", urlPatterns = {"/SvClienteAlta"})
public class SvClienteAlta extends HttpServlet {
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
        
        SimpleDateFormat textFormat = new SimpleDateFormat("yyyy-MM-dd");
        String conp=request.getParameter("fecha_nac");
        Date fecha_nac = null;        
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String direccion = request.getParameter("direccion");
        String dni = request.getParameter("dni");
    
      try {
        
        fecha_nac = textFormat.parse(conp);
    } catch (ParseException ex) {
        Logger.getLogger(SvClienteAlta.class.getName()).log(Level.SEVERE, null, ex);
    }
    
        //Date fecha_nac = Date.valueOf(conv);
        String nacionalidad = request.getParameter("nacionalidad");
        String celular = request.getParameter("celular");
        String email = request.getParameter("email");
        
        //Paso atributos a la sesion
        request.getSession().setAttribute("nombre", nombre);
        request.getSession().setAttribute("apellido", apellido);
        request.getSession().setAttribute("direccion", direccion);
        request.getSession().setAttribute("dni", dni);
        request.getSession().setAttribute("fecha_nac", fecha_nac);
        request.getSession().setAttribute("nacionalidad", nacionalidad);
        request.getSession().setAttribute("celular", celular);
        request.getSession().setAttribute("email", email);
       
        control.crearCliente (nombre,apellido,direccion,dni,
                                fecha_nac,nacionalidad,
                                celular,email);
        
        response.sendRedirect("clientes.jsp");    
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

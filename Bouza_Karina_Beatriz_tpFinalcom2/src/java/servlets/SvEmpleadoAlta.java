
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


@WebServlet(name = "SvEmpleadoAlta", urlPatterns = {"/SvEmpleadoAlta"})
public class SvEmpleadoAlta extends HttpServlet {
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
        String conv=request.getParameter("fecha_nac");
        Date fecha_nac = null;
        
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String direccion = request.getParameter("direccion");
        String dni = request.getParameter("dni");
        
    try {
        fecha_nac = textFormat.parse(conv);
    } catch (ParseException ex) {
        Logger.getLogger(SvEmpleadoAlta.class.getName()).log(Level.SEVERE, null, ex);
    }
        //Date fecha_nac = Date.valueOf(conv);
        String cargo = request.getParameter("cargo");
        Double sueldo = Double.parseDouble(request.getParameter("sueldo"));
        String nacionalidad = request.getParameter("nacionalidad");
        String celular = request.getParameter("celular");
        String email = request.getParameter("email");
        String usuario = request.getParameter("user");
        String contrasenia = request.getParameter("pass");
        String estado = request.getParameter("estado");
        
        //Paso atributos a la sesion
        request.getSession().setAttribute("nombre", nombre);
        request.getSession().setAttribute("apellido", apellido);
        request.getSession().setAttribute("direccion", direccion);
        request.getSession().setAttribute("dni", dni);
        request.getSession().setAttribute("fecha_nac", fecha_nac);
        request.getSession().setAttribute("cargo", cargo);
        request.getSession().setAttribute("sueldo", sueldo);
        request.getSession().setAttribute("nacionalidad", nacionalidad);
        request.getSession().setAttribute("celular", celular);
        request.getSession().setAttribute("email", email);
        request.getSession().setAttribute("estado", estado);
        
        control.crearEmpleado (nombre,apellido,direccion,dni,
                                fecha_nac,cargo,sueldo,nacionalidad,
                                celular,email,usuario,contrasenia,estado);
        
        response.sendRedirect("empleados.jsp");
    }
     

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

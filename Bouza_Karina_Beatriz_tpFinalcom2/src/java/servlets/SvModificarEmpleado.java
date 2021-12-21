
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
import logica.Empleado;


@WebServlet(name = "SvModificarEmpleado", urlPatterns = {"/SvModificarEmpleado"})
public class SvModificarEmpleado extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        SimpleDateFormat textFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        int id_empleado = Integer.parseInt(request.getParameter("id_empleado"));
        String conr = request.getParameter("fecha_nac");
        Date fecha_nac = null;
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String direccion = request.getParameter("direccion");
        String dni = request.getParameter("dni");
        try {
            fecha_nac = textFormat.parse(conr);
        } catch (ParseException ex) {
            Logger.getLogger(SvModificarEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        String cargo = request.getParameter("cargo");
        double sueldo = Double.parseDouble(request.getParameter("sueldo"));
        String nacionalidad = request.getParameter("nacionalidad");
        String celular = request.getParameter("celular");
        String email = request.getParameter("email");
        String usuario = request.getParameter("user");
        String contrasenia = request.getParameter("pass");
        String estado = request.getParameter("estado");
        
        Controladora control = new Controladora();
        Empleado emple = control.buscarEmpleado(id_empleado);
        
            
        
        
        emple.setNombre(nombre);
        emple.setApellido(apellido);
        emple.setDireccion(direccion);
        emple.setDni(dni);
        emple.setFecha_nac(fecha_nac);
        emple.setCargo(cargo);
        emple.setSueldo(sueldo);
        emple.setNacionalidad(nacionalidad);
        emple.setCelular(celular);
        emple.setEmail(email);
        emple.un_usuario.setNombre_usuario(usuario);
        emple.un_usuario.setContrasenia(contrasenia);
        emple.setEstado(estado);
        control.modificarEmpleado(emple);
        
        request.getSession().setAttribute("listaEmpleados", control.traerEmpleados());
        response.sendRedirect("verEmpleados.jsp");
    } 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id_empleado = Integer.parseInt(request.getParameter("id_emple"));
        Controladora control = new Controladora();
        Empleado emple = control.buscarEmpleado(id_empleado);
        
        HttpSession misession = request.getSession();
        misession.setAttribute("empleado", emple);
        response.sendRedirect("modificarEmpleados.jsp");
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

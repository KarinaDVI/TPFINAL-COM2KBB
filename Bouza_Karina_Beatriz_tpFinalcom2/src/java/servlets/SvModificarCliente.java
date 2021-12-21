
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
import logica.Cliente;
import logica.Controladora;


@WebServlet(name = "SvModificarCliente", urlPatterns = {"/SvModificarCliente"})
public class SvModificarCliente extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
    }

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        SimpleDateFormat textFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        int id_cliente = Integer.parseInt(request.getParameter("id_cliente"));
        String cons = request.getParameter("fecha_nac");
        Date fecha_nac = null;
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String direccion = request.getParameter("direccion");
        String dni = request.getParameter("dni");
        try {
            fecha_nac = textFormat.parse(cons);
        } catch (ParseException ex) {
            Logger.getLogger(SvModificarCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String nacionalidad = request.getParameter("nacionalidad");
        String celular = request.getParameter("celular");
        String email = request.getParameter("email");
        
        
        Controladora control = new Controladora();
        Cliente clien = control.buscarCliente(id_cliente);
        
            System.out.println(clien);
        
        
        clien.setNombre(nombre);
        clien.setApellido(apellido);
        clien.setDireccion(direccion);
        clien.setDni(dni);
        clien.setFecha_nac(fecha_nac);
        clien.setNacionalidad(nacionalidad);
        clien.setCelular(celular);
        clien.setEmail(email);
        
        control.modificarCliente(clien);
        
        request.getSession().setAttribute("listaClientes", control.traerClientes());
        response.sendRedirect("verClientes.jsp");
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id_cliente = Integer.parseInt(request.getParameter("id_clien"));
        Controladora control = new Controladora();
        Cliente clien = control.buscarCliente(id_cliente);
        
        HttpSession misession = request.getSession();
        misession.setAttribute("cliente", clien);
        response.sendRedirect("modificarClientes.jsp");
    }

  
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

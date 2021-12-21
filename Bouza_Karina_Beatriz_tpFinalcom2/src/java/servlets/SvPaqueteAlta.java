
package servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Controladora;
import logica.ServicioTuristico;


@WebServlet(name = "SvPaqueteAlta", urlPatterns = {"/SvPaqueteAlta"})
public class SvPaqueteAlta extends HttpServlet {
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
        
        //Obtener datos del JSP y los paso a una lista
        String[] lista_servicios = request.getParameterValues("seleccionar_servicio");
        
    
        //Creo lista para almacenar los codigos de servicio
        ArrayList<ServicioTuristico> servicios = 
                new ArrayList<ServicioTuristico>();

        double costoServicio = 0;
        double costo_paquete = 0;
        // Rrecorrido para castear a entero
        for (String codigo_servicio : lista_servicios) {
            int codigo_parsed = Integer.parseInt(codigo_servicio);

            //Busco el servicio turistico de ese codigo  
            ServicioTuristico servicio = 
                    control.buscarServicioTuristico(codigo_parsed);

            //Lo agrego a la lista
            servicios.add(servicio);
            costoServicio += servicio.getCosto_servicio();
            System.out.println(servicios);
            }
        
        costo_paquete = (costoServicio * 0.90);
        //Agrego los servicios al objeto paquete y el costo
        
        //Seteo atributos a la sesion
        request.getSession().setAttribute("lista_servicios_incluidos", servicios);
        request.getSession().setAttribute("costo_paquete", costo_paquete);
        
        //request.getSession().setAttribute("lista_servicios", control.traerServicioTuristico());
        
        
        //Llamado a la controladora para crear el paquete
        control.crearNuevoPaquete(servicios, costo_paquete);
       //Vuelta a la pagina de alta de paquetes
        response.sendRedirect("paquetes.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

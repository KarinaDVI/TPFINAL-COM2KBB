
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


@WebServlet(name = "SvVentaAlta", urlPatterns = {"/SvVentaAlta"})
public class SvVentaAlta extends HttpServlet {
Controladora control = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                
                String OpcionVentas =request.getParameter("Servicio/Paquete");
                
                int codigo_servi=-1;
                int codigo_paque=-1;
                if (OpcionVentas.equals("servicio")){
                    codigo_servi = Integer.parseInt(request.getParameter("seleccionar_servicio"));
                    
                }
                if (OpcionVentas.equals("paquete")){
                    codigo_paque = Integer.parseInt(request.getParameter("seleccionar_paquete"));
                    
                }
                
                
                int empleado = Integer.parseInt(request.getParameter("seleccionar_empleado"));
                int cliente = Integer.parseInt(request.getParameter("seleccionar_cliente"));
                SimpleDateFormat textFormat = new SimpleDateFormat("yyyy-MM-dd");
                
                
                String convf=request.getParameter("fecha_venta");
                Date fecha_venta = null;
                try {
                     fecha_venta = textFormat.parse(convf);
                } catch (ParseException ex) {
                    Logger.getLogger(SvVentaAlta.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                String medio_pago = request.getParameter("medio_pago");             
                control.crearVenta(codigo_servi,codigo_paque
                        ,empleado,cliente,fecha_venta,medio_pago);

                response.sendRedirect("ventasAltas.jsp");
    }
    

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

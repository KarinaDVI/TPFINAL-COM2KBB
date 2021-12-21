
package persistencia;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.Cliente;
import logica.Empleado;
import logica.ServicioTuristico;
import logica.Usuario;
import logica.PaqueteTuristico;
import logica.Ventas;
import persistencia.exceptions.NonexistentEntityException;


public class ControladoraPersistencia {
    EmpleadoJpaController empleJPA = new EmpleadoJpaController ();
    UsuarioJpaController usuJPA = new UsuarioJpaController ();
    ClienteJpaController clienJPA = new ClienteJpaController ();
    ServicioTuristicoJpaController serviJPA = new  ServicioTuristicoJpaController ();
    PaqueteTuristicoJpaController paqueJPA = new PaqueteTuristicoJpaController ();
    VentasJpaController ventaJPA = new VentasJpaController ();
    
    public void crearEmpleado(Empleado emple, Usuario usu){
        usuJPA.create(usu);
        empleJPA.create(emple);
    }

    public void crearCliente(Cliente clien) {
        clienJPA.create(clien);    
    }
    public Cliente buscarCliente(int id_cliente){
        return clienJPA.findCliente(id_cliente);
    } 
    
    public List<Empleado> traerEmpleado() {
        return empleJPA.findEmpleadoEntities();
    }

    public void crearServicio(ServicioTuristico servi) {
       serviJPA.create(servi);
    }

    public List<ServicioTuristico> traerServicioTuristico() {
        return serviJPA.findServicioTuristicoEntities();
    }
    
    public ServicioTuristico buscarServicioTuristico(int codigo_servicio){
        return serviJPA.findServicioTuristico(codigo_servicio);
    }
  

    public void crearPaquete(PaqueteTuristico paque) {
       paqueJPA.create(paque);
    }
    
     public List<PaqueteTuristico> traerPaqueteTuristico() {
        return paqueJPA.findPaqueteTuristicoEntities();
    }
    
    public void crearVenta(Ventas venta){
        ventaJPA.create(venta);
    }
    public PaqueteTuristico buscarPaqueteTuristico(int codigo_paquete){
        return paqueJPA.findPaqueteTuristico(codigo_paquete);
    }

    public Empleado buscarEmpleado(int id_empleado) {
        return empleJPA.findEmpleado(id_empleado);
    }

    public List<Ventas> traerVentas() {
        return ventaJPA.findVentasEntities();
    }

    public Ventas buscarVenta(int num_venta) {
        return ventaJPA.findVentas(num_venta);
    }

    public void eliminarEmpleado(int id_empleado) {
        try {
            empleJPA.destroy(id_empleado);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Usuario> traerUsuarios() {
        return usuJPA.findUsuarioEntities();
    }

    public void modificarEmpleado(Empleado emple) {
        try {
            empleJPA.edit(emple);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Empleado> traerEmpleados() {
        return empleJPA.findEmpleadoEntities();
    }

    public void modificarCliente(Cliente clien) {
        try {
            clienJPA.edit(clien);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 

    public List<Cliente> traerClientes() {
        return clienJPA.findClienteEntities();
    }

    public void eliminarServicio(int codigo_servicio) {
        try {
            serviJPA.destroy(codigo_servicio);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modificarServicioTuristico(ServicioTuristico servi) {
        try {
            serviJPA.edit(servi);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminarCliente(int id_cliente) {
        try {
            clienJPA.destroy(id_cliente);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modificarPaqueteTuristico(PaqueteTuristico paque) {
        try {
            paqueJPA.edit(paque);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminarPaquete(int codigo_paquete) {
        try {
            paqueJPA.destroy(codigo_paquete);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    

    
   

 
    
}
    
        
    


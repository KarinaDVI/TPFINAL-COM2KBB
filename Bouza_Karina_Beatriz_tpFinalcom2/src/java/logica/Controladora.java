package logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import persistencia.ControladoraPersistencia;

public class Controladora {

    ControladoraPersistencia controlPersis = new ControladoraPersistencia();

    public void crearCliente(String nombre, String apellido, String direccion, String dni, Date fecha_nac, String nacionalidad, String celular, String email) {

        Cliente clien = new Cliente();

        clien.setNombre(nombre);
        clien.setApellido(apellido);
        clien.setDireccion(direccion);
        clien.setDni(dni);
        clien.setFecha_nac(fecha_nac);
        clien.setNacionalidad(nacionalidad);
        clien.setCelular(celular);
        clien.setEmail(email);

        controlPersis.crearCliente(clien);
    }

    public List<Empleado> traerEmpleado() {
        return controlPersis.traerEmpleado();
    }

    public void crearEmpleado(String nombre, String apellido, String direccion, String dni, Date fecha_nac, String cargo, Double sueldo, String nacionalidad, String celular, String email, String usuario, String contrasenia, String estado) {
        Empleado emple = new Empleado();
        Usuario usu = new Usuario();

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

        //Asigno valores a usuario
        usu.setNombre_usuario(usuario);
        usu.setContrasenia(contrasenia);
        emple.setEstado(estado);
        //Asigno usuario a empleado
        emple.setUn_usuario(usu);

        controlPersis.crearEmpleado(emple, usu);
    }

    public void crearServicio(String nombre_servicio, String descripcion_breve, Date fecha_servicio, String destino, double costo_servicio, String disponibilidad) {
        ServicioTuristico servi = new ServicioTuristico();

        servi.setNombre(nombre_servicio);
        servi.setDescripcion_breve(descripcion_breve);
        servi.setDestino_servicio(destino);
        servi.setFecha_servicio(fecha_servicio);
        servi.setCosto_servicio(costo_servicio);
        servi.setDisponibilidad(disponibilidad);

        controlPersis.crearServicio(servi);
    }

    public List<ServicioTuristico> traerServicioTuristico() {
        return controlPersis.traerServicioTuristico();
    }

    public void crearPaquete(String[] lista_servicios) {
        PaqueteTuristico paque = new PaqueteTuristico();

        //Creo lista para almacenar los codigos de servicio
        ArrayList<ServicioTuristico> servicios = new ArrayList<ServicioTuristico>();

        double costoServicio = 0;
        double costo_paquete = 0;
        // Rrecorrido para castear a entero
        for (String codigo_servicio : lista_servicios) {
            int codigo_parsed = Integer.parseInt(codigo_servicio);

            //Busco el servicio turistico de ese codigo  
            ServicioTuristico servicio = buscarServicioTuristico(codigo_parsed);

            //Lo agrego a la lista
            servicios.add(servicio);
            costoServicio += servicio.getCosto_servicio();
        
        }
        costo_paquete = (costoServicio * 0.90);
        //Agrego los servicios al objeto paquete y el costo
        paque.setLista_servicios_incluidos(servicios);
        paque.setCosto_paquete(costo_paquete);

        controlPersis.crearPaquete(paque);
    }
    
    public void crearNuevoPaquete(ArrayList servicios, double costo_paquete){
        PaqueteTuristico paque = new PaqueteTuristico();
        paque.setLista_servicios_incluidos(servicios);
        paque.setCosto_paquete(costo_paquete);
        controlPersis.crearPaquete(paque);
    }

    public ServicioTuristico buscarServicioTuristico(int codigo_servicio) {
        return controlPersis.buscarServicioTuristico(codigo_servicio);
    }

    public List<Cliente> traerClientes() {
        return controlPersis.traerClientes();
    }

    public List<PaqueteTuristico> traerPaqueteTuristico() {
        return controlPersis.traerPaqueteTuristico();
    }

    public Cliente buscarCliente(int id_cliente) {
        return controlPersis.buscarCliente(id_cliente);
    }

    public PaqueteTuristico buscarPaqueteTuristico(int codigo_paquete) {
        return controlPersis.buscarPaqueteTuristico(codigo_paquete);
    }

    public Empleado buscarEmpleado(int id_empleado) {
        return controlPersis.buscarEmpleado(id_empleado);
    }

    public void crearVenta(int codigo_servi, int codigo_paque, int empleado,
            int un_cliente, Date fecha_venta, String medio_pago) {
        Ventas venta = new Ventas();
        venta.setFecha_venta(fecha_venta);
        venta.setMedio_pago(medio_pago);
        venta.setUn_paquete(buscarPaqueteTuristico(codigo_paque));
        venta.setUn_servicio(buscarServicioTuristico(codigo_servi));
        venta.setUn_cliente(buscarCliente(un_cliente));
        venta.setUn_empleado(buscarEmpleado(empleado));
        controlPersis.crearVenta(venta);
    }

    public List<Ventas> traerVentas() {
        return controlPersis.traerVentas();
    }

    public Ventas buscarVentas(int num_venta) {
        return controlPersis.buscarVenta(num_venta);
    }

    public void eliminarEmpleado(int id_empleado) {
        controlPersis.eliminarEmpleado(id_empleado);
    }

    public boolean verificarUsuario(String user, String contra) {
        List<Usuario> listaUsuarios = controlPersis.traerUsuarios();

        if (listaUsuarios != null) {
            for (Usuario usu : listaUsuarios) {
                if (usu.getNombre_usuario().equals(user) && usu.getContrasenia().equals(contra)) 
                {
                    return true;
                }
            }
        }
        return false;
    }

    public void modificarEmpleado(Empleado emple) {
        controlPersis.modificarEmpleado(emple);
    }

    public List<Empleado> traerEmpleados() {
        return controlPersis.traerEmpleados();
    }

    public void modificarCliente(Cliente clien) {
        controlPersis.modificarCliente(clien);
    }

    public void eliminarCliente(int id_cliente) {
        controlPersis.eliminarCliente(id_cliente);
    }

    public void eliminarServicio(int codigo_servicio) {
        controlPersis.eliminarServicio(codigo_servicio);
    }

    public void modificarServicioTuristico(ServicioTuristico servi) {
        controlPersis.modificarServicioTuristico(servi);
    }

    public void modificarPaquete(PaqueteTuristico paque) {
        controlPersis.modificarPaqueteTuristico(paque);
    }

    public void eliminarPaquete(int codigo_paquete) {
        controlPersis.eliminarPaquete(codigo_paquete);
    }

   
}

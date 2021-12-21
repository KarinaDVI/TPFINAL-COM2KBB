package logica;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Cliente;
import logica.Empleado;
import logica.PaqueteTuristico;
import logica.ServicioTuristico;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-12-20T00:28:19")
@StaticMetamodel(Ventas.class)
public class Ventas_ { 

    public static volatile SingularAttribute<Ventas, Integer> num_venta;
    public static volatile SingularAttribute<Ventas, ServicioTuristico> un_servicio;
    public static volatile SingularAttribute<Ventas, Empleado> un_empleado;
    public static volatile SingularAttribute<Ventas, String> medio_pago;
    public static volatile SingularAttribute<Ventas, Cliente> un_cliente;
    public static volatile SingularAttribute<Ventas, Date> fecha_venta;
    public static volatile SingularAttribute<Ventas, PaqueteTuristico> un_paquete;

}
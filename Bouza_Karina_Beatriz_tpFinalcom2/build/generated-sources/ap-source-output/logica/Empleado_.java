package logica;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Usuario;
import logica.Ventas;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-12-20T00:28:19")
@StaticMetamodel(Empleado.class)
public class Empleado_ { 

    public static volatile SingularAttribute<Empleado, String> estado;
    public static volatile ListAttribute<Empleado, Ventas> lista_ventas;
    public static volatile SingularAttribute<Empleado, String> direccion;
    public static volatile SingularAttribute<Empleado, Double> sueldo;
    public static volatile SingularAttribute<Empleado, String> nombre;
    public static volatile SingularAttribute<Empleado, Integer> id_empleado;
    public static volatile SingularAttribute<Empleado, String> nacionalidad;
    public static volatile SingularAttribute<Empleado, Date> fecha_nac;
    public static volatile SingularAttribute<Empleado, Usuario> un_usuario;
    public static volatile SingularAttribute<Empleado, String> apellido;
    public static volatile SingularAttribute<Empleado, String> celular;
    public static volatile SingularAttribute<Empleado, String> cargo;
    public static volatile SingularAttribute<Empleado, String> dni;
    public static volatile SingularAttribute<Empleado, String> email;

}
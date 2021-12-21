package logica;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.ServicioTuristico;
import logica.Ventas;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-12-20T00:28:19")
@StaticMetamodel(PaqueteTuristico.class)
public class PaqueteTuristico_ { 

    public static volatile SingularAttribute<PaqueteTuristico, Double> costo_paquete;
    public static volatile SingularAttribute<PaqueteTuristico, Integer> codigo_paquete;
    public static volatile ListAttribute<PaqueteTuristico, ServicioTuristico> lista_servicios_incluidos;
    public static volatile ListAttribute<PaqueteTuristico, Ventas> lista_de_ventas_paquetes;

}
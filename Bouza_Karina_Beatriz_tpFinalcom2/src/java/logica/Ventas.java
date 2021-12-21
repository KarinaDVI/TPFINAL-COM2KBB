
package logica;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Basic;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;

@Entity
public class Ventas implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int num_venta;
    @Temporal(TemporalType.DATE)
    private Date fecha_venta;
    @Basic
    private String medio_pago;
    
    @ManyToOne
    private Cliente un_cliente;
    @ManyToOne
    private ServicioTuristico un_servicio;
    @ManyToOne
    private PaqueteTuristico un_paquete;
    @ManyToOne
    private Empleado un_empleado;

    public Ventas() {
    }
    

    public Ventas(int num_venta, Date fecha_venta, String medio_pago, Cliente un_cliente, ServicioTuristico un_servicio, PaqueteTuristico un_paquete, Empleado un_empleado) {
        this.num_venta = num_venta;
        this.fecha_venta = fecha_venta;
        this.medio_pago = medio_pago;
        this.un_cliente = un_cliente;
        this.un_servicio = un_servicio;
        this.un_paquete = un_paquete;
        this.un_empleado = un_empleado;
    }

    public int getNum_venta() {
        return num_venta;
    }

    public void setNum_venta(int num_venta) {
        this.num_venta = num_venta;
    }

    public String getFecha_venta() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd"); 
        String fecha_venta2 = sdf.format(fecha_venta);
        return fecha_venta2;
    }

    public void setFecha_venta(Date fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    public String getMedio_pago() {
        return medio_pago;
    }

    public void setMedio_pago(String medio_pago) {
        this.medio_pago = medio_pago;
    }

    public Cliente getUn_cliente() {
        return un_cliente;
    }

    public void setUn_cliente(Cliente un_cliente) {
        this.un_cliente = un_cliente;
    }

    public ServicioTuristico getUn_servicio() {  
        return un_servicio;
    }

    public void setUn_servicio(ServicioTuristico un_servicio) {
        this.un_servicio = un_servicio;
    }

    public PaqueteTuristico getUn_paquete() { 
        return un_paquete;
    }

    public void setUn_paquete(PaqueteTuristico un_paquete) {
        this.un_paquete = un_paquete;
    }

    public Empleado getUn_empleado() {
        return un_empleado;
    }

    public void setUn_empleado(Empleado un_empleado) {
        this.un_empleado = un_empleado;
    }
    
    
    
}

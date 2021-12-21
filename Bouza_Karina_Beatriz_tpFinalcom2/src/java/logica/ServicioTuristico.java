package logica;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class ServicioTuristico implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int codigo_servicio;
    @Basic
    private String nombre;
    private String descripcion_breve;
    @ManyToMany
    private List<PaqueteTuristico> lista_paquetes;
    @OneToMany
    public List<Ventas> lista_de_ventas_servicios;
    @Basic
    private String destino_servicio;
    @Temporal(TemporalType.DATE)
    private Date fecha_servicio;
    @Basic
    private double costo_servicio;
    @Basic
    private String disponibilidad;

    public ServicioTuristico() {
    }

    public ServicioTuristico(int codigo_servicio, String nombre, String descripcion_breve, List<PaqueteTuristico> lista_paquetes, List<Ventas> lista_de_ventas_servicios, String destino_servicio, Date fecha_servicio, double costo_servicio,String disponibilidad) {
        this.codigo_servicio = codigo_servicio;
        this.nombre = nombre;
        this.descripcion_breve = descripcion_breve;
        this.lista_paquetes = lista_paquetes;
        this.lista_de_ventas_servicios = lista_de_ventas_servicios;
        this.destino_servicio = destino_servicio;
        this.fecha_servicio = fecha_servicio;
        this.costo_servicio = costo_servicio;
        this.disponibilidad = disponibilidad;
    }

    public int getCodigo_servicio() {
        
        return codigo_servicio;
    }

    public void setCodigo_servicio(int codigo_servicio) {
        this.codigo_servicio = codigo_servicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion_breve() {
        return descripcion_breve;
    }

    public void setDescripcion_breve(String descripcion_breve) {
        this.descripcion_breve = descripcion_breve;
    }

    public List<PaqueteTuristico> getLista_paquetes() {
        return lista_paquetes;
    }

    public void setLista_paquetes(List<PaqueteTuristico> lista_paquetes) {
        this.lista_paquetes = lista_paquetes;
    }

    public List<Ventas> getLista_de_ventas_servicios() {
        return lista_de_ventas_servicios;
    }

    public void setLista_de_ventas_servicios(List<Ventas> lista_de_ventas_servicios) {
        this.lista_de_ventas_servicios = lista_de_ventas_servicios;
    }

    public String getDestino_servicio() {
        return destino_servicio;
    }

    public void setDestino_servicio(String destino_servicio) {
        this.destino_servicio = destino_servicio;
    }

    public Date getFecha_servicio() {
        
        return fecha_servicio;
    }

    public void setFecha_servicio(Date fecha_servicio) {
        this.fecha_servicio = fecha_servicio;
    }

    public double getCosto_servicio() {
        return costo_servicio;
    }

    public void setCosto_servicio(double costo_servicio) {
        this.costo_servicio = costo_servicio;
    }
    
    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }





    
    
    
}

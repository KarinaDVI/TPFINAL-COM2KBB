package logica;

//Mapeado de clase

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class PaqueteTuristico implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int codigo_paquete;
    private double costo_paquete;
    @ManyToMany
    public List<ServicioTuristico> lista_servicios_incluidos;
    @OneToMany
    public List<Ventas> lista_de_ventas_paquetes;

    public PaqueteTuristico() {
    }

    public PaqueteTuristico(int codigo_paquete, double costo_paquete, List<ServicioTuristico> lista_servicios_incluidos, List<Ventas> lista_de_ventas_paquetes) {
        this.codigo_paquete = codigo_paquete;
        this.costo_paquete = costo_paquete;
        this.lista_servicios_incluidos = lista_servicios_incluidos;
        this.lista_de_ventas_paquetes = lista_de_ventas_paquetes;
    }

    public int getCodigo_paquete() {
        return codigo_paquete;
    }

    public void setCodigo_paquete(int codigo_paquete) {
        this.codigo_paquete = codigo_paquete;
    }

    public double getCosto_paquete() {
        return costo_paquete;
    }

    public void setCosto_paquete(double costo_paquete) {
        this.costo_paquete = costo_paquete;
        
    }

    public List<ServicioTuristico> getLista_servicios_incluidos() {
        return lista_servicios_incluidos;
    }

    public void setLista_servicios_incluidos(List<ServicioTuristico> lista_servicios_incluidos) {
        this.lista_servicios_incluidos = lista_servicios_incluidos;
    }

    public List<Ventas> getLista_de_ventas_paquetes() {
        return lista_de_ventas_paquetes;
    }

    public void setLista_de_ventas_paquetes(List<Ventas> lista_de_ventas_paquetes) {
        this.lista_de_ventas_paquetes = lista_de_ventas_paquetes;
    }

    
    
    
    
    
}

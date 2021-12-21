
package logica;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity 
public class Empleado implements Serializable{  
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)    
    private int id_empleado;
    @Basic
    private String nombre;
    private String apellido;
    private String direccion;
    private String dni;
    @Temporal(TemporalType.DATE)
    private Date fecha_nac;
    @Basic
    private String nacionalidad;
    private String celular;
    private String email;
    @Basic
    private String cargo;
    private double sueldo;
    private String estado;
    @OneToOne
    public Usuario un_usuario;
    @OneToMany
    public List <Ventas> lista_ventas;
    
    public Empleado() {
    }

    public Empleado(int id_empleado, String nombre, String apellido, String direccion, String dni, Date fecha_nac, String nacionalidad, String celular, String email, String cargo, double sueldo, String estado, Usuario un_usuario, List<Ventas> lista_ventas) {
        this.id_empleado = id_empleado;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.dni = dni;
        this.fecha_nac = fecha_nac;
        this.nacionalidad = nacionalidad;
        this.celular = celular;
        this.email = email;
        this.cargo = cargo;
        this.sueldo = sueldo;
        this.estado = estado;
        this.un_usuario = un_usuario;
        this.lista_ventas = lista_ventas;
        
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getFecha_nac() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd"); 
    String fecha_nac2 = sdf.format(fecha_nac);
        
        return fecha_nac2;
    }

    public void setFecha_nac(Date fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
        
    }
    public Usuario getUn_usuario() {
        return un_usuario;
    }

    public void setUn_usuario(Usuario un_usuario) {
        this.un_usuario = un_usuario;
    }

    public List<Ventas> getLista_ventas() {
        return lista_ventas;
    }

    public void setLista_ventas(List<Ventas> lista_ventas) {
        this.lista_ventas = lista_ventas;
    }

   

  

    

   
    
    
}

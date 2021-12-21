
package persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.Cliente;
import logica.ServicioTuristico;
import logica.PaqueteTuristico;
import logica.Empleado;
import logica.Ventas;
import persistencia.exceptions.NonexistentEntityException;


public class VentasJpaController implements Serializable {

    public VentasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public VentasJpaController(){
        emf = Persistence.createEntityManagerFactory("Bouza_Karina_Beatriz_tpFinalcom2PU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ventas ventas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente un_cliente = ventas.getUn_cliente();
            if (un_cliente != null) {
                un_cliente = em.getReference(un_cliente.getClass(), un_cliente.getId_cliente());
                ventas.setUn_cliente(un_cliente);
            }
            ServicioTuristico un_servicio = ventas.getUn_servicio();
            if (un_servicio != null) {
                un_servicio = em.getReference(un_servicio.getClass(), un_servicio.getCodigo_servicio());
                ventas.setUn_servicio(un_servicio);
            }
            PaqueteTuristico un_paquete = ventas.getUn_paquete();
            if (un_paquete != null) {
                un_paquete = em.getReference(un_paquete.getClass(), un_paquete.getCodigo_paquete());
                ventas.setUn_paquete(un_paquete);
            }
            Empleado un_empleado = ventas.getUn_empleado();
            if (un_empleado != null) {
                un_empleado = em.getReference(un_empleado.getClass(), un_empleado.getId_empleado());
                ventas.setUn_empleado(un_empleado);
            }
            em.persist(ventas);
            if (un_cliente != null) {
                un_cliente.getLista_ventas().add(ventas);
                un_cliente = em.merge(un_cliente);
            }
            if (un_servicio != null) {
                un_servicio.getLista_de_ventas_servicios().add(ventas);
                un_servicio = em.merge(un_servicio);
            }
            if (un_paquete != null) {
                un_paquete.getLista_de_ventas_paquetes().add(ventas);
                un_paquete = em.merge(un_paquete);
            }
            if (un_empleado != null) {
                un_empleado.getLista_ventas().add(ventas);
                un_empleado = em.merge(un_empleado);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ventas ventas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ventas persistentVentas = em.find(Ventas.class, ventas.getNum_venta());
            Cliente un_clienteOld = persistentVentas.getUn_cliente();
            Cliente un_clienteNew = ventas.getUn_cliente();
            ServicioTuristico un_servicioOld = persistentVentas.getUn_servicio();
            ServicioTuristico un_servicioNew = ventas.getUn_servicio();
            PaqueteTuristico un_paqueteOld = persistentVentas.getUn_paquete();
            PaqueteTuristico un_paqueteNew = ventas.getUn_paquete();
            Empleado un_empleadoOld = persistentVentas.getUn_empleado();
            Empleado un_empleadoNew = ventas.getUn_empleado();
            if (un_clienteNew != null) {
                un_clienteNew = em.getReference(un_clienteNew.getClass(), un_clienteNew.getId_cliente());
                ventas.setUn_cliente(un_clienteNew);
            }
            if (un_servicioNew != null) {
                un_servicioNew = em.getReference(un_servicioNew.getClass(), un_servicioNew.getCodigo_servicio());
                ventas.setUn_servicio(un_servicioNew);
            }
            if (un_paqueteNew != null) {
                un_paqueteNew = em.getReference(un_paqueteNew.getClass(), un_paqueteNew.getCodigo_paquete());
                ventas.setUn_paquete(un_paqueteNew);
            }
            if (un_empleadoNew != null) {
                un_empleadoNew = em.getReference(un_empleadoNew.getClass(), un_empleadoNew.getId_empleado());
                ventas.setUn_empleado(un_empleadoNew);
            }
            ventas = em.merge(ventas);
            if (un_clienteOld != null && !un_clienteOld.equals(un_clienteNew)) {
                un_clienteOld.getLista_ventas().remove(ventas);
                un_clienteOld = em.merge(un_clienteOld);
            }
            if (un_clienteNew != null && !un_clienteNew.equals(un_clienteOld)) {
                un_clienteNew.getLista_ventas().add(ventas);
                un_clienteNew = em.merge(un_clienteNew);
            }
            if (un_servicioOld != null && !un_servicioOld.equals(un_servicioNew)) {
                un_servicioOld.getLista_de_ventas_servicios().remove(ventas);
                un_servicioOld = em.merge(un_servicioOld);
            }
            if (un_servicioNew != null && !un_servicioNew.equals(un_servicioOld)) {
                un_servicioNew.getLista_de_ventas_servicios().add(ventas);
                un_servicioNew = em.merge(un_servicioNew);
            }
            if (un_paqueteOld != null && !un_paqueteOld.equals(un_paqueteNew)) {
                un_paqueteOld.getLista_de_ventas_paquetes().remove(ventas);
                un_paqueteOld = em.merge(un_paqueteOld);
            }
            if (un_paqueteNew != null && !un_paqueteNew.equals(un_paqueteOld)) {
                un_paqueteNew.getLista_de_ventas_paquetes().add(ventas);
                un_paqueteNew = em.merge(un_paqueteNew);
            }
            if (un_empleadoOld != null && !un_empleadoOld.equals(un_empleadoNew)) {
                un_empleadoOld.getLista_ventas().remove(ventas);
                un_empleadoOld = em.merge(un_empleadoOld);
            }
            if (un_empleadoNew != null && !un_empleadoNew.equals(un_empleadoOld)) {
                un_empleadoNew.getLista_ventas().add(ventas);
                un_empleadoNew = em.merge(un_empleadoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = ventas.getNum_venta();
                if (findVentas(id) == null) {
                    throw new NonexistentEntityException("The ventas with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ventas ventas;
            try {
                ventas = em.getReference(Ventas.class, id);
                ventas.getNum_venta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ventas with id " + id + " no longer exists.", enfe);
            }
            Cliente un_cliente = ventas.getUn_cliente();
            if (un_cliente != null) {
                un_cliente.getLista_ventas().remove(ventas);
                un_cliente = em.merge(un_cliente);
            }
            ServicioTuristico un_servicio = ventas.getUn_servicio();
            if (un_servicio != null) {
                un_servicio.getLista_de_ventas_servicios().remove(ventas);
                un_servicio = em.merge(un_servicio);
            }
            PaqueteTuristico un_paquete = ventas.getUn_paquete();
            if (un_paquete != null) {
                un_paquete.getLista_de_ventas_paquetes().remove(ventas);
                un_paquete = em.merge(un_paquete);
            }
            Empleado un_empleado = ventas.getUn_empleado();
            if (un_empleado != null) {
                un_empleado.getLista_ventas().remove(ventas);
                un_empleado = em.merge(un_empleado);
            }
            em.remove(ventas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ventas> findVentasEntities() {
        return findVentasEntities(true, -1, -1);
    }

    public List<Ventas> findVentasEntities(int maxResults, int firstResult) {
        return findVentasEntities(false, maxResults, firstResult);
    }

    private List<Ventas> findVentasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ventas.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Ventas findVentas(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ventas.class, id);
        } finally {
            em.close();
        }
    }

    public int getVentasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ventas> rt = cq.from(Ventas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

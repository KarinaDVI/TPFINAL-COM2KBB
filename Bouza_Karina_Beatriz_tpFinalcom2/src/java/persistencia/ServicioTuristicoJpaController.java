
package persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.PaqueteTuristico;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import logica.ServicioTuristico;
import logica.Ventas;
import persistencia.exceptions.NonexistentEntityException;


public class ServicioTuristicoJpaController implements Serializable {

    
    public ServicioTuristicoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public ServicioTuristicoJpaController(){
        emf = Persistence.createEntityManagerFactory("Bouza_Karina_Beatriz_tpFinalcom2PU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ServicioTuristico servicioTuristico) {
        if (servicioTuristico.getLista_paquetes() == null) {
            servicioTuristico.setLista_paquetes(new ArrayList<PaqueteTuristico>());
        }
        if (servicioTuristico.getLista_de_ventas_servicios() == null) {
            servicioTuristico.setLista_de_ventas_servicios(new ArrayList<Ventas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<PaqueteTuristico> attachedLista_paquetes = new ArrayList<PaqueteTuristico>();
            for (PaqueteTuristico lista_paquetesPaqueteTuristicoToAttach : servicioTuristico.getLista_paquetes()) {
                lista_paquetesPaqueteTuristicoToAttach = em.getReference(lista_paquetesPaqueteTuristicoToAttach.getClass(), lista_paquetesPaqueteTuristicoToAttach.getCodigo_paquete());
                attachedLista_paquetes.add(lista_paquetesPaqueteTuristicoToAttach);
            }
            servicioTuristico.setLista_paquetes(attachedLista_paquetes);
            List<Ventas> attachedLista_de_ventas_servicios = new ArrayList<Ventas>();
            for (Ventas lista_de_ventas_serviciosVentasToAttach : servicioTuristico.getLista_de_ventas_servicios()) {
                lista_de_ventas_serviciosVentasToAttach = em.getReference(lista_de_ventas_serviciosVentasToAttach.getClass(), lista_de_ventas_serviciosVentasToAttach.getNum_venta());
                attachedLista_de_ventas_servicios.add(lista_de_ventas_serviciosVentasToAttach);
            }
            servicioTuristico.setLista_de_ventas_servicios(attachedLista_de_ventas_servicios);
            em.persist(servicioTuristico);
            for (PaqueteTuristico lista_paquetesPaqueteTuristico : servicioTuristico.getLista_paquetes()) {
                lista_paquetesPaqueteTuristico.getLista_servicios_incluidos().add(servicioTuristico);
                lista_paquetesPaqueteTuristico = em.merge(lista_paquetesPaqueteTuristico);
            }
            for (Ventas lista_de_ventas_serviciosVentas : servicioTuristico.getLista_de_ventas_servicios()) {
                ServicioTuristico oldUn_servicioOfLista_de_ventas_serviciosVentas = lista_de_ventas_serviciosVentas.getUn_servicio();
                lista_de_ventas_serviciosVentas.setUn_servicio(servicioTuristico);
                lista_de_ventas_serviciosVentas = em.merge(lista_de_ventas_serviciosVentas);
                if (oldUn_servicioOfLista_de_ventas_serviciosVentas != null) {
                    oldUn_servicioOfLista_de_ventas_serviciosVentas.getLista_de_ventas_servicios().remove(lista_de_ventas_serviciosVentas);
                    oldUn_servicioOfLista_de_ventas_serviciosVentas = em.merge(oldUn_servicioOfLista_de_ventas_serviciosVentas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ServicioTuristico servicioTuristico) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ServicioTuristico persistentServicioTuristico = em.find(ServicioTuristico.class, servicioTuristico.getCodigo_servicio());
            List<PaqueteTuristico> lista_paquetesOld = persistentServicioTuristico.getLista_paquetes();
            List<PaqueteTuristico> lista_paquetesNew = servicioTuristico.getLista_paquetes();
            List<Ventas> lista_de_ventas_serviciosOld = persistentServicioTuristico.getLista_de_ventas_servicios();
            List<Ventas> lista_de_ventas_serviciosNew = servicioTuristico.getLista_de_ventas_servicios();
            List<PaqueteTuristico> attachedLista_paquetesNew = new ArrayList<PaqueteTuristico>();
            for (PaqueteTuristico lista_paquetesNewPaqueteTuristicoToAttach : lista_paquetesNew) {
                lista_paquetesNewPaqueteTuristicoToAttach = em.getReference(lista_paquetesNewPaqueteTuristicoToAttach.getClass(), lista_paquetesNewPaqueteTuristicoToAttach.getCodigo_paquete());
                attachedLista_paquetesNew.add(lista_paquetesNewPaqueteTuristicoToAttach);
            }
            lista_paquetesNew = attachedLista_paquetesNew;
            servicioTuristico.setLista_paquetes(lista_paquetesNew);
            List<Ventas> attachedLista_de_ventas_serviciosNew = new ArrayList<Ventas>();
            for (Ventas lista_de_ventas_serviciosNewVentasToAttach : lista_de_ventas_serviciosNew) {
                lista_de_ventas_serviciosNewVentasToAttach = em.getReference(lista_de_ventas_serviciosNewVentasToAttach.getClass(), lista_de_ventas_serviciosNewVentasToAttach.getNum_venta());
                attachedLista_de_ventas_serviciosNew.add(lista_de_ventas_serviciosNewVentasToAttach);
            }
            lista_de_ventas_serviciosNew = attachedLista_de_ventas_serviciosNew;
            servicioTuristico.setLista_de_ventas_servicios(lista_de_ventas_serviciosNew);
            servicioTuristico = em.merge(servicioTuristico);
            for (PaqueteTuristico lista_paquetesOldPaqueteTuristico : lista_paquetesOld) {
                if (!lista_paquetesNew.contains(lista_paquetesOldPaqueteTuristico)) {
                    lista_paquetesOldPaqueteTuristico.getLista_servicios_incluidos().remove(servicioTuristico);
                    lista_paquetesOldPaqueteTuristico = em.merge(lista_paquetesOldPaqueteTuristico);
                }
            }
            for (PaqueteTuristico lista_paquetesNewPaqueteTuristico : lista_paquetesNew) {
                if (!lista_paquetesOld.contains(lista_paquetesNewPaqueteTuristico)) {
                    lista_paquetesNewPaqueteTuristico.getLista_servicios_incluidos().add(servicioTuristico);
                    lista_paquetesNewPaqueteTuristico = em.merge(lista_paquetesNewPaqueteTuristico);
                }
            }
            for (Ventas lista_de_ventas_serviciosOldVentas : lista_de_ventas_serviciosOld) {
                if (!lista_de_ventas_serviciosNew.contains(lista_de_ventas_serviciosOldVentas)) {
                    lista_de_ventas_serviciosOldVentas.setUn_servicio(null);
                    lista_de_ventas_serviciosOldVentas = em.merge(lista_de_ventas_serviciosOldVentas);
                }
            }
            for (Ventas lista_de_ventas_serviciosNewVentas : lista_de_ventas_serviciosNew) {
                if (!lista_de_ventas_serviciosOld.contains(lista_de_ventas_serviciosNewVentas)) {
                    ServicioTuristico oldUn_servicioOfLista_de_ventas_serviciosNewVentas = lista_de_ventas_serviciosNewVentas.getUn_servicio();
                    lista_de_ventas_serviciosNewVentas.setUn_servicio(servicioTuristico);
                    lista_de_ventas_serviciosNewVentas = em.merge(lista_de_ventas_serviciosNewVentas);
                    if (oldUn_servicioOfLista_de_ventas_serviciosNewVentas != null && !oldUn_servicioOfLista_de_ventas_serviciosNewVentas.equals(servicioTuristico)) {
                        oldUn_servicioOfLista_de_ventas_serviciosNewVentas.getLista_de_ventas_servicios().remove(lista_de_ventas_serviciosNewVentas);
                        oldUn_servicioOfLista_de_ventas_serviciosNewVentas = em.merge(oldUn_servicioOfLista_de_ventas_serviciosNewVentas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = servicioTuristico.getCodigo_servicio();
                if (findServicioTuristico(id) == null) {
                    throw new NonexistentEntityException("The servicioTuristico with id " + id + " no longer exists.");
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
            ServicioTuristico servicioTuristico;
            try {
                servicioTuristico = em.getReference(ServicioTuristico.class, id);
                servicioTuristico.getCodigo_servicio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The servicioTuristico with id " + id + " no longer exists.", enfe);
            }
            List<PaqueteTuristico> lista_paquetes = servicioTuristico.getLista_paquetes();
            for (PaqueteTuristico lista_paquetesPaqueteTuristico : lista_paquetes) {
                lista_paquetesPaqueteTuristico.getLista_servicios_incluidos().remove(servicioTuristico);
                lista_paquetesPaqueteTuristico = em.merge(lista_paquetesPaqueteTuristico);
            }
            List<Ventas> lista_de_ventas_servicios = servicioTuristico.getLista_de_ventas_servicios();
            for (Ventas lista_de_ventas_serviciosVentas : lista_de_ventas_servicios) {
                lista_de_ventas_serviciosVentas.setUn_servicio(null);
                lista_de_ventas_serviciosVentas = em.merge(lista_de_ventas_serviciosVentas);
            }
            em.remove(servicioTuristico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ServicioTuristico> findServicioTuristicoEntities() {
        return findServicioTuristicoEntities(true, -1, -1);
    }

    public List<ServicioTuristico> findServicioTuristicoEntities(int maxResults, int firstResult) {
        return findServicioTuristicoEntities(false, maxResults, firstResult);
    }

    private List<ServicioTuristico> findServicioTuristicoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ServicioTuristico.class));
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

    public ServicioTuristico findServicioTuristico(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ServicioTuristico.class, id);
        } finally {
            em.close();
        }
    }

    public int getServicioTuristicoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ServicioTuristico> rt = cq.from(ServicioTuristico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

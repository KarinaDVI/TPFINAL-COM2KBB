
package persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.ServicioTuristico;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import logica.PaqueteTuristico;
import logica.Ventas;
import persistencia.exceptions.NonexistentEntityException;


public class PaqueteTuristicoJpaController implements Serializable {

    public PaqueteTuristicoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public PaqueteTuristicoJpaController(){
        emf = Persistence.createEntityManagerFactory("Bouza_Karina_Beatriz_tpFinalcom2PU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PaqueteTuristico paqueteTuristico) {
        if (paqueteTuristico.getLista_servicios_incluidos() == null) {
            paqueteTuristico.setLista_servicios_incluidos(new ArrayList<ServicioTuristico>());
        }
        if (paqueteTuristico.getLista_de_ventas_paquetes() == null) {
            paqueteTuristico.setLista_de_ventas_paquetes(new ArrayList<Ventas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<ServicioTuristico> attachedLista_servicios_incluidos = new ArrayList<ServicioTuristico>();
            for (ServicioTuristico lista_servicios_incluidosServicioTuristicoToAttach : paqueteTuristico.getLista_servicios_incluidos()) {
                lista_servicios_incluidosServicioTuristicoToAttach = em.getReference(lista_servicios_incluidosServicioTuristicoToAttach.getClass(), lista_servicios_incluidosServicioTuristicoToAttach.getCodigo_servicio());
                attachedLista_servicios_incluidos.add(lista_servicios_incluidosServicioTuristicoToAttach);
            }
            paqueteTuristico.setLista_servicios_incluidos(attachedLista_servicios_incluidos);
            List<Ventas> attachedLista_de_ventas_paquetes = new ArrayList<Ventas>();
            for (Ventas lista_de_ventas_paquetesVentasToAttach : paqueteTuristico.getLista_de_ventas_paquetes()) {
                lista_de_ventas_paquetesVentasToAttach = em.getReference(lista_de_ventas_paquetesVentasToAttach.getClass(), lista_de_ventas_paquetesVentasToAttach.getNum_venta());
                attachedLista_de_ventas_paquetes.add(lista_de_ventas_paquetesVentasToAttach);
            }
            paqueteTuristico.setLista_de_ventas_paquetes(attachedLista_de_ventas_paquetes);
            em.persist(paqueteTuristico);
            for (ServicioTuristico lista_servicios_incluidosServicioTuristico : paqueteTuristico.getLista_servicios_incluidos()) {
                lista_servicios_incluidosServicioTuristico.getLista_paquetes().add(paqueteTuristico);
                lista_servicios_incluidosServicioTuristico = em.merge(lista_servicios_incluidosServicioTuristico);
            }
            for (Ventas lista_de_ventas_paquetesVentas : paqueteTuristico.getLista_de_ventas_paquetes()) {
                PaqueteTuristico oldUn_paqueteOfLista_de_ventas_paquetesVentas = lista_de_ventas_paquetesVentas.getUn_paquete();
                lista_de_ventas_paquetesVentas.setUn_paquete(paqueteTuristico);
                lista_de_ventas_paquetesVentas = em.merge(lista_de_ventas_paquetesVentas);
                if (oldUn_paqueteOfLista_de_ventas_paquetesVentas != null) {
                    oldUn_paqueteOfLista_de_ventas_paquetesVentas.getLista_de_ventas_paquetes().remove(lista_de_ventas_paquetesVentas);
                    oldUn_paqueteOfLista_de_ventas_paquetesVentas = em.merge(oldUn_paqueteOfLista_de_ventas_paquetesVentas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PaqueteTuristico paqueteTuristico) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PaqueteTuristico persistentPaqueteTuristico = em.find(PaqueteTuristico.class, paqueteTuristico.getCodigo_paquete());
            List<ServicioTuristico> lista_servicios_incluidosOld = persistentPaqueteTuristico.getLista_servicios_incluidos();
            List<ServicioTuristico> lista_servicios_incluidosNew = paqueteTuristico.getLista_servicios_incluidos();
            List<Ventas> lista_de_ventas_paquetesOld = persistentPaqueteTuristico.getLista_de_ventas_paquetes();
            List<Ventas> lista_de_ventas_paquetesNew = paqueteTuristico.getLista_de_ventas_paquetes();
            List<ServicioTuristico> attachedLista_servicios_incluidosNew = new ArrayList<ServicioTuristico>();
            for (ServicioTuristico lista_servicios_incluidosNewServicioTuristicoToAttach : lista_servicios_incluidosNew) {
                lista_servicios_incluidosNewServicioTuristicoToAttach = em.getReference(lista_servicios_incluidosNewServicioTuristicoToAttach.getClass(), lista_servicios_incluidosNewServicioTuristicoToAttach.getCodigo_servicio());
                attachedLista_servicios_incluidosNew.add(lista_servicios_incluidosNewServicioTuristicoToAttach);
            }
            lista_servicios_incluidosNew = attachedLista_servicios_incluidosNew;
            paqueteTuristico.setLista_servicios_incluidos(lista_servicios_incluidosNew);
            List<Ventas> attachedLista_de_ventas_paquetesNew = new ArrayList<Ventas>();
            for (Ventas lista_de_ventas_paquetesNewVentasToAttach : lista_de_ventas_paquetesNew) {
                lista_de_ventas_paquetesNewVentasToAttach = em.getReference(lista_de_ventas_paquetesNewVentasToAttach.getClass(), lista_de_ventas_paquetesNewVentasToAttach.getNum_venta());
                attachedLista_de_ventas_paquetesNew.add(lista_de_ventas_paquetesNewVentasToAttach);
            }
            lista_de_ventas_paquetesNew = attachedLista_de_ventas_paquetesNew;
            paqueteTuristico.setLista_de_ventas_paquetes(lista_de_ventas_paquetesNew);
            paqueteTuristico = em.merge(paqueteTuristico);
            for (ServicioTuristico lista_servicios_incluidosOldServicioTuristico : lista_servicios_incluidosOld) {
                if (!lista_servicios_incluidosNew.contains(lista_servicios_incluidosOldServicioTuristico)) {
                    lista_servicios_incluidosOldServicioTuristico.getLista_paquetes().remove(paqueteTuristico);
                    lista_servicios_incluidosOldServicioTuristico = em.merge(lista_servicios_incluidosOldServicioTuristico);
                }
            }
            for (ServicioTuristico lista_servicios_incluidosNewServicioTuristico : lista_servicios_incluidosNew) {
                if (!lista_servicios_incluidosOld.contains(lista_servicios_incluidosNewServicioTuristico)) {
                    lista_servicios_incluidosNewServicioTuristico.getLista_paquetes().add(paqueteTuristico);
                    lista_servicios_incluidosNewServicioTuristico = em.merge(lista_servicios_incluidosNewServicioTuristico);
                }
            }
            for (Ventas lista_de_ventas_paquetesOldVentas : lista_de_ventas_paquetesOld) {
                if (!lista_de_ventas_paquetesNew.contains(lista_de_ventas_paquetesOldVentas)) {
                    lista_de_ventas_paquetesOldVentas.setUn_paquete(null);
                    lista_de_ventas_paquetesOldVentas = em.merge(lista_de_ventas_paquetesOldVentas);
                }
            }
            for (Ventas lista_de_ventas_paquetesNewVentas : lista_de_ventas_paquetesNew) {
                if (!lista_de_ventas_paquetesOld.contains(lista_de_ventas_paquetesNewVentas)) {
                    PaqueteTuristico oldUn_paqueteOfLista_de_ventas_paquetesNewVentas = lista_de_ventas_paquetesNewVentas.getUn_paquete();
                    lista_de_ventas_paquetesNewVentas.setUn_paquete(paqueteTuristico);
                    lista_de_ventas_paquetesNewVentas = em.merge(lista_de_ventas_paquetesNewVentas);
                    if (oldUn_paqueteOfLista_de_ventas_paquetesNewVentas != null && !oldUn_paqueteOfLista_de_ventas_paquetesNewVentas.equals(paqueteTuristico)) {
                        oldUn_paqueteOfLista_de_ventas_paquetesNewVentas.getLista_de_ventas_paquetes().remove(lista_de_ventas_paquetesNewVentas);
                        oldUn_paqueteOfLista_de_ventas_paquetesNewVentas = em.merge(oldUn_paqueteOfLista_de_ventas_paquetesNewVentas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = paqueteTuristico.getCodigo_paquete();
                if (findPaqueteTuristico(id) == null) {
                    throw new NonexistentEntityException("The paqueteTuristico with id " + id + " no longer exists.");
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
            PaqueteTuristico paqueteTuristico;
            try {
                paqueteTuristico = em.getReference(PaqueteTuristico.class, id);
                paqueteTuristico.getCodigo_paquete();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The paqueteTuristico with id " + id + " no longer exists.", enfe);
            }
            List<ServicioTuristico> lista_servicios_incluidos = paqueteTuristico.getLista_servicios_incluidos();
            for (ServicioTuristico lista_servicios_incluidosServicioTuristico : lista_servicios_incluidos) {
                lista_servicios_incluidosServicioTuristico.getLista_paquetes().remove(paqueteTuristico);
                lista_servicios_incluidosServicioTuristico = em.merge(lista_servicios_incluidosServicioTuristico);
            }
            List<Ventas> lista_de_ventas_paquetes = paqueteTuristico.getLista_de_ventas_paquetes();
            for (Ventas lista_de_ventas_paquetesVentas : lista_de_ventas_paquetes) {
                lista_de_ventas_paquetesVentas.setUn_paquete(null);
                lista_de_ventas_paquetesVentas = em.merge(lista_de_ventas_paquetesVentas);
            }
            em.remove(paqueteTuristico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PaqueteTuristico> findPaqueteTuristicoEntities() {
        return findPaqueteTuristicoEntities(true, -1, -1);
    }

    public List<PaqueteTuristico> findPaqueteTuristicoEntities(int maxResults, int firstResult) {
        return findPaqueteTuristicoEntities(false, maxResults, firstResult);
    }

    private List<PaqueteTuristico> findPaqueteTuristicoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PaqueteTuristico.class));
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

    public PaqueteTuristico findPaqueteTuristico(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PaqueteTuristico.class, id);
        } finally {
            em.close();
        }
    }

    public int getPaqueteTuristicoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PaqueteTuristico> rt = cq.from(PaqueteTuristico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

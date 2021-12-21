
package persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.Ventas;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import logica.Empleado;
import persistencia.exceptions.NonexistentEntityException;


public class EmpleadoJpaController implements Serializable {

    public EmpleadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public EmpleadoJpaController(){
        emf = Persistence.createEntityManagerFactory("Bouza_Karina_Beatriz_tpFinalcom2PU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Empleado empleado) {
        if (empleado.getLista_ventas() == null) {
            empleado.setLista_ventas(new ArrayList<Ventas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Ventas> attachedLista_ventas = new ArrayList<Ventas>();
            for (Ventas lista_ventasVentasToAttach : empleado.getLista_ventas()) {
                lista_ventasVentasToAttach = em.getReference(lista_ventasVentasToAttach.getClass(), lista_ventasVentasToAttach.getNum_venta());
                attachedLista_ventas.add(lista_ventasVentasToAttach);
            }
            empleado.setLista_ventas(attachedLista_ventas);
            em.persist(empleado);
            for (Ventas lista_ventasVentas : empleado.getLista_ventas()) {
                Empleado oldUn_empleadoOfLista_ventasVentas = lista_ventasVentas.getUn_empleado();
                lista_ventasVentas.setUn_empleado(empleado);
                lista_ventasVentas = em.merge(lista_ventasVentas);
                if (oldUn_empleadoOfLista_ventasVentas != null) {
                    oldUn_empleadoOfLista_ventasVentas.getLista_ventas().remove(lista_ventasVentas);
                    oldUn_empleadoOfLista_ventasVentas = em.merge(oldUn_empleadoOfLista_ventasVentas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Empleado empleado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado persistentEmpleado = em.find(Empleado.class, empleado.getId_empleado());
            List<Ventas> lista_ventasOld = persistentEmpleado.getLista_ventas();
            List<Ventas> lista_ventasNew = empleado.getLista_ventas();
            List<Ventas> attachedLista_ventasNew = new ArrayList<Ventas>();
            for (Ventas lista_ventasNewVentasToAttach : lista_ventasNew) {
                lista_ventasNewVentasToAttach = em.getReference(lista_ventasNewVentasToAttach.getClass(), lista_ventasNewVentasToAttach.getNum_venta());
                attachedLista_ventasNew.add(lista_ventasNewVentasToAttach);
            }
            lista_ventasNew = attachedLista_ventasNew;
            empleado.setLista_ventas(lista_ventasNew);
            empleado = em.merge(empleado);
            for (Ventas lista_ventasOldVentas : lista_ventasOld) {
                if (!lista_ventasNew.contains(lista_ventasOldVentas)) {
                    lista_ventasOldVentas.setUn_empleado(null);
                    lista_ventasOldVentas = em.merge(lista_ventasOldVentas);
                }
            }
            for (Ventas lista_ventasNewVentas : lista_ventasNew) {
                if (!lista_ventasOld.contains(lista_ventasNewVentas)) {
                    Empleado oldUn_empleadoOfLista_ventasNewVentas = lista_ventasNewVentas.getUn_empleado();
                    lista_ventasNewVentas.setUn_empleado(empleado);
                    lista_ventasNewVentas = em.merge(lista_ventasNewVentas);
                    if (oldUn_empleadoOfLista_ventasNewVentas != null && !oldUn_empleadoOfLista_ventasNewVentas.equals(empleado)) {
                        oldUn_empleadoOfLista_ventasNewVentas.getLista_ventas().remove(lista_ventasNewVentas);
                        oldUn_empleadoOfLista_ventasNewVentas = em.merge(oldUn_empleadoOfLista_ventasNewVentas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = empleado.getId_empleado();
                if (findEmpleado(id) == null) {
                    throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.");
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
            Empleado empleado;
            try {
                empleado = em.getReference(Empleado.class, id);
                empleado.getId_empleado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.", enfe);
            }
            List<Ventas> lista_ventas = empleado.getLista_ventas();
            for (Ventas lista_ventasVentas : lista_ventas) {
                lista_ventasVentas.setUn_empleado(null);
                lista_ventasVentas = em.merge(lista_ventasVentas);
            }
            em.remove(empleado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Empleado> findEmpleadoEntities() {
        return findEmpleadoEntities(true, -1, -1);
    }

    public List<Empleado> findEmpleadoEntities(int maxResults, int firstResult) {
        return findEmpleadoEntities(false, maxResults, firstResult);
    }

    private List<Empleado> findEmpleadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Empleado.class));
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

    public Empleado findEmpleado(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Empleado.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmpleadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Empleado> rt = cq.from(Empleado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

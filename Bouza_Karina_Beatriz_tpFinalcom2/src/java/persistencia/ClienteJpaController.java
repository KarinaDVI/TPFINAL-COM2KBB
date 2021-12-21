
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
import logica.Cliente;
import persistencia.exceptions.NonexistentEntityException;


public class ClienteJpaController implements Serializable {

    public ClienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public ClienteJpaController(){
        emf = Persistence.createEntityManagerFactory("Bouza_Karina_Beatriz_tpFinalcom2PU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cliente cliente) {
        if (cliente.getLista_ventas() == null) {
            cliente.setLista_ventas(new ArrayList<Ventas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Ventas> attachedLista_ventas = new ArrayList<Ventas>();
            for (Ventas lista_ventasVentasToAttach : cliente.getLista_ventas()) {
                lista_ventasVentasToAttach = em.getReference(lista_ventasVentasToAttach.getClass(), lista_ventasVentasToAttach.getNum_venta());
                attachedLista_ventas.add(lista_ventasVentasToAttach);
            }
            cliente.setLista_ventas(attachedLista_ventas);
            em.persist(cliente);
            for (Ventas lista_ventasVentas : cliente.getLista_ventas()) {
                Cliente oldUn_clienteOfLista_ventasVentas = lista_ventasVentas.getUn_cliente();
                lista_ventasVentas.setUn_cliente(cliente);
                lista_ventasVentas = em.merge(lista_ventasVentas);
                if (oldUn_clienteOfLista_ventasVentas != null) {
                    oldUn_clienteOfLista_ventasVentas.getLista_ventas().remove(lista_ventasVentas);
                    oldUn_clienteOfLista_ventasVentas = em.merge(oldUn_clienteOfLista_ventasVentas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cliente cliente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente persistentCliente = em.find(Cliente.class, cliente.getId_cliente());
            List<Ventas> lista_ventasOld = persistentCliente.getLista_ventas();
            List<Ventas> lista_ventasNew = cliente.getLista_ventas();
            List<Ventas> attachedLista_ventasNew = new ArrayList<Ventas>();
            for (Ventas lista_ventasNewVentasToAttach : lista_ventasNew) {
                lista_ventasNewVentasToAttach = em.getReference(lista_ventasNewVentasToAttach.getClass(), lista_ventasNewVentasToAttach.getNum_venta());
                attachedLista_ventasNew.add(lista_ventasNewVentasToAttach);
            }
            lista_ventasNew = attachedLista_ventasNew;
            cliente.setLista_ventas(lista_ventasNew);
            cliente = em.merge(cliente);
            for (Ventas lista_ventasOldVentas : lista_ventasOld) {
                if (!lista_ventasNew.contains(lista_ventasOldVentas)) {
                    lista_ventasOldVentas.setUn_cliente(null);
                    lista_ventasOldVentas = em.merge(lista_ventasOldVentas);
                }
            }
            for (Ventas lista_ventasNewVentas : lista_ventasNew) {
                if (!lista_ventasOld.contains(lista_ventasNewVentas)) {
                    Cliente oldUn_clienteOfLista_ventasNewVentas = lista_ventasNewVentas.getUn_cliente();
                    lista_ventasNewVentas.setUn_cliente(cliente);
                    lista_ventasNewVentas = em.merge(lista_ventasNewVentas);
                    if (oldUn_clienteOfLista_ventasNewVentas != null && !oldUn_clienteOfLista_ventasNewVentas.equals(cliente)) {
                        oldUn_clienteOfLista_ventasNewVentas.getLista_ventas().remove(lista_ventasNewVentas);
                        oldUn_clienteOfLista_ventasNewVentas = em.merge(oldUn_clienteOfLista_ventasNewVentas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = cliente.getId_cliente();
                if (findCliente(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
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
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getId_cliente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            List<Ventas> lista_ventas = cliente.getLista_ventas();
            for (Ventas lista_ventasVentas : lista_ventas) {
                lista_ventasVentas.setUn_cliente(null);
                lista_ventasVentas = em.merge(lista_ventasVentas);
            }
            em.remove(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cliente> findClienteEntities() {
        return findClienteEntities(true, -1, -1);
    }

    public List<Cliente> findClienteEntities(int maxResults, int firstResult) {
        return findClienteEntities(false, maxResults, firstResult);
    }

    private List<Cliente> findClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cliente.class));
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

    public Cliente findCliente(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cliente> rt = cq.from(Cliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

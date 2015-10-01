/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maquila.mservices.controller;

import com.maquila.mservices.controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.maquila.mservices.entities.Cliente;
import java.util.ArrayList;
import java.util.List;
import com.maquila.mservices.entities.Empleado;
import com.maquila.mservices.entities.EncabezadoServicio;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Alex
 */
public class EncabezadoServicioJpaController implements Serializable {

    public EncabezadoServicioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EncabezadoServicio encabezadoServicio) {
        if (encabezadoServicio.getCliente() == null) {
            encabezadoServicio.setCliente(new ArrayList<Cliente>());
        }
        if (encabezadoServicio.getEmpleado() == null) {
            encabezadoServicio.setEmpleado(new ArrayList<Empleado>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Cliente> attachedCliente = new ArrayList<Cliente>();
            for (Cliente clienteClienteToAttach : encabezadoServicio.getCliente()) {
                clienteClienteToAttach = em.getReference(clienteClienteToAttach.getClass(), clienteClienteToAttach.getId());
                attachedCliente.add(clienteClienteToAttach);
            }
            encabezadoServicio.setCliente(attachedCliente);
            List<Empleado> attachedEmpleado = new ArrayList<Empleado>();
            for (Empleado empleadoEmpleadoToAttach : encabezadoServicio.getEmpleado()) {
                empleadoEmpleadoToAttach = em.getReference(empleadoEmpleadoToAttach.getClass(), empleadoEmpleadoToAttach.getId());
                attachedEmpleado.add(empleadoEmpleadoToAttach);
            }
            encabezadoServicio.setEmpleado(attachedEmpleado);
            em.persist(encabezadoServicio);
            for (Cliente clienteCliente : encabezadoServicio.getCliente()) {
                clienteCliente.getId().add(encabezadoServicio);
                clienteCliente = em.merge(clienteCliente);
            }
            for (Empleado empleadoEmpleado : encabezadoServicio.getEmpleado()) {
                empleadoEmpleado.getId().add(encabezadoServicio);
                empleadoEmpleado = em.merge(empleadoEmpleado);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EncabezadoServicio encabezadoServicio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EncabezadoServicio persistentEncabezadoServicio = em.find(EncabezadoServicio.class, encabezadoServicio.getId());
            List<Cliente> clienteOld = persistentEncabezadoServicio.getCliente();
            List<Cliente> clienteNew = encabezadoServicio.getCliente();
            List<Empleado> empleadoOld = persistentEncabezadoServicio.getEmpleado();
            List<Empleado> empleadoNew = encabezadoServicio.getEmpleado();
            List<Cliente> attachedClienteNew = new ArrayList<Cliente>();
            for (Cliente clienteNewClienteToAttach : clienteNew) {
                clienteNewClienteToAttach = em.getReference(clienteNewClienteToAttach.getClass(), clienteNewClienteToAttach.getId());
                attachedClienteNew.add(clienteNewClienteToAttach);
            }
            clienteNew = attachedClienteNew;
            encabezadoServicio.setCliente(clienteNew);
            List<Empleado> attachedEmpleadoNew = new ArrayList<Empleado>();
            for (Empleado empleadoNewEmpleadoToAttach : empleadoNew) {
                empleadoNewEmpleadoToAttach = em.getReference(empleadoNewEmpleadoToAttach.getClass(), empleadoNewEmpleadoToAttach.getId());
                attachedEmpleadoNew.add(empleadoNewEmpleadoToAttach);
            }
            empleadoNew = attachedEmpleadoNew;
            encabezadoServicio.setEmpleado(empleadoNew);
            encabezadoServicio = em.merge(encabezadoServicio);
            for (Cliente clienteOldCliente : clienteOld) {
                if (!clienteNew.contains(clienteOldCliente)) {
                    clienteOldCliente.getId().remove(encabezadoServicio);
                    clienteOldCliente = em.merge(clienteOldCliente);
                }
            }
            for (Cliente clienteNewCliente : clienteNew) {
                if (!clienteOld.contains(clienteNewCliente)) {
                    clienteNewCliente.getId().add(encabezadoServicio);
                    clienteNewCliente = em.merge(clienteNewCliente);
                }
            }
            for (Empleado empleadoOldEmpleado : empleadoOld) {
                if (!empleadoNew.contains(empleadoOldEmpleado)) {
                    empleadoOldEmpleado.getId().remove(encabezadoServicio);
                    empleadoOldEmpleado = em.merge(empleadoOldEmpleado);
                }
            }
            for (Empleado empleadoNewEmpleado : empleadoNew) {
                if (!empleadoOld.contains(empleadoNewEmpleado)) {
                    empleadoNewEmpleado.getId().add(encabezadoServicio);
                    empleadoNewEmpleado = em.merge(empleadoNewEmpleado);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = encabezadoServicio.getId();
                if (findEncabezadoServicio(id) == null) {
                    throw new NonexistentEntityException("The encabezadoServicio with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EncabezadoServicio encabezadoServicio;
            try {
                encabezadoServicio = em.getReference(EncabezadoServicio.class, id);
                encabezadoServicio.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The encabezadoServicio with id " + id + " no longer exists.", enfe);
            }
            List<Cliente> cliente = encabezadoServicio.getCliente();
            for (Cliente clienteCliente : cliente) {
                clienteCliente.getId().remove(encabezadoServicio);
                clienteCliente = em.merge(clienteCliente);
            }
            List<Empleado> empleado = encabezadoServicio.getEmpleado();
            for (Empleado empleadoEmpleado : empleado) {
                empleadoEmpleado.getId().remove(encabezadoServicio);
                empleadoEmpleado = em.merge(empleadoEmpleado);
            }
            em.remove(encabezadoServicio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EncabezadoServicio> findEncabezadoServicioEntities() {
        return findEncabezadoServicioEntities(true, -1, -1);
    }

    public List<EncabezadoServicio> findEncabezadoServicioEntities(int maxResults, int firstResult) {
        return findEncabezadoServicioEntities(false, maxResults, firstResult);
    }

    private List<EncabezadoServicio> findEncabezadoServicioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EncabezadoServicio.class));
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

    public EncabezadoServicio findEncabezadoServicio(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EncabezadoServicio.class, id);
        } finally {
            em.close();
        }
    }

    public int getEncabezadoServicioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EncabezadoServicio> rt = cq.from(EncabezadoServicio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maquila.mservices.controller;

import com.maquila.mservices.controller.exceptions.NonexistentEntityException;
import com.maquila.mservices.entities.Cliente;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.maquila.mservices.entities.EncabezadoServicio;
import com.maquila.mservices.entities.Persona;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author marlly montoya
 */
public class ClienteJpaController implements Serializable {

    public ClienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cliente cliente) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EncabezadoServicio encabezadoServicio = cliente.getEncabezadoServicio();
            if (encabezadoServicio != null) {
                encabezadoServicio = em.getReference(encabezadoServicio.getClass(), encabezadoServicio.getId());
                cliente.setEncabezadoServicio(encabezadoServicio);
            }
            Persona persona = cliente.getPersona();
            if (persona != null) {
                persona = em.getReference(persona.getClass(), persona.getId());
                cliente.setPersona(persona);
            }
            em.persist(cliente);
            if (encabezadoServicio != null) {
                encabezadoServicio.getCliente().add(cliente);
                encabezadoServicio = em.merge(encabezadoServicio);
            }
            if (persona != null) {
                Cliente oldClienteOfPersona = persona.getCliente();
                if (oldClienteOfPersona != null) {
                    oldClienteOfPersona.setPersona(null);
                    oldClienteOfPersona = em.merge(oldClienteOfPersona);
                }
                persona.setCliente(cliente);
                persona = em.merge(persona);
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
            Cliente persistentCliente = em.find(Cliente.class, cliente.getId());
            EncabezadoServicio encabezadoServicioOld = persistentCliente.getEncabezadoServicio();
            EncabezadoServicio encabezadoServicioNew = cliente.getEncabezadoServicio();
            Persona personaOld = persistentCliente.getPersona();
            Persona personaNew = cliente.getPersona();
            if (encabezadoServicioNew != null) {
                encabezadoServicioNew = em.getReference(encabezadoServicioNew.getClass(), encabezadoServicioNew.getId());
                cliente.setEncabezadoServicio(encabezadoServicioNew);
            }
            if (personaNew != null) {
                personaNew = em.getReference(personaNew.getClass(), personaNew.getId());
                cliente.setPersona(personaNew);
            }
            cliente = em.merge(cliente);
            if (encabezadoServicioOld != null && !encabezadoServicioOld.equals(encabezadoServicioNew)) {
                encabezadoServicioOld.getCliente().remove(cliente);
                encabezadoServicioOld = em.merge(encabezadoServicioOld);
            }
            if (encabezadoServicioNew != null && !encabezadoServicioNew.equals(encabezadoServicioOld)) {
                encabezadoServicioNew.getCliente().add(cliente);
                encabezadoServicioNew = em.merge(encabezadoServicioNew);
            }
            if (personaOld != null && !personaOld.equals(personaNew)) {
                personaOld.setCliente(null);
                personaOld = em.merge(personaOld);
            }
            if (personaNew != null && !personaNew.equals(personaOld)) {
                Cliente oldClienteOfPersona = personaNew.getCliente();
                if (oldClienteOfPersona != null) {
                    oldClienteOfPersona.setPersona(null);
                    oldClienteOfPersona = em.merge(oldClienteOfPersona);
                }
                personaNew.setCliente(cliente);
                personaNew = em.merge(personaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cliente.getId();
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

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            EncabezadoServicio encabezadoServicio = cliente.getEncabezadoServicio();
            if (encabezadoServicio != null) {
                encabezadoServicio.getCliente().remove(cliente);
                encabezadoServicio = em.merge(encabezadoServicio);
            }
            Persona persona = cliente.getPersona();
            if (persona != null) {
                persona.setCliente(null);
                persona = em.merge(persona);
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

    public Cliente findCliente(Integer id) {
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

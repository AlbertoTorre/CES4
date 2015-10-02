/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maquila.mservices.controller;

import com.maquila.mservices.controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.maquila.mservices.entities.Empleado;
import com.maquila.mservices.entities.Cliente;
import com.maquila.mservices.entities.Persona;
import com.maquila.mservices.entities.TipoDocumento;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author marlly montoya
 */
public class PersonaJpaController implements Serializable {

    public PersonaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Persona persona) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado empleados = persona.getEmpleados();
            if (empleados != null) {
                empleados = em.getReference(empleados.getClass(), empleados.getId());
                persona.setEmpleados(empleados);
            }
            Cliente cliente = persona.getCliente();
            if (cliente != null) {
                cliente = em.getReference(cliente.getClass(), cliente.getId());
                persona.setCliente(cliente);
            }
            TipoDocumento idTipoDocumento = persona.getIdTipoDocumento();
            if (idTipoDocumento != null) {
                idTipoDocumento = em.getReference(idTipoDocumento.getClass(), idTipoDocumento.getId());
                persona.setIdTipoDocumento(idTipoDocumento);
            }
            em.persist(persona);
            if (empleados != null) {
                Persona oldPersonaOfEmpleados = empleados.getPersona();
                if (oldPersonaOfEmpleados != null) {
                    oldPersonaOfEmpleados.setEmpleados(null);
                    oldPersonaOfEmpleados = em.merge(oldPersonaOfEmpleados);
                }
                empleados.setPersona(persona);
                empleados = em.merge(empleados);
            }
            if (cliente != null) {
                Persona oldPersonaOfCliente = cliente.getPersona();
                if (oldPersonaOfCliente != null) {
                    oldPersonaOfCliente.setCliente(null);
                    oldPersonaOfCliente = em.merge(oldPersonaOfCliente);
                }
                cliente.setPersona(persona);
                cliente = em.merge(cliente);
            }
            if (idTipoDocumento != null) {
                idTipoDocumento.getPersona().add(persona);
                idTipoDocumento = em.merge(idTipoDocumento);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Persona persona) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Persona persistentPersona = em.find(Persona.class, persona.getId());
            Empleado empleadosOld = persistentPersona.getEmpleados();
            Empleado empleadosNew = persona.getEmpleados();
            Cliente clienteOld = persistentPersona.getCliente();
            Cliente clienteNew = persona.getCliente();
            TipoDocumento idTipoDocumentoOld = persistentPersona.getIdTipoDocumento();
            TipoDocumento idTipoDocumentoNew = persona.getIdTipoDocumento();
            if (empleadosNew != null) {
                empleadosNew = em.getReference(empleadosNew.getClass(), empleadosNew.getId());
                persona.setEmpleados(empleadosNew);
            }
            if (clienteNew != null) {
                clienteNew = em.getReference(clienteNew.getClass(), clienteNew.getId());
                persona.setCliente(clienteNew);
            }
            if (idTipoDocumentoNew != null) {
                idTipoDocumentoNew = em.getReference(idTipoDocumentoNew.getClass(), idTipoDocumentoNew.getId());
                persona.setIdTipoDocumento(idTipoDocumentoNew);
            }
            persona = em.merge(persona);
            if (empleadosOld != null && !empleadosOld.equals(empleadosNew)) {
                empleadosOld.setPersona(null);
                empleadosOld = em.merge(empleadosOld);
            }
            if (empleadosNew != null && !empleadosNew.equals(empleadosOld)) {
                Persona oldPersonaOfEmpleados = empleadosNew.getPersona();
                if (oldPersonaOfEmpleados != null) {
                    oldPersonaOfEmpleados.setEmpleados(null);
                    oldPersonaOfEmpleados = em.merge(oldPersonaOfEmpleados);
                }
                empleadosNew.setPersona(persona);
                empleadosNew = em.merge(empleadosNew);
            }
            if (clienteOld != null && !clienteOld.equals(clienteNew)) {
                clienteOld.setPersona(null);
                clienteOld = em.merge(clienteOld);
            }
            if (clienteNew != null && !clienteNew.equals(clienteOld)) {
                Persona oldPersonaOfCliente = clienteNew.getPersona();
                if (oldPersonaOfCliente != null) {
                    oldPersonaOfCliente.setCliente(null);
                    oldPersonaOfCliente = em.merge(oldPersonaOfCliente);
                }
                clienteNew.setPersona(persona);
                clienteNew = em.merge(clienteNew);
            }
            if (idTipoDocumentoOld != null && !idTipoDocumentoOld.equals(idTipoDocumentoNew)) {
                idTipoDocumentoOld.getPersona().remove(persona);
                idTipoDocumentoOld = em.merge(idTipoDocumentoOld);
            }
            if (idTipoDocumentoNew != null && !idTipoDocumentoNew.equals(idTipoDocumentoOld)) {
                idTipoDocumentoNew.getPersona().add(persona);
                idTipoDocumentoNew = em.merge(idTipoDocumentoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = persona.getId();
                if (findPersona(id) == null) {
                    throw new NonexistentEntityException("The persona with id " + id + " no longer exists.");
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
            Persona persona;
            try {
                persona = em.getReference(Persona.class, id);
                persona.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The persona with id " + id + " no longer exists.", enfe);
            }
            Empleado empleados = persona.getEmpleados();
            if (empleados != null) {
                empleados.setPersona(null);
                empleados = em.merge(empleados);
            }
            Cliente cliente = persona.getCliente();
            if (cliente != null) {
                cliente.setPersona(null);
                cliente = em.merge(cliente);
            }
            TipoDocumento idTipoDocumento = persona.getIdTipoDocumento();
            if (idTipoDocumento != null) {
                idTipoDocumento.getPersona().remove(persona);
                idTipoDocumento = em.merge(idTipoDocumento);
            }
            em.remove(persona);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Persona> findPersonaEntities() {
        return findPersonaEntities(true, -1, -1);
    }

    public List<Persona> findPersonaEntities(int maxResults, int firstResult) {
        return findPersonaEntities(false, maxResults, firstResult);
    }

    private List<Persona> findPersonaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Persona.class));
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

    public Persona findPersona(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Persona.class, id);
        } finally {
            em.close();
        }
    }

    public int getPersonaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Persona> rt = cq.from(Persona.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

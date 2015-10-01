/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maquila.mservices.controller;

import com.maquila.mservices.controller.exceptions.NonexistentEntityException;
import com.maquila.mservices.entities.Empleado;
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
 * @author Alex
 */
public class EmpleadoJpaController implements Serializable {

    public EmpleadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Empleado empleado) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EncabezadoServicio encabezadoServicio = empleado.getEncabezadoServicio();
            if (encabezadoServicio != null) {
                encabezadoServicio = em.getReference(encabezadoServicio.getClass(), encabezadoServicio.getId());
                empleado.setEncabezadoServicio(encabezadoServicio);
            }
            Persona persona = empleado.getPersona();
            if (persona != null) {
                persona = em.getReference(persona.getClass(), persona.getId());
                empleado.setPersona(persona);
            }
            em.persist(empleado);
            if (encabezadoServicio != null) {
                encabezadoServicio.getEmpleado().add(empleado);
                encabezadoServicio = em.merge(encabezadoServicio);
            }
            if (persona != null) {
                Empleado oldEmpleadosOfPersona = persona.getEmpleados();
                if (oldEmpleadosOfPersona != null) {
                    oldEmpleadosOfPersona.setPersona(null);
                    oldEmpleadosOfPersona = em.merge(oldEmpleadosOfPersona);
                }
                persona.setEmpleados(empleado);
                persona = em.merge(persona);
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
            Empleado persistentEmpleado = em.find(Empleado.class, empleado.getId());
            EncabezadoServicio encabezadoServicioOld = persistentEmpleado.getEncabezadoServicio();
            EncabezadoServicio encabezadoServicioNew = empleado.getEncabezadoServicio();
            Persona personaOld = persistentEmpleado.getPersona();
            Persona personaNew = empleado.getPersona();
            if (encabezadoServicioNew != null) {
                encabezadoServicioNew = em.getReference(encabezadoServicioNew.getClass(), encabezadoServicioNew.getId());
                empleado.setEncabezadoServicio(encabezadoServicioNew);
            }
            if (personaNew != null) {
                personaNew = em.getReference(personaNew.getClass(), personaNew.getId());
                empleado.setPersona(personaNew);
            }
            empleado = em.merge(empleado);
            if (encabezadoServicioOld != null && !encabezadoServicioOld.equals(encabezadoServicioNew)) {
                encabezadoServicioOld.getEmpleado().remove(empleado);
                encabezadoServicioOld = em.merge(encabezadoServicioOld);
            }
            if (encabezadoServicioNew != null && !encabezadoServicioNew.equals(encabezadoServicioOld)) {
                encabezadoServicioNew.getEmpleado().add(empleado);
                encabezadoServicioNew = em.merge(encabezadoServicioNew);
            }
            if (personaOld != null && !personaOld.equals(personaNew)) {
                personaOld.setEmpleados(null);
                personaOld = em.merge(personaOld);
            }
            if (personaNew != null && !personaNew.equals(personaOld)) {
                Empleado oldEmpleadosOfPersona = personaNew.getEmpleados();
                if (oldEmpleadosOfPersona != null) {
                    oldEmpleadosOfPersona.setPersona(null);
                    oldEmpleadosOfPersona = em.merge(oldEmpleadosOfPersona);
                }
                personaNew.setEmpleados(empleado);
                personaNew = em.merge(personaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = empleado.getId();
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

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado empleado;
            try {
                empleado = em.getReference(Empleado.class, id);
                empleado.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.", enfe);
            }
            EncabezadoServicio encabezadoServicio = empleado.getEncabezadoServicio();
            if (encabezadoServicio != null) {
                encabezadoServicio.getEmpleado().remove(empleado);
                encabezadoServicio = em.merge(encabezadoServicio);
            }
            Persona persona = empleado.getPersona();
            if (persona != null) {
                persona.setEmpleados(null);
                persona = em.merge(persona);
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

    public Empleado findEmpleado(Integer id) {
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

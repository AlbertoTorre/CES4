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
import com.maquila.mservices.entities.Persona;
import com.maquila.mservices.entities.TipoDocumento;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author marlly montoya
 */
public class TipoDocumentoJpaController implements Serializable {

    public TipoDocumentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoDocumento tipoDocumento) {
        if (tipoDocumento.getPersona() == null) {
            tipoDocumento.setPersona(new ArrayList<Persona>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Persona> attachedPersona = new ArrayList<Persona>();
            for (Persona personaPersonaToAttach : tipoDocumento.getPersona()) {
                personaPersonaToAttach = em.getReference(personaPersonaToAttach.getClass(), personaPersonaToAttach.getId());
                attachedPersona.add(personaPersonaToAttach);
            }
            tipoDocumento.setPersona(attachedPersona);
            em.persist(tipoDocumento);
            for (Persona personaPersona : tipoDocumento.getPersona()) {
                TipoDocumento oldIdTipoDocumentoOfPersonaPersona = personaPersona.getIdTipoDocumento();
                personaPersona.setIdTipoDocumento(tipoDocumento);
                personaPersona = em.merge(personaPersona);
                if (oldIdTipoDocumentoOfPersonaPersona != null) {
                    oldIdTipoDocumentoOfPersonaPersona.getPersona().remove(personaPersona);
                    oldIdTipoDocumentoOfPersonaPersona = em.merge(oldIdTipoDocumentoOfPersonaPersona);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoDocumento tipoDocumento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoDocumento persistentTipoDocumento = em.find(TipoDocumento.class, tipoDocumento.getId());
            List<Persona> personaOld = persistentTipoDocumento.getPersona();
            List<Persona> personaNew = tipoDocumento.getPersona();
            List<Persona> attachedPersonaNew = new ArrayList<Persona>();
            for (Persona personaNewPersonaToAttach : personaNew) {
                personaNewPersonaToAttach = em.getReference(personaNewPersonaToAttach.getClass(), personaNewPersonaToAttach.getId());
                attachedPersonaNew.add(personaNewPersonaToAttach);
            }
            personaNew = attachedPersonaNew;
            tipoDocumento.setPersona(personaNew);
            tipoDocumento = em.merge(tipoDocumento);
            for (Persona personaOldPersona : personaOld) {
                if (!personaNew.contains(personaOldPersona)) {
                    personaOldPersona.setIdTipoDocumento(null);
                    personaOldPersona = em.merge(personaOldPersona);
                }
            }
            for (Persona personaNewPersona : personaNew) {
                if (!personaOld.contains(personaNewPersona)) {
                    TipoDocumento oldIdTipoDocumentoOfPersonaNewPersona = personaNewPersona.getIdTipoDocumento();
                    personaNewPersona.setIdTipoDocumento(tipoDocumento);
                    personaNewPersona = em.merge(personaNewPersona);
                    if (oldIdTipoDocumentoOfPersonaNewPersona != null && !oldIdTipoDocumentoOfPersonaNewPersona.equals(tipoDocumento)) {
                        oldIdTipoDocumentoOfPersonaNewPersona.getPersona().remove(personaNewPersona);
                        oldIdTipoDocumentoOfPersonaNewPersona = em.merge(oldIdTipoDocumentoOfPersonaNewPersona);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoDocumento.getId();
                if (findTipoDocumento(id) == null) {
                    throw new NonexistentEntityException("The tipoDocumento with id " + id + " no longer exists.");
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
            TipoDocumento tipoDocumento;
            try {
                tipoDocumento = em.getReference(TipoDocumento.class, id);
                tipoDocumento.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoDocumento with id " + id + " no longer exists.", enfe);
            }
            List<Persona> persona = tipoDocumento.getPersona();
            for (Persona personaPersona : persona) {
                personaPersona.setIdTipoDocumento(null);
                personaPersona = em.merge(personaPersona);
            }
            em.remove(tipoDocumento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoDocumento> findTipoDocumentoEntities() {
        return findTipoDocumentoEntities(true, -1, -1);
    }

    public List<TipoDocumento> findTipoDocumentoEntities(int maxResults, int firstResult) {
        return findTipoDocumentoEntities(false, maxResults, firstResult);
    }

    private List<TipoDocumento> findTipoDocumentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoDocumento.class));
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

    public TipoDocumento findTipoDocumento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoDocumento.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoDocumentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoDocumento> rt = cq.from(TipoDocumento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

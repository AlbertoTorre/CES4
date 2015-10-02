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
import com.maquila.mservices.entities.DetalleServicio;
import com.maquila.mservices.entities.Insumo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author marlly montoya
 */
public class InsumoJpaController implements Serializable {

    public InsumoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Insumo insumo) {
        if (insumo.getDetalleServicios() == null) {
            insumo.setDetalleServicios(new ArrayList<DetalleServicio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<DetalleServicio> attachedDetalleServicios = new ArrayList<DetalleServicio>();
            for (DetalleServicio detalleServiciosDetalleServicioToAttach : insumo.getDetalleServicios()) {
                detalleServiciosDetalleServicioToAttach = em.getReference(detalleServiciosDetalleServicioToAttach.getClass(), detalleServiciosDetalleServicioToAttach.getId());
                attachedDetalleServicios.add(detalleServiciosDetalleServicioToAttach);
            }
            insumo.setDetalleServicios(attachedDetalleServicios);
            em.persist(insumo);
            for (DetalleServicio detalleServiciosDetalleServicio : insumo.getDetalleServicios()) {
                detalleServiciosDetalleServicio.getInsumos().add(insumo);
                detalleServiciosDetalleServicio = em.merge(detalleServiciosDetalleServicio);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Insumo insumo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Insumo persistentInsumo = em.find(Insumo.class, insumo.getId());
            List<DetalleServicio> detalleServiciosOld = persistentInsumo.getDetalleServicios();
            List<DetalleServicio> detalleServiciosNew = insumo.getDetalleServicios();
            List<DetalleServicio> attachedDetalleServiciosNew = new ArrayList<DetalleServicio>();
            for (DetalleServicio detalleServiciosNewDetalleServicioToAttach : detalleServiciosNew) {
                detalleServiciosNewDetalleServicioToAttach = em.getReference(detalleServiciosNewDetalleServicioToAttach.getClass(), detalleServiciosNewDetalleServicioToAttach.getId());
                attachedDetalleServiciosNew.add(detalleServiciosNewDetalleServicioToAttach);
            }
            detalleServiciosNew = attachedDetalleServiciosNew;
            insumo.setDetalleServicios(detalleServiciosNew);
            insumo = em.merge(insumo);
            for (DetalleServicio detalleServiciosOldDetalleServicio : detalleServiciosOld) {
                if (!detalleServiciosNew.contains(detalleServiciosOldDetalleServicio)) {
                    detalleServiciosOldDetalleServicio.getInsumos().remove(insumo);
                    detalleServiciosOldDetalleServicio = em.merge(detalleServiciosOldDetalleServicio);
                }
            }
            for (DetalleServicio detalleServiciosNewDetalleServicio : detalleServiciosNew) {
                if (!detalleServiciosOld.contains(detalleServiciosNewDetalleServicio)) {
                    detalleServiciosNewDetalleServicio.getInsumos().add(insumo);
                    detalleServiciosNewDetalleServicio = em.merge(detalleServiciosNewDetalleServicio);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = insumo.getId();
                if (findInsumo(id) == null) {
                    throw new NonexistentEntityException("The insumo with id " + id + " no longer exists.");
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
            Insumo insumo;
            try {
                insumo = em.getReference(Insumo.class, id);
                insumo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The insumo with id " + id + " no longer exists.", enfe);
            }
            List<DetalleServicio> detalleServicios = insumo.getDetalleServicios();
            for (DetalleServicio detalleServiciosDetalleServicio : detalleServicios) {
                detalleServiciosDetalleServicio.getInsumos().remove(insumo);
                detalleServiciosDetalleServicio = em.merge(detalleServiciosDetalleServicio);
            }
            em.remove(insumo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Insumo> findInsumoEntities() {
        return findInsumoEntities(true, -1, -1);
    }

    public List<Insumo> findInsumoEntities(int maxResults, int firstResult) {
        return findInsumoEntities(false, maxResults, firstResult);
    }

    private List<Insumo> findInsumoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Insumo.class));
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

    public Insumo findInsumo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Insumo.class, id);
        } finally {
            em.close();
        }
    }

    public int getInsumoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Insumo> rt = cq.from(Insumo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

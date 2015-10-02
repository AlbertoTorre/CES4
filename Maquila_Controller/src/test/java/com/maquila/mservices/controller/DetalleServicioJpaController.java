/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maquila.mservices.controller;

import com.maquila.mservices.controller.exceptions.NonexistentEntityException;
import com.maquila.mservices.entities.DetalleServicio;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.maquila.mservices.entities.Servicio;
import java.util.ArrayList;
import java.util.List;
import com.maquila.mservices.entities.Insumo;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author marlly montoya
 */
public class DetalleServicioJpaController implements Serializable {

    public DetalleServicioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DetalleServicio detalleServicio) {
        if (detalleServicio.getServicio() == null) {
            detalleServicio.setServicio(new ArrayList<Servicio>());
        }
        if (detalleServicio.getInsumos() == null) {
            detalleServicio.setInsumos(new ArrayList<Insumo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Servicio> attachedServicio = new ArrayList<Servicio>();
            for (Servicio servicioServicioToAttach : detalleServicio.getServicio()) {
                servicioServicioToAttach = em.getReference(servicioServicioToAttach.getClass(), servicioServicioToAttach.getId());
                attachedServicio.add(servicioServicioToAttach);
            }
            detalleServicio.setServicio(attachedServicio);
            List<Insumo> attachedInsumos = new ArrayList<Insumo>();
            for (Insumo insumosInsumoToAttach : detalleServicio.getInsumos()) {
                insumosInsumoToAttach = em.getReference(insumosInsumoToAttach.getClass(), insumosInsumoToAttach.getId());
                attachedInsumos.add(insumosInsumoToAttach);
            }
            detalleServicio.setInsumos(attachedInsumos);
            em.persist(detalleServicio);
            for (Servicio servicioServicio : detalleServicio.getServicio()) {
                DetalleServicio oldDetalleServicioOfServicioServicio = servicioServicio.getDetalleServicio();
                servicioServicio.setDetalleServicio(detalleServicio);
                servicioServicio = em.merge(servicioServicio);
                if (oldDetalleServicioOfServicioServicio != null) {
                    oldDetalleServicioOfServicioServicio.getServicio().remove(servicioServicio);
                    oldDetalleServicioOfServicioServicio = em.merge(oldDetalleServicioOfServicioServicio);
                }
            }
            for (Insumo insumosInsumo : detalleServicio.getInsumos()) {
                insumosInsumo.getDetalleServicios().add(detalleServicio);
                insumosInsumo = em.merge(insumosInsumo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DetalleServicio detalleServicio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DetalleServicio persistentDetalleServicio = em.find(DetalleServicio.class, detalleServicio.getId());
            List<Servicio> servicioOld = persistentDetalleServicio.getServicio();
            List<Servicio> servicioNew = detalleServicio.getServicio();
            List<Insumo> insumosOld = persistentDetalleServicio.getInsumos();
            List<Insumo> insumosNew = detalleServicio.getInsumos();
            List<Servicio> attachedServicioNew = new ArrayList<Servicio>();
            for (Servicio servicioNewServicioToAttach : servicioNew) {
                servicioNewServicioToAttach = em.getReference(servicioNewServicioToAttach.getClass(), servicioNewServicioToAttach.getId());
                attachedServicioNew.add(servicioNewServicioToAttach);
            }
            servicioNew = attachedServicioNew;
            detalleServicio.setServicio(servicioNew);
            List<Insumo> attachedInsumosNew = new ArrayList<Insumo>();
            for (Insumo insumosNewInsumoToAttach : insumosNew) {
                insumosNewInsumoToAttach = em.getReference(insumosNewInsumoToAttach.getClass(), insumosNewInsumoToAttach.getId());
                attachedInsumosNew.add(insumosNewInsumoToAttach);
            }
            insumosNew = attachedInsumosNew;
            detalleServicio.setInsumos(insumosNew);
            detalleServicio = em.merge(detalleServicio);
            for (Servicio servicioOldServicio : servicioOld) {
                if (!servicioNew.contains(servicioOldServicio)) {
                    servicioOldServicio.setDetalleServicio(null);
                    servicioOldServicio = em.merge(servicioOldServicio);
                }
            }
            for (Servicio servicioNewServicio : servicioNew) {
                if (!servicioOld.contains(servicioNewServicio)) {
                    DetalleServicio oldDetalleServicioOfServicioNewServicio = servicioNewServicio.getDetalleServicio();
                    servicioNewServicio.setDetalleServicio(detalleServicio);
                    servicioNewServicio = em.merge(servicioNewServicio);
                    if (oldDetalleServicioOfServicioNewServicio != null && !oldDetalleServicioOfServicioNewServicio.equals(detalleServicio)) {
                        oldDetalleServicioOfServicioNewServicio.getServicio().remove(servicioNewServicio);
                        oldDetalleServicioOfServicioNewServicio = em.merge(oldDetalleServicioOfServicioNewServicio);
                    }
                }
            }
            for (Insumo insumosOldInsumo : insumosOld) {
                if (!insumosNew.contains(insumosOldInsumo)) {
                    insumosOldInsumo.getDetalleServicios().remove(detalleServicio);
                    insumosOldInsumo = em.merge(insumosOldInsumo);
                }
            }
            for (Insumo insumosNewInsumo : insumosNew) {
                if (!insumosOld.contains(insumosNewInsumo)) {
                    insumosNewInsumo.getDetalleServicios().add(detalleServicio);
                    insumosNewInsumo = em.merge(insumosNewInsumo);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = detalleServicio.getId();
                if (findDetalleServicio(id) == null) {
                    throw new NonexistentEntityException("The detalleServicio with id " + id + " no longer exists.");
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
            DetalleServicio detalleServicio;
            try {
                detalleServicio = em.getReference(DetalleServicio.class, id);
                detalleServicio.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleServicio with id " + id + " no longer exists.", enfe);
            }
            List<Servicio> servicio = detalleServicio.getServicio();
            for (Servicio servicioServicio : servicio) {
                servicioServicio.setDetalleServicio(null);
                servicioServicio = em.merge(servicioServicio);
            }
            List<Insumo> insumos = detalleServicio.getInsumos();
            for (Insumo insumosInsumo : insumos) {
                insumosInsumo.getDetalleServicios().remove(detalleServicio);
                insumosInsumo = em.merge(insumosInsumo);
            }
            em.remove(detalleServicio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DetalleServicio> findDetalleServicioEntities() {
        return findDetalleServicioEntities(true, -1, -1);
    }

    public List<DetalleServicio> findDetalleServicioEntities(int maxResults, int firstResult) {
        return findDetalleServicioEntities(false, maxResults, firstResult);
    }

    private List<DetalleServicio> findDetalleServicioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DetalleServicio.class));
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

    public DetalleServicio findDetalleServicio(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DetalleServicio.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetalleServicioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DetalleServicio> rt = cq.from(DetalleServicio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

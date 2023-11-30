
package com.academy.tpveterinaria.repository;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.academy.tpveterinaria.model.Medico;
import com.academy.tpveterinaria.model.TurnoMedico;
import com.academy.tpveterinaria.repository.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TurnoMedicoJpaController implements Serializable {
  public TurnoMedicoJpaController() {
    emf = Persistence.createEntityManagerFactory("tpVeterinariaPU");
  }

  public TurnoMedicoJpaController(EntityManagerFactory emf) {
    this.emf = emf;
  }
  private EntityManagerFactory emf = null;

  public EntityManager getEntityManager() {
    return emf.createEntityManager();
  }

  public void create(TurnoMedico turnoMedico) {
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Medico medico = turnoMedico.getMedico();
      if (medico != null) {
        medico = em.getReference(medico.getClass(), medico.getId());
        turnoMedico.setMedico(medico);
      }
      em.persist(turnoMedico);
      if (medico != null) {
        medico.getTurnos().add(turnoMedico);
        medico = em.merge(medico);
      }
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void edit(TurnoMedico turnoMedico) throws NonexistentEntityException, Exception {
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      TurnoMedico persistentTurnoMedico = em.find(TurnoMedico.class, turnoMedico.getId());
      Medico medicoOld = persistentTurnoMedico.getMedico();
      Medico medicoNew = turnoMedico.getMedico();
      if (medicoNew != null) {
        medicoNew = em.getReference(medicoNew.getClass(), medicoNew.getId());
        turnoMedico.setMedico(medicoNew);
      }
      turnoMedico = em.merge(turnoMedico);
      if (medicoOld != null && !medicoOld.equals(medicoNew)) {
        medicoOld.getTurnos().remove(turnoMedico);
        medicoOld = em.merge(medicoOld);
      }
      if (medicoNew != null && !medicoNew.equals(medicoOld)) {
        medicoNew.getTurnos().add(turnoMedico);
        medicoNew = em.merge(medicoNew);
      }
      em.getTransaction().commit();
    } catch (Exception ex) {
      String msg = ex.getLocalizedMessage();
      if (msg == null || msg.length() == 0) {
        Long id = turnoMedico.getId();
        if (findTurnoMedico(id) == null) {
          throw new NonexistentEntityException("The turnoMedico with id " + id + " no longer exists.");
        }
      }
      throw ex;
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void destroy(Long id) throws NonexistentEntityException {
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      TurnoMedico turnoMedico;
      try {
        turnoMedico = em.getReference(TurnoMedico.class, id);
        turnoMedico.getId();
      } catch (EntityNotFoundException enfe) {
        throw new NonexistentEntityException("The turnoMedico with id " + id + " no longer exists.", enfe);
      }
      Medico medico = turnoMedico.getMedico();
      if (medico != null) {
        medico.getTurnos().remove(turnoMedico);
        medico = em.merge(medico);
      }
      em.remove(turnoMedico);
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public List<TurnoMedico> findTurnoMedicoEntities() {
    return findTurnoMedicoEntities(true, -1, -1);
  }

  public List<TurnoMedico> findTurnoMedicoEntities(int maxResults, int firstResult) {
    return findTurnoMedicoEntities(false, maxResults, firstResult);
  }

  private List<TurnoMedico> findTurnoMedicoEntities(boolean all, int maxResults, int firstResult) {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      cq.select(cq.from(TurnoMedico.class));
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

  public TurnoMedico findTurnoMedico(Long id) {
    EntityManager em = getEntityManager();
    try {
      return em.find(TurnoMedico.class, id);
    } finally {
      em.close();
    }
  }

  public int getTurnoMedicoCount() {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      Root<TurnoMedico> rt = cq.from(TurnoMedico.class);
      cq.select(em.getCriteriaBuilder().count(rt));
      Query q = em.createQuery(cq);
      return ((Long) q.getSingleResult()).intValue();
    } finally {
      em.close();
    }
  }
  
}

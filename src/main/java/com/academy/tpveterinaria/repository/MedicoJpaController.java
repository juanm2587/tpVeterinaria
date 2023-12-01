
package com.academy.tpveterinaria.repository;

import com.academy.tpveterinaria.model.Medico;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.academy.tpveterinaria.model.TurnoMedico;
import com.academy.tpveterinaria.repository.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

//creacion de metodo que inicia la conexion a la BD.
public class MedicoJpaController implements Serializable {
  public MedicoJpaController() {
    emf = Persistence.createEntityManagerFactory("tpVeterinariaPU");
  }

  public MedicoJpaController(EntityManagerFactory emf) {
    this.emf = emf;
  }
  private EntityManagerFactory emf = null;

  public EntityManager getEntityManager() {
    return emf.createEntityManager();
  }

  public void create(Medico medico) {
    if (medico.getTurnos() == null) {
      medico.setTurnos(new ArrayList<TurnoMedico>());
    }
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      List<TurnoMedico> attachedTurnos = new ArrayList<TurnoMedico>();
      for (TurnoMedico turnosTurnoMedicoToAttach : medico.getTurnos()) {
        turnosTurnoMedicoToAttach = em.getReference(turnosTurnoMedicoToAttach.getClass(), turnosTurnoMedicoToAttach.getId());
        attachedTurnos.add(turnosTurnoMedicoToAttach);
      }
      medico.setTurnos(attachedTurnos);
      em.persist(medico);
      for (TurnoMedico turnosTurnoMedico : medico.getTurnos()) {
        Medico oldMedicoOfTurnosTurnoMedico = turnosTurnoMedico.getMedico();
        turnosTurnoMedico.setMedico(medico);
        turnosTurnoMedico = em.merge(turnosTurnoMedico);
        if (oldMedicoOfTurnosTurnoMedico != null) {
          oldMedicoOfTurnosTurnoMedico.getTurnos().remove(turnosTurnoMedico);
          oldMedicoOfTurnosTurnoMedico = em.merge(oldMedicoOfTurnosTurnoMedico);
        }
      }
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void edit(Medico medico) throws NonexistentEntityException, Exception {
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Medico persistentMedico = em.find(Medico.class, medico.getId());
      List<TurnoMedico> turnosOld = persistentMedico.getTurnos();
      List<TurnoMedico> turnosNew = medico.getTurnos();
      List<TurnoMedico> attachedTurnosNew = new ArrayList<TurnoMedico>();
      for (TurnoMedico turnosNewTurnoMedicoToAttach : turnosNew) {
        turnosNewTurnoMedicoToAttach = em.getReference(turnosNewTurnoMedicoToAttach.getClass(), turnosNewTurnoMedicoToAttach.getId());
        attachedTurnosNew.add(turnosNewTurnoMedicoToAttach);
      }
      turnosNew = attachedTurnosNew;
      medico.setTurnos(turnosNew);
      medico = em.merge(medico);
      for (TurnoMedico turnosOldTurnoMedico : turnosOld) {
        if (!turnosNew.contains(turnosOldTurnoMedico)) {
          turnosOldTurnoMedico.setMedico(null);
          turnosOldTurnoMedico = em.merge(turnosOldTurnoMedico);
        }
      }
      for (TurnoMedico turnosNewTurnoMedico : turnosNew) {
        if (!turnosOld.contains(turnosNewTurnoMedico)) {
          Medico oldMedicoOfTurnosNewTurnoMedico = turnosNewTurnoMedico.getMedico();
          turnosNewTurnoMedico.setMedico(medico);
          turnosNewTurnoMedico = em.merge(turnosNewTurnoMedico);
          if (oldMedicoOfTurnosNewTurnoMedico != null && !oldMedicoOfTurnosNewTurnoMedico.equals(medico)) {
            oldMedicoOfTurnosNewTurnoMedico.getTurnos().remove(turnosNewTurnoMedico);
            oldMedicoOfTurnosNewTurnoMedico = em.merge(oldMedicoOfTurnosNewTurnoMedico);
          }
        }
      }
      em.getTransaction().commit();
    } catch (Exception ex) {
      String msg = ex.getLocalizedMessage();
      if (msg == null || msg.length() == 0) {
        Long id = medico.getId();
        if (findMedico(id) == null) {
          throw new NonexistentEntityException("The medico with id " + id + " no longer exists.");
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
      Medico medico;
      try {
        medico = em.getReference(Medico.class, id);
        medico.getId();
      } catch (EntityNotFoundException enfe) {
        throw new NonexistentEntityException("The medico with id " + id + " no longer exists.", enfe);
      }
      List<TurnoMedico> turnos = medico.getTurnos();
      for (TurnoMedico turnosTurnoMedico : turnos) {
        turnosTurnoMedico.setMedico(null);
        turnosTurnoMedico = em.merge(turnosTurnoMedico);
      }
      em.remove(medico);
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public List<Medico> findMedicoEntities() {
    return findMedicoEntities(true, -1, -1);
  }

  public List<Medico> findMedicoEntities(int maxResults, int firstResult) {
    return findMedicoEntities(false, maxResults, firstResult);
  }

  private List<Medico> findMedicoEntities(boolean all, int maxResults, int firstResult) {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      cq.select(cq.from(Medico.class));
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

  public Medico findMedico(Long id) {
    EntityManager em = getEntityManager();
    try {
      return em.find(Medico.class, id);
    } finally {
      em.close();
    }
  }

  public int getMedicoCount() {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      Root<Medico> rt = cq.from(Medico.class);
      cq.select(em.getCriteriaBuilder().count(rt));
      Query q = em.createQuery(cq);
      return ((Long) q.getSingleResult()).intValue();
    } finally {
      em.close();
    }
  }
  
}

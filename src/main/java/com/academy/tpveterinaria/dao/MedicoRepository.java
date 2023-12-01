package com.academy.tpveterinaria.dao;

import com.academy.tpveterinaria.model.Medico;
import com.academy.tpveterinaria.repository.MedicoJpaController;
import com.academy.tpveterinaria.repository.exceptions.NonexistentEntityException;
import java.util.List;

public class MedicoRepository implements MedicoDAO {

  public MedicoJpaController controllerMedicoJpa;

  public MedicoRepository() {
    this.controllerMedicoJpa = new MedicoJpaController();
  }

  @Override
  public Medico obtenerPorId(int id) {
    return controllerMedicoJpa.findMedico(Long.MIN_VALUE);
  }

  @Override
  public List<Medico> obtenerTodos() {
    return controllerMedicoJpa.findMedicoEntities(0, 0);
  }

  @Override
  public void guardar(Medico medico) {
    controllerMedicoJpa.create(medico);
  }

  @Override
  public void actualizar(Medico medico) {
    try {
      controllerMedicoJpa.edit(medico);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void eliminar(int id) {
    try {
      controllerMedicoJpa.destroy(Long.MIN_VALUE);
    } catch (NonexistentEntityException e) {
      e.printStackTrace();
    }
  }

}

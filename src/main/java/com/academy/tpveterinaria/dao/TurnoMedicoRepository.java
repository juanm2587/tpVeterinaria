package com.academy.tpveterinaria.dao;

import com.academy.tpveterinaria.model.TurnoMedico;
import com.academy.tpveterinaria.repository.TurnoMedicoJpaController;
import com.academy.tpveterinaria.repository.exceptions.NonexistentEntityException;
import java.util.List;

public class TurnoMedicoRepository implements TurnoMedicoDAO {

  public TurnoMedicoJpaController controllerTurnoMedicoJpa;

  public TurnoMedicoRepository() {
    this.controllerTurnoMedicoJpa = new TurnoMedicoJpaController();
  }

  @Override
  public TurnoMedico obtenerPorId(int id) {
    return controllerTurnoMedicoJpa.findTurnoMedico(Long.MIN_VALUE);
  }

  @Override
  public List<TurnoMedico> obtenerTodos() {
    return controllerTurnoMedicoJpa.findTurnoMedicoEntities(0, 0);
  }

  @Override
  public void guardar(TurnoMedico turnoMedico) {
    controllerTurnoMedicoJpa.create(turnoMedico);
  }

  @Override
  public void actualizar(TurnoMedico turnoMedico) {
    try {
      controllerTurnoMedicoJpa.edit(turnoMedico);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void eliminar(int id) {
    try {
      controllerTurnoMedicoJpa.destroy(Long.MIN_VALUE);
    } catch (NonexistentEntityException e) {
      e.printStackTrace();
    }

  }

}

package com.academy.tpveterinaria.dao;

import com.academy.tpveterinaria.model.Mascota;
import com.academy.tpveterinaria.repository.MascotaJpaController;
import com.academy.tpveterinaria.repository.exceptions.NonexistentEntityException;
import java.util.List;

public class MascotaRepository implements MascotaDAO {

  public MascotaJpaController controllerMascotaJpa;

  public MascotaRepository() {
    this.controllerMascotaJpa = new MascotaJpaController();
  }

  @Override
  public Mascota obtenerPorId(int id) {
    return controllerMascotaJpa.findMascota(Long.MIN_VALUE);

  }

  @Override
  public List<Mascota> obtenerTodos() {
    return controllerMascotaJpa.findMascotaEntities(0, 0);
  }

  @Override
  public void guardar(Mascota mascota) {
    controllerMascotaJpa.create(mascota);
  }

  @Override
  public void actualizar(Mascota mascota) {
    try {
      controllerMascotaJpa.edit(mascota);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void eliminar(int id) {
    try {
      controllerMascotaJpa.destroy(Long.MIN_VALUE);
    } catch (NonexistentEntityException e) {
      e.printStackTrace();
    }
  }
}

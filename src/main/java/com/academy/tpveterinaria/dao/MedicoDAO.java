
package com.academy.tpveterinaria.dao;

import com.academy.tpveterinaria.model.Medico;
import java.util.List;

public interface MedicoDAO {
  Medico obtenerPorId(int id);
  List<Medico> obtenerTodos();
  void guardar (Medico medico);
  void actualizar (Medico medico);
  void eliminar (int id);  
}

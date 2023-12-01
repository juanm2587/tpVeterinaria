
package com.academy.tpveterinaria.dao;

import com.academy.tpveterinaria.model.Mascota;
import java.util.List;

public interface MascotaDAO {
  Mascota obtenerPorId(int id);
  List<Mascota> obtenerTodos();
  void guardar (Mascota mascota);
  void actualizar (Mascota mascota);
  void eliminar (int id);  
}

  
package com.academy.tpveterinaria.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Gato extends Mascota{
  private boolean esCazador;  

  public Gato() {
  }

  public Gato(boolean esCazador) {
    this.esCazador = esCazador;
  }

  public Gato(boolean esCazador, Long id, String nombre, String sexo, String Raza, int edad) {
    super(id, nombre, sexo, Raza, edad);
    this.esCazador = esCazador;
  }

  

  
  
}

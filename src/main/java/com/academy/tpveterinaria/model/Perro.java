
package com.academy.tpveterinaria.model;


import lombok.Getter;

import lombok.Setter;

@Getter
@Setter

public class Perro extends Mascota{
  private String tamanio;
  

  public Perro() {
  }

  public Perro(String tamanio) {
    this.tamanio = tamanio;
  }

  public Perro(String tamanio, Long id, String nombre, String sexo, String Raza, int edad) {
    super(id, nombre, sexo, Raza, edad);
    this.tamanio = tamanio;
  }



  
          
}

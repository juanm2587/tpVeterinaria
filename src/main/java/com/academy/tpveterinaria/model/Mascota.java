package com.academy.tpveterinaria.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "MASCOTA")
public class Mascota {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String nombre;
  private String sexo;
  private String Raza;
  private int edad;

  public Mascota() {
  }

  public Mascota(Long id, String nombre, String sexo, String Raza, int edad) {
    this.id = id;
    this.nombre = nombre;
    this.sexo = sexo;
    this.Raza = Raza;
    this.edad = edad;
  }


}

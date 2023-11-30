
package com.academy.tpveterinaria.model;



import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="MEDICO")
public class Medico{
  @Id
  @Column(name="id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String nombre;
  @OneToMany(mappedBy = "medico")
  private List<TurnoMedico> turnos;

  public Medico(Long id, String nombre, List<TurnoMedico> turnos) {
    this.id = id;
    this.nombre = nombre;
    this.turnos = turnos;
  }

  public Medico() {
  }
}

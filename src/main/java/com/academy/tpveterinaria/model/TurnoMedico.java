
package com.academy.tpveterinaria.model;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="TURNOMEDICO")
public class TurnoMedico{
  @Id
  @Column(name="id")
  @GeneratedValue (strategy = GenerationType.AUTO)
  private Long id;  
  private LocalDate fecha;
  @ManyToOne
  @JoinColumn(name = "mascota_id")
  private Mascota mascota;
  @ManyToOne
  @JoinColumn(name="medico_id")
  private Medico medico;
  private boolean aprobado;
}

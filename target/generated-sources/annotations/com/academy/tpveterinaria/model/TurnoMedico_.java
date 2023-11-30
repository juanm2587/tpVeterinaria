package com.academy.tpveterinaria.model;

import com.academy.tpveterinaria.model.Mascota;
import com.academy.tpveterinaria.model.Medico;
import java.time.LocalDate;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-11-28T15:17:12", comments="EclipseLink-2.7.13.v20230724-rNA")
@StaticMetamodel(TurnoMedico.class)
public class TurnoMedico_ { 

    public static volatile SingularAttribute<TurnoMedico, LocalDate> fecha;
    public static volatile SingularAttribute<TurnoMedico, Medico> medico;
    public static volatile SingularAttribute<TurnoMedico, Mascota> mascota;
    public static volatile SingularAttribute<TurnoMedico, Boolean> aprobado;
    public static volatile SingularAttribute<TurnoMedico, Long> id;

}
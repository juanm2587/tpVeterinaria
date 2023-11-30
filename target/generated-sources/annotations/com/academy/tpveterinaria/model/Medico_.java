package com.academy.tpveterinaria.model;

import com.academy.tpveterinaria.model.TurnoMedico;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-11-28T15:17:12", comments="EclipseLink-2.7.13.v20230724-rNA")
@StaticMetamodel(Medico.class)
public class Medico_ { 

    public static volatile ListAttribute<Medico, TurnoMedico> turnos;
    public static volatile SingularAttribute<Medico, Long> id;
    public static volatile SingularAttribute<Medico, String> nombre;

}
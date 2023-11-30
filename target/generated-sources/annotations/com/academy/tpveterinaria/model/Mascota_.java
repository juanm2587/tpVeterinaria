package com.academy.tpveterinaria.model;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-11-28T15:17:12", comments="EclipseLink-2.7.13.v20230724-rNA")
@StaticMetamodel(Mascota.class)
public class Mascota_ { 

    public static volatile SingularAttribute<Mascota, String> Raza;
    public static volatile SingularAttribute<Mascota, Long> id;
    public static volatile SingularAttribute<Mascota, String> sexo;
    public static volatile SingularAttribute<Mascota, String> nombre;
    public static volatile SingularAttribute<Mascota, Integer> edad;

}
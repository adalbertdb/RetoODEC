package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "jugadors") // Asegura que el nombre de la tabla sea correcto
public class jugadors extends PanacheEntity {
    
    public String nom;
    public String equip;
    public int dorsal;
    public String lloc;
    public int sou;
}

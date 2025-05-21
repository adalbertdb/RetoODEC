package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class jugadors extends PanacheEntity {
    public String equip;
    public int dorsal;
    public String nom;
    public String lloc;
    public int sou;
}

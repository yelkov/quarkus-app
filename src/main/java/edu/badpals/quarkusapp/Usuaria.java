package edu.badpals.quarkusapp;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="usuarias")
public class Usuaria extends PanacheEntityBase {

    public Usuaria(){}

    public Usuaria(String nombre, int destreza) {
        this.nombre = nombre;
        this.destreza = destreza;
    }

    @Id
    @Column(name="nombre_usuaria")
    private String nombre;

    @Column(name="destreza")
    private int destreza;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDestreza() {
        return destreza;
    }

    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }
}

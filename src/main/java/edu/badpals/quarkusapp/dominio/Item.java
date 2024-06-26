package edu.badpals.quarkusapp.dominio;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="items")
public class Item extends PanacheEntityBase {
    public Item() {
    }

    public Item(String nombre) {
        this.nombre = nombre;
    }

    public Item(String nombre, int quality) {
        this.nombre = nombre;
        this.quality = quality;
    }

    public Item(String nombre, int quality, String tipo) {
        this.nombre = nombre;
        this.quality = quality;
        this.tipo = tipo;
    }

    @Id
    @Column(name="nombre_item")
    private String nombre = "";

    @Column(name="calidad")
    private int quality = 0;

    @Column(name="tipo")
    private String tipo;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Item{" +
                "nombre='" + nombre + '\'' +
                ", calidad=" + quality +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}

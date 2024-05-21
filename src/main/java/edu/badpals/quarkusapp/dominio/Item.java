package edu.badpals.quarkusapp.dominio;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="t_items")
public class Item extends PanacheEntityBase {
    public Item() {
    }

    public Item(String nombre, Integer quality) {
        this.nombre = nombre;
        this.quality = quality;
    }

    public Item(String nombre, Integer quality, String tipo) {
        this.nombre = nombre;
        this.quality = quality;
        this.tipo = tipo;
    }

    @Id
    @Column(name="item_nom")
    private String nombre = "";

    @Column(name="item_prop")
    private Integer quality = 0;

    @Column(name="item_tipo")
    private String tipo = "";

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getQuality() {
        return quality;
    }

    public void setQuality(Integer quality) {
        this.quality = quality;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}

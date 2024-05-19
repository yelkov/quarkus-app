package edu.badpals.quarkusapp;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="ordenes")
public class Orden extends PanacheEntityBase {

    public Orden() {
    }

    public Orden(Usuaria user, Item item) {
        this.user = user;
        this.item = item;
    }

    public Orden(int id, Usuaria user, Item item) {
        this.id = id;
        this.user = user;
        this.item = item;
    }

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_orden")
    public int id;

    @OneToOne
    @JoinColumn(name="usuaria",referencedColumnName = "nombre_usuaria")
    public Usuaria user;

    @OneToOne
    @JoinColumn(name="item", referencedColumnName = "nombre_item")
    public Item item;

    public static List<Orden> findByUserName(String nombre_usuaria) {
        List<Orden> pedidos = Orden.findAll().list();
        List<Orden> pedidosFiltrados = new ArrayList<>();
        Usuaria usuaria = Usuaria.find("nombre",nombre_usuaria).firstResult();
        if ( usuaria != null){
            for (Orden pedido : pedidos){
                if (pedido.getUser().getNombre().equals(nombre_usuaria)){
                    pedidosFiltrados.add(pedido);
                }
            }
        }
        return pedidosFiltrados;
    }

    /*public static List<Orden> findByUserName(String name) {
        List<Orden> ordenes = Orden.listAll();
        List<Orden> ordenesByName = ordenes
                .stream()
                .filter(o -> o.getUser().getNombre().equalsIgnoreCase(name))
                .collect(Collectors.toList());
        return ordenesByName.isEmpty()? List.of(): ordenesByName;
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuaria getUser() {
        return user;
    }

    public void setUser(Usuaria user) {
        this.user = user;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}

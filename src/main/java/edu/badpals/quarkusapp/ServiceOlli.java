package edu.badpals.quarkusapp;

import edu.badpals.quarkusapp.dominio.Item;
import edu.badpals.quarkusapp.dominio.Orden;
import edu.badpals.quarkusapp.dominio.Usuaria;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ServiceOlli {
    public Usuaria cargaUsuaria(String nombre_usuaria) {
        Optional<Usuaria> usuaria = Usuaria.findByIdOptional(nombre_usuaria);
        return usuaria.isPresent()?
                usuaria.get():
                new Usuaria();
    }


    public Item cargaItem(String nombre_item) {
        Optional<Item> item = Item.findByIdOptional(nombre_item);
        return item.isPresent()?
                item.get():
                new Item();
    }

    public List<Orden> cargaOrden(String nombre_usuaria) {
        List<Orden> ordenes = Orden.listAll();
        Optional<Usuaria> usuaria = Usuaria.findByIdOptional(nombre_usuaria);

        List<Orden> ordenesFiltradas = new ArrayList<>();
        for (Orden orden : ordenes){
            if(orden.getUser().getNombre().equals(nombre_usuaria)){
                ordenesFiltradas.add(orden);
            }
        }

        return ordenesFiltradas;
    }

    public Orden comanda(String nombre_usuaria, String nombre_item) {
        Usuaria usuaria = Usuaria.findById(nombre_usuaria);
        Item item = Item.findById(nombre_item);

        Orden orden = null;
        if( usuaria != null && item != null
            && usuaria.getDestreza() > item.getQuality()) {
           orden = new Orden(usuaria,item);
           orden.persist();
        }

        return orden;
    }

    public List<Orden> comandaMultiple(String nombre_usuaria, List<String> items) {
        Usuaria usuaria = Usuaria.findById(nombre_usuaria);
        List<Orden> ordenes = new ArrayList<>();

        if(usuaria != null){
            for(String item : items){
                Orden orden = comanda(nombre_usuaria,item);
                if (orden != null) {
                    ordenes.add(orden);
                }
            }
        }
        return ordenes;
    }
}

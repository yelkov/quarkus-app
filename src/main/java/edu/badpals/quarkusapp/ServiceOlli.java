package edu.badpals.quarkusapp;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ServiceOlli {
    public ServiceOlli(){

    }

    public Usuaria cargaUsuaria(String nombre_usuaria){
        Optional<Usuaria> usuaria = Usuaria.findByIdOptional(nombre_usuaria);
        return usuaria.isPresent()?
                usuaria.get():
                new Usuaria();
    }

    @Transactional
    public boolean isOrdenCreada(Usuaria user, Item item){
        Usuaria usuaria = Usuaria.find("nombre",user.getNombre()).firstResult();
        Item producto = Item.find("nombre",item.getNombre()).firstResult();
            if(usuaria != null && producto != null){
                Orden orden = new Orden(usuaria,producto);
                orden.persist();
                return true;
            }else{
                return false;
            }

    }


    public List<Orden> cargaOrden(String nombre_usuaria) {
        return Orden.findByUserName(nombre_usuaria);
    }

    public Item cargaItem(String nombre_item) {
        Optional<Item> item = Item.findByIdOptional(nombre_item);
        return item.isPresent()?
                item.get():
                new Item();
    }

    public Orden comanda(String nombre_usuaria, String nombre_item) {
        Usuaria usuaria = Usuaria.findById(nombre_usuaria);
        Item item = Item.findById(nombre_item);
        Orden orden = null;

        if (usuaria != null && item != null
            && usuaria.getDestreza() > item.getQuality()){
            orden = new Orden(usuaria,item);
            orden.persist();
            return orden;
        }
        return orden;
    }

    public List<Orden> comandaMultiple(String nombre_usuaria, List<String> items) {
        List<Orden> ordenesMultiples = new ArrayList<>();
        for(String item : items){
            ordenesMultiples.add(comanda(nombre_usuaria,item));
        }
        return ordenesMultiples;
    }


    /*public List<Orden> cargaOrden(String usuaria_nombre) {
        return Orden.findByUserName(usuaria_nombre);
    }*/
}

package edu.badpals.quarkusapp;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ServiceOllie {
    public ServiceOllie(){

    }

    @Transactional
    public boolean crearPedido(Usuaria user, Item item){
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

}

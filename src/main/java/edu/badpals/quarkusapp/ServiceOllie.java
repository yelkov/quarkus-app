package edu.badpals.quarkusapp;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ServiceOllie {
    public ServiceOllie(){

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


    public List<Orden> obtenerPedidoUsuaria(String nombre_usuaria) {
        return Orden.findByUserName(nombre_usuaria);
    }

    /*public List<Orden> cargaOrden(String usuaria_nombre) {
        return Orden.findByUserName(usuaria_nombre);
    }*/
}

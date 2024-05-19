package edu.badpals.quarkusapp;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ServiceOllie {
    public ServiceOllie(){

    }

    @Transactional
    public boolean crearPedido(Usuaria user, Item item){
        Orden orden = Orden.find("user",user).firstResult();
        if(orden != null ){
            return true;
        }else{
            if(Usuaria.find("nombre",user.getNombre()) != null && Item.find("nombre",item.getNombre()) != null){
                orden = new Orden(user,item);
                orden.persist();
                return true;
            }else{
                return false;
            }
        }
    }

}

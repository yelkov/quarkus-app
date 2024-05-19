package edu.badpals.quarkusapp;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@Path("/")
public class ResourcesOlli {

    @Inject
    ServiceOllie service;

    @GET
    @Path("wellcome")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Wellcome Ollivanders!";
    }

    @GET
    @Path("usuaria/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuaria(@PathParam("nombre")String nombre){
        Optional<Usuaria> usuaria = Usuaria.findByIdOptional(nombre);
        return usuaria.isPresent()?
                Response.status(Response.Status.OK).entity(usuaria).build():
                Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Path("ordena")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response orden(Orden orden){
        Usuaria usuaria = orden.getUser();
        Item item = orden.getItem();
        return service.isOrdenCreada(usuaria,item)?
                Response.status(201).entity(orden).build():
                Response.status(404).build();
    }


    @GET
    @Path("pedidos/{usuaria}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Orden> getPedidosUsuaria(@PathParam("usuaria")String nombre_usuaria){
        return service.obtenerPedidoUsuaria(nombre_usuaria);
    }

    /*@GET
    @Path("/pedidos/{usuaria}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    // curl -w "\n" http://localhost:8080/pedidos/Hermione -v
    public List<Orden> list(@PathParam("usuaria") String usuaria) {
        return service.cargaOrden(usuaria);
    }*/

    @GET
    @Path("item/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItem(@PathParam("nombre")String nombre_item){
        Optional<Item> item = Item.findByIdOptional(nombre_item);
        return item.isPresent()?
                Response.status(200).entity(item).build():
                Response.status(404).build();
    }

}

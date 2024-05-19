package edu.badpals.quarkusapp;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

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

}

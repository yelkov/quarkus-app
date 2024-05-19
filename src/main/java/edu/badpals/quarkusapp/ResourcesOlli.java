package edu.badpals.quarkusapp;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

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
}

package com.controller;

import com.model.Person;
import com.service.PersonServiceImpl;
import javax.ws.rs.core.Response;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/persons")
public class PersonController {

    PersonServiceImpl personServiceImpl ;

    public PersonController() {
        personServiceImpl= new PersonServiceImpl();
    }


    @GET
    @Path("/persons-select")
    public void getPerson() {
        personServiceImpl.getPerson();

    }
    @DELETE
    @Path("/person-delete")//  /person-delete;personId=5;
    public Response removePerson(@MatrixParam("personId") int personId) {
        try {
            personServiceImpl.removePerson(personId);
            return Response.status(204).entity("deleted").build();
        }catch (Exception e)
        {
            return Response.status(500).entity("not deleted").build();
        }

    }

    @PUT
    @Path("/person-update")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePerson(Person person) {
        try {
            personServiceImpl.updatePerson(person);
            return Response.status(200).entity("updated").build();
        }catch (Exception e)
        {
            return Response.status(500).entity("not updated"+e).build();
        }

    }
    @POST
    @Path("/person-insert")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPerson(Person person) {
        try {
            personServiceImpl.insertPerson(person);
            return Response.status(201).entity("created").build();
        }
        catch (Exception e)
        {
            return Response.status(500).entity("internal server").build();
        }

    }
}

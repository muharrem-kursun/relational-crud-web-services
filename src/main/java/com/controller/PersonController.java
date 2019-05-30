package com.controller;

import com.model.Person;
import com.service.PersonServiceImpl;

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
    public void removePerson(@MatrixParam("personId") int personId) {
        personServiceImpl.removePerson(personId);
    }

    @PUT
    @Path("/person-update")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updatePerson(Person person) {
        personServiceImpl.updatePerson(person);
    }
    @POST
    @Path("/person-insert")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addPerson( Person person) {
        personServiceImpl.insertPerson(person);


    }
}

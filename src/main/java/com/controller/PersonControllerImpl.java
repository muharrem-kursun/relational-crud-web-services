package com.controller;

import com.model.PersonModel;
import com.service.PersonServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/persons")
public class PersonControllerImpl implements PersonController{
    PersonServiceImpl personServiceImpl = new PersonServiceImpl();
    @GET
    @Path("/persons-select")
    public void personSelectController() {
        personServiceImpl.personSelectService();

    }
    @DELETE
    @Path("/person-delete")//  /person-delete;personId=5;
    public void personDeleteController(@MatrixParam("personId") int personId) {
        personServiceImpl.personDeleteService(personId);
    }

    @PUT
    @Path("/person-update")
    @Consumes(MediaType.APPLICATION_JSON)
    public void personUpdateController(PersonModel personModel) {
        personServiceImpl.personUpdateService(personModel);
    }
    @POST
    @Path("/person-insert")
    @Consumes(MediaType.APPLICATION_JSON)
    public void personInsertController( PersonModel personModel) {
        personServiceImpl.personInsertService(personModel);


    }
}

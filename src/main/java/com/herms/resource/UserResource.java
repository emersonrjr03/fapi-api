package com.herms.resource;

import com.herms.model.Resource;
import com.herms.model.User;
import com.herms.model.UserRegistration;
import com.herms.pages.PageRequest;
import com.herms.service.ResourceService;
import com.herms.service.UserService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
public class UserResource {
    @Inject
    UserService service;

    @GET
    @Transactional
    public Response get(@BeanParam PageRequest pageRequest) {
        return service.getAllPaged(pageRequest);
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") Long id) {
        return service.getById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(UserRegistration model) {
        return service.create(model);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, User model) {
        return service.update(id, model);
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        return service.delete(id);
    }

}

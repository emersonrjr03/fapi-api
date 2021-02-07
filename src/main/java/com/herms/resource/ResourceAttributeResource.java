package com.herms.resource;

import com.herms.model.Resource;
import com.herms.model.ResourceAttribute;
import com.herms.pages.PageRequest;
import com.herms.service.ResourceAttributeService;
import com.herms.service.ResourceService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/attribute")
public class ResourceAttributeResource {
    @Inject
    ResourceAttributeService service;

    @GET
    @Transactional
    public Response get(@BeanParam PageRequest pageRequest) {
        return service.getAllPaged(pageRequest);
    }

    @GET
    @Path("{id}")
    @Transactional
    public Response getById(@PathParam("id") Long id) {
        return service.getById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(ResourceAttribute model) {
        return service.create(model);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, ResourceAttribute model) {
        return service.update(id, model);
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        return service.delete(id);
    }

}

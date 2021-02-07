package com.herms.resource;

import com.herms.service.GenericService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("{apiSecret}")
public class GenericResource {

    @Inject
    private GenericService service;

    @GET
    @Path("/{resourceName}")
    @Transactional
    public Response getById(@PathParam("apiSecret") String apiSecret, @PathParam("resourceName") String resourceName) {
        return service.getAllPaged(apiSecret, resourceName);
    }

}

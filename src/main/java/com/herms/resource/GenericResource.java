package com.herms.resource;

import com.herms.model.Resource;
import com.herms.service.GenericService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

@Path("{apiSecret}")
public class GenericResource {

    @Inject
    private GenericService service;

    @GET
    @Path("/{resourceName}")
    @Transactional
    public Response get(@PathParam("apiSecret") String apiSecret,
                            @PathParam("resourceName") String resourceName) {
        return service.getAllPaged(apiSecret, resourceName);
    }

    @GET
    @Path("/{resourceName}/{resourceId}")
    @Transactional
    public Response getById(@PathParam("apiSecret") String apiSecret,
                            @PathParam("resourceName") String resourceName,
                            @PathParam("resourceId") String resourceId) {
        return service.getById(apiSecret, resourceName, resourceId);
    }

    @POST
    @Path("/{resourceName}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(@PathParam("apiSecret") String apiSecret,
                           @PathParam("resourceName") String resourceName,
                           Map<String, Object> row) {
        return service.create(apiSecret, resourceName, row);
    }

}

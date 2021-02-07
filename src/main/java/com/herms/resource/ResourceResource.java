package com.herms.resource;

import com.herms.model.Resource;
import com.herms.pages.PageRequest;
import com.herms.service.ResourceService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/resource")
public class ResourceResource {
    @Inject
    ResourceService service;

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
    public Response create(Resource resource) {
        return service.create(resource);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, Resource resource) {
        return service.update(id, resource);
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        return service.delete(id);
    }

//    @GET
//    @Path("/{id}")
//    public Set<ResourceData> getAll(@PathParam("id") Long id) {
//        UserEntity user = new UserEntity(1000003L, "emersonrjr03@gmail.com", "18081990", "696969");
//
//
//        ResourceEntity r = new ResourceEntity(null, "person", Arrays.<ResourceAttributeEntity>asList(), user);
//        service.create(r);
//        Map content = new HashMap();
//        content.put("nome", "João");
//        content.put("idade", "25");
//
//        ResourceData data = new ResourceData(1L, r, content);
//
//        return Collections.singleton(data);
//    }



}

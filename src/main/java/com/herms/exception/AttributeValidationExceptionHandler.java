package com.herms.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AttributeValidationExceptionHandler implements ExceptionMapper<AttributeValidationException> {
    @Override
    public Response toResponse(AttributeValidationException exception) {
        return Response.status(exception.getStatus()).entity(exception.getResposeObj()).build();
    }
}
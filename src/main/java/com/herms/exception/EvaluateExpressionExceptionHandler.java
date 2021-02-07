package com.herms.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class EvaluateExpressionExceptionHandler implements ExceptionMapper<EvaluateExpressionException> {
    @Override
    public Response toResponse(EvaluateExpressionException exception) {
        return Response.status(exception.getStatus()).entity(exception.getMessage()).build();
    }
}
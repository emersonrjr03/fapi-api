package com.herms.exception;

import javax.ws.rs.core.Response;

public class AttributeValidationException extends RuntimeException {

    private Object resposeObj;
    private Response.Status status;

    public AttributeValidationException(Object resposeObj) {
        this(resposeObj, Response.Status.BAD_REQUEST);
    }

    public AttributeValidationException(Object resposeObj, Response.Status status) {
        this.resposeObj = resposeObj;
        this.status = status;
    }

    public Object getResposeObj() {
        return resposeObj;
    }

    public Response.Status getStatus() {
        return status;
    }
}

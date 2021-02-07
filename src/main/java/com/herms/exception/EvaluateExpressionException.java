package com.herms.exception;

import javax.ws.rs.core.Response;

public class EvaluateExpressionException extends RuntimeException {

    private String message;
    private Response.Status status;

    public EvaluateExpressionException(String message) {
        this.message = message;
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Response.Status getStatus() {
        return status;
    }
}

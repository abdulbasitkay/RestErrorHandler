package com.abulkay.errorhandling;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by akay.
 */
@Provider
public class GeneralApiExceptionMapper implements ExceptionMapper<ApiException> {

    @Override
    public Response toResponse(ApiException ex) {

        ApiErrorDto apiError = new ApiErrorDto();
        apiError.setStatus(ex.getStatus());
        apiError.setMessage(ex.getMessage());
        apiError.setDeveloperMessage(ex.getDeveloperMessage());

        return Response.status(ex.getStatus())
                .entity(apiError)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}

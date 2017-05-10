package com.abulkay.errorhandling;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by akay.
 */
@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable ex) {

        ApiErrorDto apiError = new ApiErrorDto();
        setHttpStatus(ex, apiError);
        apiError.setMessage(ex.getMessage());
        StringWriter errorStackTrace = new StringWriter();
        ex.printStackTrace(new PrintWriter(errorStackTrace));
        apiError.setDeveloperMessage(errorStackTrace.toString());

        return Response.status(apiError.getStatus())
                .entity(apiError)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    private void setHttpStatus(Throwable ex, ApiErrorDto errorerrorDto) {
        if(ex instanceof WebApplicationException) {
            errorerrorDto.setStatus(((WebApplicationException)ex).getResponse().getStatus());
        } else {
            errorerrorDto.setStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
        }
    }
}

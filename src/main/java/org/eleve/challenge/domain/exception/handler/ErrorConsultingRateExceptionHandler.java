package org.eleve.challenge.domain.exception.handler;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;
import org.eleve.challenge.domain.exception.ErrorConsultingRateException;
import org.eleve.challenge.domain.exception.ErrorResponse;

@Slf4j
@Provider
public class ErrorConsultingRateExceptionHandler implements ExceptionMapper<ErrorConsultingRateException> {

    @Override
    public Response toResponse(ErrorConsultingRateException e) {
        log.error("Error on check rate: {}", e.getMessage());

        var status = Response.Status.BAD_REQUEST;
        var errorResponse = new ErrorResponse(
            status.getStatusCode(),
            e.getMessage()
        );

        return Response.status(status)
            .entity(errorResponse)
            .build();
    }

}

package org.eleve.challenge.domain.exception.handler;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;
import org.eleve.challenge.domain.exception.ErrorResponse;
import org.hibernate.exception.SQLGrammarException;

@Slf4j
@Provider
public class SQLGrammarExceptionHandler implements ExceptionMapper<SQLGrammarException> {

    @Override
    public Response toResponse(SQLGrammarException e) {
        log.error("Error on execute database operation: {}", e.getMessage());

        var status = Response.Status.INTERNAL_SERVER_ERROR;
        var errorResponse = new ErrorResponse(
            status.getStatusCode(),
            e.getMessage()
        );

        return Response.status(status)
            .entity(errorResponse)
            .build();
    }

}

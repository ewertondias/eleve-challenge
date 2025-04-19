package org.eleve.challenge.application.openapi;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eleve.challenge.application.dto.ExchangeRateResponse;
import org.eleve.challenge.domain.exception.ErrorResponse;

@Path("/exchange-rate")
@Tag(name = "Exchange Rate")
@Produces(MediaType.APPLICATION_JSON)
public interface ExchangeRateControllerOpenApi {

    @Operation(summary = "Get conversion rate between two currencies")
    @APIResponses(value = {
        @APIResponse(responseCode = "200", description = "OK", content = {
            @Content(schema = @Schema(implementation = ExchangeRateResponse.class))
        }),
        @APIResponse(responseCode = "400", description = "Bad Request", content = {
            @Content(schema = @Schema(implementation = ErrorResponse.class))
        }),
        @APIResponse(responseCode = "500", description = "Internal Server Error", content = {
            @Content(schema = @Schema(implementation = ErrorResponse.class))
        })
    })
    @GET
    @Path("/conversion/{rate-base}/{rate-target}")
    Response getConversionRate(@PathParam("rate-base") String rateBase, @PathParam("rate-target") String rateTarget);

}

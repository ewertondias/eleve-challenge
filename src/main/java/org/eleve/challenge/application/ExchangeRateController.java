package org.eleve.challenge.application;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eleve.challenge.application.openapi.ExchangeRateControllerOpenApi;
import org.eleve.challenge.domain.GetConversionRateUseCase;

@Path("/exchange-rate")
@Produces(MediaType.APPLICATION_JSON)
public class ExchangeRateController implements ExchangeRateControllerOpenApi {

    private final GetConversionRateUseCase getConversionRate;

    public ExchangeRateController(GetConversionRateUseCase getConversionRate) {
        this.getConversionRate = getConversionRate;
    }

    @Override
    @GET
    @Path("/conversion/{ratebase}/{ratetarget}")
    public Response getConversionRate(@PathParam("ratebase") String rateBase, @PathParam("ratetarget") String rateTarget) {
        var conversionRateResponse = getConversionRate.handle(rateBase, rateTarget);

        return Response.ok(conversionRateResponse)
            .build();
    }

}

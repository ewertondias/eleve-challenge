package org.eleve.challenge.application;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import org.eleve.challenge.application.openapi.ExchangeRateControllerOpenApi;
import org.eleve.challenge.domain.GetConversionRateUseCase;

@ApplicationScoped
public class ExchangeRateController implements ExchangeRateControllerOpenApi {

    private final GetConversionRateUseCase getConversionRate;

    public ExchangeRateController(GetConversionRateUseCase getConversionRate) {
        this.getConversionRate = getConversionRate;
    }

    @Override
    public Response getConversionRate(String rateBase, String rateTarget) {
        var conversionRateResponse = getConversionRate.handle(rateBase, rateTarget);

        return Response.ok(conversionRateResponse)
            .build();
    }

}

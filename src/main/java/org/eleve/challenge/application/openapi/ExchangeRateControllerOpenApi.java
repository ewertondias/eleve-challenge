package org.eleve.challenge.application.openapi;

import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

public interface ExchangeRateControllerOpenApi {

    Response getConversionRate(@PathParam("ratebase") String rateBase, @PathParam("ratetarget") String rateTarget);

}

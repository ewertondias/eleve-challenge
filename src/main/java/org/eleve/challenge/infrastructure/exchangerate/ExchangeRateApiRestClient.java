package org.eleve.challenge.infrastructure.exchangerate;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@ApplicationScoped
@RegisterRestClient(configKey = "exchange-rate-api")
public interface ExchangeRateApiRestClient {

    @GET
    @Path("/pair/{rateBase}/{rateTarget}")
    ExchangeRateApiResponse pairConversion(@PathParam("rateBase") String rateBase, @PathParam("rateTarget") String rateTarget);

}

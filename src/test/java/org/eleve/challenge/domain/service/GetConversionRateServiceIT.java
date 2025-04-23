package org.eleve.challenge.domain.service;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.eleve.challenge.domain.PostgresConfigIT;
import org.eleve.challenge.domain.WireMockConfigIT;
import org.eleve.challenge.domain.repository.ExchangeRepository;
import org.eleve.challenge.infrastructure.exchangerate.ExchangeRateApiRestClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Get Conversion Rate - Integration - Service")
@QuarkusTest
@Testcontainers
@QuarkusTestResource(PostgresConfigIT.class)
class GetConversionRateServiceIT extends WireMockConfigIT {

    @Inject
    GetConversionRateService getConversionRateService;

    @Inject
    @RestClient
    ExchangeRateApiRestClient exchangeRateApiRestClient;

    @Inject
    ExchangeRepository repository;

    @DisplayName("Should get the conversion rate")
    @Test
    void shouldGetConversionRate() {
        var rateBase = "BRL";
        var rateTarget = "USD";

        WireMockConfigIT.exchangeRateApiSuccess(rateBase, rateTarget);

        var exchangeRateResponse = getConversionRateService.handle(rateBase, rateTarget);

        var exchangeRateCreate = repository.find(exchangeRateResponse.getId())
            .orElse(null);

        assertNotNull(exchangeRateCreate);
        assertNotNull(exchangeRateCreate.getId());
        assertEquals(new BigDecimal("0.17"), exchangeRateCreate.getRateConversion());
    }

}

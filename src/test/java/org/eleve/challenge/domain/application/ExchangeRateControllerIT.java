package org.eleve.challenge.domain.application;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.eleve.challenge.domain.PostgresConfigIT;
import org.eleve.challenge.domain.WireMockConfigIT;
import org.eleve.challenge.domain.repository.ExchangeRepository;
import org.eleve.challenge.domain.service.GetConversionRateService;
import org.eleve.challenge.infrastructure.exchangerate.ExchangeRateApiRestClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Testcontainers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

@DisplayName("Get Conversion Rate - Integration - Controller")
@QuarkusTest
@Testcontainers
@QuarkusTestResource(PostgresConfigIT.class)
class ExchangeRateControllerIT extends WireMockConfigIT {

    @Inject
    GetConversionRateService getConversionRateService;

    @Inject
    @RestClient
    ExchangeRateApiRestClient exchangeRateApiRestClient;

    @Inject
    ExchangeRepository repository;

    @Test
    @DisplayName("Should get the conversion rate")
    void shouldGetConversionRate() {
        var rateBase = "BRL";
        var rateTarget = "USD";

        WireMockConfigIT.exchangeRateApiSuccess(rateBase, rateTarget);

        given()
            .pathParam("rateBase", rateBase)
            .pathParam("rateTarget", rateTarget)
            .when()
            .get("/exchange-rate/conversion/{rateBase}/{rateTarget}")
            .then()
            .statusCode(200)
            .contentType(MediaType.APPLICATION_JSON)
            .body("id", notNullValue())
            .body("rateBase", is(rateBase))
            .body("rateTarget", is(rateTarget))
            .body("rateConversion", is(0.1722F));
    }

    @Test
    @DisplayName("Not should get the conversion rate with invalid params")
    void notShouldGetConversionRateWithInvalidParams() {
        var rateBase = "BRL123";
        var rateTarget = "USDA";

        WireMockConfigIT.exchangeRateApiSuccess(rateBase, rateTarget);

        given()
            .pathParam("rateBase", rateBase)
            .pathParam("rateTarget", rateTarget)
            .when()
            .get("/exchange-rate/conversion/{rateBase}/{rateTarget}")
            .then()
            .statusCode(400);
    }

}
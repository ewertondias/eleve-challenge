package org.eleve.challenge.domain.application;

import jakarta.ws.rs.core.Response;
import org.eleve.challenge.application.ExchangeRateController;
import org.eleve.challenge.domain.repository.ExchangeRepository;
import org.eleve.challenge.domain.service.GetConversionRateService;
import org.eleve.challenge.domain.util.ExchangeRateTestFactory;
import org.eleve.challenge.infrastructure.exchangerate.ExchangeRateApiRestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@DisplayName("Get Conversion Rate - Service")
@ExtendWith(MockitoExtension.class)
class ExchangeRateControllerTest {

    ExchangeRateController exchangeRateController;

    @Mock
    ExchangeRateApiRestClient exchangeRateApiRestClient;

    @Mock
    ExchangeRepository repository;

    @BeforeEach
    void setUp() {
        var getConversionRateService = new GetConversionRateService(exchangeRateApiRestClient, repository);
        exchangeRateController = new ExchangeRateController(getConversionRateService);
    }

    @Test
    @DisplayName("Should get the conversion rate")
    void shouldGetConversionRate() {
        var rateBase = "BRL";
        var rateTarget = "USD";

        when(exchangeRateApiRestClient.pairConversion(anyString(), anyString())).thenReturn(ExchangeRateTestFactory.oneExchangeRateApiResponse());
        when(repository.save(any())).thenReturn(ExchangeRateTestFactory.oneExchangeRate());

        Response response = exchangeRateController.getConversionRate(rateBase, rateTarget);

        assertNotNull(response);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

}

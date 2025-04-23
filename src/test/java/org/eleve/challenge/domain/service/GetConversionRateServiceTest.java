package org.eleve.challenge.domain.service;

import org.eleve.challenge.domain.exception.ErrorConsultingRateException;
import org.eleve.challenge.domain.repository.ExchangeRepository;
import org.eleve.challenge.domain.util.ExchangeRateTestFactory;
import org.eleve.challenge.infrastructure.exchangerate.ExchangeRateApiRestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@DisplayName("Get Conversion Rate - Service")
@ExtendWith(MockitoExtension.class)
class GetConversionRateServiceTest {

    GetConversionRateService getConversionRateService;

    @Mock
    ExchangeRateApiRestClient exchangeRateApiRestClient;

    @Mock
    ExchangeRepository repository;

    @BeforeEach
    void setUp() {
        getConversionRateService = new GetConversionRateService(exchangeRateApiRestClient, repository);
    }

    @Test
    @DisplayName("Should get the conversion rate")
    void shouldGetConversionRate() {
        var rateBase = "BRL";
        var rateTarget = "USD";

        when(exchangeRateApiRestClient.pairConversion(anyString(), anyString())).thenReturn(ExchangeRateTestFactory.oneExchangeRateApiResponse());
        when(repository.save(any())).thenReturn(ExchangeRateTestFactory.oneExchangeRate());

        getConversionRateService.handle(rateBase, rateTarget);
    }

    @Test
    @DisplayName("Given invalid parameters should return ErrorConsultingRateException")
    void shouldReturnErrorConsultingRateException() {
        var rateBase = "BRL1234";
        var rateTarget = "USD5678";

        when(exchangeRateApiRestClient.pairConversion(anyString(), anyString())).thenReturn(null);

        assertThrows(ErrorConsultingRateException.class, () -> getConversionRateService.handle(rateBase, rateTarget));
    }

}

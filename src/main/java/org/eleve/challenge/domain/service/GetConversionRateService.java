package org.eleve.challenge.domain.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.eleve.challenge.application.dto.ExchangeRateResponse;
import org.eleve.challenge.domain.GetConversionRateUseCase;
import org.eleve.challenge.domain.assembler.ExchangeRateAssembler;
import org.eleve.challenge.infrastructure.exchangerate.ExchangeRateApiRestClient;

import java.time.LocalDateTime;

@ApplicationScoped
public class GetConversionRateService implements GetConversionRateUseCase {

    private final ExchangeRateApiRestClient exchangeRateApiRestClient;

    public GetConversionRateService(@RestClient ExchangeRateApiRestClient exchangeRateApiRestClient) {
        this.exchangeRateApiRestClient = exchangeRateApiRestClient;
    }

    @Override
    public ExchangeRateResponse handle(String rateBase, String rateTarget) {
        var date = LocalDateTime.now().toString();
        var result = exchangeRateApiRestClient.pairConversion(rateBase, rateTarget);

        return ExchangeRateAssembler.toExchangeRateResponse(result, date);
    }

}

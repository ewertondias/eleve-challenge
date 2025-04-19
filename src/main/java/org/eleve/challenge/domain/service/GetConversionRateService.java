package org.eleve.challenge.domain.service;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.eleve.challenge.application.dto.ExchangeRateResponse;
import org.eleve.challenge.domain.GetConversionRateUseCase;
import org.eleve.challenge.domain.assembler.ExchangeRateAssembler;
import org.eleve.challenge.domain.exception.ErrorConsultingRateException;
import org.eleve.challenge.infrastructure.exchangerate.ExchangeRateApiRestClient;

import java.time.LocalDateTime;

@ApplicationScoped
@Slf4j
public class GetConversionRateService implements GetConversionRateUseCase {

    private final ExchangeRateApiRestClient exchangeRateApiRestClient;

    public GetConversionRateService(@RestClient ExchangeRateApiRestClient exchangeRateApiRestClient) {
        this.exchangeRateApiRestClient = exchangeRateApiRestClient;
    }

    @Override
    public ExchangeRateResponse handle(String rateBase, String rateTarget) {
        log.info("Start check rate");

        try {
            var dateConsultation = LocalDateTime.now();
            var result = exchangeRateApiRestClient.pairConversion(rateBase, rateTarget);

            return ExchangeRateAssembler.toExchangeRateResponse(result, dateConsultation);
        } catch (Exception e) {
            throw new ErrorConsultingRateException(e);
        }
    }

}

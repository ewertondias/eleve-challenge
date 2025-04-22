package org.eleve.challenge.domain.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.eleve.challenge.application.dto.ExchangeRateResponse;
import org.eleve.challenge.domain.GetConversionRateUseCase;
import org.eleve.challenge.domain.assembler.ExchangeRateAssembler;
import org.eleve.challenge.domain.exception.ErrorConsultingRateException;
import org.eleve.challenge.domain.repository.ExchangeRepository;
import org.eleve.challenge.infrastructure.exchangerate.ExchangeRateApiRestClient;

import java.time.LocalDateTime;

@ApplicationScoped
@Slf4j
public class GetConversionRateService implements GetConversionRateUseCase {

    private final ExchangeRateApiRestClient exchangeRateApiRestClient;
    private final ExchangeRepository repository;

    public GetConversionRateService(@RestClient ExchangeRateApiRestClient exchangeRateApiRestClient, ExchangeRepository repository) {
        this.exchangeRateApiRestClient = exchangeRateApiRestClient;
        this.repository = repository;
    }

    @Override
    @Transactional
    public ExchangeRateResponse handle(String rateBase, String rateTarget) {
        log.info("Start get conversion rate from {} to {}", rateBase, rateTarget);

        try {
            var dateConsultation = LocalDateTime.now();
            var result = exchangeRateApiRestClient.pairConversion(rateBase, rateTarget);

            var exchangeRate = ExchangeRateAssembler.toExchange(result, dateConsultation);
            var exchange = repository.save(exchangeRate);

            return ExchangeRateAssembler.toExchangeRateResponse(exchange, dateConsultation);
        } catch (Exception e) {
            throw new ErrorConsultingRateException(e);
        }
    }

}

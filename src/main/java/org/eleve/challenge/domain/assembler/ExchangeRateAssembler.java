package org.eleve.challenge.domain.assembler;

import org.eleve.challenge.application.dto.ExchangeRateResponse;
import org.eleve.challenge.domain.ExchangeRate;
import org.eleve.challenge.infrastructure.exchangerate.ExchangeRateApiResponse;

import java.time.LocalDateTime;

public class ExchangeRateAssembler {

    public static ExchangeRateResponse toExchangeRateResponse(ExchangeRate exchangeRate, LocalDateTime dateConsultation) {
        return new ExchangeRateResponse(
            exchangeRate.getId(),
            exchangeRate.getRateBase(),
            exchangeRate.getRateTarget(),
            exchangeRate.getRateConversion(),
            dateConsultation
        );
    }

    public static ExchangeRate toExchange(ExchangeRateApiResponse exchangeResponse, LocalDateTime dateConsultation) {
        return ExchangeRate.builder()
            .rateBase(exchangeResponse.getBaseCode())
            .rateTarget(exchangeResponse.getTargetCode())
            .rateConversion(exchangeResponse.getConversionRate())
            .dateConsultation(dateConsultation)
            .build();
    }

}

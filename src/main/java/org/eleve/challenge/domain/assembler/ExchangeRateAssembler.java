package org.eleve.challenge.domain.assembler;

import org.eleve.challenge.application.dto.ExchangeRateResponse;
import org.eleve.challenge.infrastructure.exchangerate.ExchangeRateApiResponse;

import java.time.LocalDateTime;

public class ExchangeRateAssembler {

    public static ExchangeRateResponse toExchangeRateResponse(ExchangeRateApiResponse response, LocalDateTime dateConsultation) {
        return new ExchangeRateResponse(
            response.getBaseCode(),
            response.getTargetCode(),
            response.getConversionRate(),
            dateConsultation
        );
    }

}

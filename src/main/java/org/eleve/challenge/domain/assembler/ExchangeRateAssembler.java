package org.eleve.challenge.domain.assembler;

import org.eleve.challenge.application.dto.ExchangeRateResponse;
import org.eleve.challenge.infrastructure.exchangerate.ExchangeRateApiResponse;

public class ExchangeRateAssembler {

    public static ExchangeRateResponse toExchangeRateResponse(ExchangeRateApiResponse response, String date) {
        return new ExchangeRateResponse(
            response.base_code(),
            response.target_code(),
            response.conversion_rate(),
            date
        );
    }

}

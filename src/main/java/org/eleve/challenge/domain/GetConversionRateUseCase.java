package org.eleve.challenge.domain;

import org.eleve.challenge.application.dto.ExchangeRateResponse;

public interface GetConversionRateUseCase {

    ExchangeRateResponse handle(String rateBase, String rateTarget);

}

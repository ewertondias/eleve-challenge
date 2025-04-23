package org.eleve.challenge.domain.util;

import org.eleve.challenge.domain.ExchangeRate;
import org.eleve.challenge.infrastructure.exchangerate.ExchangeRateApiResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class ExchangeRateTestFactory {

    private static final String rateBase = "EUR";
    private static final String rateTarget = "GBP";
    private static final BigDecimal rateConversion = new BigDecimal("0.8567");
    private static final LocalDateTime dateConsultation = LocalDateTime.now();

    public static ExchangeRateApiResponse oneExchangeRateApiResponse() {
        return new ExchangeRateApiResponse(
            "success",
            rateConversion,
            rateBase,
            rateTarget
        );
    }

    public static ExchangeRate oneExchangeRate() {
        return new ExchangeRate(
            UUID.randomUUID(),
            rateBase,
            rateTarget,
            rateConversion,
            dateConsultation
        );
    }

}

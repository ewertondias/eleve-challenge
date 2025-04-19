package org.eleve.challenge.application.dto;

import java.math.BigDecimal;

public record ExchangeRateResponse(String rateBase, String rateTarget, BigDecimal rateConversion, String date) {
}

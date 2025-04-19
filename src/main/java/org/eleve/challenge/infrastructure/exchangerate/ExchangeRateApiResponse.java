package org.eleve.challenge.infrastructure.exchangerate;

import java.math.BigDecimal;

public record ExchangeRateApiResponse(String result, BigDecimal conversion_rate, String base_code, String target_code) {
}

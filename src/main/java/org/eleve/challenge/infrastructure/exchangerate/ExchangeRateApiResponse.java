package org.eleve.challenge.infrastructure.exchangerate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ExchangeRateApiResponse {

    private String result;

    @JsonProperty("conversion_rate")
    private BigDecimal conversionRate;

    @JsonProperty("base_code")
    private String baseCode;

    @JsonProperty("target_code")
    private String targetCode;

}

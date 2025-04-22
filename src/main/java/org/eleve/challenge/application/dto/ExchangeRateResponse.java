package org.eleve.challenge.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class ExchangeRateResponse {

    @Schema(description = "Id exchange rate", examples = "3f6f778e-9f68-4858-9424-669113f03baf")
    private UUID id;

    @Schema(description = "Base currency", examples = "BRL")
    private String rateBase;

    @Schema(description = "Target currency", examples = "USD")
    private String rateTarget;

    @Schema(description = "Rate conversion", examples = "0.1234")
    private BigDecimal rateConversion;

    @Schema(description = "Date of consultation", examples = "2025-04-18T22:45:27.804058")
    private LocalDateTime dateConsultation;

}

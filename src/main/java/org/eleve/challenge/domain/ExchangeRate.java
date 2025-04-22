package org.eleve.challenge.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ExchangeRate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String rateBase;
    private String rateTarget;
    private BigDecimal rateConversion;
    private LocalDateTime dateConsultation;

}

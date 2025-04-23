package org.eleve.challenge.domain.repository;

import org.eleve.challenge.domain.ExchangeRate;

import java.util.Optional;
import java.util.UUID;

public interface ExchangeRepository {

    ExchangeRate save(ExchangeRate exchangeRate);
    Optional<ExchangeRate> find(UUID id);

}

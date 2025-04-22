package org.eleve.challenge.domain.repository;

import org.eleve.challenge.domain.ExchangeRate;

public interface ExchangeRepository {

    ExchangeRate save(ExchangeRate exchangeRate);

}

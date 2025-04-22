package org.eleve.challenge.infrastructure.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.eleve.challenge.domain.ExchangeRate;
import org.eleve.challenge.domain.repository.ExchangeRepository;

@ApplicationScoped
public class ExchangeRepositoryJpa implements ExchangeRepository, PanacheRepository<ExchangeRate> {

    @Override
    public ExchangeRate save(ExchangeRate exchangeRate) {
        persist(exchangeRate);

        return exchangeRate;
    }

}

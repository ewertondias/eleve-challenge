package org.eleve.challenge.infrastructure.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.eleve.challenge.domain.ExchangeRate;
import org.eleve.challenge.domain.repository.ExchangeRepository;

import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class ExchangeRepositoryJpa implements ExchangeRepository, PanacheRepositoryBase<ExchangeRate, UUID> {

    @Override
    public ExchangeRate save(ExchangeRate exchangeRate) {
        persist(exchangeRate);

        return exchangeRate;
    }

    @Override
    public Optional<ExchangeRate> find(UUID id) {
        return findByIdOptional(id);
    }

}

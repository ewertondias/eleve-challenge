package org.eleve.challenge.domain;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.HashMap;
import java.util.Map;

public class PostgresConfigIT implements QuarkusTestResourceLifecycleManager {

    private static final PostgreSQLContainer<?> POSTGRES = new PostgreSQLContainer<>("postgres:13-alpine")
        .withDatabaseName("exchange")
        .withUsername("exchange")
        .withPassword("exchange");

    @Override
    public Map<String, String> start() {
        POSTGRES.start();

        Map<String, String> config = new HashMap<>();
        config.put("quarkus.datasource.jdbc.url", POSTGRES.getJdbcUrl());
        config.put("quarkus.datasource.username", POSTGRES.getUsername());
        config.put("quarkus.datasource.password", POSTGRES.getPassword());
        config.put("quarkus.datasource.db-kind", "postgresql");
        config.put("quarkus.hibernate-orm.database.generation", "drop-and-create");

        return config;
    }

    @Override
    public void stop() {
        POSTGRES.stop();
    }

}

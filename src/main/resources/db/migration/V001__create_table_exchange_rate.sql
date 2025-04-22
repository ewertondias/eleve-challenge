CREATE TABLE IF NOT EXISTS exchange_rate (
    id                  UUID            PRIMARY KEY NOT NULL,
    rate_base           VARCHAR(3)      NOT NULL,
    rate_target         VARCHAR(3)      NOT NULL,
    rate_conversion     NUMERIC(10, 4)  NOT NULL,
    date_consultation   TIMESTAMP       NOT NULL
);
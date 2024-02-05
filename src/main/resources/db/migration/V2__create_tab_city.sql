CREATE TABLE tab_city
(
    id           BIGSERIAL PRIMARY KEY,
    city_name    VARCHAR(255) NOT NULL,
    country_name VARCHAR(255),
    logo         BYTEA
);
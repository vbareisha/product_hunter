DROP SCHEMA IF EXISTS product_hunter_test CASCADE;
CREATE SCHEMA product_hunter_test;

DROP TABLE IF EXISTS product_hunter_test.products CASCADE;
CREATE TABLE product_hunter_test.products (
  id                     BIGSERIAL PRIMARY KEY,
  title                  VARCHAR NOT NULL,
  country                VARCHAR NOT NULL,
  dt_update              TIMESTAMP WITHOUT TIME ZONE,
  uuid                   UUID    NOT NULL,
  price                  NUMERIC,
  price_discount         NUMERIC
);
CREATE UNIQUE INDEX product_uuid_idx
  ON product_hunter_test.products (uuid);

COMMENT ON TABLE product_hunter_test.products IS 'Таблица с продуктами';


DROP SCHEMA IF EXISTS product_hunter CASCADE;
CREATE SCHEMA product_hunter;

DROP TABLE IF EXISTS product_hunter.products CASCADE;
CREATE TABLE product_hunter.products (
  id                     BIGSERIAL PRIMARY KEY,
  title                  VARCHAR NOT NULL,
  country                VARCHAR NOT NULL,
  dt_update              TIMESTAMP WITHOUT TIME ZONE,
  uuid                   UUID    NOT NULL,
  price                  NUMERIC,
  price_discount         NUMERIC,
  img                    VARCHAR NOT NULL
);
CREATE UNIQUE INDEX product_uuid_idx
  ON product_hunter.products (uuid);

COMMENT ON TABLE product_hunter.products IS 'Таблица с продуктами';

CREATE USER production WITH ENCRYPTED PASSWORD 'root';
GRANT ALL PRIVILEGES ON DATABASE product_hunter TO production;
--ALTER USER postgres WITH ENCRYPTED PASSWORD '';
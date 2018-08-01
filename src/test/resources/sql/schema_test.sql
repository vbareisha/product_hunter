DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (
  id                     BIGSERIAL PRIMARY KEY,
  title                  VARCHAR NOT NULL,
  country                VARCHAR NOT NULL,
  dt_update              TIMESTAMP WITHOUT TIME ZONE,
  uuid                   UUID    NOT NULL,
  price                  NUMERIC,
  price_discount         NUMERIC
);
CREATE UNIQUE INDEX product_uuid_idx
  ON products (uuid);

COMMENT ON TABLE products IS 'Таблица с продуктами';


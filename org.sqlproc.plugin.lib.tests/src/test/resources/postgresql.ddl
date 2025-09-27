DROP TABLE IF EXISTS address CASCADE;
DROP TABLE IF EXISTS person CASCADE;

CREATE TABLE person (
    id BIGSERIAL PRIMARY KEY,
    full_name VARCHAR(120) NOT NULL,
    created_at TIMESTAMPTZ DEFAULT NOW()
);

CREATE TABLE address (
    id BIGSERIAL PRIMARY KEY,
    person_id BIGINT NOT NULL REFERENCES person(id),
    street VARCHAR(200) NOT NULL,
    city VARCHAR(120) NOT NULL
);

CREATE INDEX idx_address_city ON address(city);

COMMENT ON TABLE person IS 'Person master table';
COMMENT ON COLUMN address.city IS 'City name lookup';
